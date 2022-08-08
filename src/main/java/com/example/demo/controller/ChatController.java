package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.AuthenticationException;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatMessageService chatMessageService;
    @GetMapping("/messages/{username}")
    public String getChat(Model model, @PathVariable String username, @AuthenticationPrincipal User user){
        User friend = userRepository.findByUsername(username);
        model.addAttribute("userFrom", user);
        model.addAttribute("userTo", friend);
        model.addAttribute("allMessages", chatMessageService.showAllMessages(user, friend));
        return "index";
    }


    @MessageMapping("/chat.send/{id1}/{id2}")
    @SendTo("/topic/{id1}/{id2}")
    public ChatMessage sendMessage(@Payload final ChatMessage chatMessage){
        messageRepository.save(chatMessage);
        return chatMessage;
    }

    //@MessageMapping("/chat.newUser")
    //@SendTo("/topic/1")
    //public ChatMessage newUser(@Payload final ChatMessage chatMessage,
    //                           SimpMessageHeaderAccessor headerAccessor){
    //
    //    headerAccessor.getSessionAttributes().put("username", chatMessage.getUserFrom().getUsername());
    //    return chatMessage;
    //}
}
