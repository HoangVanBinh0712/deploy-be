package mypack.config.oauth2;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import mypack.model.User;
import mypack.repository.UserRepository;
import mypack.service.CustomOAuth2User;
import mypack.utility.RandomString;
import mypack.utility.datatype.ERole;
import mypack.utility.jwt.JwtUtils;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	@Value("${fontend.app.googleloginurl}")
	String redirectUrl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// Data google send back
		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
		String email = oAuth2User.getEmail();
		String name = oAuth2User.getFullName();
		String code = "";

		// only allow buyer login with google
		String jwt = "";
		String refresh = "";
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isEmpty()) {

			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setEmailConfirm(true);
			user.setActive(true);
			user.setPhone(null);
			user.setRole(ERole.ROLE_USER);
			user.setCreateDate(new Date());
			user.setPassword((RandomString.get(20)));
			user.setWrongPasswordcount(0L);
			code = RandomString.get(10);
			user.setCode(code);
			userRepository.save(user);
		}
		User us = optionalUser.get();
		us.setWrongPasswordcount(0L);
		userRepository.save(us);
		jwt = jwtUtils.generateJwtToken(email);
		refresh = jwtUtils.generateJwtRefreshToken(email);

		String url = redirectUrl + jwt + "&refresh=" + refresh;

		if (code.length() != 0)
			url += "&code=" + code;
		getRedirectStrategy().sendRedirect(request, response, url);
		// super.onAuthenticationSuccess(request, response, authentication);
	}
}
