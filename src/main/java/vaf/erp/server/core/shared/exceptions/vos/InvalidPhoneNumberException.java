package vaf.erp.server.core.shared.exceptions.vos;

import org.springframework.http.HttpStatus;
import vaf.erp.server.core.shared.exceptions.DomainException;

public class InvalidPhoneNumberException extends DomainException {
    public InvalidPhoneNumberException(String detail) {
        super(detail, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
