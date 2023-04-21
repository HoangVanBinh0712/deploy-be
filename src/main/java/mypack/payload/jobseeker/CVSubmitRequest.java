package mypack.payload.jobseeker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CVSubmitRequest {
	@NotNull
	private Long postId;
	@NotNull

	private Long mediaId;
	
	@NotBlank
	@Length(min = 6)
	private String coverLetter;
}
