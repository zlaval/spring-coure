package hu.zlaval.springcourse.configuration;


import hu.zlaval.springcourse.configuration.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

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
}
