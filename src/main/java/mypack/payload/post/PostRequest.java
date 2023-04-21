package mypack.payload.post;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotNull
	private EMethod method;

	@NotNull
	private EPosition position;

	@NotNull
	private EExperience experience;

	@NotNull
	private EGender gender;

	@NotBlank
	private String requirement;

	@NotBlank
	private String benifit;

	@NotBlank
	private String contact;

	private Long salary;

	@NotNull
	private ECurrency currency;

	@NotBlank
	private String location;

	@NotNull
	@Min(1)
	private Long recruit;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	@NotNull
	private Long industry;
	@NotNull
	private Long city;

}
