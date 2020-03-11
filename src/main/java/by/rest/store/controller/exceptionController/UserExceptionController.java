package by.rest.store.controller.exceptionController;

import by.rest.store.exception.user.UserBadRequestException;
import by.rest.store.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class UserExceptionController extends ResponseEntityExceptionHandler {
     
     @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
          return new ResponseEntity<>("User not valid", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(value = {ConstraintViolationException.class})
     protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
          return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
     }
     
     @ExceptionHandler(UserBadRequestException.class)
     public ResponseEntity exBadRequest() {
          log.warn("Bad Request");
          return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(UserNotFoundException.class)
     public ResponseEntity exNotFound() {
          log.warn("Not Found");
          return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
     }
}
