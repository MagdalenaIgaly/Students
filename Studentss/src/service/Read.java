package service;

import java.util.List;

import argumentsCheck.InputArgumentsCheck;
import dataObjects.StudentDataObject;


public class Read {
	
	public void fetchAndPrintStudentByJmbag(String[] commandAsArray, List<StudentDataObject> listOfStudents) {
		
		boolean isFetched = false;
		
		//Input argument is not OK - Student will not be fetched
		if (!isInputArgsCorrect(commandAsArray, listOfStudents)) {
			return;
		}
		
		//Input argument is OK - we can start searching Student
		String jmbag = commandAsArray[1];
		
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
	
	private static boolean isInputArgsCorrect (String[] commandAsArray, List<StudentDataObject> listOfStudents) {
		
		InputArgumentsCheck argCheck = new InputArgumentsCheck();
		
		//Wrong number of input arguments
		if (!argCheck.isNumberOfInputArgsCorrect(commandAsArray, 2, "read jmbag")) {
			return false;
		}
		
		//Since jmbag is sent, we can fetch its value
		String jmbag = commandAsArray[1];
		
		//Check if sent jmbag contains some non-digit symbol
		if (!argCheck.isDigitsOnly(jmbag)) {
			return false;
			
		} else {
			return true;
		}
	}
}
