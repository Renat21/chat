package com.example.demo.service;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.User;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ChatMessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<ChatMessage> showAllMessages(User userFrom, User userTo){
        return Stream.concat(messageRepository.findChatMessagesByUserFromAndUserTo(userFrom, userTo).stream(),
        messageRepository.findChatMessagesByUserFromAndUserTo(userTo, userFrom).stream()).
                sorted(Comparator.comparing(ChatMessage::getTime)).toList();
    }
}
