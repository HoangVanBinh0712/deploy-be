package mypack.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationDTO {

	private Long id;

	private String title;

	private PostDTO post;
	
	private Date date;
}
