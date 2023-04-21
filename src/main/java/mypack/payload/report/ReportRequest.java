package mypack.payload.report;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "0[0-9]+", message = "Phone number must contain only number character and begin with 0")
    @Size(min = 10, max = 10, message = "Phone number must have 10 number character")
    private String phone;

    @NotBlank
    @Email
    @Size(min = 7, max = 50, message = "Email must have between 7 and 50 chars.")
    private String email;

    @NotBlank
    @Length(min = 10)
    private String reportContet;

    // Id of post report
    @NotNull
    private Long postId;
}
