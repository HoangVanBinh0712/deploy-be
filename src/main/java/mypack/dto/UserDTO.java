package mypack.dto;

import java.util.Date;

import lombok.Data;
import mypack.utility.datatype.ERole;

@Data
public class UserDTO {
	private Long id;

	private String email;
	
	private Boolean emailConfirm;

	private String name;

	private String phone;

	private CityDTO city;

	private IndustryDTO industry;

	private String urlAvatar;
	
	private String urlCover;

	private String address;

	private String description;

	private ERole role;

	private ServiceDTO service;

	private Date serviceExpirationDate;
}
