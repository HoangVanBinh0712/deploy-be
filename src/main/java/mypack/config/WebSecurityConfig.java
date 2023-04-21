package mypack.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import mypack.config.oauth2.OAuth2LoginSuccessHandler;
import mypack.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;
	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	protected AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http, CustomAccessDeniedHandler customAccessDeniedHandler,
			CustomAuthenticationEntryPoint authenticationExceptionHandling) throws Exception {
		http.cors().and().csrf().disable().httpBasic().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(authenticationExceptionHandling).and().exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler);

		http.authorizeRequests()
				.antMatchers("/api/comment", "/api/employer/information/**", "/api/user/list-company",
						"/api/user/highlight-company")
				.permitAll().and().authorizeRequests().antMatchers("/api/admin/**").hasRole("ADMIN").and()
				.authorizeRequests().antMatchers("/api/user/**").hasRole("USER").and().authorizeRequests()
				.antMatchers("/api/employer/**", "/api/pay").hasRole("EMPLOYER").and().authorizeRequests()
				.antMatchers("/api/chat/**").hasAnyRole("USER", "EMPLOYER").and().authorizeRequests().antMatchers("/**")
				.permitAll().and().oauth2Login().userInfoEndpoint().userService(oAuth2UserService).and()
				.successHandler(oAuth2LoginSuccessHandler);
		;

		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	protected CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("/*"));
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
