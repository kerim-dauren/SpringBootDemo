package kz.suterminal.domain.managers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.suterminal.domain.interfaces.DevicePayManager;
import kz.suterminal.models.DevicePayRequest;
import kz.suterminal.models.DevicePayRequestDto;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultDevicePayManager implements DevicePayManager {
    private static final Logger logger = LoggerFactory.getLogger(DefaultDevicePayManager.class);
    private IMqttAsyncClient mqttClient;
    private ObjectMapper mapper;

    @Autowired
    public DefaultDevicePayManager(IMqttAsyncClient mqttClient, ObjectMapper mapper) {
        this.mqttClient = mqttClient;
        this.mapper = mapper;
    }


    @SneakyThrows
    @Override
    public void pay(DevicePayRequestDto request) {
        if (!mqttClient.isConnected()) return;
        var terminalPayRequest = new DevicePayRequest();
        terminalPayRequest.setPayId(1);
        terminalPayRequest.setAction("pay");
        terminalPayRequest.setPulseCount(request.quantity.intValue());
        byte[] bytes = mapper.writeValueAsBytes(terminalPayRequest);
        var message = new MqttMessage(bytes);
        message.setQos(2);
        message.setRetained(true);
        mqttClient.publish("device/" + request.deviceId, message);
    }
}
