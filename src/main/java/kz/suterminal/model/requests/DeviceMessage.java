package kz.suterminal.model.requests;

import kz.suterminal.model.DeviceStatus;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DeviceMessage {
    private String sessionId;
    private String imei;
    private DeviceStatus data;
}
