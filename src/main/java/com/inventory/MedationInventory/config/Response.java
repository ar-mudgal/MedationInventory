package com.inventory.MedationInventory.config;

import lombok.*;
import org.springframework.http.HttpStatus;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private int code;
    private String message;
    private HttpStatus status;
    private Object responseObject;

    public Response(String message, HttpStatus  status) {
        this.message = message;
        this.status = status;
        this.code = status.value();
    }

    public Response(String message, Object responseObject, HttpStatus  status) {
        this.message = message;
        this.status = status;
        this.code = status.value();
        this.responseObject = responseObject;
    }
    public void setResult(Object responseObject) {
        this.responseObject = responseObject;
    }
}
