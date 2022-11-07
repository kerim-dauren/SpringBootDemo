package kz.suterminal.domain.managers;

import kz.suterminal.domain.interfaces.TerminalPayManager;
import kz.suterminal.models.TerminalPayRequest;
import kz.suterminal.models.TerminalPayRequestDto;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

@Component
public class DefaultTerminalPayManager implements TerminalPayManager {
    private static final Logger logger = LoggerFactory.getLogger(DefaultTerminalPayManager.class);
    private IMqttAsyncClient mqttClient;

    @Autowired
    public DefaultTerminalPayManager(IMqttAsyncClient mqttClient) {
        this.mqttClient = mqttClient;
    }


    @SneakyThrows
    @Override
    public void pay(TerminalPayRequestDto request) {
        if (!mqttClient.isConnected()) return;
        var terminalPayRequest = new TerminalPayRequest();
        terminalPayRequest.setPayId(1);
        terminalPayRequest.setAction("pay");
        terminalPayRequest.setPulseCount(request.quantity.intValue());
        var message = new MqttMessage(SerializationUtils.serialize(terminalPayRequest));
        message.setQos(2);
        message.setRetained(true);
        mqttClient.publish(request.terminalId, message, null, new MqttActionListener());
    }

    private static class MqttActionListener implements IMqttActionListener {

        @Override
        public void onSuccess(IMqttToken token) {
            logger.info(String.format("Mqtt message sent: {%s}", token.getMessageId()));
        }

        @Override
        public void onFailure(IMqttToken token, Throwable ex) {
            logger.error(ex.getMessage());
        }
    }
}
