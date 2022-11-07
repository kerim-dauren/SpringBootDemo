package kz.suterminal.models;

import lombok.Data;

@Data
public class TerminalPayRequest {
    private String action;
    private long payId;
    private int pulseCount;
}
