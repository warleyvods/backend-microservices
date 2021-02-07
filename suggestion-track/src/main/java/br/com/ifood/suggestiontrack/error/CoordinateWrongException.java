package br.com.ifood.suggestiontrack.error;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoordinateWrongException extends RuntimeException {

    public CoordinateWrongException(String msg) {
        super(msg);
    }

}
