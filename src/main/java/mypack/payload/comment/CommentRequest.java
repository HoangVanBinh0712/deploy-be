package mypack.payload.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentRequest {
    @NotNull
    private Long postId;
    @NotBlank
    private String content;
    @NotNull
    private Long replyId;
}
