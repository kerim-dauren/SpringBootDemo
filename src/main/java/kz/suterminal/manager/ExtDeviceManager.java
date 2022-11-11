package kz.suterminal.manager;

import kz.suterminal.model.requests.DeviceCheckRequest;
import kz.suterminal.model.requests.DevicePayRequest;
import kz.suterminal.model.response.DeviceCheckResponse;
import kz.suterminal.model.response.DevicePayResponse;

public interface ExtDeviceManager {
    DeviceCheckResponse check(DeviceCheckRequest request);

    DevicePayResponse pay(DevicePayRequest request);
}
