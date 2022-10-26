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
package com.i2i.recruitment.service.impl;

import java.util.List;

import com.i2i.recruitment.dao.CandidateDao;
import com.i2i.recruitment.dao.impl.CandidateDaoImpl;
import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.model.Candidate;
import com.i2i.recruitment.model.Employee;
import com.i2i.recruitment.service.CandidateService;

/*
 * Candidate Service class is to handle all the operations of cadidate create, read, delete, update 
 * 
 * @version 1.0
 * @author Subashrajinikanth 
 */
public class CandidateServiceImpl implements CandidateService {

    private CandidateDao candidateDao = new CandidateDaoImpl();

    @Override
    public boolean createCandidate(Candidate candidate) throws CustomException {
        return candidateDao.createCandidate(candidate); 
    } 

    @Override
    public Candidate getCandidateById(int candidateId) throws CustomException {
        return candidateDao.getCandidateById(candidateId);
    }

    @Override
    public List<Candidate> getAllCandidates() throws CustomException { 
	return candidateDao.getAllCandidates();
    }

    @Override
    public boolean deleteCandidateById(int existingCandidateId) throws CustomException { 
	return candidateDao.deleteCandidateById(existingCandidateId);
    }

    @Override
    public boolean updateCandidateById(Candidate candidate) throws CustomException { 
        return candidateDao.updateCandidateById(candidate);
    }

    @Override
    public int generateId() throws CustomException {
        return candidateDao.generateId();
    }

    @Override
    public boolean assignEmployeesByCandidateId(Candidate candidate, Employee employee) throws CustomException {
        return candidateDao.assignEmployeesByCandidateId(candidate, employee);
    } 

    @Override
    public Employee getAllCandidatesByEmployeeId(Employee employee) throws CustomException {
        return candidateDao.getAllCandidatesByEmployeeId(employee);
    }

    @Override
    public boolean unAssignEmployeesByCandidateId(int candidateId, int employeeId) throws CustomException {
        return candidateDao.unAssignEmployeesByCandidateId(candidateId, employeeId);
    }
}