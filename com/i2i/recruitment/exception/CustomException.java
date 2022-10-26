/*
 * Subashrajinikanth M
 *
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * This is the simple Recruitment System console application,
 * and it implements CRUD Operations 
 * 
 */
package com.i2i.recruitment.exception;

/**
 * Here is the class handling to the custom Exception : Date of birth's YEAR
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable clause) {
        super(message, clause);
    }

    public CustomException(Throwable clause) {
        super(clause);
    }
/*
    public ErrorCode getCode() {
        return this.code;
    }

    public CustomException(String message, Throwable cause, ErrorCode code) {
        super(message, clause);
        this.code = code;
    }
*/

}