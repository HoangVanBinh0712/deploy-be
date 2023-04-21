package mypack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.BaseResponse;
import mypack.service.ChatService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("/api")
public class UserChatController {
	@Autowired
	ChatService chatService;

	@GetMapping("chat/message/{roomId}/{userId}")
	public ResponseEntity<?> userFetchMessages(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("userId") Long userId, @PathVariable("roomId") Long roomId,
			@RequestParam(name = "messageId", required = false) Long messageId,
			@RequestParam(name = "page", required = true) Integer page,
			@RequestParam(name = "limit", required = true) Integer limit) {
		if (user != null)
			return ResponseEntity.ok(chatService.fetchMessage(userId, user.getId(), roomId, messageId, page, limit));
		return ResponseEntity.ok(new BaseResponse(false, "Must login first !"));
	}

	@GetMapping("chat/chat-room")
	public ResponseEntity<?> userFetchRoomChat(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestParam(name = "page", required = true) Integer page,
			@RequestParam(name = "limit", required = true) Integer limit) {
		if (user != null)
			return ResponseEntity.ok(chatService.fetchChatRoom(user.getId(), page, limit));
		return ResponseEntity.ok(new BaseResponse(false, "Must login first !"));
	}

	@GetMapping("chat/single-chat-room/{userId}")
	public ResponseEntity<?> userFetchSingleRoomChat(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("userId") Long userId) {
		if (user != null) {
			if (user.getId() == userId)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(false, "Same Id !"));
			else {
				return ResponseEntity.ok(chatService.fetchSingleChatRoom(user.getId(), userId));
			}
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse(false, "Must login first !"));
	}

	@GetMapping("chat/unread")
	public ResponseEntity<?> getUnReadMessageWithRoom(@AuthenticationPrincipal UserDetailsCustom user,
			@RequestParam(value = "roomIds", required = true) List<Long> roomIds) {
		if (user == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse(false, "Must login first !"));

		return ResponseEntity.ok(chatService.getNewestMessageByRoom(roomIds, user.getId()));
	}

	@PutMapping("chat/message/{messageId}")
	public ResponseEntity<?> updateSeenMessage(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable(value = "messageId", required = true) Long messageId) {
		if (user == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse(false, "Must login first !"));

		return ResponseEntity.ok(chatService.updateSeenMessage(messageId));
	}

}
