package br.com.ifood.suggestiontrack.error.handler;

import br.com.ifood.suggestiontrack.error.CityNotFoundException;
import br.com.ifood.suggestiontrack.error.CoordinateWrongException;
import br.com.ifood.suggestiontrack.error.ExceptionFilters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ExceptionFilters> handleCityNotFoundException(CityNotFoundException cityNotFoundException) {
        ExceptionFilters noCity = ExceptionFilters.ExceptionFiltersBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .details(cityNotFoundException.getMessage())
                .devMsg(cityNotFoundException.getClass().getName())
                .status(HttpStatus.FORBIDDEN.value())
                .title("City Not Found!")
                .build();

        return new ResponseEntity<>(noCity, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CoordinateWrongException.class)
    public ResponseEntity<ExceptionFilters> handlerCoordinateWrongException(CoordinateWrongException coordinateWrongException) {
        ExceptionFilters wrongCoordinate = ExceptionFilters.ExceptionFiltersBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .details(coordinateWrongException.getMessage())
                .devMsg(coordinateWrongException.getClass().getName())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Wrong value for coordinates")
                .build();

        return new ResponseEntity<>(wrongCoordinate, HttpStatus.BAD_REQUEST);
    }

}
