package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            ValidationException.class,
            MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError badRequestException(final RuntimeException exception) {
        log.info(HttpStatus.BAD_REQUEST + " {}", exception.getMessage());
        return ApiError.builder()
                .errors(List.of(exception.getClass().getName()))
                .status(HttpStatus.BAD_REQUEST)
                .reason("Incorrectly made request.")
                .message(exception.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFoundException(final NotFoundException exception) {
        log.info(HttpStatus.NOT_FOUND + " {}", exception.getMessage());
        return ApiError.builder()
                .errors(List.of(exception.getClass().getName()))
                .status(HttpStatus.NOT_FOUND)
                .reason("The required object was not found.")
                .message(exception.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler({DataIntegrityViolationException.class, AccessException.class,
            TransactionSystemException.class,
            ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError notValidateException(final RuntimeException exception) {
        log.info(HttpStatus.CONFLICT + " {}", exception.getMessage());
        return ApiError.builder()
                .errors(List.of(exception.getClass().getName()))
                .status(HttpStatus.CONFLICT)
                .reason("Integrity constraint has been violated.")
                .message(exception.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError throwable(final Throwable exception) {
        log.info(HttpStatus.INTERNAL_SERVER_ERROR + " {}", exception.getMessage());
        return ApiError.builder()
                .errors(List.of(exception.getClass().getName()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .reason("Internet server error.")
                .message(exception.getLocalizedMessage())
                .build();
    }
}