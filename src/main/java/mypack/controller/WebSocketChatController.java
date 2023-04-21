package mypack.controller;

import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import mypack.config.websocket.InboundChatMessage;
import mypack.config.websocket.OutboundChatMessage;
import mypack.service.ChatService;
import mypack.service.UserDetailsCustom;

@Controller
public class WebSocketChatController {
	// Autowire SimpMessagingTemplate and SimpUserRegistry

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	ChatService chatService;

	@Autowired
	private SimpUserRegistry simpUserRegistry;

	@MessageMapping("/chat")
	public void send(SimpMessageHeaderAccessor sha, @Payload InboundChatMessage inboundMessage) {
//		 Get a list of all connected users
		Iterable<SimpUser> users = simpUserRegistry.getUsers();
		for (SimpUser user : users) {
			System.out.println("User: " + user.getName() + " Session ID: " + user.getSessions().toString());
		}
		Object ouser = ((UsernamePasswordAuthenticationToken) sha.getUser()).getPrincipal();

		UserDetailsCustom user = (UserDetailsCustom) ouser;
		Long id = chatService.saveMessage(inboundMessage, user.getId());
		String recieverEmail = chatService.getReceiverUserEmail(inboundMessage.getIdReceiver());
		String url = "/queue"; // "/queue/" + inboundMessage.getIdReceiver() + "/messages/" +
								// user.getId().toString();
		simpMessagingTemplate.convertAndSendToUser(recieverEmail, url,
				new OutboundChatMessage(id, inboundMessage.getContentType(), inboundMessage.getData(),
						inboundMessage.getChatRoomId(), inboundMessage.getTime()));

	}
}
