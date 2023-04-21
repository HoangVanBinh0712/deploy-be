package mypack.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private UserDTO user;
    private Date date;
}
