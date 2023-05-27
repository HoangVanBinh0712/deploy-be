package mypack.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.model.pk.CVViewedPK;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CVViewedDTO {

	@EmbeddedId
	CVViewedPK id;

	@MapsId("viewerId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "viewer_id", insertable = false, updatable = false)
	private UserDTO viewer;

	@Column
	private Date date;

}
