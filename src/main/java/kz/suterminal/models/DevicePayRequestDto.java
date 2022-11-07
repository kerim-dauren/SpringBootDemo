package kz.suterminal.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DevicePayRequestDto {
    public String deviceId;
    public BigDecimal sum;
    public BigDecimal quantity;

    public DevicePayRequestDto(String deviceId, BigDecimal sum, BigDecimal quantity) {
        this.deviceId = deviceId;
        this.sum = sum;
        this.quantity = quantity;
    }
}
