package mypack.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import mypack.model.User;

public class UserDetailsCustom extends UserDetailsImpl {

	private static final long serialVersionUID = 5049020068819796467L;

	public UserDetailsCustom() {
		super();
	}

	public UserDetailsCustom(Long id, String email, String name, String password, Boolean active,
			Collection<? extends GrantedAuthority> authorities) {
		super(id, email, password, name, active, authorities);

	}

	public static UserDetailsCustom build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

		return new UserDetailsCustom(user.getId(), user.getEmail(), user.getName(), user.getPassword(),
				user.getActive(), authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
