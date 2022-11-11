package kz.suterminal.manager.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.suterminal.entity.Device;
import kz.suterminal.manager.DeviceMessageManager;
import kz.suterminal.model.requests.DeviceMessage;
import kz.suterminal.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultDeviceMessageManager implements DeviceMessageManager {

    private DeviceRepository deviceRepository;
    private ObjectMapper mapper;

    @Override
    @SneakyThrows
    public void handleMessage(Message<?> message) {
        Object payload = message.getPayload();
        DeviceMessage deviceMessage = mapper.readValue((String) payload, DeviceMessage.class);
        Device device = deviceRepository.findDeviceByImei(deviceMessage.getImei());
        if (device != null) {
            device.setStatus(deviceMessage.getData());
        }
    }
}
