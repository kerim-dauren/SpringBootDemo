package kz.suterminal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.suterminal.entity.Device;
import kz.suterminal.exceptions.BusinessException;
import kz.suterminal.exceptions.FaultCode;
import kz.suterminal.model.DeviceStatus;
import kz.suterminal.model.requests.DeviceCheckRequest;
import kz.suterminal.model.requests.DevicePayRequest;
import kz.suterminal.model.response.DeviceCheckResponse;
import kz.suterminal.model.response.DevicePayResponse;
import kz.suterminal.repository.DeviceRepository;
import kz.suterminal.services.ExternalDeviceRequestService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultExternalDeviceRequestService implements ExternalDeviceRequestService {
    private IMqttAsyncClient mqttClient;
    private ObjectMapper mapper;
    private DeviceRepository deviceRepository;


    @SneakyThrows
    @Override
    public DeviceCheckResponse check(DeviceCheckRequest request) {
        var device = deviceRepository.findDeviceByImei(request.getDeviceId());
        if (device == null) return null;
        if (device.getStatus() == DeviceStatus.NOT_AVAILABLE) {
            throw new BusinessException(FaultCode.DEVICE_NOT_AVAILABLE, "device not available");
        }
        var response = new DeviceCheckResponse();
        response.address = device.getAddress();
        response.phoneNumber = device.getPartner().getPhone();
        response.status = device.getStatus().toString();
        response.tariffs = device.getTariffs();

        return response;
    }

    @SneakyThrows
    @Override
    public DevicePayResponse pay(DevicePayRequest request) {
        var device = deviceRepository.findDeviceByImei(request.getDeviceId());
        byte[] bytes = mapper.writeValueAsBytes(request);
        mqttClient.publish(device.getImei(), new MqttMessage(bytes));
        return new DevicePayResponse();
    }
}
