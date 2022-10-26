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
 * Candidate Service interface of application and its having abstract methods
 *
 * @version 1.0
 * @author Subashrajinikanth 
 */
public interface CandidateService {
  
    /** 
     * Create candidate and stored in to database
     *
     * @param Object - candidate Object
     * @throws Custom exception
     * @return boolean
     */
    public boolean createCandidate(Candidate candidate) throws CustomException;

    /** 
     * Get a candidate details using candidate id
     *
     * @param int - candidate Object
     * @throws Custom exception
     * @return Object
     */
    public Candidate getCandidateById(int candidateId) throws CustomException;

    /** 
     * Get all the candidate details
     *
     * @throw Custom exception
     *
     * @return cadidate object
     */
    public List<Candidate> getAllCandidates() throws CustomException;

    /**
     * Delete a Candidate based on the id
     *
     * @param int - candidate id
     * @throws Custom exception
     * @return boolean
     */
    public boolean deleteCandidateById(int existingCandidateId) throws CustomException;

    /**
     * update te Candidate details based on the element
     *
     * @param Object - candidate
     * @throw Custom exception
     * @return Candidate object
     */
    public boolean updateCandidateById(Candidate candidate) throws CustomException;

    /**
     * Assign employees to the candidate using candidate id 
     * 
     * @param Object - candidate
     * @param Object - employee
     * @throws Custom exception
     * @return boolean
     */
    public boolean assignEmployeesByCandidateId(Candidate candidate, Employee employee) throws CustomException;

    /**
     * To display all the candidates using employee id 
     * 
     * @param Object - employee
     * @throw Custom exception
     * @return Employee Object
     */
    public Employee getAllCandidatesByEmployeeId(Employee employee) throws CustomException;

    /**
     * Unassign employees from candidate using candidate and employee Id
     * 
     * @param int - candidate id
     * @param int - employee id
     * @throws Custom exception
     * @return boolean
     */
    public boolean unAssignEmployeesByCandidateId(int candidateId, int employeeId) throws CustomException;

    /**
     * Generate the candidate id for uniquely identified the particular candidate
     *
     * @throws Custom exception
     * @return id
     */
    public int generateId() throws CustomException;
}