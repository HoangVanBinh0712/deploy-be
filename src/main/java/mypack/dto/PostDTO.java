package mypack.dto;

import java.util.Date;


import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;

import mypack.utility.datatype.EStatus;

import lombok.Data;

@Data
public class PostDTO {

	private Long id;

	private String title;

	private String description;

	private EMethod method;

	private EPosition position;

	private EExperience experience;

	private EGender gender;

	private String requirement;

	private String benifit;

	private String contact;

	private Long salary;

	private ECurrency currency;

	private String location;

	private Long recruit;

	private Date createDate;

	private Date expirationDate;

	// private UserDTO acceptedBy;

	// private Date acceptedDate;

	private UserDTO author;

	private IndustryDTO industry;

	private CityDTO city;

	private EStatus status;

	private Integer viewCount;

	private ServiceDTO service;
}
