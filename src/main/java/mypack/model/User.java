package mypack.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mypack.utility.datatype.ERole;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Require
	@Column(unique = true)
	@Email
	@NotBlank
	@Size(min = 7, max = 50, message = "Email must have between 7 and 50 chars.")
	private String email;

	// Require
	@Column
	@Length(max = 120)
	@NotBlank
	private String password;

	// Require
	@Column
	@Length(min = 5, max = 120, message = "Name must have between 5 and 120 chars.")
	@NotBlank
	private String name;

	@Column
	@Pattern(regexp = "0[0-9]+", message = "Phone number must contain only number character and begin with 0")
	@Size(min = 10, max = 10, message = "Phone number must have 10 number character")
	private String phone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "industry_id")
	private Industry industry;

	@OneToOne
	@JoinColumn(name = "avatar_id")
	private MediaResource avatar;
	
	@OneToOne
	@JoinColumn(name = "cover_id")
	private MediaResource cover;
	
	@Column
	private String address;

	@Column(name = "description", columnDefinition = "varchar(2000)")
	private String description;

	@Column(columnDefinition = "varchar(20)")
	private String code;

	@Column(name = "email_confirm")
	private Boolean emailConfirm;

	@Column
	private Boolean active;

	@Column
	@Enumerated(EnumType.STRING)
	private ERole role;

	@Column
	private Date createDate;

	
	@Column(name = "wrong_password_count")
	private Long wrongPasswordcount;
	
	// Package
	@ManyToOne
	@JoinColumn(name = "current_service")
	private Service service;

	@Column(name = "service_expiration_date")
	private Date serviceExpirationDate;

	@OneToMany(mappedBy = "user")
	private List<ListImages> listImages;

}
