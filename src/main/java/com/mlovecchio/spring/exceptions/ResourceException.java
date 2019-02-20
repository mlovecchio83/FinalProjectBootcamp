package com.mlovecchio.spring.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class ResourceException extends RuntimeException {

    private HttpStatus httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

    public ResourceException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }

}
