package mypack.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.dto.CommentDTO;
import mypack.dto.UserDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private UserDTO user;
    private List<CommentDTO> childs;
}
