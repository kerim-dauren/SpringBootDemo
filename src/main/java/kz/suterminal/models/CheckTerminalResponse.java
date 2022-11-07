package kz.suterminal.models;

import kz.suterminal.models.entities.TerminalTariff;
import lombok.Data;

import java.util.List;

@Data
public class CheckTerminalResponse {
    public String address;
    public String bin;
    public String phoneNumber;
    public String status;
    public List<TerminalTariff> tariffs;
}
