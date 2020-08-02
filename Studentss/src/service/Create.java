package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class Create {
	
	/**
	 * @throws IOException 
	 * 
	 */
	public StudentDataObject createAndAddNewStudent(String[] commandAsArray, List<StudentDataObject> listOfStudents) throws IOException {
		
		//Input arguments are not OK - Student will not be created
		if (!isInputArgsCorrect(commandAsArray, listOfStudents)) {
			return null;
		}
		
		//All input arguments are OK - new Student can be created
		String jmbag = commandAsArray[1];
		String name = commandAsArray[2];
		String surname = commandAsArray[3];
		int grade = Integer.parseInt(commandAsArray[4]);
		
		StudentDataObject student = new StudentDataObject(jmbag, name, surname, grade);
		
		System.out.println("The student has been successfully created!");
		
		//Adding new Student into "database"
		listOfStudents.add(student);
		
		return student;		
	}
	
	

	/////     PRIVATE METHODS     /////
	
	private static boolean isInputArgsCorrect (String[] commandAsArray, List<StudentDataObject> listOfStudents) {
		
		//Some of the Student entity is missing (jmbag, name, surname or grade)
		if (!isAllEntitiesSent(commandAsArray)) {
			return false;
		}

		//If all entities are sent, we can fetch their values
		String jmbag = commandAsArray[1];
		String name = commandAsArray[2];
		String surname = commandAsArray[3];
		int grade = Integer.parseInt(commandAsArray[4]);

		
		if (!isJmbagUnique(jmbag, listOfStudents)   //The Student with given jmbag already exist
				|| !isDigitsOnly(jmbag)     //Jmbag contains non-digit symbol
				|| !isNameWord(name)   //Name is not word
				|| !isSurnameWord(surname)   //Surname is not word
				|| !isGradeInRange(grade)){   //Grade is out of range [1, 5]
			
			return false;
		
		} else {
			return true;
		}
	}
	
	private static boolean isAllEntitiesSent(String[] commandAsArray) {
		if (commandAsArray.length < 5) {
			System.out.println("One or more arguments are missing. \n"
					+ "Please enter the command in the following form: create jmbag name surname grade");
			
			return false;
		
		} else {
			return true;
		}
	}
	
	private static boolean isJmbagUnique(String jmbag, List<StudentDataObject> listOfStudents) {
		List<String> listOfJmbags = new ArrayList<String>(); 

		for (StudentDataObject s : listOfStudents) {
			listOfJmbags.add(s.getJmbag());
		}

		if (listOfJmbags.contains(jmbag)) {
			System.out.println("The Student with given jmbag already exist! Please write different jmbag.");

			return false;
		
		} else {
			return true;
		}
	}
	
	private static boolean isDigitsOnly(String jmbag) {
		if (!jmbag.matches("[0-9]+")) {
			System.out.println("Jmbag can contains digits only!");
			
			return false;
		
		} else {
			return true;
		}
	}
	
	private static boolean isNameWord(String name) {
		if (!name.matches("[a-zA-Z]+")) {
			System.out.println("Name can contains letters only!");
			
			return false;
		
		} else {
			return true;
		}
	}
	
	private static boolean isSurnameWord(String surname) {
		if (!surname.matches("[a-zA-Z]+")) {
			System.out.println("Surname can contains letters only!");

			return false;
		
		} else {
			return true;
		}
	}
	
	private static boolean isGradeInRange(int grade) {
		if (grade > 5 || grade < 1) {
			System.out.println("Grade must be between 1 and 5, included.");
			
			return false;
		
		} else {
			return true;
		}
	}

}
