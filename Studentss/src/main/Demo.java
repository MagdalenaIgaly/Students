package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import csv.CsvOperations;
import dataObjects.StudentDataObject;
import service.Create;
import service.Filter;
import service.Read;

public class Demo {
	
	private static CsvOperations csvOperations = new CsvOperations();
	
	public static void main(String[] args) throws IOException {
//		String FILE_PATH = args[0];
		String FILE_PATH = "C:/Users/User/git/Students/Studentss/src/students.csv";
		
		List<StudentDataObject> listOfStudents = new ArrayList<StudentDataObject>();
		
		try {
			listOfStudents = csvOperations.readStudentsFromFile(FILE_PATH);
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("File with the specified pathname does not exist!\n");
			return;
		
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		
		
		System.out.println("Welcome, dear user...\r\n" + "List of Students has been successfully loaded!\n");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = (br.readLine()).trim();

		while (!"exit".equalsIgnoreCase(input)) {
			String[] inputAsArray = input.split("\\s+");
			
		executeGivenCommand(inputAsArray, listOfStudents, FILE_PATH);
		
		System.out.println();
		
		br = new BufferedReader(new InputStreamReader(System.in));
		input = (br.readLine()).trim();
		}		
	}

	
	/////     PRIVATE METHODS     /////
	

	private static void executeGivenCommand(String[] commandAsArray, List<StudentDataObject> listOfStudents, String filePath) throws IOException {
		switch(commandAsArray[0].toLowerCase()) {
		case "create":
			Create create = new Create();
			StudentDataObject newStudent = create.createAndAddNewStudent(commandAsArray, listOfStudents);
			
			if (newStudent != null) {
				csvOperations.writeNewStudentIntoFile(newStudent, filePath);
			}
			break;
		
		case "read":
			Read read = new Read();
			read.fetchAndPrintStudentByJmbag(commandAsArray, listOfStudents);
			break;
		
		case "filter-grade":
			Filter filterGrade = new Filter();
			filterGrade.filterStudentsByGrade(commandAsArray, listOfStudents);
			break;

		case "filter-name":
			Filter filterName = new Filter();
			filterName.filterStudentsByName(commandAsArray, listOfStudents);
			break;
			
		case "commands":
			System.out.println("SUPPORTED COMMANDS:");
			printAllSupportedCommands();
			break;
			
		default:
			System.out.println("\nUnsupported command !"
				+ "\nPlease write one of the following forms:");
			printAllSupportedCommands();
		}
	}
	
	private static void printAllSupportedCommands() {
		System.out.println("\ncreate jmbag name surname grade                          ->   CREATE NEW STUDENT"
				+ "\nread jmbag                                               ->   READ BY JMBAG"
				+ "\nfilter-grade {l,g,e} grade                               ->   FILTER BY GRADE"
				+ "\nfilter-name initial {-l,-u}, where {-l,-u} is optional   ->   FILTER BY NAME"
				+ "\nexit                                                     ->   EXIT");
	}
}
