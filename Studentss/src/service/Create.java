package service;

import java.io.IOException;
import java.util.List;

import argumentsCheck.InputArgumentsCheck;
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
		String grade = commandAsArray[4];
		
		StudentDataObject student = new StudentDataObject(jmbag, name, surname, grade);
		
		System.out.println("The student has been successfully created!");
		
		//Adding new Student into "database"
		listOfStudents.add(student);
		
		return student;		
	}
	
	

	/////     PRIVATE METHODS     /////
	
	private static boolean isInputArgsCorrect (String[] commandAsArray, List<StudentDataObject> listOfStudents) {
		
		InputArgumentsCheck argCheck = new InputArgumentsCheck();
		
		//Wrong number of input arguments
		if (!argCheck.isNumberOfInputArgsCorrect(commandAsArray, 5, "create jmbag name surname grade")) {
			return false;
		}

		//Since all entities are sent, we can fetch their values
		String jmbag = commandAsArray[1];
		String name = commandAsArray[2];
		String surname = commandAsArray[3];
		String grade = commandAsArray[4];

		
		if (!argCheck.isJmbagUnique(jmbag, listOfStudents)    //The Student with given jmbag already exist
				| !argCheck.isDigitsOnly(jmbag)              //Jmbag contains non-digit symbol
				| !argCheck.isNameWord(name)                 //Name is not word
				| !argCheck.isSurnameWord(surname)           //Surname is not word
				| !argCheck.isGradeInRange(grade)){          //Grade is out of range [1, 5]
			
			return false;
		
		} else {
			return true;
		}
	}

}
