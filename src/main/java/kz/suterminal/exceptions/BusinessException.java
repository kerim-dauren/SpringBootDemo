package kz.suterminal.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessException extends Exception {
    private FaultCode faultCode;
    private String message;

    public BusinessException(FaultCode faultCode, String message) {
        super(message);
        this.faultCode = faultCode;
        this.message = message;
    }
}
