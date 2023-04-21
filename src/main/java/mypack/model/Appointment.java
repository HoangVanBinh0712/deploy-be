package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "employer")
	private User employer;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Column(name = "start_time")
	@NotNull
	private Date startTime;

	@Column(name = "note")
	private String note;

	@Column(columnDefinition = "boolean default false")
	private Boolean deny;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean complete;

}
