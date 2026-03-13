package vaf.erp.server.core.shared.exceptions.vos;

import org.springframework.http.HttpStatus;
import vaf.erp.server.core.shared.exceptions.DomainException;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException(String detail) {
        super(detail, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
