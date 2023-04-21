package mypack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse<T> {
	private String token;
	private String refreshToken;
	private T userInfo;

	public JwtResponse(String token, String refreshToken, T userInfo) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.userInfo = userInfo;
	}
}