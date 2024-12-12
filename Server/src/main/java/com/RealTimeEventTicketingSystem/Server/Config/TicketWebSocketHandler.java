package com.RealTimeEventTicketingSystem.Server.Config;

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
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Component
public class TicketWebSocketHandler implements org.springframework.web.socket.WebSocketHandler {

    @Autowired
    @Lazy //lazy annotation is used to  only inject the dependency when the dependency accessed
    private TicketService ticketService;

    // List to store active WebSocket sessions
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final Logger logger = Logger.getLogger(TicketWebSocketHandler.class.getName());

    /**
     * This method is called when a WebSocket connection is established.
     * Adds the new session to the session list and sends the current ticket count.
     *
     * @param session The WebSocket session that was established.
     * @throws Exception If an error occurs while handling the connection.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // Add the session to the list of active sessions
        logger.info("WebSocket connection established with sessionId " + session.getId());
        sendTicketCountToSession(session);  // Send the current ticket count to the new session
    }

    /**
     * This method is called when a message is received from a WebSocket session.
     * Logs the received message.
     *
     * @param session The WebSocket session that sent the message.
     * @param message The WebSocket message received.
     * @throws Exception If an error occurs while handling the message.
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("WebSocket message received: " + message.getPayload());
    }

    /**
     * This method handles errors during the WebSocket transport.
     * Logs the error message.
     *
     * @param session   The WebSocket session where the error occurred.
     * @param exception The exception that occurred during the transport.
     * @throws Exception If an error occurs while handling the transport error.
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.warning("WebSocket transport error: " + exception.getMessage());
    }

    /**
     * This method is called when a WebSocket connection is closed.
     * Removes the closed session from the session list.
     *
     * @param session The WebSocket session that was closed.
     * @param status  The status of the connection closure.
     * @throws Exception If an error occurs while handling the connection closure.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // Remove the closed session from the list
        logger.info("WebSocket connection closed with status: " + status.getCode());
    }

    /**
     * This method checks if partial WebSocket messages are supported.
     *
     * @return false since partial messages are not supported in this case.
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * Sends the current ticket count to a specific session.
     *
     * @param session The WebSocket session to which the ticket count should be sent.
     * @throws IOException If an error occurs while sending the message.
     */
    private void sendTicketCountToSession(WebSocketSession session) throws IOException {

        try {
            int ticketCount = this.ticketService.getAvailableTickets();  // Retrieve the available ticket count
            broadcastTicketCount(ticketCount);  // Broadcast the ticket count to all connected sessions
        } catch (Exception e) {
            logger.severe("Error sending ticket count to session " + session.getId() + ": " + e.getMessage());
            throw new IOException("Error retrieving or sending ticket count", e);  // Rethrow as IOException
        }
    }

    /**
     * Broadcasts the current ticket count to all connected WebSocket sessions.
     *
     * @param ticketCount The current available ticket count to send.
     */
    public void broadcastTicketCount(int ticketCount) {

        TextMessage textMessage = new TextMessage(String.valueOf(ticketCount)); // Creating the message with  ticketCount
        for (WebSocketSession session : sessions ) {
            try {
                session.sendMessage(textMessage);// Sending the message to each active session

            } catch (IOException e) {
                logger.warning("Error sending ticket count to session " + session.getId() + ": " + e.getMessage());
            }
        }
    }


}
