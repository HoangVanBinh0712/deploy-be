package mypack.config.websocket;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class InboundChatMessage {
	@NonNull
	private Long idReceiver;

	@NonNull
	private EMessageContentType contentType;

	/**
	 * Text plain or base64 encoded if type is binary
	 */
	@NotBlank
	private String data;

	@NotNull
	private Long chatRoomId;

	@NotNull
	private Date time;
}
