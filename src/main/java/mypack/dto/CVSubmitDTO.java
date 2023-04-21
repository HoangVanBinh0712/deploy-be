package mypack.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CVSubmitDTO {

	// Url for the CV
	private String url;

	private Long matchPercent;

	private Date date;

	@NotBlank
	@Length(min = 5)
	private String coverLetter;

	//User
	private ProfileDTO profile;
}
