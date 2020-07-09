package com.springboot.backed.chat.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.backed.chat.models.documents.Message;
import com.springboot.backed.chat.models.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public List<Message> getLast10Messages() {
		return chatRepository.findFirst10ByOrderByDateDesc();
	}

	@Override
	public Message save(Message message) {
		return chatRepository.save(message);
	}

}
