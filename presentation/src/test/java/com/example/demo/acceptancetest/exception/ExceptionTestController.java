package com.example.demo.acceptancetest.exception;

import com.example.demo.exception.InternalErrorException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTestController {

    @GetMapping("/notfoundException")
    public ResponseEntity<Void> notfoundException() {
        throw new NotFoundException("message");
    }

    @GetMapping("/internalErrorException")
    public ResponseEntity<Void> internalErrorException() {
        throw new InternalErrorException("message", null);
    }

    @GetMapping("/unauthorizedException")
    public ResponseEntity<Void> unauthorizedException() {
        throw new UnauthorizedException("message");
    }

    @GetMapping("/runTimeException")
    public ResponseEntity<Void> runTimeException() {
        throw new RuntimeException("message");
    }
}
