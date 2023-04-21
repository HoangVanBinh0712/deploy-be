package mypack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String reportContent;

    private Boolean handle;

    private Date date;
    // Id of post report
    private PostDTO post;
}
