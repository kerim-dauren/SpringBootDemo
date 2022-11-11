package kz.suterminal.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceCheckRequest {
    private String deviceId;
}
