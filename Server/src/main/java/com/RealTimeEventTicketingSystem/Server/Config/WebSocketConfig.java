package com.RealTimeEventTicketingSystem.Server.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private TicketWebSocketHandler ticketWebSocketHandler;

    @Autowired
    public WebSocketConfig(TicketWebSocketHandler ticketWebSocketHandler) {
        this.ticketWebSocketHandler = ticketWebSocketHandler;
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(ticketWebSocketHandler, "/ws/tickets").setAllowedOrigins("*");
    }


}
