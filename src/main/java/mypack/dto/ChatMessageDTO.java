package mypack.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mypack.model.ChatRoom;
import mypack.model.MediaResource;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDTO {
	private Long id;

//	private ChatRoomDTO chatRoom;

	private Long chatRoomId;
	
	private MediaResource image;

	private Long senderId;

	private String content;

	private Boolean user1Seen;

	private Boolean user2Seen;

	private Date time;
}
