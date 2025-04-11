package recipes.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class YeaForBiddenException extends RuntimeException {
    public YeaForBiddenException(String message) {
        super(message);
    }
}
