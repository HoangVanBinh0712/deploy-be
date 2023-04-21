package mypack.payload.predict;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOptionResponse {

	private String code;
	private String name;
	private String percent;
}
