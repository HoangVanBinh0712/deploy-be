package mypack.payload.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerProfileUpdateRequest {

	@Schema(type = "string", format = "email")
	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Length(min = 5, max = 120, message = "Name must have between 5 and 120 chars.")
	private String name;

	@NotBlank
	@Pattern(regexp = "0[0-9]+", message = "Phone number must contain only number character and begin with 0")
	@Size(min = 10, max = 10, message = "Phone number must have 10 number character")
	private String phone;

	@NotNull
	private Long industryId;
	@NotNull
	private Long cityId;
	@NotBlank
	private String address;
	@NotBlank
	private String description;

}
