package kz.suterminal.manager.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.suterminal.manager.ExtDeviceManager;
import kz.suterminal.model.requests.DeviceCheckRequest;
import kz.suterminal.model.requests.DevicePayRequest;
import kz.suterminal.model.response.DeviceCheckResponse;
import kz.suterminal.model.response.DevicePayResponse;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultExtDevicePayManager implements ExtDeviceManager {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExtDevicePayManager.class);
    private IMqttAsyncClient mqttClient;
    private ObjectMapper mapper;

    @Autowired
    public DefaultExtDevicePayManager(IMqttAsyncClient mqttClient, ObjectMapper mapper) {
        this.mqttClient = mqttClient;
        this.mapper = mapper;
    }


//    @SneakyThrows
//    public void notify(DeviceNotifyRequest request) {
//        if (request.getCommand() == DeviceNotifyCommand.CHECK) {
//
//        } else if (request.getCommand() == DeviceNotifyCommand.PAY) {
//
//        }
//        if (!mqttClient.isConnected()) return;
//        var terminalPayRequest = new DeviceNotifyRequest();
//        terminalPayRequest.setPayId(1);
//        terminalPayRequest.setAction("pay");
//        terminalPayRequest.setPulseCount(request.quantity.intValue());
//        byte[] bytes = mapper.writeValueAsBytes(terminalPayRequest);
//        var message = new MqttMessage(bytes);
//        message.setQos(2);
//        message.setRetained(true);
//        mqttClient.publish("device/" + request.deviceId, message);
//    }

    @Override
    public DeviceCheckResponse check(DeviceCheckRequest request) {

        return null;
    }

    @Override
    public DevicePayResponse pay(DevicePayRequest request) {
        return null;
    }
}
