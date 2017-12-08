package br.com.crescer.tcc.handler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Carlos H. Nonnemacher
 */
@ControllerAdvice
public class HandlerException {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HandlerExceptionMessage defaultErrorHandler(final HttpServletRequest httpServletRequest, final Exception exception) throws Exception {
        final HandlerExceptionMessage handlerExceptionMessage = new HandlerExceptionMessage();
        handlerExceptionMessage.setException(exception);
        handlerExceptionMessage.setMessage(createCustomExceptionMessage(exception));
        return handlerExceptionMessage;
    }

    private String createCustomExceptionMessage(final Exception exception) {
        return exception.getMessage();
    }

    public static class HandlerExceptionMessage {

        public HandlerExceptionMessage() {

        }

        Exception exception;
        String message;

        public Exception getException() {
            return exception;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
