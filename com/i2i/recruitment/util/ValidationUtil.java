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
package com.i2i.recruitment.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the Validation class of application.
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public class ValidationUtil {
    
    /**
     * In this method validating every fields for employees as well as candidates
     * by the given regex pattern 
     *
     * @param Filed name of candidate or employee
     *
     * @param Regex pattern for appropiate filed
     *
     * @return boolean : if the pattern matched return true otherwise return false
     */
    public boolean isValidString(String name, String regex) {
	Pattern stringPattern = Pattern.compile(regex);
        Matcher matcher = stringPattern.matcher(name);
	return matcher.matches();
    }
}