package kz.suterminal.model.requests;

import lombok.Data;

@Data
public class DeviceMessage {
    private String sessionId;
    private String imei;
    private String data;
}
