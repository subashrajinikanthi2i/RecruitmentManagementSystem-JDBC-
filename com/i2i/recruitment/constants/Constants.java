/*
 * Subash rajinikanth M
 *
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * This is the simple Recruitment System console application,
 * add the employee and candidate details to the mysql Database
 * using JDBC
 * 
 */
package com.i2i.recruitment.constants;

import java.util.Map;
import java.util.HashMap;

/**
 * This is the constants class for declare any constant valirible,
 * for this application, and we can us it directly in the application.
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public class Constants {

    public static Map<String, String> errorMessages = new HashMap();

    public static final String ERROR_CODE_1001 = "1001";
    public static final String ERROR_CODE_1002 = "1002";
    public static final String ERROR_CODE_1003 = "1003";
    public static final String ERROR_CODE_1004 = "1004";
    public static final String ERROR_CODE_1005 = "1005";
    public static final String ERROR_CODE_1006 = "1006";
   
    public static final String NAME_REGEX = "^[a-zA-Z]*";
    public static final String ID_REGEX= "[Cc][0-9]+$";
    public static final String AGE_REGEX = "[1-9]{2}";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9.]+@[A-Za-z]+[.][a-z]{3}$";
    public static final String MOBILE_REGEX = "[6-9][0-9]{9}";
    public static final String DATE_REGEX = "[0-3][0-9][/][0-1][0-9][/][0-9]{4}";
    public static final String YEAR_REGEX = "[2][0-9]{3}$";
    public static final String EXPERIENCE_REGEX = "((^[0-9]{1,2})|(([0-9]{1,2})([.]{1}])([0-9]{1,2})))";
    public static final String CGPA_REGEX = "^[0-9]([.]{1})?[0-9]$";
    public static final String SALARY_REGEX = "[1-9][0-9]*";
    public static final String ADDRESS_REGEX = "^[a-zA-Z]*";

    public static final String ENTER_NAME = "Enter the Name : ";
    public static final String ENTER_AGE = "Enter the Age : ";
    public static final String ENTER_DATE_OF_BIRTH = "Enter the date of birth : ";
    public static final String ENTER_SKILL= "Enter the Skill : ";
    public static final String ENTER_QUALIFICATION = "Enter the Qualification : ";
    public static final String ENTER_EXPERIENCE = "Enter the Experience : ";
    public static final String ENTER_SALARY = "Enter the salary";

    public static final String SUCCESS_ALERT_MESSAGE = "succcess...";
    public static final String FAILURE_ALERT_MESSAGE = "Failure...";
    public static final String INVALID_OPTION = "Invalid option selected...";


    /**
     * Get the error code message for the appropriate key
     *
     */
    public static Map<String, String> getErrorCode() {
        String ERROR_MESSAGE_1001 = "Connection error, Unable to create profile";
    	String ERROR_MESSAGE_1002 = "Connection error, Unable to read profile";
    	String ERROR_MESSAGE_1003 = "Connection error, Unable to update profile";
    	String ERROR_MESSAGE_1004 = "Connection error, Unable to delete profile";
    	String ERROR_MESSAGE_1005 = "Connection error, Unable to assign profile";
    	String ERROR_MESSAGE_1006 = "Connection error, Unable to unassign profile";

        errorMessages.put(ERROR_CODE_1001, ERROR_MESSAGE_1001);
        errorMessages.put(ERROR_CODE_1002, ERROR_MESSAGE_1002);
        errorMessages.put(ERROR_CODE_1003, ERROR_MESSAGE_1003);
        errorMessages.put(ERROR_CODE_1004, ERROR_MESSAGE_1004);
        errorMessages.put(ERROR_CODE_1005, ERROR_MESSAGE_1005);
        errorMessages.put(ERROR_CODE_1006, ERROR_MESSAGE_1006);
	return errorMessages;
    }
}