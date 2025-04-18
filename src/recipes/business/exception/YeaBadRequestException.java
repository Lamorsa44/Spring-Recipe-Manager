package recipes.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class YeaBadRequestException extends RuntimeException {
    public YeaBadRequestException(String message) {
        super(message);
    }
}
