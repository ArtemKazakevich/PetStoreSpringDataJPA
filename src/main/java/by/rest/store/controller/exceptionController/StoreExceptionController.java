package by.rest.store.controller.exceptionController;

import by.rest.store.exception.store.StoreBadRequestException;
import by.rest.store.exception.store.StoreNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class StoreExceptionController {
     
     @ExceptionHandler(StoreBadRequestException.class)
     public ResponseEntity exBadRequest() {
          log.warn("Bad Request");
          return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(StoreNotFoundException.class)
     public ResponseEntity exNotFound() {
          log.warn("Not Found");
          return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
     }
}
