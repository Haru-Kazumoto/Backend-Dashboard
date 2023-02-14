package dev.pack.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        body.put("Status", status);
        body.put("message", errors);

        return new ResponseEntity<>(body, headers, status);
    }

//    @ResponseBody
//    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class})
//    public ResponseEntity<Object> handleDataIntegrityViolationException(
//            DataIntegrityViolationException ex,
//            HttpHeaders headers,
//            HttpStatusCode status){
//        Map<String, Object> body = new HashMap<>();
//        List<String> errorMessage = new LinkedList<>();
//
//        //Adding the error message into array collection
//        errorMessage.add("Cannot add duplicate unique field!");
//
//        body.put("Status", status);
//        body.put("message", errorMessage);
//
//        return new ResponseEntity<>(body, headers, status);
//    }
}
