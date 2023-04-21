package mypack.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import mypack.controller.exception.CommonRuntimeException;
import mypack.service.UserDetailsCustom;
import mypack.service.UserDetailsServiceImpl;
import mypack.utility.jwt.JwtUtils;

public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private CustomAuthenticationEntryPoint authenticationExceptionHandling;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = jwtUtils.parseJwt(request);
			if (token != null && !jwtUtils.isTokenExpired(token)) {
				String email = jwtUtils.getUsernameFromJwtToken(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				UserDetailsCustom us = (UserDetailsCustom) authentication.getPrincipal();
				if (!us.isAccountNonLocked())
					throw new CommonRuntimeException(
							"Account is already blocked ! Contact admin to futher information .");
			}
		} catch (Exception e) {
			authenticationExceptionHandling.commence(request, response, new AuthenticationException(e.getMessage(), e) {

				private static final long serialVersionUID = -3315234540923774344L;
			});
			return;
		}

		filterChain.doFilter(request, response);
	}
}