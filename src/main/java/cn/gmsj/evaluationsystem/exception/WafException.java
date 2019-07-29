package cn.gmsj.evaluationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WafException extends RuntimeException {
    private ResponseEntity<ErrorMessage> responseEntity;

    public WafException(ResponseEntity<ErrorMessage> responseEntity, Throwable cause) {
        super(((ErrorMessage) responseEntity.getBody()).getMessage(), cause);
        this.responseEntity = responseEntity;
    }

    public WafException(ResponseEntity<ErrorMessage> responseEntity) {
        this((ResponseEntity) responseEntity, (Throwable) null);
    }

    public WafException(ErrorMessage errorMessage, HttpStatus status, Throwable cause) {
        this(new ResponseEntity(errorMessage, status), cause);
    }

    public WafException(ErrorMessage errorMessage, HttpStatus status) {
        this(new ResponseEntity(errorMessage, status));
    }

    public WafException(String code, String message, String detail, HttpStatus status,
                        Throwable cause) {
        this(new ErrorMessage(code, message, detail), status, cause);
    }

    public WafException(String code, String message, String detail, HttpStatus status) {
        this((ErrorMessage) (new ErrorMessage(code, message, detail)), (HttpStatus) status,
                (Throwable) null);
    }

    public WafException(String code, String message, HttpStatus status, Throwable cause) {
        this(new ErrorMessage(code, message), status, cause);
    }

    public WafException(String code, String message, HttpStatus status) {
        this(code, message, (HttpStatus) status, (Throwable) null);
    }

    public WafException(String code, String message, Throwable cause) {
        this(new ErrorMessage(code, message), HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }

    public WafException(String code, String message) {
        this(code, message, (Throwable) null);
    }

    public ResponseEntity<ErrorMessage> getResponseEntity() {
        return this.responseEntity;
    }

    public ErrorMessage getError() {
        return (ErrorMessage) this.responseEntity.getBody();
    }

    @Override
    public String getLocalizedMessage() {
        return I18NProvider.getString(this.getMessage());
    }
}