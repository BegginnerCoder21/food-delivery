package com.api.food_delivery_api.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
public class ApiExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ApiResponseEntityDto> handlerInternalServerErrorExecption(InternalServerErrorException ex)
    {
        log.error("Internal server error: {}", ex.getLocalizedMessage());

        var apiResponseEntityDto = ApiResponseEntityDto.builder()
                .message("Internal server error")
                .errorCode("500")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .messageDescription(ex.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .responseData(EmptyException.builder().build())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponseEntityDto);
    }

    @ExceptionHandler(UserNotFoundErrorException.class)
    public ResponseEntity<ApiResponseEntityDto> handlerUserNotFoundErrorException(UserNotFoundErrorException ex)
    {
        log.error("Utilisateur non trouvé: {}", ex.getLocalizedMessage());

        var apiResponseEntityDto = ApiResponseEntityDto.builder()
                .message("Utilisateur non trouvé")
                .errorCode("404")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .messageDescription(ex.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .responseData(EmptyException.builder().build())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(apiResponseEntityDto);
    }
}
