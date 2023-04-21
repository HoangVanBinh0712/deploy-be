package mypack.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CVViewedPK implements Serializable {
	private static final long serialVersionUID = -478169959508107689L;

	@Column(name = "viewer_id")
	private Long viewerId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "media_id")
	private Long mediaId;


}
