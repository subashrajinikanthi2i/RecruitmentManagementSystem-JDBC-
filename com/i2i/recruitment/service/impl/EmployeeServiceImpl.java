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
import com.i2i.recruitment.dao.EmployeeDao;
import com.i2i.recruitment.dao.impl.EmployeeDaoImpl;
import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.model.Candidate;
import com.i2i.recruitment.model.Employee;
import com.i2i.recruitment.service.EmployeeService;

/*
 * Employee Service class is to handle all the operations of 
 * cadidate create, read, delete, update 
 * 
 * @version 1.0
 * @author Subashrajinikanth 
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
   
    @Override
    public boolean createEmployee(Employee employee) throws CustomException {
	return employeeDao.createEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() throws CustomException {
        return employeeDao.getAllEmployees();	
    }

    @Override
     public Employee getEmployeeById(int empId) throws CustomException {
         return employeeDao.getEmployeeById(empId);
     }

    @Override
    public boolean deleteEmployeeById(int employeeId) throws CustomException {         
	return employeeDao.deleteEmployeeById(employeeId);
    }

    @Override
    public boolean updateEmployeeById(Employee employee) throws CustomException {
        return employeeDao.updateEmployeeById(employee);
    }

    @Override
    public int generateEmployeeId() throws CustomException {
        return employeeDao.generateEmployeeId();
    }

    @Override
    public boolean updateCandidatesToEmployee(Candidate candidate, Employee employee) throws CustomException {
        return employeeDao.updateCandidatesToEmployee(candidate, employee);
    }

    @Override
    public Candidate getAllEmployeesByCandidate(Candidate candidate) throws CustomException {
	return employeeDao.getAllEmployeesByCandidate(candidate); 
    }

    @Override
    public boolean deleteCandidatesFromEmployee(int candidateId, int employeeId) throws CustomException {
        return employeeDao.deleteCandidatesFromEmployee(candidateId, employeeId);
    }
}