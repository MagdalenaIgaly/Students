package service;

import java.util.List;


import argumentsCheck.InputArgumentsCheck;
import dataObjects.StudentDataObject;

/**
 * Class contains methods for filtering Students from database
 * 
 * @author Magdalena Igaly
 */
public class Filter {
	
	private static InputArgumentsCheck argCheck = new InputArgumentsCheck();

	/**
	 * Method processes command: filter-grade arg1 arg2.
	 * Method prints details of all Students whose grade is less than / equal to / greater than grade given with arg2.
	 * Argument agr1 can be one of three possible values {l,g,e} ant it represents relation (less, greater, equal)
	 * 
	 * @param inputAsArray       input command and arguments, separated into Array
	 * @param listOfStudents     list of Students, representing database
	 */
	public void filterStudentsByGrade (String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		
		//Input arguments are not OK - Students will not be filtered
		if (!isInputArgsCorrectGrade(inputAsArray, listOfStudents)) {
			return;
		}
		
		//All input arguments are OK - we can start filtering list of Students
		String relation = inputAsArray[1].toLowerCase();
		int intValueOfGivenGrade = Integer.parseInt(inputAsArray[2]);

		//Grade can't be less than 1
		if ( "l".equals(relation) && intValueOfGivenGrade == 1 ) {
			System.out.println("\nThere is no Student with grade less than 1.");
			return;
		}
		
		//Grade can't be greater than 5
		if ( "g".equals(relation) && intValueOfGivenGrade == 5 ) {
			System.out.println("\nThere is no Student with grade greater than 5.");
			return;
		}
		
		if ("l".equals(relation)) {
			fetchAndPrintLower (listOfStudents, intValueOfGivenGrade);
		
		} else if ("g".equals(relation)) {
			fetchAndPrintGreater (listOfStudents, intValueOfGivenGrade);
	
		} else {
			fetchAndPrintEqual (listOfStudents, intValueOfGivenGrade);
		}

		return;
	}
	
	/////     PRIVATE METHODS     /////
	
	private static boolean isInputArgsCorrectGrade (String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		
		//Wrong number of input arguments
		if (!argCheck.isNumberOfInputArgsCorrect(inputAsArray, 3)) {
			System.out.println("\nWrong number of input arguments! \n"
					+ "Please enter the command in the following form: filter-grade {l,g,e} grade");
			
			return false;
		}
		
		String relation = inputAsArray[1];
		String grade = inputAsArray[2];
		
		if (!argCheck.isRelationExist(relation)              //Relation does not exist
				| !argCheck.isGradeInRange(grade)){          //Grade is out of range [1, 5]
			
			return false;
		} else {
			return true;
		}
	}
	
	//LOWER
	private static void fetchAndPrintLower (List<StudentDataObject> listOfStudents, int intValueOfGivenGrade) {
		int counter = 0;
		
		for (StudentDataObject student : listOfStudents) {
			int studentsGrade = getIntFromString (student.getGrade());
			
			if (0 < studentsGrade && studentsGrade < intValueOfGivenGrade) {
				System.out.println(student);
				counter++;
			}
		}
		
		if (counter == 0) {
			System.out.println("\nThere is no Student with grade less than " + intValueOfGivenGrade);
		}
	}
	
	//GREATER
	private static void fetchAndPrintGreater (List<StudentDataObject> listOfStudents, int intValueOfGivenGrade) {
		int counter = 0;

		for (StudentDataObject student : listOfStudents) {
			int studentsGrade = getIntFromString (student.getGrade());
			
			if (0 < studentsGrade && studentsGrade > intValueOfGivenGrade) {
				System.out.println(student);
				counter++;
			}
		}

		if (counter == 0) {
			System.out.println("\nThere is no Student with grade greater than " + intValueOfGivenGrade);
		}
	}

	//EQUAL
	private static void fetchAndPrintEqual (List<StudentDataObject> listOfStudents, int intValueOfGivenGrade) {
		int counter = 0;
		
		for (StudentDataObject student : listOfStudents) {
			int studentsGrade = getIntFromString (student.getGrade());
			
			if (0 < studentsGrade && studentsGrade == intValueOfGivenGrade) {
				System.out.println(student);
				counter++;
			}
		}
		
		if (counter == 0) {
			System.out.println("\nThere is no Student with grade equal to " + intValueOfGivenGrade);
		}
	}

	private static int getIntFromString (String grade) {
		int intValueOfStudentsGrade;
		
		try {
			intValueOfStudentsGrade = Integer.parseInt(grade);
		} catch (NumberFormatException nfe) {
			return 0;
		}
		
		return intValueOfStudentsGrade;
	}

	/**
	 * Method processes command: filter-name arg1 arg2.
	 * Method prints name and surname of all Students whose name starts with arg1.
	 * Argument agr2 is optional and can be -u or -l which indicates that name and surname are printed in lower case or upper case, respectively 
	 * 
	 * @param inputAsArray       input command and arguments, separated into Array
	 * @param listOfStudents     list of Students, representing database
	 */
	public void filterStudentsByName (String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		//Input arguments are not OK - Students will not be filtered
		if (!isInputArgsCorrectName(inputAsArray, listOfStudents)) {
			return;
		}
		
		String initial = inputAsArray[1].toLowerCase();
		
		if (inputAsArray.length == 2) {
			fetchAndPrintByName(listOfStudents, initial);
			
		} else {
			String font = inputAsArray[2].toLowerCase();
			fetchAndPrintByNameInGivenFont(listOfStudents, initial, font);
		}

		return;
	}
	
	
	/////     PRIVATE METHODS     /////
	
	private static boolean isInputArgsCorrectName (String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		
		//Wrong number of input arguments
		if (!argCheck.isNumberOfInputArgsCorrect(inputAsArray, 2)
				&& !argCheck.isNumberOfInputArgsCorrect(inputAsArray, 3)) {
			
			System.out.println("\nWrong number of input arguments! \n"
					+ "Please enter the command in the following form: filter-name initial {-l,-u}, where {-l,-u} is optional");
			
			return false;
		}
		

		String initial = inputAsArray[1];
		
		switch (inputAsArray.length) {
		case 2:
			return isInitialOk (initial);

		case 3:   //Check both initial and font
			String font = inputAsArray[2];
			return isInitialAndFontOk (initial, font);
		}

		return true;
	}
	
	private static boolean isInitialOk (String initial) {
		if (!argCheck.isInitialSymbolLetter(initial)){          
			return false;
		} else {
			return true;
		}
	}
	
	private static boolean isInitialAndFontOk (String initial, String font) {
		if (!argCheck.isInitialSymbolLetter (initial) | !argCheck.isFontExist (font)) {
			return false;
		} else {
			return true;
		}
	}
	
	private static void fetchAndPrintByName(List<StudentDataObject> listOfStudents, String initial) {
		int counter = 0;
		
		for (StudentDataObject student : listOfStudents) {
			if (student.getName().toLowerCase().startsWith(initial)) {
				System.out.println(student.getName() + " " + student.getSurname());
				counter++;
			}
		}

		if (counter == 0) {
			System.out.println("\nThere is no Student whose name starts with " + initial);
		}
	}
	
	private static void fetchAndPrintByNameInGivenFont(List<StudentDataObject> listOfStudents, String initial, String font) {
		int counter = 0;
		
		for (StudentDataObject student : listOfStudents) {
			if (student.getName().toLowerCase().startsWith(initial)) {
				printInGivenFont(student, font);
				counter++;
			}
		}
		
		if (counter == 0) {
			System.out.println("There is no Student whose name starts with: " + initial);
		}
	}
	
	private static void printInGivenFont(StudentDataObject student, String font) {
		if ("-l".equalsIgnoreCase(font)) {
			System.out.println(student.getName().toLowerCase() + " " + student.getSurname().toLowerCase());
		
		} else {
			System.out.println(student.getName().toUpperCase() + " " + student.getSurname().toUpperCase());
		}
	}
}
