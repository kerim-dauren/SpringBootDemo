package kz.suterminal.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DevicePayRequest {
    private String deviceId;
    private String transactionId;
    private BigDecimal sum;
    private BigDecimal quantity;
}
