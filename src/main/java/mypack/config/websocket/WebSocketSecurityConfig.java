package mypack.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	@Override
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		messages.simpSubscribeDestMatchers("/user/queue/messages").permitAll().simpDestMatchers("/websocket/**")
				.hasAnyRole("EMPLOYER", "USER");
		super.configureInbound(messages);
	}

	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}

}
