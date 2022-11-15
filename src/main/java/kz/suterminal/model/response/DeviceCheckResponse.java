package kz.suterminal.model.response;

import kz.suterminal.entity.Tariff;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DeviceCheckResponse {
    public String address;
    public String bin;
    public String phoneNumber;
    public String status;
    public Set<Tariff> tariffs;
}
