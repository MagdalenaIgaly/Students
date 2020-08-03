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

	public void filterStudentsByGrade (String[] commandAsArray, List<StudentDataObject> listOfStudents) {
		
		//Input arguments are not OK - Students will not be filtered
		if (!isInputArgsCorrect(commandAsArray, listOfStudents)) {
			return;
		}
		
		//All input arguments are OK - we can start filtering Student
		String relation = commandAsArray[1].toLowerCase();
		int intValueOfGivenGrade = Integer.parseInt(commandAsArray[2]);

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
	
	private static boolean isInputArgsCorrect (String[] commandAsArray, List<StudentDataObject> listOfStudents) {
		
		InputArgumentsCheck argCheck = new InputArgumentsCheck();
		
		//Wrong number of input arguments
		if (!argCheck.isNumberOfInputArgsCorrect(commandAsArray, 3, "filter-grade {l,g,e} grade")) {
			return false;
		}
		
		//Since all arguments are sent, we can fetch their values
		String relation = commandAsArray[1];
		String grade = commandAsArray[2];
		
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

}
