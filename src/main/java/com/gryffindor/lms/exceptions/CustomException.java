package com.gryffindor.lms.exceptions;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
