package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import mypack.model.pk.ProfilePK;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;

@Data
@Entity
public class Profile {
	@EmbeddedId
	ProfilePK id;

	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@MapsId("mediaId")
	@ManyToOne
	@JoinColumn(name = "media_id", insertable = false, updatable = false)
	private MediaResource mediaResource;

	@Column
	@NotEmpty
	private String name;

	@Column(name = "work_experiences ",length = 60000, columnDefinition = "MEDIUMTEXT CHARACTER SET UTF8MB4")
	@NotBlank
	@Length(min = 30)
	private String workExperiences;

	@Column(name = "skills_and_knowledges",length = 60000, columnDefinition = "MEDIUMTEXT CHARACTER SET UTF8MB4")
	@NotBlank
	@Length(min = 30)
	private String skillsAndKnowledges;

	@Column(name = "is_public", columnDefinition = "boolean default false")
	@NotNull
	private Boolean isPublic;

	@Column(name = "last_modified")
	@NotNull
	private Date lastModified;

	@Column
	@Enumerated(EnumType.STRING)
	private EPosition position;

	@Column
	@Enumerated(EnumType.STRING)
	private EMethod method;

	@Column
	@Enumerated(EnumType.STRING)
	private EExperience experience;
}
