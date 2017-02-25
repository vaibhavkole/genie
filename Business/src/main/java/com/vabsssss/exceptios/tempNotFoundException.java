package main.java.com.vabsssss.exceptios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class tempNotFoundException extends RuntimeException {
    public tempNotFoundException(String tenantName) {
        super("could not find tenant '" + tenantName + "'.");
    }
}