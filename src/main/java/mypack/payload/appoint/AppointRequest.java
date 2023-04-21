package mypack.payload.appoint;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointRequest {
	@NotNull
	private Long empId;
	@NotNull
	private Long userId;
	private String note;
	@NotNull
	private Date startTime;
}
