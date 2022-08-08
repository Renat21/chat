//package com.example.demo.controller;
//
//import com.example.demo.model.ChatMessage;
//import com.example.demo.model.MessageType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionConnectedEvent;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//
//@Component
//public class WebSocketEventListener {
//
//
//    @Autowired
//    private SimpMessageSendingOperations sendingOperations;
//
//    @EventListener
//    public void handleWebSocketEventListener(final SessionConnectedEvent event){
//        System.out.println("User connected");
//    }
//
//    @EventListener
//    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event){
//        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        final String username = (String) headerAccessor.getSessionAttributes().get("username");
//
//        final ChatMessage chatMessage = ChatMessage.builder()
//                .type(MessageType.DISCONNECT)
//                .sender(username).build();
//
//        sendingOperations.convertAndSend("/topic/1", chatMessage);
//    }
//}
