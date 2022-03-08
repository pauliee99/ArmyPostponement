package gr.hua.ds.postponement.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostponementNotFoundException extends RuntimeException {

    public PostponementNotFoundException(String exception) {
        super(exception);
    }

}
