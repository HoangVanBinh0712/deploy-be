package mypack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//for user -> Emp
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowedUserDTO {
    private UserDTO user;
    private Date date;
}