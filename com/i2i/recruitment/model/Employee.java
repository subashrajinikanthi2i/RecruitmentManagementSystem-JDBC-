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
package com.i2i.recruitment.model;

import java.util.Date;
import java.util.List;

import com.i2i.recruitment.model.Candidate;

/**
 * POJO Class of Employee - All deteails are private
 *
 * @version 1.0
 * @author subashrajinikant
 */
public class Employee {
    private String name;
    private int id;
    private String age;
    private String mobile;
    private String email;
    private String qualification;
    private Date dateOfBirth;
    private float experience;
    private double salary;
    private String address;
    private String role;
    private static String companyName = "Ideas2it";
    private List<Candidate> candidates;

    /**
     * we can get and display the details using getter
     */
    public List<Candidate> getEmployeeCandidates() {
        return candidates;
    }
    
    public String getEmployeeName() {
	return name;
    }

    public int getEmployeeId() {
	return id;
    }

    public String getEmployeeQualification() {
	return qualification;
    }

    public String getEmployeeAge() {
	return age;
    }

    public String getEmployeeMobile() {
	return mobile;
    }

    public Date getEmployeeDateOfBirth() {
	return dateOfBirth;
    }

    public String getEmployeeEmail() {
	return email;
    }

    public float getEmployeeExperience() {
	return experience;
    }

    public double getEmployeeSalary() {
	return salary;
    }

    public String getEmployeeAddress() {
	return address;
    }
    public String getEmployeeCompanyName() {
	return companyName;
    }

    public String getEmployeeRole() {
	return role;
    }

    /**
     * we update and insert record using setter
     */
    public void setEmployeeName(String name) {
	this.name = name;
    }

    public void setEmployeeId(int id) {
	this.id = id;
    }

    public void setEmployeeQualification(String qualification) {
	this.qualification = qualification;
    }

    public void setEmployeeAge(String age) {
	this.age = age;
    }

    public void setEmployeeMobile(String mobile) {
	this.mobile = mobile;
    }

    public void setEmployeeDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public void setEmployeeEmail(String email) {
	this.email = email;
    }
    public void setEmployeeExperience(float experience) {
	this.experience = experience;
    }

    public void setEmployeeSalary(double salary) {
	this.salary = salary;
    }

    public void setEmployeeAddress(String address) {
	this.address = address;
    }

    public void setEmployeeRole(String role) {
	this.role = role;
    }
    
    public void setEmployeeCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() { 
        StringBuilder employeeProfile = new StringBuilder();
 
        employeeProfile.append("Employeee Name              : ").append(getEmployeeName())
                       .append("\nEmployeee Id              : ").append(getEmployeeId())
                       .append("\nEmployeee Mobile          : ").append(getEmployeeMobile())
                       .append("\nEmployeee Email           : ").append(getEmployeeEmail())
                       .append("\nEmployeee DateOfBirth     : ").append(getEmployeeDateOfBirth())
                       .append("\nEmployeee Qualification   : ").append(getEmployeeQualification())
                       .append("\nEmployeee Experience      : ").append(getEmployeeExperience())
                       .append("\nEmployeee Salary          : ").append(getEmployeeSalary())
                       .append("\nEmployeee Address         : ").append(getEmployeeAddress())
                       .append("\nEmployeee Candidates List : \n").append(getEmployeeCandidates());
        return employeeProfile.toString();
    }  
}