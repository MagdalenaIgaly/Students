package service;

import java.util.List;


import argumentsCheck.InputArgumentsCheck;
import dataObjects.StudentDataObject;

/**
 * filter-grade arg1 arg2" - ispisuje detalje svih studenata èija je ocjena manja/jednaka/veæa od ocjene dane argumentom arg2,
 *  argument arg1 može biti jedan od 3 moguæih vrijednosti {"l", "g", "e"}, te oznaèava korištenu relaciju (lower, greater ili equal). 
 * @author User
 *
 */

public class Filter {
	
	private static InputArgumentsCheck argCheck = new InputArgumentsCheck();

	public void filterStudentsByGrade (String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		
		//Input arguments are not OK - Students will not be filtered
		if (!isInputArgsCorrectGrade(inputAsArray, listOfStudents)) {
			return;
		}
		
		//All input arguments are OK - we can start filtering list of Students
		String relation = inputAsArray[1].toLowerCase();
		int intValueOfGivenGrade = Integer.parseInt(inputAsArray[2]);

		//Since the grade can't be less than 1 or greater than 5, empty set is returned 
		if ( ("l".equals(relation) && intValueOfGivenGrade == 1)
				|| ("g".equals(relation) && intValueOfGivenGrade == 5)) {
			
			System.out.println("{}");
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
		
		//Since all arguments are sent, we can fetch their values
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
		for (StudentDataObject student : listOfStudents) {
			printIfLower (student, intValueOfGivenGrade);
		}
	}
	
	private static void printIfLower (StudentDataObject student, int intValueOfGivenGrade) {
		int intValueOfStudentsGrade;

		try {
			intValueOfStudentsGrade = Integer.parseInt(student.getGrade());
		} catch (NumberFormatException nfe) {
			return;
		}
		
		if (intValueOfStudentsGrade < intValueOfGivenGrade) {
			System.out.println(student);
		}
	}
	
	
	//GREATER
	private static void fetchAndPrintGreater (List<StudentDataObject> listOfStudents, int intValueOfGivenGrade) {
		for (StudentDataObject student : listOfStudents) {
			printIfGreater (student, intValueOfGivenGrade);
		}
	}

	private static void printIfGreater (StudentDataObject student, int intValueOfGivenGrade) {
		int intValueOfStudentsGrade;

		try {
			intValueOfStudentsGrade = Integer.parseInt(student.getGrade());
		} catch (NumberFormatException nfe) {
			return;
		}

		if (intValueOfStudentsGrade > intValueOfGivenGrade) {
			System.out.println(student);
		}
	}



	//EQUAL
	private static void fetchAndPrintEqual (List<StudentDataObject> listOfStudents, int intValueOfGivenGrade) {
		for (StudentDataObject student : listOfStudents) {
			printIfEqual (student, intValueOfGivenGrade);
		}
	}

	private static void printIfEqual (StudentDataObject student, int intValueOfGivenGrade) {
		int intValueOfStudentsGrade;

		try {
			intValueOfStudentsGrade = Integer.parseInt(student.getGrade());
		} catch (NumberFormatException nfe) {
			return;
		}

		if (intValueOfStudentsGrade == intValueOfGivenGrade) {
			System.out.println(student);
		}
	}

	
	
	/**
	 * "filter-name arg1 arg2" - ispisuje ime i prezime svih studenata èije ime poèinje s argumentom arg1, 
	 * 
	 * argument arg2 je opcionalan te ukoliko je prisutan njegova vrijednost mora biti "-u" ili "-l" 
	 * te oznaèava da se ime i prezime ispisuje velikim, odnosno malim slovima
	 */
	public void filterStudentsByName (String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		//Input arguments are not OK - Students will not be filtered
		if (!isInputArgsCorrectName(inputAsArray, listOfStudents)) {
			
			return;
		}
		
		if (inputAsArray.length == 2) {
			String initial = inputAsArray[1].toLowerCase();
			fetchAndPrintByName(listOfStudents, initial);
			
		} else {
			String initial = inputAsArray[1].toLowerCase();
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
		for (StudentDataObject student : listOfStudents) {
			if (student.getName().toLowerCase().startsWith(initial)) {
				System.out.println(student.getName() + " " + student.getSurname());
			}
		}
	}
	
	private static void fetchAndPrintByNameInGivenFont(List<StudentDataObject> listOfStudents, String initial, String font) {
		for (StudentDataObject student : listOfStudents) {
			if (student.getName().toLowerCase().startsWith(initial)) {
				printInGivenFont(student, font);
			}
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
