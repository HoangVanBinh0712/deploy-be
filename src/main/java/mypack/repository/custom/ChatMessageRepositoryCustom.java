package mypack.repository.custom;

import java.util.List;

import mypack.model.ChatMessage;
import mypack.model.ChatRoom;
import mypack.utility.Page;

public interface ChatMessageRepositoryCustom {

	List<ChatMessage> get(Long user1Id, Long user2Id, Long chatRoomId, Page page, Long messageId);

	Long count(Long user1Id, Long user2Id, Long chatRoomId);

	List<ChatRoom> getChatRoom(Long userId, Page page);

	Long countChatRoom(Long userId);
}