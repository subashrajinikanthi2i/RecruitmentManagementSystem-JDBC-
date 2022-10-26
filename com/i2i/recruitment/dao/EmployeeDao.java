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
package com.i2i.recruitment.dao;

import java.util.List;

import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.model.Candidate;
import com.i2i.recruitment.model.Employee;

/**
 * This is the Employee Dao class of application and intract with data base.
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public interface EmployeeDao {

    /**
     * create the Employee details
     *
     * @param Object - Employee 
     * @throws InvalidInsertionException
     * @return boolean
     */
    boolean createEmployee(Employee employee) throws CustomException;

    /**
     * Get Employee details
     *
     * @param int - Employee id
     * @throws Custom exception
     * @return boolean
     */
    Employee getEmployeeById(int empId) throws CustomException;

    /**
     * display the all Employee details
     *
     * @throw Custom exception
     * @return List of employees
     */
    List<Employee> getAllEmployees() throws CustomException;

    /**
     * delete the Employee 
     *
     * @param int - id of employee
     * @throws Custom exception
     * @return boolean
     *
     */
    boolean deleteEmployeeById(int employeeId) throws CustomException;

    /**
     * Update the Employee details
     *
     * @param Object - employee
     * @throws Custom exception
     * @return Employee Object 
     */
    boolean updateEmployeeById(Employee employee) throws CustomException;

    /**
     * Generate int - Employee id
     *
     * @throws Custom exception
     * @return Employee id 
     */
    int generateEmployeeId() throws CustomException;

    /**
     * Update the Candidates to the employees using employee id
     * candidate id
     *
     * @param Object - employee, candidate
     * @throws Custom exception
     * @return boolean 
     */
    boolean updateCandidatesToEmployee(Candidate candidate, Employee employee) throws CustomException;

    /**
     * View all the employees for each candidate using candidate id
     * candidate id
     *
     * @param Object - candidate
     * @throw Custom exception
     * @return Object of candidate
     */
    Candidate getAllEmployeesByCandidate(Candidate candidate) throws CustomException;

    /**
     * To remove each candidates from employee using candidate id
     * candidate id
     *
     * @param int - id on employeeId, candidateId
     * @throws Custom exception
     * @return boolean 
     */ 
    public boolean deleteCandidatesFromEmployee(int candidateId, int employeeId) throws CustomException;    
}