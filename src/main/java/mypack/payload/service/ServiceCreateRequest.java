package mypack.payload.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCreateRequest {
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;

	@NotNull
	private EServiceType type;

	@NotNull
	@Min(0)
	private Double price;

	@NotNull
	private ECurrency currency;

	@NotNull
	@Min(1)
	private Long postDuration;

	@NotNull
	private Boolean active;
	
	@NotNull
	private Boolean canSearchCV;
	
	@NotNull
	private Boolean canFilterCVSubmit;

}
