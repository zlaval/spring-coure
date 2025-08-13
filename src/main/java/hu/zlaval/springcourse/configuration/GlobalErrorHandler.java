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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFoundErrors(NotFoundException e) {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Item does not exist");
        pd.setDetail(e.getMessage());
        pd.setType(URI.create("/spring-course"));
        return ResponseEntity.internalServerError().body(pd);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleAllErrors(Exception e) {
        var pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Something went wrong");
        pd.setDetail("Something went wrong");
        pd.setType(URI.create("/spring-course"));
        return ResponseEntity.internalServerError().body(pd);
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleValidationError(BindException error) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation error");
        problemDetail.setDetail("Fields are invalid");
        problemDetail.setType(URI.create("/spring-course"));
        var errors = error.getBindingResult().getAllErrors()
                .stream().map(entry -> {
                            var message = entry.getDefaultMessage();
                            String property = entry.getCode();
                            if (entry.contains(ConstraintViolation.class)) {
                                var cv = entry.unwrap(ConstraintViolation.class);
                                var f = cv.getPropertyPath().toString();
                                if (!f.isBlank()) {
                                    property = f;
                                }
                            }
                            return new AbstractMap.SimpleEntry<>(property, message);
                        }
                );

        problemDetail.setProperty("invalid_fields", errors);

        return ResponseEntity.badRequest().body(problemDetail);
    }

}
