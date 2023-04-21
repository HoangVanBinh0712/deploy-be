package mypack.config.websocket;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mypack.utility.datatype.EMessageContentType;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class OutboundChatMessage {
	@NonNull
	private Long chatMessageId;

	@NonNull
	private EMessageContentType contentType;

	/**
	 * Text plain or base64 encoded if type is binary
	 */
	@NonNull
	private String data;

	@NonNull
	private Long chatRoomId;

	@NotNull
	private Date time;
}
