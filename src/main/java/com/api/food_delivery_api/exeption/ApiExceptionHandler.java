package com.api.food_delivery_api.exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ApiResponseEntityDto> handleInternalServerErrorException(InternalServerErrorException ex)
    {
        log.error("Internal server error: {}", ex.getLocalizedMessage());

        var apiResponseEntityDto = ApiResponseEntityDto.builder()
                .message("Internal server error")
                .errorCode("500")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .messageDescription(ex.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .responseData(new EmptyResponse())
                .build();

        return new ResponseEntity<>(apiResponseEntityDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundErrorException.class)
    public ResponseEntity<ApiResponseEntityDto> handleUserNotFoundErrorException(UserNotFoundErrorException ex)
    {
        log.error("Utilisateur non trouvé: {}", ex.getLocalizedMessage());

        var apiResponseEntityDto = ApiResponseEntityDto.builder()
                .message("Utilisateur non trouvé")
                .errorCode("404")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .messageDescription(ex.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .responseData(new EmptyResponse())
                .build();

        return new ResponseEntity<>(apiResponseEntityDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestErrorException.class)
    public ResponseEntity<ApiResponseEntityDto> handleBadRequestErrorException(BadRequestErrorException ex)
    {
        log.error("Bad request error: {}", ex.getLocalizedMessage());

        var apiResponseEntityDto = ApiResponseEntityDto.builder()
                .message("Requête mal envoyé")
                .errorCode("400")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .messageDescription(ex.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .responseData(new EmptyResponse())
                .build();

        return new ResponseEntity<>(apiResponseEntityDto, HttpStatus.BAD_REQUEST);
    }
}
