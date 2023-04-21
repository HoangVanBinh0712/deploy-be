package mypack.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CityDTO {
	private Long id;

	@NotBlank
	private String name;
}
