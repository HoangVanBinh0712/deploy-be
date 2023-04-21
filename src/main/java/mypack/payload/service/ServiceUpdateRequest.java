package mypack.payload.service;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EServiceType;

@Getter
@Setter
public class ServiceUpdateRequest {

	@NotNull
	private Long id;

	private String name;

	private String description;

	private EServiceType type;

	private Double price;

	private ECurrency currency;

	private Long postDuration;

	private Boolean active;

	private Boolean canSearchCV;

	private Boolean canFilterCVSubmit;

}
