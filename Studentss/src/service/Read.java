package service;

import java.util.List;

import argumentsCheck.InputArgumentsCheck;
import dataObjects.StudentDataObject;

/**
 * Class contains methods for selecting Students from database.
 * 
 * @author Magdalena Igaly
 */
public class Read {
	
	/**
	 * Method fetches Student by given jmbag and prints its details.
	 * If given jmbag does not exist, message is shown.
	 * 
	 * @param inputAsArray       input command and arguments, separated into Array
	 * @param listOfStudents     list of Students, representing database
	 */
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
