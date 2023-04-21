package mypack.service.impl.websocket;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mypack.model.User;
import mypack.repository.UserRepository;
import mypack.service.UserDetailsCustom;

public class WebSocketUserDetailsServiceImpl implements UserDetailsService {
	UserRepository buyerRepository;

	public WebSocketUserDetailsServiceImpl(UserRepository buyerRepository) {
		super();
		this.buyerRepository = buyerRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User buyer = buyerRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(String.format("%s user not found with username: %s", email)));
		return UserDetailsCustom.build(buyer);

	}
}
