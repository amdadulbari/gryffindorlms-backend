package com.gryffindor.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionResponse {
    private Date timestamp;
    private boolean success;
    private String message;

    public ExceptionResponse(Date timestamp, boolean success, String message) {
        this.timestamp = timestamp;
        this.success = success;
        this.message = message;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
