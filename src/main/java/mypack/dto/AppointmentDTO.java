package mypack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
	private Long id;

	private UserDTO employer;

	private UserDTO user;

	private Date startTime;

	private String note;

	private Boolean deny;
	
	private Boolean complete;

}
