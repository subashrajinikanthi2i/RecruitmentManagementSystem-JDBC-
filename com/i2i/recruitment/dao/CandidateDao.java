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
 * This is the Candidate Dao interface of application and its having abstract methods
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public interface CandidateDao {

    /**
     * Create the Candidate 
     * 
     * @param Object - Candidate 
     * @throws Custom exception
     * @return boolean
     */
    boolean createCandidate(Candidate candidate) throws CustomException;

    /** 
     * Create candidate and stored into database candidate table
     *
     * @param int - candidate id
     * @throws Custom exception
     * @return Object - candidate object
     *
     */
    Candidate getCandidateById(int candidateId) throws CustomException;

    /**
     * To display the all Candidate details
     *
     * @throw Custom exception
     * @return list of candidates
     */
    List<Candidate> getAllCandidates() throws CustomException;

    /**
     * To delete the candidate using candidate id
     *
     * @param int - candidate id
     * @throws Custom exception
     * @return boolean
     */
    boolean deleteCandidateById(int existingCandidateId) throws CustomException;

    /**
     * Update the Candidate details using candiate id
     *
     * @param Object - candidate
     * @throws Custom exception
     * @return boolean
     */
    boolean updateCandidateById(Candidate candidate) throws CustomException;

    /**
     * Generate a candidate id
     *
     * @throws Custom exception
     * @return id
     */
    int generateId() throws CustomException;

    /**
     * Assign employees to the candidate using candidate id 
     * 
     * @param Object - candidate 
     * @param Object - employee
     * @throws Custom exception
     * @return boolean
     */
    boolean assignEmployeesByCandidateId(Candidate candidate, Employee employee) throws CustomException;

    /**
     * View all the candidates list who interviewed by employee 
     *
     * @param Object - Employee 
     * @throw Custom exception
     * @return Employee Object
     */
    Employee getAllCandidatesByEmployeeId(Employee employee) throws CustomException;
    
    /**
     * To remove employees from candidate using candidate and employee Id
     * 
     * @param int - id of candidate and employee
     * @throw Custom exception
     * @return boolean
     */
    boolean unAssignEmployeesByCandidateId(int candidateId, int employeeId) throws CustomException;
}