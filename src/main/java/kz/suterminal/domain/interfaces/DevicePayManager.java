package kz.suterminal.domain.interfaces;

import kz.suterminal.models.DevicePayRequestDto;

public interface DevicePayManager {
    void pay(DevicePayRequestDto request);
}
