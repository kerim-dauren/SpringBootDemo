package kz.suterminal.model.response;

import lombok.Data;

import java.util.List;

@Data
public class DeviceCheckResponse {
    public String address;
    public String bin;
    public String phoneNumber;
    public String status;
    public List<TerminalTariff> tariffs;
}
