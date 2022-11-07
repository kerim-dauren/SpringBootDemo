package kz.suterminal.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TerminalPayRequestDto {
    public String terminalId;
    public BigDecimal sum;
    public BigDecimal quantity;

    public TerminalPayRequestDto(String terminalId, BigDecimal sum, BigDecimal quantity) {
        this.terminalId = terminalId;
        this.sum = sum;
        this.quantity = quantity;
    }
}
