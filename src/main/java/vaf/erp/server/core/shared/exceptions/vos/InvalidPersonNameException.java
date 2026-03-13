package vaf.erp.server.core.shared.exceptions.vos;

import org.springframework.http.HttpStatus;
import vaf.erp.server.core.shared.exceptions.DomainException;

public class InvalidPersonNameException extends DomainException {
    public InvalidPersonNameException(String detail) {
        super(detail, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
