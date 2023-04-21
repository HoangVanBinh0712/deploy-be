package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report")
public class Report {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank
	private String name;

	@Column
	@NotBlank
	@Pattern(regexp = "0[0-9]+", message = "Phone number must contain only number character and begin with 0")
	@Size(min = 10, max = 10, message = "Phone number must have 10 number character")
	private String phone;

	@Column
	@NotBlank
	@Email
	@Size(min = 7, max = 50, message = "Email must have between 7 and 50 chars.")
	private String email;

	@Column
	@NotBlank
	private String reportContent;

	@Column
	private Date date;

	@Column(columnDefinition = "boolean default false")
	private Boolean handle;

	@Column(name = "handle_date")
	private Date handleDate;

	// Id of post report
	@ManyToOne
	@JoinColumn(name = "post_id")
	@NotNull
	private Post post;
}
