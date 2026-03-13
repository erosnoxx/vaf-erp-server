package vaf.erp.server.core.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DomainException extends RuntimeException {
    private final String detail;
    private final HttpStatus httpStatus;

    public DomainException(String detail, HttpStatus httpStatus) {
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(httpStatus);
        pb.setTitle(this.getClass().getSimpleName());
        pb.setDetail(detail);
        return pb;
    }

    @Override
    public String getMessage() {
        return detail;
    }
}
