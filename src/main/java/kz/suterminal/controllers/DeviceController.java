package kz.suterminal.controllers;

import kz.suterminal.domain.interfaces.DevicePayManager;
import kz.suterminal.models.CheckTerminalResponse;
import kz.suterminal.models.DevicePayRequestDto;
import kz.suterminal.models.TerminalPayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/device")
public class DeviceController {

    private DevicePayManager devicePayManager;

    @Autowired
    public DeviceController(DevicePayManager devicePayManager) {
        this.devicePayManager = devicePayManager;
    }


    @GetMapping("{deviceId}/check")
    public ResponseEntity<CheckTerminalResponse> check(@PathVariable("deviceId") String deviceId) {
        return ResponseEntity.ok(new CheckTerminalResponse());
    }

    @GetMapping("{deviceId}/pay")
    public ResponseEntity<TerminalPayResponse> pay(
            @PathVariable("deviceId") String deviceId,
            @RequestParam("sum") BigDecimal sum,
            @RequestParam("quantity") BigDecimal quantity
    ) {
        devicePayManager.pay(new DevicePayRequestDto(deviceId, sum, quantity));
        return ResponseEntity.ok().build();
    }
}
