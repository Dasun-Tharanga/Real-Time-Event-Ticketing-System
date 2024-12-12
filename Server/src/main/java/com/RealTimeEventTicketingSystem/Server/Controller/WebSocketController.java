package com.RealTimeEventTicketingSystem.Server.Controller;

import com.RealTimeEventTicketingSystem.Server.Config.TicketWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketController implements WebSocketConfigurer {

    private TicketWebSocketHandler ticketWebSocketHandler;

    @Autowired
    public WebSocketController(TicketWebSocketHandler ticketWebSocketHandler) {
        this.ticketWebSocketHandler = ticketWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(ticketWebSocketHandler, "/ws/tickets")
                .setAllowedOrigins("*");

    }


}
