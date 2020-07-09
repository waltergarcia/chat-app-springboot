package com.springboot.backed.chat.models.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.backed.chat.models.documents.Message;

public interface ChatRepository extends MongoRepository<Message, String>{

	List<Message> findFirst10ByOrderByDateDesc();
}
