package co.com.astropay.api.handlingexceptions;

import co.com.astropay.usecase.exceptions.Response;
import co.com.astropay.usecase.exceptions.custom.BadRequestException;
import co.com.astropay.usecase.exceptions.custom.CustomException;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Response> custom(CustomException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
}
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Response> custom(RuntimeException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<Response> connection(NoContentException ex){
        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("NO_CONTENT")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Response> connection(BadRequestException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }



}
