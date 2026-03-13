package vaf.erp.server.core.registry.location.domain.exceptions;

import org.springframework.http.HttpStatus;
import vaf.erp.server.core.shared.exceptions.DomainException;

public class InvalidCityNameException extends DomainException {
    public InvalidCityNameException(String detail) {
        super(detail, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
