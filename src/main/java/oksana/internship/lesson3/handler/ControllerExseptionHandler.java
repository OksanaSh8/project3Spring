package oksana.internship.lesson3.handler;

import oksana.internship.lesson3.validator.Errors;
import oksana.internship.lesson3.validator.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "oksana.internship.lesson3.controllers")
public class ControllerExseptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Errors> handleException(Exceptions e)  {
        Errors response = new Errors(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
