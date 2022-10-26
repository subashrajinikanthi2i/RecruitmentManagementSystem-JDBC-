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
import com.i2i.recruitment.service.EmployeeService;
import com.i2i.recruitment.service.impl.EmployeeServiceImpl;
import com.i2i.recruitment.util.ValidationUtil;

/**
 * Employee controller class is to handle all the operations of employee like, 
 * create, read, update, and delete
 * 
 * @version jdk 8 2022
 * @author subashrajinikanth
 */
public class EmployeeController {
    private CustomLogger customLogger = new CustomLogger(EmployeeController.class);
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private ValidationUtil validationUtil = new ValidationUtil();

    /**
     * Validate Employee name
     *
     * @param String - Name of employee
     * @return boolean
     *
     */
    public boolean isValidEmployeeName(String name) {
        return validationUtil.isValidString(name, Constants.NAME_REGEX);
    }   

    /**
     * Validate Employee Email
     *
     * @param String - Email of Employee
     * @return boolean
     *
     */
    public boolean isValidEmployeeEmail(String email) {
        return validationUtil.isValidString(email, Constants.EMAIL_REGEX);
    }

    /**
     * Validate Employee Date of Birth 
     *
     * @param String - date of birth of Employee
     * @return boolean
     *
     */

    public boolean isValidEmployeeDateOfBirth(String dateOfBirth) {
        return validationUtil.isValidString(dateOfBirth, Constants.DATE_REGEX);
    }

    /**
     * Validate Employee mobile
     *
     * @param String - Mobile of Employee
     * @return boolean
     *
     */
    public boolean isValidEmployeeMobile(String mobile) {
        return validationUtil.isValidString(mobile, Constants.MOBILE_REGEX);
    }

    /**
     * Validate Employee salary
     *
     * @param String - salary of Employee
     * @return boolean
     *
     */
    public boolean isValidEmployeeSalary(String salary) {
        return validationUtil.isValidString(salary, Constants.SALARY_REGEX);
    }

    /**
     * Validate Employee experience
     *
     * @param String - experience of Employee
     * @return boolean
     *
     */
    public boolean isValidEmployeeExperience(String experience) {
        return validationUtil.isValidString(experience, Constants.EXPERIENCE_REGEX);
    }

    /**
     * Validate Employee address
     *
     * @param String - Address of employee
     *
     * @return boolean
     */
    public boolean isValidEmployeeAddress(String address) {
        return validationUtil.isValidString(address, Constants.ADDRESS_REGEX);
    }
    
    /**
     * Create Employee into the database
     *
     * @param Object - Employee
     * @return boolean
     */
    public boolean createEmployee(Employee employee) {
        boolean isCreate = false;

        try {
            isCreate = employeeService.createEmployee(employee);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isCreate;
    }
   
    /**
     * Get all the Employee details 
     *
     * @return list of employee
     */
    public List<Employee> getAllEmployees() {
        List<Employee> employees = null;

        try {
            employees = employeeService.getAllEmployees();   
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return employees;
    } 

    /** 
     * Get employee details using employee id
     *
     * @param int - employee id
     * @return employee object
     */
     public Employee getEmployeeById(int employeeId) {
        Employee employee = null;

        try {
            employee = employeeService.getEmployeeById(employeeId);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return employee;
     }
    
    /**
     * Update the Employee details based on the element
     *
     * @param int - employee id
     * @return boolean
     */
    public boolean updateEmployeeById(Employee employee) {
        boolean isUpdte = false;

        try {
            isUpdte = employeeService.updateEmployeeById(employee);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isUpdte;
    }

    /**
     * Delete some of the Employees based on the id
     *
     * @param int - id of employee
     * @return boolean
     */
    public boolean deleteEmployeeById(int employeeId) { 
        boolean isDelete = false;

        try {
            isDelete = employeeService.deleteEmployeeById(employeeId);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isDelete;
    }

    /**
     * Generate the Employee id
     *
     * @return Employee id
     */
    public int generateEmployeeId() {
        int id = 0;

        try {
            id = employeeService.generateEmployeeId();
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return id;
    }

    /**
     * Update the Candidates to the employee using employee id
     * candidate id
     *
     * @param int - id on employee, candidate
     * @return boolean
     *  
     */
    public boolean updateCandidatesToEmployee(Candidate candidate, Employee employee) {
        boolean isUpdate = false;

        try {
            isUpdate = employeeService.updateCandidatesToEmployee(candidate, employee);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isUpdate;
    }

    /**
     * Get all the employees for each candidate using candidate id
     * candidate id
     *
     * @param int - candidateId
     * @return Object of candidate
     */
    public Candidate getAllEmployeesByCandidate(Candidate candidate) {
        Candidate candidateResult = null;

        try {
            candidateResult = employeeService.getAllEmployeesByCandidate(candidate);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return candidateResult;
    }

    /**
     * Delete the candidates from employee using candidate id
     *
     * @param int - id on employeeId, candidateId
     * @return boolean 
     */ 
    public boolean deleteCandidatesFromEmployee(int candidateId, int employeeId) {
        boolean isDelete = false;

        try {
            isDelete = employeeService.deleteCandidatesFromEmployee(candidateId, employeeId);
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
        }
        return isDelete;
    }
}