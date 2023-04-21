package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_message")
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "chat_room_id")
	@NotNull
	private ChatRoom chatRoom;

	@OneToOne
	@JoinColumn(name = "id_image")
	private MediaResource image;
	
	@Column
	@NotNull
	private Long senderId;

	@Lob
	@Column(columnDefinition = "MEDIUMTEXT")
	@NotBlank
	private String content;

	@Column(name = "user1_seen", columnDefinition = "boolean default false")
	private Boolean user1Seen;

	@Column(name = "user2_seen", columnDefinition = "boolean default false")
	private Boolean user2Seen;

	@Column
	private Date time;

}
