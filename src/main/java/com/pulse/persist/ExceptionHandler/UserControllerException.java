package com.pulse.persist.ExceptionHandler;

import com.pulse.persist.Exception.DuplicateEmailException;
import com.pulse.persist.Exception.UserNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserControllerException {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException e, WebRequest request){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<String> handleUserNameNotFoundException(UserNameNotFoundException e, WebRequest request){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
