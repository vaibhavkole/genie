package com.vabsssss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vaibhav.janardhan on 25/01/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenantNotFoundException extends RuntimeException {
    public TenantNotFoundException(String tenantName) {
        super("could not find tenant '" + tenantName + "'.");
    }
}
