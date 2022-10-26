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
package com.i2i.recruitment.controller;

import java.util.List;

import com.i2i.recruitment.constants.Constants;
import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.logger.CustomLogger;
import com.i2i.recruitment.model.Candidate;
import com.i2i.recruitment.model.Employee;
import com.i2i.recruitment.service.CandidateService;
import com.i2i.recruitment.service.impl.CandidateServiceImpl;
import com.i2i.recruitment.util.ValidationUtil;

/*
 * Candidate controller class is to handle all the operations of cadidate 
 * 
 * @version 1.0
 * @author Subashrajinikanth 
 */
public class CandidateController {

    private CustomLogger customLogger = new CustomLogger(CandidateController.class);
    private CandidateService candidateService = new CandidateServiceImpl();
    private ValidationUtil validationUtil = new ValidationUtil();

    /**
     * Create the new candidate
     *
     * @param Object -  candidate 
     * @return boolean
     */
    public boolean createCandidate(Candidate candidate) {
	boolean isCreate = false;

        try {
            isCreate = candidateService.createCandidate(candidate);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isCreate;
    }

    /**
     * Get the candidate form database using candidate id
     *
     * @param int - candidate id
     * @return Object of candidate
     */
    public Candidate getCandidateById(int candidateId) {
        Candidate candidate = null;

        try {
    	    candidate = candidateService.getCandidateById(candidateId);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return candidate;
    }

    /**
     * Get all the candidate details from database
     *
     * @return List
     */
    public List<Candidate> getAllCandidates() {
	List<Candidate> candidates = null;

        try {
            candidates = candidateService.getAllCandidates();
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return candidates;    
    } 

    /**
     * Update the Candidate details using candidate id
     *
     * @param Object - Candidate object
     * @return boolean
     */
    public boolean updateCandidateById(Candidate candidate) { 
	boolean isUpdate = false;

        try {
            isUpdate = candidateService.updateCandidateById(candidate);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isUpdate;
    }

    /**
     * Delete the candidates from employee using candidate id
     *
     * @param int - id of candidate
     * @return boolean
     */
    public boolean deleteCandidateById(int candidateId) { 
	boolean isDelete = false;

        try {
            isDelete = candidateService.deleteCandidateById(candidateId);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isDelete;
    }

    /**
     * Generate the candidate id
     *
     * @return id
     */
    public int generateId() {
        int id = 0;

        try {
            id = candidateService.generateId();
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return id;
    }

    /**
     * Assign employees to the candidate using candidate id 
     * 
     * @param Object - candidate and employee
     * @return boolean
     */
    public boolean assignEmployeesByCandidateId(Candidate candidate, Employee employee) {
        boolean isAssign = false;

        try {
            isAssign = candidateService.assignEmployeesByCandidateId(candidate, employee);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isAssign;
    } 

    /**
     * Get all the candidates who interviewd by employee using employee id 
     * 
     * @param Object - candidate and employee
     * @return Object of Employee 
     */
    public Employee getAllCandidatesByEmployeeId(Employee employee) {
        Employee employeeResult = null;

        try {
    	    employeeResult = candidateService.getAllCandidatesByEmployeeId(employee);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return employeeResult;
    }

    /**
     * Unassign employees from candidate using candidate and employee Id
     * 
     * @param int - id of candidate and employee
     * @return boolean
     */
    public boolean unAssignEmployeesByCandidateId(int candidateId, int employeeId) {
        boolean isUnassign = false;

        try {
            isUnassign = candidateService.unAssignEmployeesByCandidateId(candidateId, employeeId);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isUnassign;
    }

    /**
     * Validate the candidate name
     *
     * @param String - Name of candidate
     * @return boolean
     *
     */
    public boolean isValidCandidateName(String name) {
        return validationUtil.isValidString(name, Constants.NAME_REGEX);
    }

    /**
     * Validate the candidate Email
     *
     * @param String - Email of candidate
     * @return boolean
     *
     */
    public boolean isValidCandidateEmail(String email) {
        return validationUtil.isValidString(email, Constants.EMAIL_REGEX);
    }

    /**
     * Validate the candidate Date Of Birth 
     *
     * @param String - Date Of Birth of candidate
     * @return boolean
     *
     */
    public boolean isValidCandidateDateOfBirth(String dateOfBirth) {
        return validationUtil.isValidString(dateOfBirth, Constants.DATE_REGEX);
    }

    /**
     * Validate the candidate Mobile 
     *
     * @param String - Mobile of candidate
     *
     * @return boolean
     *
     */
    public boolean isValidCandidateMobile(String mobile) {
        return validationUtil.isValidString(mobile, Constants.MOBILE_REGEX);
    }

    /**
     * Validate the candidate YearOfPassedOut
     *
     * @param int - YearOfPassedOut of candidate
     * @return boolean
     *
     */
    public boolean isValidCandidateYearOfPassedOut(String yearOfPassedOut) {
        return validationUtil.isValidString(yearOfPassedOut, Constants.YEAR_REGEX);
    } 

    /**
     * Validate the candidate salary 
     *
     * @param double - salary of candidate
     * @return boolean
     *
     */
    public boolean isValidCandidateSalary(String salary) {
        return validationUtil.isValidString(salary, Constants.SALARY_REGEX);
    }

    /**
     * Validate the candidate Experience 
     *
     * @param float - experience of candidate
     * @return boolean
     */
    public boolean isValidCandidateExperience(String experience) {
        return validationUtil.isValidString(experience, Constants.EXPERIENCE_REGEX);
    }

    /**
     * Validate the candidate Address
     *
     * @param String - Address of candidate
     * @return boolean
     */
    public boolean isValidCandidateAddress(String address) {
        return validationUtil.isValidString(address, Constants.ADDRESS_REGEX);
    }
}