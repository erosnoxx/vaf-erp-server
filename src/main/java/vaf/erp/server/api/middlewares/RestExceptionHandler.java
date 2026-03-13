package vaf.erp.server.api.middlewares;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vaf.erp.server.core.shared.exceptions.DomainException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainException(DomainException ex) {
        return ex.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var fieldErrors = ex.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(
                        f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("your request parameters didn't validate.");
        pb.setProperty("invalid-params", fieldErrors);
        return pb;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("internal server error");
        pb.setDetail(ex.getMessage());
        return pb;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        var pb = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        pb.setTitle("access denied");
        pb.setDetail(ex.getMessage());
        return pb;
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ProblemDetail handleAuthenticationException(AuthenticationException ex) {
//        var pb = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
//        pb.setTitle("access denied");
//        pb.setDetail("invalid username or password");
//        return pb;
//    }

    private record InvalidParam(String name, String reason) {}
}
