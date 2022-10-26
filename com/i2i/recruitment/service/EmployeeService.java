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
package com.i2i.recruitment.service;

import java.util.List;

import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.model.Candidate;
import com.i2i.recruitment.model.Employee;

/*
 * Employee Service class is to handle all the operations of 
 * employee create, read, delete, update 
 * 
 * @version 1.0
 * @author Subashrajinikanth 
 */
public interface EmployeeService {

    /** 
     * Create employee and stored in to database
     *
     * @param Object - employee Object
     * @throws Custom exception
     * @return boolean
     */
    public boolean createEmployee(Employee employee) throws CustomException;

    /** 
     * Get all the employees detail from database and stored into list and return the list
     *
     * @throw Custom exception
     * @return List of employees
     */
    public List<Employee> getAllEmployees() throws CustomException;

    /** 
     * Get an particular employee detail by the employee id
     *
     * @param int - Employee id
     * @throws Custom exception
     * @return Object of the employee
     */
     public Employee getEmployeeById(int empId) throws CustomException;

    /**
     * Delete a partcular employee using existing employee id 
     *
     * @param int - employee id
     * @throws Custom exception
     * @return boolean
     */
    public boolean deleteEmployeeById(int employeeId) throws CustomException;

    /**
     * Update a particular Employee details using employee id
     *
     * @param Object - employee
     * @throws Custom exception
     * @return employee object
     */
    public boolean updateEmployeeById(Employee employee) throws CustomException;

    /**
     * Generate an unique employee id 
     *
     * @throws Custom exception
     * @return Employee id
     */
    public int generateEmployeeId() throws CustomException;

    /**
     * Update the Candidates to the employee using employee id
     * candidate id
     *
     * @param Object - employee
     * @param Object - candidate
     * @throws Custom exception
     * @return boolean 
     */
    public boolean updateCandidatesToEmployee(Candidate candidate, Employee employee) throws CustomException;

    /**
     * Get all the employees for each candidate using candidate id
     * candidate id
     *
     * @param Object - candidate
     * @throw Custom exception
     * @return Object of candidate
     */
    public Candidate getAllEmployeesByCandidate(Candidate candidate) throws CustomException;

    /**
     * Delete the candidates from employee using candidate id
     * candidate id
     *
     * @param int - employee id
     * @param int - candidate id
     * @throws Custom exception
     * @return boolean 
     */
    public boolean deleteCandidatesFromEmployee(int candidateId, int employeeId) throws CustomException;
}