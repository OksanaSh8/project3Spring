package oksana.internship.lesson3.handler;

import oksana.internship.lesson3.validator.Errors;
import oksana.internship.lesson3.validator.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ControllerExseptionHandler {
  /*  @ExceptionHandler
    private ResponseEntity<Errors> handleException(Exceptions e) {
        Errors response = new Errors(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }*/
}
