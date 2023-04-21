package mypack.payload.appoint;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointUpdateRequest {
	private String note;
	
	@NotNull
	private Date startTime;
}
