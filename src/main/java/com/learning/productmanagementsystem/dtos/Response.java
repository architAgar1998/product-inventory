package com.learning.productmanagementsystem.dtos;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Response<T extends DTO> {
    private int statusCode;
    private String message;
    private T responseEntity;

    public void setSuccessResponse(T dto) {
        this.setStatusCode(200);
        this.setMessage("Request completed successfully");
        this.setResponseEntity(dto);
    }

    public void setSuccessResponse(int statusCode, String message, T dto) {
        this.setStatusCode(statusCode);
        this.setMessage(message);
        this.setResponseEntity(dto);
    }

    public void setFailureResponse() {
        this.setStatusCode(500);
        this.setMessage("Unable to perform the request. Please try after some time.");
    }

    public void setFailureResponse(int statusCode, String message) {
        this.setStatusCode(statusCode);
        this.setMessage(message);
    }
}
