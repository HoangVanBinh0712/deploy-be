package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import mypack.model.pk.CVSubmitPK;

@Entity
@Data
public class CVSubmit {

	@EmbeddedId
	CVSubmitPK id;

	@MapsId("postId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", insertable = false, updatable = false)
	private Post post;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	@JoinColumn(name = "media_id", referencedColumnName = "media_id", insertable = false, updatable = false)
	private Profile profile;

	@Column
	private Date date;

	@Column(name = "match_percent")
	private Long matchPercent;

	@Column(name = "cover_letter", length = 60000, columnDefinition = "MEDIUMTEXT CHARACTER SET UTF8MB4")
	@NotBlank
	@Length(min = 30)
	private String coverLetter;

	@Column
	private String personality;
}
