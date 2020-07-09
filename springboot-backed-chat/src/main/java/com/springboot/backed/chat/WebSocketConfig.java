package com.springboot.backed.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	private static final String URL_CHAT_ENDPOINT = "/chat-websocket";
	private static final String URL_ORIGIN = "http://localhost:4200";
	private static final String URL_BROKER = "/chat/";
	private static final String PREFIX_DESTINATON = "/app";
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(URL_CHAT_ENDPOINT)
				.setAllowedOrigins(URL_ORIGIN)
				.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(URL_BROKER);
		registry.setApplicationDestinationPrefixes(PREFIX_DESTINATON);
	}
}