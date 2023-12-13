package com.example.demo.miscellaneous;

import com.example.demo.exception.InternalErrorException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.gen.springbootserver.model.ProblemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UnauthorizedException.class })
    protected ResponseEntity<ProblemDto> handleUnauthorizedException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(createProblem(ex, request), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    protected ResponseEntity<ProblemDto> handleNotFoundException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(createProblem(ex, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { InternalErrorException.class })
    protected ResponseEntity<ProblemDto> handleInternalErrorException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(createProblem(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ProblemDto> handleGenericException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(createProblem(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    ProblemDto createProblem(RuntimeException ex, WebRequest request) {
        ProblemDto problemDto = new ProblemDto();
        problemDto.setType(((ServletWebRequest) request).getRequest().getRequestURI().toString());
        problemDto.setTitle(ex.getClass().getSimpleName());
        problemDto.setDetail(ex.getMessage());
        problemDto.setContext("");
        return problemDto;
    }
}