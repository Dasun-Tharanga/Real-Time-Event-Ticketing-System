package com.RealTimeEventTicketingSystem.Server.Config;

import com.RealTimeEventTicketingSystem.Server.Model.TicketPool;
import com.RealTimeEventTicketingSystem.Server.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

@Component
public class TicketWebSocketHandler implements org.springframework.web.socket.WebSocketHandler {

    @Autowired
    @Lazy //lazy annotation is used to  only inject the dependency when the dependency accessed
    private TicketPool ticketPool;

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        logger.info("WebSocket connection established with sessionId " + session.getId());
        sendTicketCountToSession(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("WebSocket message received: " + message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("WebSocket transport error: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        logger.info("WebSocket connection closed with status: " + status.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void sendTicketCountToSession(WebSocketSession session) throws IOException {

        int ticketCount = this.ticketPool.getAvailableTickets();
        broadcastTicketCount(ticketCount);
    }

    public void broadcastTicketCount(int ticketCount) {
        System.out.println(sessions.size());

        TextMessage textMessage = new TextMessage(String.valueOf(ticketCount));
        for (WebSocketSession session : sessions ) {
            try {
                session.sendMessage(textMessage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
