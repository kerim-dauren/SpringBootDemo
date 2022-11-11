package kz.suterminal.controller;

import kz.suterminal.manager.ExtDeviceManager;
import kz.suterminal.model.requests.DeviceCheckRequest;
import kz.suterminal.model.requests.DevicePayRequest;
import kz.suterminal.model.response.DeviceCheckResponse;
import kz.suterminal.model.response.DevicePayResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/device")
@Slf4j
public class ExtDeviceController {

    private ExtDeviceManager extDeviceManager;

    @GetMapping("{deviceId}/check")
    public ResponseEntity<DeviceCheckResponse> check(@PathVariable String deviceId) {
        try {
            var result = extDeviceManager.check(new DeviceCheckRequest(deviceId));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("unexpected", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("{deviceId}/pay")
    public ResponseEntity<DevicePayResponse> pay(
            @PathVariable String deviceId,
            @RequestParam("txn_id") String transactionId,
            @RequestParam("sum") BigDecimal sum,
            @RequestParam("qty") BigDecimal quantity
    ) {
        try {
            var request = new DevicePayRequest(deviceId, transactionId, sum, quantity);
            var result = extDeviceManager.pay(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("unexpected", e);
            return ResponseEntity.status(500).build();
        }
    }
}
