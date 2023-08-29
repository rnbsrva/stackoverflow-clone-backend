package com.akerke.stackoverflow.exception.handler;

import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.exception.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.function.BiFunction;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    private final BiFunction<RuntimeException, HttpStatus, ProblemDetail> withDetails =
            (e, status) ->
                    ProblemDetail.forStatusAndDetail(status, e.getMessage());
}
