package mypack.service;

import static mypack.utility.ResponseMapper.listWithPagingResponseMakeup;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.config.websocket.InboundChatMessage;
import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.ChatMessageDTO;
import mypack.dto.ChatRoomDTO;
import mypack.model.ChatMessage;
import mypack.model.ChatRoom;
import mypack.model.MediaResource;
import mypack.model.User;
import mypack.payload.ListWithPagingResponse;
import mypack.repository.ChatMessageRepository;
import mypack.repository.ChatRoomRepository;
import mypack.repository.UserRepository;
import mypack.utility.Page;
import mypack.utility.datatype.EMessageContentType;

@Service
public class ChatService {
	@Autowired
	ChatMessageRepository chatMessageRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MediaResourceService mediaResourceService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ChatRoomRepository chatRoomRepo;

	public ListWithPagingResponse<ChatMessageDTO> fetchMessage(Long userSend, Long userRecieve, Long roomId,
			Long messageId, Integer ipage, Integer limit) {

		Page page = new Page(ipage, limit, chatMessageRepository.count(userSend, userRecieve, roomId).intValue());

		ChatRoom room = chatRoomRepo.findById(roomId).orElseThrow(() -> new CommonRuntimeException("Room not found !"));
		// Update message to current user to seen
		// User Recieve is current user
		chatMessageRepository.updateSeenForMessages(roomId, userRecieve);
		List<ChatMessage> lst = chatMessageRepository.get(userSend, userRecieve, roomId, page, messageId);
		Collections.reverse(lst);
		return listWithPagingResponseMakeup(lst, page, ChatMessageDTO.class, modelMapper);
	}

	public boolean updateSeenMessage(Long messageId) {
		chatMessageRepository.updateSeenForMessage(messageId);
		return true;
	}

	public List<ChatMessageDTO> getNewestMessageByRoom(List<Long> roomIds, Long userId) {

		return chatMessageRepository.getNewestMessageByRoom(roomIds, userId).stream()
				.map(x -> modelMapper.map(x, ChatMessageDTO.class)).toList();
	}

	public ListWithPagingResponse<ChatRoomDTO> fetchChatRoom(Long userId, Integer ipage, Integer limit) {

		Page page = new Page(ipage, limit, chatMessageRepository.countChatRoom(userId).intValue());

		return listWithPagingResponseMakeup(chatMessageRepository.getChatRoom(userId, page), page, ChatRoomDTO.class,
				modelMapper);
	}

	public ChatRoomDTO fetchSingleChatRoom(Long user1Id, Long user2Id) {
		ChatRoom chatRoom = chatRoomRepo.getSingeChatRoom(user1Id, user2Id);
		if (chatRoom == null) {
			// Create a new Chat room
			User user1 = userRepository.getReferenceById(user1Id);
			User user2 = userRepository.getReferenceById(user2Id);
			ChatRoom cr = new ChatRoom();
			cr.setUser1(user1);
			cr.setUser2(user2);
			chatRoom = chatRoomRepo.save(cr);
		}
		return modelMapper.map(chatRoom, ChatRoomDTO.class);
	}

	public Long saveMessage(InboundChatMessage chatMessage, Long idUserSent) {
		MediaResource img = null;
		String messageContent = null;
		if (chatMessage.getContentType() == EMessageContentType.IMAGE) {
			byte[] data = Base64.getDecoder().decode(chatMessage.getData());
			img = mediaResourceService.save(data);
		} else {
			messageContent = chatMessage.getData();
		}
		// Check if chat room exist
		ChatRoom chatRoom = chatRoomRepo.findById(chatMessage.getChatRoomId()).get();

		ChatMessage cm = new ChatMessage();
		cm.setChatRoom(chatRoom);
		cm.setImage(null);
		cm.setTime(chatMessage.getTime());
		cm.setContent(messageContent);
		cm.setSenderId(idUserSent);
		chatRoom.setLastChat(cm.getTime());
		if (chatRoom.getUser1().getId() == idUserSent) {
			cm.setUser1Seen(true);
			cm.setUser2Seen(false);

		} else {
			cm.setUser2Seen(true);
			cm.setUser1Seen(false);

		}
		cm = chatMessageRepository.save(cm);
		chatRoomRepo.save(chatRoom);
		return cm.getId();
	}

	public String getReceiverUserEmail(Long idUser) {
		User user = userRepository.findById(idUser).get();
		return user.getEmail();
	}
}
