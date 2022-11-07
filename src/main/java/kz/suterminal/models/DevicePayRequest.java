package kz.suterminal.models;

import lombok.Data;

@Data
public class DevicePayRequest {
    private String action;
    private long payId;
    private int pulseCount;
}
