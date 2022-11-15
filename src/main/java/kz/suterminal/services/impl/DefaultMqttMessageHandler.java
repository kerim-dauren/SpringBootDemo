package kz.suterminal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.suterminal.entity.Device;
import kz.suterminal.model.requests.DeviceMessage;
import kz.suterminal.repository.DeviceRepository;
import kz.suterminal.services.MqttMessageHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultMqttMessageHandler implements MqttMessageHandler {

    private DeviceRepository deviceRepository;
    private ObjectMapper mapper;

    @SneakyThrows
    @Override
    public void handleMessage(Message<?> message) {
        var payload = message.getPayload();
        var deviceMessage = mapper.readValue((String) payload, DeviceMessage.class);
        var device = deviceRepository.findDeviceByImei(deviceMessage.getImei());
        if (device == null) {
            log.warn("device not found: {}", payload);
            return;
        }
        device.setStatus(deviceMessage.getData());
        deviceRepository.save(device);
    }
}
