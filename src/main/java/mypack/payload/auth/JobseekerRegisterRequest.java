package mypack.payload.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobseekerRegisterRequest {
	@NotEmpty
	@Length(min = 5, max = 120, message = "Name must have between 5 and 120 chars.")
	private String name;

	@NotBlank
	@Schema(type = "string", format = "email")
	@Email
	private String email;

	@NotEmpty
	@Size(min = 6, max = 30, message = "Password should have at least 6 characters")
	private String password;

}
