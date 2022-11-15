package kz.suterminal.services;

import org.springframework.messaging.Message;

public interface MqttMessageHandler {
    void handleMessage(Message<?> message);
}
