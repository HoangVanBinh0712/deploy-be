package mypack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {
	private Long id;

	private UserDTO user1;

	private UserDTO user2;

	private String chatName;
}
