package mypack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// for emp -> user
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowerUserDTO {
    private UserDTO follower;
    private Date date;
}