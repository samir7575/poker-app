package com.sg.labs.pokerapp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * Poker Business Exception
 */
public class PokerBusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * The FUNC-ERR code
     */
    private final String message;

    /**
     * Dynamic parameters that can be injected into the error description.
     */
    private final String[] params;

    /**
     * Optional parameter used to override the HTTP return code if required.
     */
    private final HttpStatus httpStatus;

    /**
     * Cutom message displayed in the
     */
    private final String customMessage;

    public PokerBusinessException(Builder builder) {
        super();
        this.message = builder.message;
        this.params = builder.params;
        this.customMessage = builder.customMessage;
        this.httpStatus = builder.httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String[] getParams() {
        if (params == null) {
            return new String[0];
        } else {
            return Arrays.copyOf(this.params, this.params.length);
        }
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public static class Builder {

        private String message;
        private String[] params;
        private HttpStatus httpStatus;
        private String customMessage;

        public Builder() {

        }

        public Builder(String message) {
            this.message = message;
            this.httpStatus = HttpStatus.BAD_REQUEST;
        }

        public Builder(String message, String... params) {
            this.message = message;
            this.params = params;
            this.httpStatus = HttpStatus.BAD_REQUEST;
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder customMessage(String customMessage) {
            this.customMessage = customMessage;
            return this;
        }

        public PokerBusinessException build() {
            return new PokerBusinessException(this);
        }

    }
}
