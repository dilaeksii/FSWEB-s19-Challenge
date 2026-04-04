package com.workintech.Sprint19_Challenge.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationErrorResponse {

    private int status;
    private String message;
    private long timestamp;

    public ApplicationErrorResponse(String message) {
        this.message = message;
    }
}
