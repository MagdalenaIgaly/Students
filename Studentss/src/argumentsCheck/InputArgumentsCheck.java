package argumentsCheck;

import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class InputArgumentsCheck {
	
	public boolean isNumberOfInputArgsCorrect (String[] inputAsArray, int numberOfArgs) {
		if (inputAsArray.length != numberOfArgs) {
			return false;
		
		} else {
			return true;
		}
	}
	
	
	public boolean isDigitsOnly(String jmbag) {
		
		boolean isDigitsOnly = true;
		
		if (!jmbag.matches("[0-9]+")) {
			System.out.println("\nJmbag can contains digits only!");
			isDigitsOnly = false;
		}
		
		return isDigitsOnly;
		
	}
	
	
	public boolean isJmbagUnique (String jmbag, List<StudentDataObject> listOfStudents) {
		boolean isJmbagUnique = true;
		
		List<String> listOfJmbags = new ArrayList<String>(); 

		for (StudentDataObject s : listOfStudents) {
			listOfJmbags.add(s.getJmbag());
		}

		if (listOfJmbags.contains(jmbag)) {
			System.out.println("\nThe Student with given jmbag already exist! Please write different jmbag.");

			isJmbagUnique = false;
		} 
		
		return isJmbagUnique;
	}
	

	
	public boolean isNameWord (String name) {
		boolean isNameWord = true;
		
		if (!name.matches("[a-zA-Z]+")) {
			System.out.println("\nName can contains letters only!");
			
			isNameWord = false;
		}
		
		return isNameWord;
	}
	
	public boolean isSurnameWord (String surname) {
		boolean isSurnameWord = true;
		
		if (!surname.matches("[a-zA-Z]+")) {
			System.out.println("\nSurname can contains letters only!");

			isSurnameWord = false;
		} 

		return isSurnameWord;
	}
	
	public boolean isGradeInRange (String grade) {
		boolean isGradeInRange = true;
		
		int intValueOfGrade;
		
		try {
			intValueOfGrade = Integer.parseInt(grade);
		
		} catch (NumberFormatException nfe) {
			System.out.println("\nGrade must be NUMBER between 1 and 5, included.");
			isGradeInRange = false;
			
			return isGradeInRange;
		}
		
		
		if (intValueOfGrade > 5 || intValueOfGrade < 1) {
			System.out.println("\nGrade must be between 1 and 5, included.");
			isGradeInRange = false;
		
		} 
		
		return isGradeInRange;
	}
	
	
	public boolean isRelationExist (String relation) {
		boolean isRelationExist = true;
		
		if (!relation.toLowerCase().matches("[lge]")) {
			System.out.println("\nGiven relation does not exist!\n"
					+ "Please write: l for lower, g for greater or e for equal.");			
			isRelationExist = false;
		} 
		
		return isRelationExist;
	}
	
	public boolean isInitialSymbolLetter (String initial) {
		boolean isInitialLetter = true;
		
		if (!initial.toLowerCase().matches("[a-zA-Z]{1}")) {
			System.out.println("\nInitial must be (one) letter!");			
			isInitialLetter = false;
		} 
		
		return isInitialLetter;
	}
	
	public boolean isFontExist (String font) {
		boolean isFontExist = true;
		
		if (!"-u".equalsIgnoreCase(font)
				&&	!"-l".equalsIgnoreCase(font)) {
			
			System.out.println("\nGiven font does not exist!\n"
					+ "Please write: -l for lower case or -u for upper case.");			
			
			isFontExist = false;
		} 
		
		return isFontExist;
	}
}
