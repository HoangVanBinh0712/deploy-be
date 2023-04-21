package mypack.payload.jobseeker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.utility.datatype.EAchievementType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementRequest {

	@NotBlank
	private String name;

	@NotNull
	private EAchievementType type;

	private String url;
}
