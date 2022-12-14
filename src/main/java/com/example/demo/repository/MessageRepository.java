package com.example.demo.repository;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findChatMessagesByUserFromAndUserTo(User userFrom, User userTo);
}
