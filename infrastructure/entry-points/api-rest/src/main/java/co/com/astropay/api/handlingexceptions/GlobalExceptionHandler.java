package co.com.astropay.api.handlingexceptions;

import co.com.astropay.usecase.exceptions.Response;
import co.com.astropay.usecase.exceptions.custom.BadRequestException;
import co.com.astropay.usecase.exceptions.custom.CustomException;
import co.com.astropay.usecase.exceptions.custom.NoContentException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice("co.com.astropay.api")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Response> custom(CustomException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
}
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Response> custom(RuntimeException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<Response> connection(NoContentException ex){
        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("NO_CONTENT")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();
        return new ResponseEntity<Response>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Response> connection(BadRequestException ex){

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("BAD_REQUEST")
                .descripcionRespuesta(ex.getMessage())
                .result(Collections.emptyList())
                .build();
        return new ResponseEntity<Response>(response, HttpStatus.NO_CONTENT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        Response response = Response.builder()
                .fecha(LocalDateTime.now().toString())
                .codigoResultado("UNPROCESSABLE_ENTITY")
                .descripcionRespuesta(errors.stream().reduce(" ", String::concat))
                .result(Collections.emptyList())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }




}
