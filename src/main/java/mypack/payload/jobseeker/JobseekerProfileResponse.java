package mypack.payload.jobseeker;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.dto.AchievementDTO;
import mypack.dto.UserDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobseekerProfileResponse {
    private UserDTO user;
    private List<AchievementDTO> achievements;
}
