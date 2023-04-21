package mypack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.utility.datatype.EAchievementType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementDTO {
    private Long id;

    private String name;

    private UserDTO user;

    private String imageUrl;

    private EAchievementType type;

    private String url;
    
    private Date createDate;
}
