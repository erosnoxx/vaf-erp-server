package vaf.erp.server.core.shared.exceptions.vos;

import org.springframework.http.HttpStatus;
import vaf.erp.server.core.shared.exceptions.DomainException;

public class InvalidTradeNameException extends DomainException {
    public InvalidTradeNameException(String detail) {
        super(detail, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
