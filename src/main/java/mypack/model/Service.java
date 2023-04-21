package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EServiceType;

@Entity
@Table(name = "service")
@Data
public class Service {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank
	private String name;

	@Column
	@NotBlank
	private String description;

	@Column(name = "type", columnDefinition = "varchar(30)")
	@Enumerated(EnumType.STRING)
	@NotNull
	private EServiceType type;

	@Column
	@NotNull
	@Min(0)
	private Double price;

	@Column(name = "currency", columnDefinition = "varchar(30)")
	@Enumerated(EnumType.STRING)
	@NotNull
	private ECurrency currency;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "post_duration")
	@NotNull
	@Min(1)
	private Long postDuration;

	@Column(name = "active", columnDefinition = "boolean default true")
	private Boolean active;
	
	@Column(name = "can_search_cv")
	private Boolean canSearchCV;
	
	@Column(name = "can_filter_cv_submit")
	private Boolean canFilterCVSubmit;

}
