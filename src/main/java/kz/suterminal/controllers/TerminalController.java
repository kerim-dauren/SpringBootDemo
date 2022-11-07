package kz.suterminal.controllers;

import kz.suterminal.domain.interfaces.TerminalPayManager;
import kz.suterminal.models.CheckTerminalResponse;
import kz.suterminal.models.TerminalPayRequestDto;
import kz.suterminal.models.TerminalPayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/terminal")
public class TerminalController {

    private TerminalPayManager terminalPayManager;

    @Autowired
    public TerminalController(TerminalPayManager terminalPayManager) {
        this.terminalPayManager = terminalPayManager;
    }


    @GetMapping("{terminalId}/check")
    public ResponseEntity<CheckTerminalResponse> check(@PathVariable("terminalId") String terminalId) {
        return ResponseEntity.ok(new CheckTerminalResponse());
    }

    @GetMapping("{terminalId}/pay")
    public ResponseEntity<TerminalPayResponse> pay(
            @PathVariable("terminalId") String terminalId,
            @RequestParam("sum") BigDecimal sum,
            @RequestParam("quantity") BigDecimal quantity
    ) {
        terminalPayManager.pay(new TerminalPayRequestDto(terminalId, sum, quantity));
        return ResponseEntity.ok().build();
    }
}
