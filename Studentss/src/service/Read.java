package service;

import java.util.List;

import argumentsCheck.InputArgumentsCheck;
import dataObjects.StudentDataObject;


public class Read {
	
	public void fetchAndPrintStudentByJmbag(String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		
		boolean isFetched = false;
		
		//Input argument is not OK - Student will not be fetched
		if (!isInputArgsCorrect(inputAsArray, listOfStudents)) {
			return;
		}
		
		//Input argument is OK - we can start searching Student
		String jmbag = inputAsArray[1];
		
		for (StudentDataObject student : listOfStudents) {
			if (jmbag.equals(student.getJmbag())) {
				isFetched = true;
				System.out.println(student);
				break;
			}
		}
		
		if (!isFetched) {
			System.out.println("There is no Student with jmbag = " + jmbag);
		}
		
		return;
	}

	
	/////     PRIVATE METHODS     /////
	
	private static boolean isInputArgsCorrect (String[] inputAsArray, List<StudentDataObject> listOfStudents) {
		
		InputArgumentsCheck argCheck = new InputArgumentsCheck();
		
		//Wrong number of input arguments
		if (!argCheck.isNumberOfInputArgsCorrect(inputAsArray, 2)) {
			System.out.println("\nWrong number of input arguments! \n"
					+ "Please enter the command in the following form: read jmbag");
			
			return false;
		}
		
		String jmbag = inputAsArray[1];
		
		if (!argCheck.isDigitsOnly(jmbag)) {
			return false;
		} else {
			return true;
		}
	}
}
