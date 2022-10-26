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

import com.i2i.recruitment.connections.DatabaseConnection;
import com.i2i.recruitment.constants.Constants;
import com.i2i.recruitment.dao.CandidateDao;
import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.logger.CustomLogger;
import com.i2i.recruitment.model.Candidate;
import com.i2i.recruitment.model.Employee;

/**
 * This is the Candidate Dao implementation class of application and it having the 
 * implemention of intherface signature methods create, read, update, delete, assign and unassign
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public class CandidateDaoImpl implements CandidateDao {
    private Connection connection = null; 
    private PreparedStatement preparedStatement = null;
    private CustomLogger customLogger = new CustomLogger(CandidateDaoImpl.class);

    @Override
    public boolean createCandidate(Candidate candidate) throws CustomException {
        int row = 0;
        StringBuilder query = new StringBuilder();

        query.append("insert into candidate")
            .append("(name, id, mobile, email, year_of_passed_out,")
            .append("qualification, skill, date_of_birth, experience, salary, address) ")
            .append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
        try {
	    connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, candidate.getCandidateName());
            preparedStatement.setInt(2, candidate.getCandidateId());
            preparedStatement.setString(3, candidate.getCandidateMobile());
            preparedStatement.setString(4, candidate.getCandidateEmail());
            preparedStatement.setInt(5, candidate.getCandidateYearOfPassedOut());
            preparedStatement.setString(6, candidate.getCandidateQualification());
            preparedStatement.setString(7, candidate.getCandidateSkill());
            preparedStatement.setDate(8, new java.sql.Date(
	            (candidate.getCandidateDateOfBirth()).getTime()));
            preparedStatement.setFloat(9, candidate.getCandidateExperience());
	    preparedStatement.setDouble(10, candidate.getCandidateSalary());
            preparedStatement.setString(11, candidate.getCandidateAddress());
            row = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1001);
        } 
        return (row > 0) ? true : false;    
    }
  
    @Override
    public Candidate getCandidateById(int candidateId) throws CustomException {
        Candidate candidate = null;
        String query = "select * from candidate where id = ?";

        try {         
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, candidateId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                candidate = new Candidate();
                candidate.setCandidateName(resultSet.getString("name"));
                candidate.setCandidateId(resultSet.getInt("id"));
                candidate.setCandidateMobile(resultSet.getString("mobile"));
                candidate.setCandidateEmail(resultSet.getString("email"));
                candidate.setCandidateYearOfPassedOut(resultSet.getInt("year_of_passed_out"));
                candidate.setCandidateQualification(resultSet.getString("qualification"));
                candidate.setCandidateSkill(resultSet.getString("skill"));
                candidate.setCandidateDateOfBirth(resultSet.getDate("date_of_birth"));
                candidate.setCandidateExperience(resultSet.getFloat("experience"));
                candidate.setCandidateSalary(resultSet.getDouble("salary"));
                candidate.setCandidateAddress(resultSet.getString("address")); 
                connection.close();   
            }            
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1002);
        } 
        return candidate;
    }     

    @Override
    public List<Candidate> getAllCandidates() throws CustomException {
        String query = "select * from candidate";
        List<Candidate> candidates = new ArrayList<Candidate>();

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Candidate candidate = new Candidate();

                candidate.setCandidateName(resultSet.getString("name"));
                candidate.setCandidateId(resultSet.getInt("id"));
                candidate.setCandidateMobile(resultSet.getString("mobile"));
                candidate.setCandidateEmail(resultSet.getString("email"));
                candidate.setCandidateYearOfPassedOut(resultSet.getInt("year_of_passed_out"));
                candidate.setCandidateQualification(resultSet.getString("qualification"));
                candidate.setCandidateSkill(resultSet.getString("skill"));
                candidate.setCandidateDateOfBirth(resultSet.getDate("date_of_birth"));
                candidate.setCandidateExperience(resultSet.getFloat("experience"));
                candidate.setCandidateSalary(resultSet.getDouble("salary"));
                candidate.setCandidateAddress(resultSet.getString("address"));    
                candidates.add(candidate);
            }
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1001);
        } 
        return candidates;     
    }

    @Override
    public boolean deleteCandidateById(int existingCandidateId) throws CustomException {
	int row = 0;

        try {
            String query = "delete from candidate where id = ?";
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, existingCandidateId);
            row = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1004);
        } 
        return (row > 0) ? true : false;
    }

    @Override
    public boolean updateCandidateById(Candidate candidate) throws CustomException { 
        int row = 0;
        StringBuilder query = new StringBuilder();

        query.append("update candidate set name = ?, ")
                .append("mobile = ?, email = ?, year_of_passed_out = ?, ")
                .append("qualification = ?, skill = ?, date_of_birth = ?, ")
                .append("experience = ?, salary = ?, address = ? ")
                .append("where id = ?");

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, candidate.getCandidateName());
            preparedStatement.setString(2, candidate.getCandidateMobile());
            preparedStatement.setString(3, candidate.getCandidateEmail());
            preparedStatement.setInt(4, candidate.getCandidateYearOfPassedOut());
            preparedStatement.setString(5, candidate.getCandidateQualification());
            preparedStatement.setString(6, candidate.getCandidateSkill());
            preparedStatement.setDate(7, new java.sql.Date((candidate.getCandidateDateOfBirth()).getTime()));
            preparedStatement.setFloat(8, candidate.getCandidateExperience());
            preparedStatement.setDouble(9, candidate.getCandidateSalary());
            preparedStatement.setString(10, candidate.getCandidateAddress());
            preparedStatement.setInt(11, candidate.getCandidateId());
            row = preparedStatement.executeUpdate(); 
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1003);
        } 
        return (row > 0) ? true : false;
    }	
    
    @Override
    public int generateId() throws CustomException {
        int initialValue = 100;
        int lastId = getLastId();

        return (0 == lastId) ? initialValue : lastId + 1;    
    }
    
    /*
     * Get the last candidate id from cadidate table for generating new id for anothe cadidate
     * 
     * @return id if cadidate from db
     */  
    public int getLastId() throws CustomException {
        int lastId = 0;
        String query = "select id from candidate order by id desc limit 1";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement= connection.prepareStatement(query.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                lastId = resultSet.getInt("id");       
	    connection.close();
	} catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1001);
        }
        return lastId;  
    }

    @Override
    public boolean assignEmployeesByCandidateId(Candidate candidate, Employee employee) throws CustomException {
	int row = 0;
        String query = "insert into candidates_to_employee (candidate_id, employee_id) values(?,?)";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, candidate.getCandidateId());
            preparedStatement.setInt(2, employee.getEmployeeId());
            row = preparedStatement.executeUpdate();
       	    connection.close(); 
	} catch (SQLException exception) {
	    throw new CustomException(Constants.ERROR_CODE_1005);
        }
        return (row > 0) ? true : false;
    } 

    @Override
    public Employee getAllCandidatesByEmployeeId(Employee employee) throws CustomException {
        List<Candidate> candidates = new ArrayList<Candidate>(); 
        StringBuilder query = new StringBuilder();

        query.append("select c.* from candidate as c inner join ")
                .append("candidates_to_employee as ce on ce.candidate_id = c.id ")
                .append("where ce.employee_id = ?");

	try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, employee.getEmployeeId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Candidate candidate = new Candidate();

                candidate.setCandidateName(resultSet.getString("name"));
                candidate.setCandidateId(resultSet.getInt("id"));
                candidate.setCandidateMobile(resultSet.getString("mobile"));
                candidate.setCandidateEmail(resultSet.getString("email"));
                candidate.setCandidateQualification(resultSet.getString("qualification"));
                candidate.setCandidateExperience(resultSet.getFloat("experience"));
                candidate.setCandidateSalary(resultSet.getDouble("salary"));
                candidate.setCandidateAddress(resultSet.getString("address"));           
                candidates.add(candidate);
            }
            employee.setEmployeeCandidates(candidates);
            connection.close();
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
	    throw new CustomException(Constants.ERROR_CODE_1002);
        } 
        return employee;  
    }

    @Override
    public boolean unAssignEmployeesByCandidateId(int candidateId, int employeeId) throws CustomException {
	int row = 0;
        StringBuilder query = new StringBuilder();

        query.append("delete from candidates_to_employee where candidate_id = ? and employee_id = ?");

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, candidateId);
            preparedStatement.setInt(2, employeeId);
            row = preparedStatement.executeUpdate();
            connection.close(); 
        } catch (SQLException exception) {
            customLogger.error(exception.getMessage(), exception);
            throw new CustomException(Constants.ERROR_CODE_1003);
        } 
        return (row > 0) ? true : false;   	
    }
}