package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.model.pk.CVViewedPK;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CVViewed {

	@EmbeddedId
	CVViewedPK id;

	@MapsId("viewerId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "viewer_id", insertable = false, updatable = false)
	private User viewer;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	@JoinColumn(name = "media_id", referencedColumnName = "media_id", insertable = false, updatable = false)
	private Profile profile;

	@Column
	private Date date;

}
