package mypack.dto;

import java.util.Date;

import lombok.Data;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;

@Data
public class ProfileDTO {

	private Long mediaId;

	private String url;

	private Boolean isPublic;

	private String name;

	private String workExperiences;

	private String skillsAndKnowledges;

	private EExperience experience;

	private EPosition position;

	private EMethod method;

	private Date lastModified;

	private UserDTO user;
}
