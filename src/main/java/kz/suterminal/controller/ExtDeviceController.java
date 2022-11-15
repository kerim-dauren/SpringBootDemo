package kz.suterminal.controller;

import kz.suterminal.model.requests.DeviceCheckRequest;
import kz.suterminal.model.requests.DevicePayRequest;
import kz.suterminal.model.response.DeviceCheckResponse;
import kz.suterminal.model.response.DevicePayResponse;
import kz.suterminal.services.ExternalDeviceRequestService;
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

    private ExternalDeviceRequestService externalDeviceRequestService;

    @GetMapping("{deviceId}/check")
    public ResponseEntity<DeviceCheckResponse> check(@PathVariable String deviceId) {
        var result = externalDeviceRequestService.check(new DeviceCheckRequest(deviceId));
        return ResponseEntity.ok(result);
    }

    @GetMapping("{deviceId}/pay")
    public ResponseEntity<DevicePayResponse> pay(
            @PathVariable String deviceId,
            @RequestParam("txn_id") String transactionId,
            @RequestParam("sum") BigDecimal sum,
            @RequestParam("qty") BigDecimal quantity
    ) {
        var request = new DevicePayRequest(deviceId, transactionId, sum, quantity);
        var result = externalDeviceRequestService.pay(request);
        return ResponseEntity.ok(result);
    }
}
