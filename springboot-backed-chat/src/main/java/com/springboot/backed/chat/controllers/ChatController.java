package com.springboot.backed.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.springboot.backed.chat.models.documents.Message;
import com.springboot.backed.chat.models.service.ChatService;

@Controller
public class ChatController {

	private static final String PREFIX_MESSAGE = "/message";
	private static final String PREFIX_CHAT = "/chat/message";
	private static final String PREFIX_TYPING = "/typing";
	private static final String PREFIX_CHAT_TYPING = "/chat/typing";
	private static final String PREFIX_HISTORY = "/history";
	private static final String PREFIX_CHAT_HISTORY = "/chat/history/";
	private static final String NEW_USER = "NEW_USER";
	private static final String PREFIX_NEW_USER = "new user :";
	private static final String PREFIX_USER_IS_TYPING = " is typing ...";
	
	private String[] colors = {"red", "green", "blue", "magenta", "purple", "orange", "yellow", "pink"};
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@MessageMapping(PREFIX_MESSAGE)
	@SendTo(PREFIX_CHAT)
	public Message receiveMessage(Message message) {
		message.setDate(new Date().getTime());
		
		if(message.getType().equals(NEW_USER)) {
			message.setColor(colors[new Random().nextInt(colors.length)]);
			message.setText(PREFIX_NEW_USER);
		}else {
			chatService.save(message);
		}
		
		return message;
	}
	
	@MessageMapping(PREFIX_TYPING)
	@SendTo(PREFIX_CHAT_TYPING)
	public String someoneIsTyping(String username) {
		return username.concat(PREFIX_USER_IS_TYPING);
	}
	
	@MessageMapping(PREFIX_HISTORY)
	public void history(String clientId){
		webSocket.convertAndSend(PREFIX_CHAT_HISTORY.concat(clientId), chatService.getLast10Messages());
	}
}
