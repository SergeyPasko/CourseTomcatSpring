package lesson37.exception;

import lesson37.dto.ErrorMessageResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author spasko
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorMessageResponse unexpected(Exception e) {
        LOG.error("Unexpected exception {}", e.getMessage());
        return new ErrorMessageResponse(e.getMessage());
    }

    @ExceptionHandler(value = {UpdateException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody ErrorMessageResponse entityExistingProblem(Exception e) {
        LOG.warn("Unprocesable entity {}", e.getMessage());
        return new ErrorMessageResponse(e.getMessage());
    }

}
