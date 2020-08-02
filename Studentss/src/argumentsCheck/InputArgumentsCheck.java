package argumentsCheck;

import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class InputArgumentsCheck {
	
	public boolean isNumberOfInputArgsCorrect (String[] commandAsArray, int numberOfArgs, String correctForm) {
		if (commandAsArray.length != numberOfArgs) {
			System.out.println("Wrong number of input arguments! \n"
					+ "Please enter the command in the following form: " + correctForm);
			return false;
		
		} else {
			return true;
		}
	}
	
	
	public boolean isDigitsOnly(String jmbag) {
		if (!jmbag.matches("[0-9]+")) {
			System.out.println("Jmbag can contains digits only!");
			return false;
		
		} else {
			return true;
		}
	}
	
	
	public boolean isJmbagUnique(String jmbag, List<StudentDataObject> listOfStudents) {
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
	

	
	public boolean isNameWord(String name) {
		if (!name.matches("[a-zA-Z]+")) {
			System.out.println("Name can contains letters only!");
			
			return false;
		
		} else {
			return true;
		}
	}
	
	public boolean isSurnameWord(String surname) {
		if (!surname.matches("[a-zA-Z]+")) {
			System.out.println("Surname can contains letters only!");

			return false;
		
		} else {
			return true;
		}
	}
	
	public boolean isGradeInRange(int grade) {
		if (grade > 5 || grade < 1) {
			System.out.println("Grade must be between 1 and 5, included.");
			
			return false;
		
		} else {
			return true;
		}
	}
}
