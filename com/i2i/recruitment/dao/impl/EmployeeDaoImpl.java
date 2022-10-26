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
package com.i2i.recruitment.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.i2i.recruitment.connections.DatabaseConnection;
import com.i2i.recruitment.constants.Constants;
import com.i2i.recruitment.dao.EmployeeDao;
import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.logger.CustomLogger;
import com.i2i.recruitment.model.Employee;
import com.i2i.recruitment.model.Candidate;

/**
 * This is the Data access object class of application,
 * here is processing CRUD operations of the application
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private CustomLogger customLogger = new CustomLogger(EmployeeDaoImpl.class);
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public boolean createEmployee(Employee employee) throws CustomException {
        int row = 0;
        String query= "insert into employee(id, name," 
                .concat("mobile, email, qualification, date_of_birth,")
                .concat("experience, salary, address, role, company_name)")
                .concat(" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try {
            connection = DatabaseConnection.getConnection();
	    preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getEmployeeName());
            preparedStatement.setString(3, employee.getEmployeeMobile());
            preparedStatement.setString(4, employee.getEmployeeEmail());
            preparedStatement.setString(5, employee.getEmployeeQualification());
            preparedStatement.setDate(6, new java.sql.Date(
            	    (employee.getEmployeeDateOfBirth()).getTime()));
            preparedStatement.setFloat(7, employee.getEmployeeExperience());
            preparedStatement.setDouble(8, employee.getEmployeeSalary());
            preparedStatement.setString(9, employee.getEmployeeAddress());
            preparedStatement.setString(10, employee.getEmployeeRole());
            preparedStatement.setString(11, employee.getEmployeeCompanyName());
            row = preparedStatement.executeUpdate(); 
            connection.close();
        } catch (SQLException exception) {
	    customLogger.error(exception.getMessage(), exception);
	    throw new CustomException(Constants.ERROR_CODE_1001);
        }   
        return (row > 0) ? true : false;       
     
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws CustomException {
        Employee employee = null;
        String query = "select * from employee where id = ?";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = getEmployeeDetails(resultSet);      
                connection.close();        
            }                 
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
	    throw new CustomException(Constants.ERROR_CODE_1001);
        } 
        return employee;
    }

    /**
     * Get employee details from database and stored into employee object
     * 
     * @param ResultSet - resultSet of employee details for given query
     * @return Object - employee
     */
    private Employee getEmployeeDetails(ResultSet resultSet) {
        Employee employee = null;

        try {        
            employee = new Employee();
            employee.setEmployeeId(resultSet.getInt("id"));
            employee.setEmployeeName(resultSet.getString("name"));
            employee.setEmployeeMobile(resultSet.getString("mobile"));
            employee.setEmployeeEmail(resultSet.getString("email"));
            employee.setEmployeeQualification(resultSet.getString("qualification"));
            employee.setEmployeeDateOfBirth(resultSet.getDate("date_of_birth"));
            employee.setEmployeeExperience(resultSet.getFloat("experience"));
            employee.setEmployeeSalary(resultSet.getDouble("salary"));
            employee.setEmployeeAddress(resultSet.getString("address"));
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() throws CustomException {
        String query = "select * from employee";
        List<Employee> employees = new ArrayList<Employee>();

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {          
                employees.add(this.getEmployeeDetails(resultSet));
            }
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
	    throw new CustomException(Constants.ERROR_CODE_1002);
        } 
        return employees;  
    }

    @Override
    public Candidate getAllEmployeesByCandidate(Candidate candidate) throws CustomException {
        List<Employee> employees = new ArrayList<Employee>();
	StringBuilder query = new StringBuilder();

        query.append("select e.* from employee as e inner join ")
                .append("candidates_to_employee as ce on ce.employee_id = e.id ")
                .append("where ce.candidate_id = ?");

	try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, candidate.getCandidateId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {       
                employees.add(this.getEmployeeDetails(resultSet));
            }
            candidate.setCandidateEmployees(employees);
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
	    throw new CustomException("Invalid Employee Exception in the employee");
        } 
        return candidate;  
    }

    @Override
    public boolean deleteEmployeeById(int employeeId) throws CustomException {
        int row = 0;

        try {
            String query = "delete from employee where id = ?";
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, employeeId);
            row = preparedStatement.executeUpdate();
            connection.close();
        } catch(SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1004);
        } 
        return (row > 0) ? true : false;
    }

    @Override
    public boolean updateEmployeeById(Employee employee) throws CustomException {
        StringBuilder query = new StringBuilder();

        query.append("update employee set id = ?, name = ?, ")
                .append("mobile = ?, email = ?, qualification = ?, date_of_birth = ?, ")
                .append("experience = ?, salary = ?, address = ?, role = ?, ")
                .append("company_name = ? where id = ?");
        int row = 0;

        try {
            connection = DatabaseConnection.getConnection();
	    preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getEmployeeName());
            preparedStatement.setString(3, employee.getEmployeeMobile());
            preparedStatement.setString(4, employee.getEmployeeEmail());
            preparedStatement.setString(5, employee.getEmployeeQualification());
            preparedStatement.setDate(6, new java.sql.Date(
		    (employee.getEmployeeDateOfBirth()).getTime()));
            preparedStatement.setFloat(7, employee.getEmployeeExperience());
            preparedStatement.setDouble(8, employee.getEmployeeSalary());
            preparedStatement.setString(9, employee.getEmployeeAddress());
            preparedStatement.setString(10, employee.getEmployeeRole());
            preparedStatement.setString(11, employee.getEmployeeCompanyName());
            preparedStatement.setInt(12, employee.getEmployeeId());
            row = preparedStatement.executeUpdate(); 
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
	    throw new CustomException(Constants.ERROR_CODE_1003);
        }        
        return (row > 0) ? true : false;
    }

    @Override
    public boolean deleteCandidatesFromEmployee(int candidateId, int employeeId) throws CustomException {
        String query = "delete from candidates_to_employee where candidate_id = ? and employee_id = ?";
	int row = 0;

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, candidateId);
            preparedStatement.setInt(2, employeeId);
            row = preparedStatement.executeUpdate();
            connection.close(); 
        } catch(SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1004);
        } 
        return (row > 0) ? true : false;            	
    }

    @Override
    public boolean updateCandidatesToEmployee(Candidate candidate, Employee employee) throws CustomException {
        String query = "insert into candidates_to_employee (candidate_id, employee_id) values(?,?)";
        int row = 0;

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, candidate.getCandidateId());
            preparedStatement.setInt(2, employee.getEmployeeId());
            System.out.println(employee.getEmployeeId());
            row = preparedStatement.executeUpdate();
       	    connection.close(); 
	} catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
	    throw new CustomException(Constants.ERROR_CODE_1003);
        }
        return (row > 0) ? true : false;
    }

    @Override
    public int generateEmployeeId() throws CustomException {
        int initialValue = 200;
        int lastId = getLastId();

        return (0 == lastId) ? initialValue + 1 : lastId + 1;      
    }
    
    /**
     * Generate id for Employee
     *  
     * @return id
     */
    public int getLastId() throws CustomException {
        int lastId = 0;

        try {
            String query = "select id from employee order by id desc limit 1";
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next())
                lastId = resultSet.getInt("id");        
	    connection.close();
	} catch(SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1001);
        }
        return lastId;   
    }
}