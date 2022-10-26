/*
 * Subash rajinikanth M
 *
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * This is the simple Recruitment System console application, add the
 * employee and candidate details to the MySQL Database using JDBC.
 * Assign candidates to the employee by the recruitment team
 * for the interview process.
 *
 * Unassign the candidates from employees by the recruitment team for the not
 * availability of an employee based on the situation.
 * We can view the employee details with interviewed candidates list as well.
 * 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.i2i.recruitment.constants.Constants;
import com.i2i.recruitment.constants.Skill;
import com.i2i.recruitment.constants.Qualification;
import com.i2i.recruitment.controller.CandidateController;
import com.i2i.recruitment.controller.EmployeeController;
import com.i2i.recruitment.exception.CustomException;
import com.i2i.recruitment.logger.CustomLogger;
import com.i2i.recruitment.model.Candidate;
import com.i2i.recruitment.model.Employee;

/**
 * This is the simple Recruitment System console application, 
 * and it implements CRUD Operations using mysql and JDBC
 *
 * @version 1.0
 * @author subashrajinikanth M
 */
public class RecruitmentManagementSystem {

    private CandidateController candidateController = new CandidateController();
    private CustomLogger customLogger = new CustomLogger(RecruitmentManagementSystem.class);
    private EmployeeController employeeController = new EmployeeController();
    private Scanner input = new Scanner(System.in);
    private int userChoice;

    public static void main(String args[]) {
        RecruitmentManagementSystem recruitmentManagementSystem = new RecruitmentManagementSystem();
        recruitmentManagementSystem.viewWelcomeMenu(); 
    }

    /**
     * Show the recruitment welcome menu and for choose the option of 
     * employee, candidate, recruitment team
     */
    private void viewWelcomeMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("\n ===================================")
                .append("\n|    *** Recruitement System ***    |")
	        .append("\n|===================================|")
	        .append("\n|       1.Employee                  |")
                .append("\n|       2.Candidate                 |")
                .append("\n|       3.RecruitmentTeam           |")
                .append("\n ===================================\n");

        do {
            System.out.println(menu);
            this.getUserChoice();

	    switch (userChoice) {		
	        case 1:
		    this.viewEmployeeMenu();
	     	    break;
	        case 2:
		    this.viewCandidateMenu();
	    	    break;
	        case 3:
	    	    this.viewRecruitmentTeamMenu();
	     	    break;
	        default:
	    	    customLogger.info("Please Enter Valid choice.. 1 or 2 or 3");  
                    break;                
	    }
        } while (this.getUserConformation());               
    }    

    /**
     * Show recruitment Team Menu for assigning and 
     * unassigning the candidates and employees
     * 
     */
    private void viewRecruitmentTeamMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("\n ======================================= ")
                .append("\n|         Recruitment Team Menu         |")
                .append("\n|=======================================|")
                .append("\n|    1.Assign candidates to employee    |")
                .append("\n|    2.Delete candidates from employee  |")
	        .append("\n|    3.View All employees for candidate |")
                .append("\n|    4.Assign employees to candidate    |")
	        .append("\n|    5.Delete employees from candidate  |")
	        .append("\n|    6.View All candidates for employee |")
	        .append("\n ======================================= ");

        do {
            System.out.println(menu);
            this.getUserChoice();

            switch (userChoice) {	 
	        case 1:
	            this.assignCandidatesByEmployeeId();
	            break;
	        case 2:
                    this.unAssignCandidatesById();
	            break;
                case 3:
                    this.viewEmployeesByCandidateId();	    
	            break;
	        case 4:
	            this.assignEmployeesByCandidateId();
	            break;
	        case 5:
                    this.unAssignEmployeesByCandidateId();
	            break;
                case 6:
                    this.viewAllCandidatesByEmployeeId();	    
	            break;
	        default:
	            System.out.println(Constants.INVALID_OPTION);
	            break;
            }
        } while (this.getUserConformation()); 
    }

    /**
     * Assign employees to the candidate using candidate id
     * 
     */
    private void assignEmployeesByCandidateId() {
        System.out.println("Please Enter Candidate Id : ");
        int candidateId = getId();
        Candidate candidate = candidateController.getCandidateById(candidateId);
        
        do {
            if (candidate != null) {                 
                System.out.println("Please Enetr Employee Id : ");
                int employeeId = this.getId();
	        Employee employee = employeeController.getEmployeeById(employeeId);

                if (employee != null) { 
                    if (candidateController.assignEmployeesByCandidateId(candidate, employee)) {
                        customLogger.info("Employee assigned to candidate");
                    } else {
                        customLogger.warn("Employee not assigned to canidate");
                    }
                } else {
                    customLogger.warn("Employee not found in database");
                }
                System.out.println("\nDo you want to Continue... press-1, any key to exit)");
                this.getUserChoice();
            } else {
                customLogger.warn("No candidate found in database");
            } 
        } while (1 == userChoice);
    }

    /**
     * Unassign employees from candidate using employee id and candidate id
     * 
     */
    private void unAssignEmployeesByCandidateId() {
        System.out.println("Please Enter Canidate Id : ");
        int candidateId = getId();
        Candidate candidate = candidateController.getCandidateById(candidateId);

        if (candidate != null) {  
            do {
                System.out.println("Please Enter Employee Id : ");
                int employeeId = this.getId();
                Employee employee = employeeController.getEmployeeById(employeeId);

                if (employee != null) { 
                    if (candidateController.unAssignEmployeesByCandidateId(candidateId, employeeId)) {
                        System.out.println("Employee successfully deleted from Candidate");
                    } else {
                        customLogger.info("No Employee Deleted from candidate");
                    }
                } else {
                    customLogger.warn("Employee not found.. Please check...");
                }
                 System.out.println("\nDo you want to Continue... press-1, any key to exit)");
                this.getUserChoice();
            } while (1 == userChoice);
        } else {
            customLogger.info("No Candidate  found...Please check...");
        }   
    }

    /**
     * View all the candidates, who interviewed by the particular employee
     * 
     */
    private void viewAllCandidatesByEmployeeId() {
        int i = 1;
        StringBuilder candidatesForEmployee = new StringBuilder();

        System.out.println("Please enter Existing Employee id : ");
        int employeeId = getId();
        Employee employee = employeeController.getEmployeeById(employeeId);

        if (employee != null) {
            employee = candidateController.getAllCandidatesByEmployeeId(employee);  

            if (employee.getEmployeeCandidates().isEmpty()) {
	        customLogger.info("Employee profile candidate List is Empty......");
            } else {
                System.out.println("\n *** Display Employee Profile with interviewed candidates list ***");
                System.out.println("============================================");
                System.out.println(employee);  

                for (Candidate candidate : employee.getEmployeeCandidates()) {
                    candidatesForEmployee.append("\t\t *** Candidate Profile - ")
                            .append(i).append(" ***")
                            .append("\n==========================================\n")
                            .append(candidate);	 
                    i++;
                } 
                System.out.println(candidatesForEmployee);
           }
        } else {
            customLogger.warn("No employee found...");
        } 
    }

    /**
     * Assign candidates to the employee for the interview,
     * who are all comes under eligible catagory - year of passed out and skill 
     * 
     */
    private void assignCandidatesByEmployeeId() {
        System.out.println("Please Enter Employee Id : ");
        int employeeId = getId();
        Employee employee = employeeController.getEmployeeById(employeeId);

        if (employee != null) {  
            do {
                System.out.println("Please Enetr Candidate Id : ");
                int candidateId = getId();
	        Candidate candidate = candidateController.getCandidateById(candidateId);

                if (candidate != null) { 
                    if (this.shortlistCandidatesBasedOnCriteria(candidate)) {
                        if (employeeController.updateCandidatesToEmployee(candidate, employee)) {
                            System.out.println("Candidate Succssfully assigned to the employee");
                        } else {
                            customLogger.info("Candidate not assigend to employee");
                        }
                    } else { 
                        customLogger.warn("Candidate is not eligible for assigning ");
                    }
                } else {
                    customLogger.warn("Candidate not found..");
                }
                System.out.println("\nDo you want to Continue... press-1, any key to exit)");
                this.getUserChoice();
            } while (1 == userChoice);
        } else {
            customLogger.warn("No employee found...");
        } 
    }

    /**
     * Unassign candidates from the employee using candidate id
     */
    private void unAssignCandidatesById(){
        System.out.println("Please Enter Employee Id : ");
        int employeeId = getId();
        Employee employee = employeeController.getEmployeeById(employeeId);

        if (employee != null) {  
            do {
                System.out.println("Please Enetr Candidate Id : ");
                int candidateId = getId();
	        Candidate candidate = candidateController.getCandidateById(candidateId);

                if (candidate != null) { 
                    if (employeeController.deleteCandidatesFromEmployee(candidateId, employeeId)) {
                        customLogger.info("Candidate successfully deleted from employee");
                    } else {
                        customLogger.info("Candidate not deleted from employee");
                    }
                } else {
                    customLogger.warn("Candidate not found..");
                }
                System.out.println("\nDo you want to Continue... press-1, any key to exit)");
                this.getUserChoice();
            } while (1 == userChoice);
        } else {
            customLogger.warn("No employee found...");
        }           
    }

    /**
     * View all employee details for the candidate, 
     * if you give the candidate id and it will show all the employees of candidate​
     * 
     */
    private void viewEmployeesByCandidateId() {
        int i = 1;
        StringBuilder employeProfile = new StringBuilder();

        System.out.println("Please enter Existing Candidate id : ");
        int candidateId = getId();
        Candidate candidate = candidateController.getCandidateById(candidateId);
    
        if (candidate != null) {
            System.out.println(candidate);  
            candidate = employeeController.getAllEmployeesByCandidate(candidate);    
          
	    if (candidate.getCandidateEmployees().isEmpty()) {
	        customLogger.info("No Employee found for the candidate -  List is Empty......");
            } else {
                System.out.println("\n *** Display Employee Details using for candidate : \n");

                for (Employee employee : candidate.getCandidateEmployees()) {
	            employeProfile.append("\t\t *** Employee Profile - ")
                            .append(i).append(" ***")
                            .append("\n============================================\n")
                            .append(employee);
                    i++;
                }    
                System.out.println(employeProfile);
            }
        } else {
            customLogger.warn("No candidate found...");
        } 
    }

    /**
     * Show the candidate operation menu, we can create, read, update, and delete,
     * and getting choice from user and route to appropriate method
     *
     */
    private void viewCandidateMenu() {
        StringBuilder menu = new StringBuilder();

	menu.append("\n =============================== ")
                .append("\n|        Canidate Menu          |")
                .append("\n|===============================|")
                .append("\n|    1.Create Candidate         |")
                .append("\n|    2.View Candidate           |")
	        .append("\n|    3.Update Candidate         |")
                .append("\n|    4.Delete Candidate         |")
                .append("\n|    5.viewAllCandidates        |")
                .append("\n =============================== ");

	do {
            System.out.println(menu);
            getUserChoice();

            switch (userChoice) {	 
	        case 1:
	            this.getCandidateDetailsFromUser();
	            break;
	        case 2:
                    this.viewtCandidateById();
	            break;
	        case 3:
                    this.updateCandidateById();
	            break;
                case 4:
                    this.deleteCandidateById();	    
	            break;
                case 5:            
                    this.viewAllCandidates();
                    break;
	        default:
	            System.out.println(Constants.INVALID_OPTION);
	            break;
            }
	} while (this.getUserConformation());	
    }

    /**    
     * Get the candidate details and assign to the candidate object using setter
     *
     */
    private void getCandidateDetailsFromUser() {
	String name = this.getCandidateName();
	int id = candidateController.generateId();
	String mobileNumber = this.getCandidateMobileNumber();
	String email = this.getCandidateEmail();
	Date dateOfBirth = this.getCandidateDateOfBirth();
	String qualification = this.getCandidateQualification();
	String skill = this.getCandidateSkill();
 	String address = this.getCandidateAddress();
	int yearOfPassedOut = this.getCandidateYearOfPassedOut();
	float experience = this.getCandidateExperience();
	double salary = this.getCandidateSalary(); 
	Candidate candidate = new Candidate();

	candidate.setCandidateName(name);
	candidate.setCandidateId(id);
	candidate.setCandidateYearOfPassedOut(yearOfPassedOut);
        candidate.setCandidateMobile(mobileNumber);
        candidate.setCandidateEmail(email);
        candidate.setCandidateDateOfBirth(dateOfBirth);        
	candidate.setCandidateQualification(qualification);
	candidate.setCandidateSkill(skill);
	candidate.setCandidateExperience(experience);
	candidate.setCandidateSalary(salary);
	candidate.setCandidateAddress(address);	
        this.createCandidate(candidate);
    }

    /**
     * Create the candidate and store in database and get conformation
     *
     * @param Object - Candidate 
     * 
     */
    private void createCandidate(Candidate candidate) {
    
        if (candidateController.createCandidate(candidate)) {
            customLogger.info("Candidate successfully created..");
        } else {
            customLogger.info("Candidate not created..! Need too fill out all mandatry fields...");
        }
    }

    /**
     * Short list the candidate based on the criteira : year of passed out, skill
     *
     * @param Object - candidate object
     * @return boolean
     *
     */    
    public boolean shortlistCandidatesBasedOnCriteria(Candidate candidate) {
       
        if (candidate.getCandidateYearOfPassedOut() > 2020 
	        || (candidate.getCandidateSkill().equals("java")) 
		|| (candidate.getCandidateSkill().equals("python"))) {
            return true;
        }
        return false;
    }

    /**
     * Get the candidate name from the user and send it to validation
     * and return the valid name
     *
     * @return Name of the Candidate
     *
     */
    private String getCandidateName() {
        String name;
        boolean isValidName;

	do {
	    System.out.println("Enter Candidate name :");
    	    name = input.next();
            isValidName = candidateController.isValidCandidateName(name);

            if (!isValidName) 
                System.out.println("----Please enter valid input...");
        } while (!isValidName); 
        return name;
    }

    /**
     * Get the id from user 
     *
     * @return ID
     *
     **/
    private int getId() {
        return input.nextInt();
    }

    /**
     * Get the candidate mobile number number from user and send to the validation
     * and return the valid mobile number number
     *
     * @return mobile number number number of the Candidate
     *
     */
    private String getCandidateMobileNumber() {
        String mobileNumber;
        boolean isValidMobile; 

        do {
	    System.out.println("Enter Candidate Mobile :");
    	    mobileNumber = input.next();
            isValidMobile = candidateController.isValidCandidateMobile(mobileNumber);

            if (!isValidMobile) 
                customLogger.warn("----Please enter valid input...");            
        } while (!isValidMobile);
        return mobileNumber;
    }

    /**
     * Get the candidate email from user and send to the validation
     * and return the valid email
     *
     * @return email of the Candidate
     */
    private String getCandidateEmail() {
        String email;
        boolean isValidEmail;

        do {
	    System.out.println("Enter Candidate Email :");
    	    email = input.next();
            isValidEmail = candidateController.isValidCandidateEmail(email);

            if (isValidEmail) {
                if (this.verifyEmail(email)) {
                    isValidEmail = true;
                } else {
                    isValidEmail = false;
                }               
            } else {
                System.out.println("----Please enter valid input...");  
                isValidEmail = false;            
            }    
        } while (!isValidEmail);
        return email;
    }

    /**
     * Verify the candidate email and  handle the invalid email domain exception 
     * by own custom exception handler
     *
     * @param String - Email id
     * @return boolean
     *
     */  
    private boolean verifyEmail(String email) {
        String splittedEmail[] = email.split("@");  
        boolean isValid = true;
      
        try {
            if (!(splittedEmail[1].equals("gmail.com") || splittedEmail[1].equals("yahoo.com"))) {
                throw new CustomException("Invalid Domain Name for Email....");
	    } 
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
            isValid = false;
        }
        return isValid;
    }

    /**
     * Get the candidate dateOfBirth number from user and send to the validation
     * and return the valid dateOfBirth
     *
     * @return dateOfBirth of the Candidate
     *
     */
    private Date getCandidateDateOfBirth() {
        String dateOfBirth;
        boolean isValidDateOfBirth;

        do {
	    System.out.println("Enter Candidate DateOfBirth :");
    	    dateOfBirth = input.next();
            isValidDateOfBirth = candidateController.isValidCandidateDateOfBirth(dateOfBirth);

            if (!isValidDateOfBirth) 
                System.out.println("----Please enter valid input...");            
        } while (!isValidDateOfBirth);
        return this.verifyCandidateDate(dateOfBirth);
    }

    /**
     * Here is the method to handling exceptions are custom and predefined exception.
     * parse the date from string to date, if get any exception occured it will handled and 
     * return to valid date of birth otherwise again get input from candidate for DOB
     * 
     * @param String - dateOfBirth
     * @return DateOfBirth of Candidate
     *
     */
    private Date verifyCandidateDate(String dateOfBirth) {
        Date date = new Date();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            /* Here checking currect calender date */
            sdf.setLenient(false); 
            date = sdf.parse(dateOfBirth);
            String splitedDate[] = dateOfBirth.split("/");

            if (2020 < Integer.parseInt(splitedDate[2])) {
                throw new CustomException("Birth's year should not greater than current year");
            }          
        } catch (ParseException exception) {
	    customLogger.error(exception.getMessage());
            this.getCandidateDateOfBirth(); 
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
            this.getCandidateDateOfBirth();
        } catch (Exception exception) {
            customLogger.error(exception.getMessage());
            this.getCandidateDateOfBirth();
        }
        return date;
    }

    /**
     * Get the candidate qualification number from user and send to the validation
     * and return the valid dateOfBirth
     *
     * @return qualification of the candidate
     *
     */
    private String getCandidateQualification() {
        String qualification;
        boolean isValidQualification = false;
	System.out.println("Please enter your Qualification / degeree :");

        for (Qualification listOfQualification : Qualification.values()) {
	    System.out.println(listOfQualification);
	}

        do {
	    System.out.println("Enter your Qualification :");
 	    qualification = input.next().toUpperCase();    
 
            for (Qualification qualifications : Qualification.values()) {
                if (qualifications.name().equals(qualification)) {
		    isValidQualification = true;
		}
            }       
        } while (!isValidQualification);
        return qualification;
    }

    /**
     * Get the candidate skill number from user and send to the validation
     * and return the valid skill
     *
     * @return skill of the candidate
     *
     */
    private String getCandidateSkill() {
        boolean isValidSkillSet = false;
        String skill;
	System.out.println("Please enter any one (Primary) skill form this skill set :");

        for (Skill skillSet : Skill.values()) {
	    System.out.println(skillSet);
	}

        do {
	    System.out.println("Enter your Skill :");
 	    skill = input.next().toUpperCase();

            for (Skill skillSet : Skill.values()) {
	        if (skillSet.name().equals(skill)){
		    isValidSkillSet = true;
                }
            }
	} while (!isValidSkillSet);
        input.nextLine();
        return skill;
    }

    /**
     * Get the candidate address number from user and send to the validation
     * and return the valid address
     *
     * @return address of the candidate
     *
     */
    private String getCandidateAddress() {
        String address;
        boolean isValidAddress;

        do {
	    System.out.println("Enter Candidate Address: ");
	    address = input.nextLine();
            isValidAddress = candidateController.isValidCandidateAddress(address);

	    if (!isValidAddress) 
                System.out.println("----Please enter valid input...");
        } while (!isValidAddress); 
        return address;
    }

    /**
     * Get the candidate year of passed out number from user and send to the validation
     * and return the valid year of passed out
     *
     * @return yearOfPassedOut of the candidate
     *
     */
    private int getCandidateYearOfPassedOut() {
	int yearOfPassedOut = 0;
        boolean isValidYearOfPassedOut;

        do {
	    System.out.println("Enter Candidate Year Of Passed Out : ");
	    yearOfPassedOut = input.nextInt();
            isValidYearOfPassedOut = candidateController.isValidCandidateYearOfPassedOut(Integer.toString(yearOfPassedOut));
	    System.out.println(isValidYearOfPassedOut);

            if (!isValidYearOfPassedOut) 
                System.out.println("----Please enter valid input...");
        } while (!isValidYearOfPassedOut);
        return yearOfPassedOut;
    }

    /**
     * Get the candidate experience from user and send to the validation
     * and return the valid experience
     *
     * @return experience of the candidate
     *
     */
    private float getCandidateExperience() {
        float experience = 0;
        boolean isValidExperience; 

        do {
	    System.out.println("Enter Candidate Experience : ");
	    experience = input.nextFloat();
            isValidExperience = candidateController.isValidCandidateExperience(Float.toString(experience));

            if (!isValidExperience) 
                System.out.println("----Please enter valid input...");
        } while (!isValidExperience);
        return experience;
    }

    /**
     * Get the candidate salary from user and send to the validation
     * and return the valid salary
     *
     * @return salary of the candidate
     *
     */
    private double getCandidateSalary() {
        double salary = 0;
        boolean isValidSalary;

        do {
            System.out.println("Enter Candidate Salary : ");
            salary = input.nextDouble();
            isValidSalary = candidateController.isValidCandidateSalary(Double.toString(salary));

            if (!isValidSalary) 
                System.out.println("----Please enter valid input...");
        } while (!isValidSalary);
	input.nextLine();
        return salary;
    }

    /**
     * View the candidate details by candidate id, 
     * search in database and display.
     *
     */
    private void viewtCandidateById() {
        System.out.println("Please enter Existing Candidate id : ");
        int candidateId = getId();
        Candidate candidate = candidateController.getCandidateById(candidateId);

        if (candidate != null) {
            System.out.println(candidate);          
        } else {
            customLogger.warn("No candidate found...");
        }         
    }

    /**
     * Delete a candidate based on id, get cadidate id from user and,
     * search the ID in to the database, if it is found delete the candidate
     * otherwise provide not found message
     *
     */
    private void deleteCandidateById() {
        System.out.println("Deleting Candidate operation....");
        System.out.println("Please enter the existing Candidate id : ");
        int candidateId = getId();
        Candidate candidate = candidateController.getCandidateById(candidateId);

        if (candidate != null) {
            if (candidateController.deleteCandidateById(candidateId)) {
                customLogger.info("Candidate is successfully deleted... ");
            } else {
                customLogger.info("No candidate deleted...");
            }
        } else {
            customLogger.warn("Candidate not found");
        }
    }

    /**
     * Update Candidate based on id, get the cadidate and update the prefered details
     * 
     */
    private void updateCandidateById() {
        System.out.println("Please enter Candidate id : ");
        int candidateId = getId();
        Candidate candidate = candidateController.getCandidateById(candidateId);

        if (candidate != null) {
            System.out.println(candidate);   
	    this.updateCandidateMenu(candidate);       
        } else {
            customLogger.warn("No candidate found...");
        }           
    }

    /**
     * Update the candidate detalis as per candidate preference by the candidate id  
     *
     * @param Object - Candidate object
     */
    private void updateCandidate(Candidate candidate) {

        if (candidateController.updateCandidateById(candidate)) {
             customLogger.info("Candidate is successfully updated..");
        } else {
             customLogger.info("Candidate is Not updated..");      
        }
    }

    /**
     * Update menu for candidate choose any option to change their details usidn
     *
     * @param Object - candidate
     *
     */
    private void updateCandidateMenu(Candidate candidate) {	
        StringBuilder menu = new StringBuilder();

        menu.append("\n ================================")
                .append("\n|          Candidate Menu        |")
                .append("\n|================================|")
                .append("\n|    1.Candidate name            |")
                .append("\n|    2.Candidate Email           |")
                .append("\n|    3.Candidate DateOfBirth     |")
                .append("\n|    4.Candidate Qualification   |")
                .append("\n|    5.Candidate Experience      |")
                .append("\n|    6.Candidate Salary          |")
                .append("\n|    7.Candidate Address         |")
                .append("\n ================================ ");

        do {
	    System.out.println(menu);
	    System.out.println("Which field do you want to update : ");
            this.getUserChoice();

            switch (userChoice) {	
 	        case 1:
                    candidate.setCandidateName(getCandidateName());
	            break; 	    
	        case 2:
                    candidate.setCandidateEmail(getCandidateEmail());
	            break;  	    
	        case 3:
                    candidate.setCandidateDateOfBirth(getCandidateDateOfBirth());
	            break; 	    
	        case 4:
	            candidate.setCandidateQualification(getCandidateQualification());
	            break; 	    
	        case 5:
	            candidate.setCandidateExperience(getCandidateExperience());
	            break; 	    
	        case 6:
	            candidate.setCandidateSalary(getCandidateSalary());
	            break; 	    
	        case 7:
	            candidate.setCandidateAddress(getCandidateAddress());
	            break; 	
                default:
                    System.out.println("wrong option selected...");
                    break;   
            }
        } while (this.getUserConformation());
        this.updateCandidate(candidate);
    }

    /**
     * View all the cadidate details form the database
     *
     */
    private void viewAllCandidates() {
        int i = 1;
        StringBuilder candateProfile = new StringBuilder();
	List<Candidate> candidates = candidateController.getAllCandidates();

        for (Candidate candidate : candidates) {
            candateProfile.append("\n\t\t *** Candidate Profile - " )
                    .append(i).append(" ***")
                    .append("\n==========================================\n")
                    .append(candidate);
	    i++;
        }
        System.out.println(candateProfile);
    }

    /**
     * View the employee operation menu create, read, update, and delete
     * choose the any option to proced 
     * 
     */
    private void viewEmployeeMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("\n ============================ ")
                .append("\n|       Employee Mene        |")
                .append("\n|============================|")
                .append("\n|    1.Create Employee       |")
                .append("\n|    2.View Employee         |")
                .append("\n|    3.Update Employee       |")
                .append("\n|    4.Delete Employee       |")
                .append("\n|    5.View All Employees    |")  
                .append("\n ============================ ");

        do {
	    System.out.println(menu);
            this.getUserChoice();
        
	    switch (userChoice) {
	        case 1:
	            this.getEmployee();
	            break;
                case 2:
                    this.getEmployeeById();
	            break;  
                case 3:
                    this.updateEmployeeById();
	            break;
	        case 4:
                    this.deleteEmployeeById();
	            break;
                case 5:
                    this.viewAllEmployees();	
                    break;
	        default:
	            System.out.println("You Entered wrong option.");
	            break;
            }
	} while (this.getUserConformation());
    }

    /**
     * Get all the Employee details form the employee list, and 
     * view all employee detail
     *
     */
    private void viewAllEmployees() {
        int i = 1;
        StringBuilder employeeProfile = new StringBuilder();
        List<Employee> employees = employeeController.getAllEmployees();

	if (employees.isEmpty()) {
	    customLogger.warn("No Employee found -  List is Empty......");
        } else {
            System.out.println("\n *** Display Employee Details using toString method : \n");

            for (Employee employee : employees) {              
                employeeProfile.append(" \t\t *** Employee Profile - ")
                        .append(i).append(" ***")
	                .append("\n============================================\n")
                        .append(employee);
                i++;
            }    
            System.out.println(employeeProfile);
        }
    }

    /**
     * Get employee id and search form the employee list, if any employee found 
     * display the the employee's invidual detais
     *
     */
    private void getEmployeeById() {
        System.out.println("Please enter Employee id : ");
        int employeeId = Integer.parseInt(input.next());
        Employee employee = employeeController.getEmployeeById(employeeId);

        if (employee != null) {
            System.out.println(employee);          
        } else {
            customLogger.info("No employee found...");
        } 
    }

    /**
     * Delete an employee by id, get the employee id and search from the database
     * if it is found delete and give conformation message otherwise give not found message.
     *
     */
    private void deleteEmployeeById() {
	System.out.println("Please enter the existing employee id : ");
	int employeeId = getId();
        Employee employee = employeeController.getEmployeeById(employeeId);

        if (employee != null) {
            if (employeeController.deleteEmployeeById(employeeId)) {
                customLogger.info("Employee is successfully deleted... ");
            } else {
                customLogger.info("Employee not deleted...");
            }
	} else {
            customLogger.warn("No employee found");
        } 
    }

    /**
     * Update the employee details based the employee id, get the id and search in to employee list,
     * if match found send the employee for updatation otherwise give not found message
     *
     */
    private void updateEmployeeById() {
        System.out.println("Employee updation operation here :");
        System.out.println("Please enter employee id : ");
        int employeeId = getId();
        Employee employee = employeeController.getEmployeeById(employeeId);

        if (employee != null) {  
	    this.updateEmployeeMenu(employee, employeeId);       
        } else {
            customLogger.warn("No employee found...");
        } 
    }

    /**
     * Update the employee details as per employee preference  
     *
     * @param Object - Employee object
     */
    private void updateEmployee(Employee employee) {
	

        if (employeeController.updateEmployeeById(employee)) {
            customLogger.info("Employee is successfully updated..");
        } else {
            customLogger.info("Employee is Not updated..");      
        }
    }

    /**
     * Update employee menu to choose any option for updating  particular filed
     *
     * @param Object - Employee Object
     */
    private void updateEmployeeMenu(Employee employee, int employeeId) {
        StringBuilder menu = new StringBuilder();

        menu.append("\n ======================================= ")
                .append("\n|       Employee Update Options         |")
                .append("\n|=======================================|")
                .append("\n|   1.Employee name                     |")
                .append("\n|   2.Employee Email                    |")
                .append("\n|   3.Employee DOB                      |")
                .append("\n|   4.Employee Qualification            |")
                .append("\n|   5.Employee Experience               |")
                .append("\n|   6.Employee Salary                   |")
                .append("\n|   7.Employee Address                  |")
                .append("\n ======================================= ");

        do {
	    System.out.println(menu);	
            this.getUserChoice();
		
            switch (userChoice) {
	        case 1:
                    employee.setEmployeeName(getEmployeeName());
                    break; 	    
	        case 2:
                    employee.setEmployeeEmail(getEmployeeEmail());
	            break; 	    
	        case 3:
                    employee.setEmployeeDateOfBirth(getEmployeeDateOfBirth());
	            break; 	    
	        case 4:
                    employee.setEmployeeQualification(getEmployeeQualification());
	            break; 	     
                case 5:
                    employee.setEmployeeExperience(getEmployeeExperience());
	            break; 	    
	        case 6:
	            employee.setEmployeeSalary(getEmployeeSalary());
	            break; 	   
		case 7:
                    employee.setEmployeeAddress(getEmployeeAddress());
	            break;	
                default:
                    System.out.println("You selected wrong choice.. ");  		    
            }
        } while (this.getUserConformation());
        this.updateEmployee(employee);
    }

    /**
     * Get employee details and create employee using setter
     *
     */
    private void getEmployee() {
	Employee employee = new Employee();
        String name = this.getEmployeeName();
        int id = this.employeeController.generateEmployeeId();
        String qualification = this.getEmployeeQualification();
        String email = this.getEmployeeEmail();
        String mobileNumber = this.getEmployeeMobileNumber();
        Date dateOfBirth = this.getEmployeeDateOfBirth();
        float experience = this.getEmployeeExperience();
        double salary =  this.getEmployeeSalary();
        String role = "Job Role will change";
        String address = this.getEmployeeAddress();
		
	employee.setEmployeeName(name);
	employee.setEmployeeId(id);
        employee.setEmployeeMobile(mobileNumber);
        employee.setEmployeeEmail(email);
	employee.setEmployeeDateOfBirth(dateOfBirth);
        employee.setEmployeeQualification(qualification);
	employee.setEmployeeExperience(experience);
	employee.setEmployeeRole(role);
	employee.setEmployeeSalary(salary);
	employee.setEmployeeAddress(address);
        this.createEmployee(employee); 
    }

    /**
     * Create employee and store into database
     *
     * @param Object - Employee 
     *
     */
    private void createEmployee(Employee employee) {

        if (employeeController.createEmployee(employee)) {
            customLogger.info("Employee created..");
        } else {
            customLogger.info("Employee not created..");
        } 
    }

    /**
     * Get the employee name from user and send to the validation
     * and return the valid name
     *
     * @return Name of the employee
     *
     */
    private String getEmployeeName() {
        boolean isValidName;
        String name;

	do {
            System.out.println("Enter Employee name :");
            name = input.next();
            isValidName = employeeController.isValidEmployeeName(name);
        } while (!isValidName);
        return name;
    }

    /**
     * Get the employee email from user and send to the validation
     * and return the valid email
     *
     * @return Email of the employee
     *
     */
    private String getEmployeeEmail() {
        boolean isValidEmail;
        String email;

        do {
	    System.out.println("Enter Employee Email :");
    	    email = input.next();
            isValidEmail = employeeController.isValidEmployeeEmail(email);

            if (isValidEmail) {
                if (this.verifyEmail(email)) {
                    isValidEmail = true;
                } else {
                    isValidEmail = false;
                }               
            } else {
                System.out.println("----Please enter valid input...");  
                isValidEmail = false;            
            }    
        } while (!isValidEmail);
        return email;
    }

    /**
     * Get the employee mobile number from user and send to the validation
     * and return the valid mobile number
     *
     * @return Mobile number of the employee
     *
     */
    private String getEmployeeMobileNumber() {
        boolean isValidMobile;
        String mobileNumber;

        do {
	    System.out.println("Enter Employee Mobile :");
    	    mobileNumber = input.next();
            isValidMobile = employeeController.isValidEmployeeMobile(mobileNumber);

            if (!isValidMobile) 
                customLogger.info("----Please enter valid input...");            
        } while (!isValidMobile);
        return mobileNumber;
    }

    /**
     * Get the employee dateOfBirth from user and send to the validation
     * and return the valid dateOfBirth
     *
     * @return DateOfBirth of the employee
     *
     */
    private Date getEmployeeDateOfBirth() {
        Date date  = new Date();
	boolean isValidDateOfBirth;
        String dateOfBirth;

        do {
	    System.out.println("Enter Employee DateOfBirth :");
    	    dateOfBirth = input.next();
            if (employeeController.isValidEmployeeDateOfBirth(dateOfBirth)) {

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    /* Here checking currect calender date */
                    sdf.setLenient(false); 
                    date = sdf.parse(dateOfBirth);  
                    isValidDateOfBirth = this.verifyEmployeeDate(dateOfBirth);       
                } catch (ParseException exception) {
                    customLogger.error(exception.getMessage());
                    isValidDateOfBirth = false;
                }
            } else {
                System.out.println("----Please enter valid input..."); 
                isValidDateOfBirth = false;
            }            
        } while (!isValidDateOfBirth);
        return date; 
    }

    /**
     * Here is the method to handling exceptions are custom and predefined exception.
     * parse the date from string to date, if get any exception occured it will handled and 
     * return to valid date of birth otherwise again get input from candidate for DOB
     * 
     * @param Date- DateOfBirth of Employee
     *
     * @return DateOfBirth of Employee
     *
     */
    private boolean verifyEmployeeDate(String dateOfBirth) {
        boolean isValid = true;

        try {
            String splitedDate[] = dateOfBirth.split("/");

            if (2020 < Integer.parseInt(splitedDate[2])) {
                throw new CustomException("Birth's year should not greater than current year");
            }          
        } catch (CustomException exception) {
            customLogger.error(exception.getMessage());
            isValid = false;
        }
        return isValid;
    }
   
    /**
     * Get the employee qualification from user and send to the validation
     * and return the valid qualification
     *
     * @return Qualification of the employee
     *
     */
    private String getEmployeeQualification() {
        boolean isValidQualification = false;
        String qualification = "";
	System.out.println("Please enter your Qualification / Degree :");

        for (Qualification listOfQualification : Qualification.values()) {
	    System.out.println(listOfQualification);
	}

        do {
	    System.out.println("Enter your Qualification :");
 	    qualification = input.next().toUpperCase();   
  
            for (Qualification qualifications : Qualification.values()) {
                if (qualifications.name().equals(qualification)) {
		    isValidQualification = true;
		}
            }       
        } while (!isValidQualification);
        return qualification;
    }

    /**
     * Get the employee experience  from user and send to the validation
     * and return the valid experience 
     *
     * @return Experience of the employee
     *
     */
    private float getEmployeeExperience() {
        float experience = 0;
        boolean isValidExperience;

        do {
	    System.out.println("Enter Employee Experience : ");
	    experience = Float.parseFloat(input.next());
            isValidExperience = employeeController.isValidEmployeeExperience(Float.toString(experience));

            if (!isValidExperience) 
                System.out.println("----Please enter valid input...");  
        } while (!isValidExperience);
        return experience;
    }

    /**
     * Get the employee salary from user and send to the validation
     * and return the valid salary 
     *
     * @return Salary of the employee
     *
     */
    private double getEmployeeSalary() {
        double salary = 0;
        boolean isValidSalary;

        do {
            System.out.println("Enter Employee Salary : ");                      
            salary = input.nextDouble();
            isValidSalary = employeeController.isValidEmployeeSalary(Double.toString(salary));

            if (!isValidSalary) 
                System.out.println("----Please enter valid input...");
        } while (!isValidSalary);
        input.nextLine();
        return salary;
    }

    /**
     * Get the employee address from user and send to the validation
     * and return the valid address 
     *
     * @return Address of the employee
     *
     */
    private String getEmployeeAddress() {
        boolean isValidAddress;
        String address;

        do {
	    System.out.println("Enter Employee Address: ");
	    address = input.next();
            isValidAddress = employeeController.isValidEmployeeAddress(address);
        } while (!isValidAddress); 
        return address;
    }

    /**
     * Get inputs from user for choosing option whenever need to get the choice as input 
     * just call this method it will get the input for user using scanner class
     *
     */
    private void getUserChoice() {
        boolean isValidChoice = true;

        do {
	    System.out.println("Please Select your choice : ");	 
  
            try {
                userChoice = input.nextInt(); 
                isValidChoice = false;
            } catch (InputMismatchException exception) {
		customLogger.error(exception.getMessage());               
                input.nextLine();        
            } 
        } while (isValidChoice);
    }

    /**
     * Get the user conformation for continue the process or quite
     *
     * @return boolean 
     */
    private boolean getUserConformation() {
	System.out.println("\nDo you want to Continue... y- Continue \t n - Exit)");
        char userConformation = input.next().charAt(0);

        if ('y' == userConformation || 'Y' == userConformation) {
            return true;
        } else if ('n' == userConformation || 'N' == userConformation) {
            customLogger.info("Thanks to visit...");
            return false;
        } else {
            customLogger.warn("Sorry! please try again........");
            return false;
        }
    }
}

