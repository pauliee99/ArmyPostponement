package gr.hua.ds.postponement.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalApplicationErrorException extends RuntimeException {

    public InternalApplicationErrorException(String exception) {
        super(exception);
    }

}