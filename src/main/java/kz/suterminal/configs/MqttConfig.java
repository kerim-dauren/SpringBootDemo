package kz.suterminal.configs;

import lombok.Data;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    private String serverURI;
    private int connectionTimeoutInSeconds;


    @SneakyThrows
    @Bean
    public IMqttAsyncClient createClient() {
        var options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(connectionTimeoutInSeconds);

        var client = new MqttAsyncClient(serverURI, MqttAsyncClient.generateClientId());
        if (!client.isConnected()) {
            client.connect(options);
        }
        return client;
    }
}
