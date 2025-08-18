package hu.zlaval.springcourse.configuration;


import hu.zlaval.springcourse.configuration.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.AbstractMap;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleAllErrors(BindException e) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation Error");
        pd.setDetail("Invalid Fields");
        pd.setType(URI.create("/spring-course"));

        var errors = e.getBindingResult().getAllErrors()
                .stream()
                .map(entry -> {
                            var message = entry.getDefaultMessage();
                            var code = entry.getCode();
                            if (entry.contains(ConstraintViolation.class)) {
                                var cv = entry.unwrap(ConstraintViolation.class);
                                var p = cv.getPropertyPath().toString();
                                if (!p.isBlank()) {
                                    code = p;
                                }

                            }

                            return new AbstractMap.SimpleEntry<>(code, message);
                        }
                );

        pd.setProperty("invalid_fields", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleAllErrors(NotFoundException e) {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Record Not Found");
        pd.setDetail(e.getMessage());
        pd.setType(URI.create("/spring-course"));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleAllErrors(Exception e) {
        var pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Something went wrong");
        pd.setDetail("Something went wrong");
        pd.setType(URI.create("/spring-course"));

        return ResponseEntity.internalServerError().body(pd);
    }

}
