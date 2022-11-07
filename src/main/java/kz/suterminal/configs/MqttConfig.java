package kz.suterminal.configs;

import lombok.Data;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {

    private static final Logger logger = LoggerFactory.getLogger(MqttConfig.class);
    private String url;
    private String username;
    private String password;
    private int connectionTimeoutInSeconds;


    @SneakyThrows
    @Bean
    public IMqttAsyncClient createClient() {
        var options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(connectionTimeoutInSeconds);
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        var client = new MqttAsyncClient(url, MqttAsyncClient.generateClientId());
        if (!client.isConnected()) {
            client.connect(options);
        }
        return client;
    }

}
