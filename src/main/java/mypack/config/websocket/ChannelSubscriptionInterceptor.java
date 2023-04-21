package mypack.config.websocket;

import java.util.List;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import mypack.service.impl.websocket.WebSocketUserDetailsServiceImpl;
import mypack.utility.jwt.JwtUtils;

public class ChannelSubscriptionInterceptor implements ChannelInterceptor {
	private WebSocketUserDetailsServiceImpl webSocketUserDetailsServiceImpl;
	private JwtUtils jwtUtils;

	public ChannelSubscriptionInterceptor(WebSocketUserDetailsServiceImpl webSocketUserDetailsServiceImpl,
			JwtUtils jwtUtils) {
		this.webSocketUserDetailsServiceImpl = webSocketUserDetailsServiceImpl;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		List<String> token = accessor.getNativeHeader("Authorization");

		if (StompCommand.CONNECT.equals(accessor.getCommand())) {
			String jwt = jwtUtils.parseJwt(token.get(0));
			String email = jwtUtils.getUsernameFromJwtToken(jwt);

			UserDetails userDetails = webSocketUserDetailsServiceImpl.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());

			accessor.setUser(authentication);
		}

		return ChannelInterceptor.super.preSend(message, channel);
	}
}
