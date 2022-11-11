package kz.suterminal.config;

import kz.suterminal.manager.DeviceMessageManager;
import lombok.Data;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    private String url;
    private String username;
    private String password;
    private boolean automaticReconnect;
    private boolean cleanSession;
    private int connectionTimeout;

    @Autowired
    private DeviceMessageManager deviceMessageManager;


    public MqttConnectOptions mqttConnectOptions() {
        var options = new MqttConnectOptions();
        options.setAutomaticReconnect(automaticReconnect);
        options.setCleanSession(cleanSession);
        options.setConnectionTimeout(connectionTimeout);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        return options;
    }

    @Bean
    public DefaultMqttPahoClientFactory pahoClientFactory() {
        DefaultMqttPahoClientFactory pahoClientFactory = new DefaultMqttPahoClientFactory();
        pahoClientFactory.setConnectionOptions(mqttConnectOptions());
        return pahoClientFactory;
    }

    @SneakyThrows
    @Bean
    public IMqttAsyncClient createClient() {
        var client = new MqttAsyncClient(url, MqttAsyncClient.generateClientId());
        if (!client.isConnected()) {
            client.connect(mqttConnectOptions());
        }

        return client;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        var adapter = new MqttPahoMessageDrivenChannelAdapter(url, MqttClient.generateClientId(), pahoClientFactory(), "device/#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {

        return new MessageHandler() {

            @SneakyThrows
            @Override
            public void handleMessage(Message<?> message) {
                deviceMessageManager.handleMessage(message);
            }

        };
    }

}
