package kz.suterminal.manager;

import org.springframework.messaging.Message;

public interface DeviceMessageManager {
    void handleMessage(Message<?> message);
}
