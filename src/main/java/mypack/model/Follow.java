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
import mypack.model.pk.FollowPK;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
	
	@EmbeddedId
	private FollowPK id;
	
	
	@MapsId("postId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", insertable = false, updatable = false)
	private Post post;
	
	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
	
	@Column
	private Date date;
}
