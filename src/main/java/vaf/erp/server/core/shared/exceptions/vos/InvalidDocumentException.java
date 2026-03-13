package vaf.erp.server.core.shared.exceptions.vos;

import org.springframework.http.HttpStatus;
import vaf.erp.server.core.shared.exceptions.DomainException;

public class InvalidDocumentException extends DomainException {
    public InvalidDocumentException(String detail) {
        super(detail, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
