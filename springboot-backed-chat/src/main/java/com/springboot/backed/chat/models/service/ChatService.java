package com.springboot.backed.chat.models.service;

import java.util.List;

import com.springboot.backed.chat.models.documents.Message;

public interface ChatService {
	List<Message> getLast10Messages();
	Message save(Message message);
}
