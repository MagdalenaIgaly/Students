package main;

import java.io.BufferedReader;
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
		listOfStudents = csvOperations.readStudentsFromFile(FILE_PATH);
		
		System.out.println("Welcome, dear user...\r\n" + "List of Students has been successfully loaded!\n");

		//samo provjera, makni poslije
		printStudents(listOfStudents);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = (br.readLine()).trim();

		while (!"exit".equalsIgnoreCase(command)) {
			String[] commandAsArray = command.split("\\s+");
			
//		for (String s : commandAsArray) {
//			System.out.println("s = " + s);
//		}
			
		executeGivenCommand(commandAsArray, listOfStudents, FILE_PATH);
		
//		POCETAK - samo provjera, makni poslije
		System.out.println("FROM LIST:");
		printStudents(listOfStudents);
		System.out.println();
		System.out.println("FROM FILE:");
		listOfStudents = csvOperations.readStudentsFromFile("C:/Users/User/git/Students/Studentss/src/students.csv");
		printStudents(listOfStudents);
//		KRAJ
		
		br = new BufferedReader(new InputStreamReader(System.in));
		command = (br.readLine()).trim();
		}		
	}


	
	/////     PRIVATE METHODS     /////
	
	/**
	 * 
	 * @param listOfStudents
	 */
	private static void printStudents(List<StudentDataObject> listOfStudents) {
		for(StudentDataObject s: listOfStudents) {
			System.out.println(s);
		}
	}
	
	
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
			break;

		case "filter-name":
			Filter filterName = new Filter();
			break;
		
//		cade default:
//			nabroji sve naredbe koje je moguce koristiti
		}
		
		
	}
}
