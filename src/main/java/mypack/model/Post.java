package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;
import mypack.utility.datatype.EStatus;

@Entity
@Data
public class Post {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Column(length = 200, columnDefinition = "varchar(200)")
	@NotBlank
	private String title;

	@Lob
	@Column(length = 60000, columnDefinition = "MEDIUMTEXT CHARACTER SET UTF8MB4")
	@NotBlank
	private String description;

	@Column
	@Enumerated(EnumType.STRING)
	@NotNull
	private EMethod method;

	@Column
	@Enumerated(EnumType.STRING)
	@NotNull
	private EPosition position;

	@Column
	@Enumerated(EnumType.STRING)
	@NotNull
	private EExperience experience;

	@Column
	@NotNull
	private EGender gender;

	@Lob
	@Column(length = 60000, columnDefinition = "MEDIUMTEXT CHARACTER SET UTF8MB4")
	@NotBlank
	private String requirement;

	@Lob
	@Column(length = 60000, columnDefinition = "MEDIUMTEXT CHARACTER SET UTF8MB4")
	@NotBlank
	private String benifit;

	@Column
	@NotBlank
	private String contact;

	@Column
	@Min(0)
	private Long salary;

	@Column(name = "currency", columnDefinition = "varchar(30)")
	@Enumerated(EnumType.STRING)
	@NotNull
	private ECurrency currency;

	@Column(length = 200)
	@NotNull
	private String location;

	@Column
	@Min(0)
	private Long recruit;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "expiration_date")
	@NotNull
	private Date expirationDate;

	@OneToOne
	@JoinColumn(name = "accepted_by")
	private User acceptedBy;

	@Column(name = "accepted_date")
	private Date acceptedDate;

	@ManyToOne
	@JoinColumn(name = "author")
	private User author;

	@ManyToOne
	@JoinColumn(name = "industry")
	private Industry industry;

	@ManyToOne
	@JoinColumn(name = "city")
	private City city;

	@Column(name = "status", columnDefinition = "varchar(30)")
	@Enumerated(EnumType.STRING)
	@NotNull
	private EStatus status;

	@Column(name = "view_count")
	private Integer viewCount;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
}
