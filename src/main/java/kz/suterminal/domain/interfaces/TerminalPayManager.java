package kz.suterminal.domain.interfaces;

import kz.suterminal.models.TerminalPayRequestDto;

public interface TerminalPayManager {
    void pay(TerminalPayRequestDto request);
}
