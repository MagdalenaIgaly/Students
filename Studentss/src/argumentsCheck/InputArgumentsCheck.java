package argumentsCheck;

import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class InputArgumentsCheck {
	
	/**
	 * Method checks if users input command contains required number of arguments
	 * 
	 * @param inputAsArray       users input command, separated into Array
	 * @param numberOfArgs       required number of arguments for command that has been called from user
	 * @return                   true if number of arguments is correct, false otherwise
	 */
	public boolean isNumberOfInputArgsCorrect (String[] inputAsArray, int numberOfArgs) {
		if (inputAsArray.length != numberOfArgs) {
			return false;
		
		} else {
			return true;
		}
	}
	
	/**
	 * Method checks if given String contains only numbers
	 * 
	 * @param string   String that will be tested
	 * @return         true if given String contains only numbers, false otherwise
	 */
	public boolean isDigitsOnly(String string) {
		
		boolean isDigitsOnly = true;
		
		if (!string.matches("[0-9]+")) {
			System.out.println("\nJmbag contains symbols other than digits!");
			isDigitsOnly = false;
		}
		
		return isDigitsOnly;
		
	}
	
	
	/**
	 * Method checks if given jmbag is unique in database
	 * 
	 * @param jmbag              jmbag that will be tested
	 * @param listOfStudents     list of Students, representing database
	 * @return                   true if jmbag is unique, false otherwise
	 */
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
	

	/**
	 * Method checks if given name contains only letters
	 * Symbols è, æ, ð, š, ž are also observed as letters.
	 * 
	 * @param name    name that will be tested
	 * @return        true if name contains only letters, false otherwise
	 */
	public boolean isNameWord (String name) {
		boolean isNameWord = true;
		
		if (!name.matches("[a-zA-ZèæðšžÈÆÐŠŽ]+")) {
			System.out.println("\nName can contains letters only!");
			
			isNameWord = false;
		}
		
		return isNameWord;
	}
	
	/**
	 * Method checks if given surname contains only letters
	 * Symbols è, æ, ð, š, ž are also observed as letters.
	 * 
	 * @param surname    surname that will be tested
	 * @return           true if surname contains only letters, false otherwise
	 */
	public boolean isSurnameWord (String surname) {
		boolean isSurnameWord = true;
		
		if (!surname.matches("[a-zA-ZèæðšžÈÆÐŠŽ]+")) {
			System.out.println("\nSurname can contains letters only!");

			isSurnameWord = false;
		} 

		return isSurnameWord;
	}
	
	/**
	 * Method checks if grade is number between 1 and 5, included.
	 * 
	 * @param grade    grade that will be tested
	 * @return         true if grade is number between 1 and 5, included, false otherwise
	 */
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
	
	
	/**
	 * Method checks if given relation is one of the symbols: l (for less), g (for greater), e (for equal)
	 * 
	 * @param relation   relation that will be tested
	 * @return           true if given relation exists, false otherwise
	 */
	public boolean isRelationExist (String relation) {
		boolean isRelationExist = true;
		
		if (!relation.toLowerCase().matches("[lge]")) {
			System.out.println("\nGiven relation '" + relation + "' does not exist!\n"
					+ "Please write: l for lower, g for greater or e for equal.");			
			isRelationExist = false;
		} 
		
		return isRelationExist;
	}
	
	/**
	 * Method checks if given initial is letter.
	 * Symbols è, æ, ð, š, ž are also observed as letters.
	 * 
	 * @param initial    initial that will be tested
	 * @return           true if given initial is letter, false otherwise
	 */
	public boolean isInitialSymbolLetter (String initial) {
		boolean isInitialLetter = true;
		
		if (!initial.toLowerCase().matches("[a-zA-ZèæðšžÈÆÐŠŽ]{1}")) {
			System.out.println("\nInitial must be (one) letter!");			
			isInitialLetter = false;
		} 
		
		return isInitialLetter;
	}
	
	/**
	 * Method checks if given font is one of the following: -u (for upper case), -l (for lower case)
	 * 
	 * @param font   font that will be tested
	 * @return       true if given font exists, false otherwise
	 */
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
