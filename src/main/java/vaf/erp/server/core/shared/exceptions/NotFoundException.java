package vaf.erp.server.core.shared.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends DomainException {
    public NotFoundException(String detail) {
        super(detail, HttpStatus.NOT_FOUND);
    }
}
