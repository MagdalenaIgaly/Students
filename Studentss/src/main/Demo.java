package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class Demo {

	public static void main(String[] args) throws IOException {
		System.out.println("Dobrodosao, dragi korisnice...\r\n" + "Studenti su uspjesno ucitani!\n");

//		String FILE_PATH = args[0];
		String FILE_PATH = "C:/Users/User/git/Students/Studentss/src/students.csv";
		
		List<StudentDataObject> listOfStudents = new ArrayList<StudentDataObject>();
		listOfStudents = readStudentsFromFile(FILE_PATH);
		
//		samo provjera, makni poslije
		printStudents(listOfStudents);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = (br.readLine()).trim();
		System.out.println("naredba = " + command);
		
		System.out.println("RASCJEPKANA NAREDBA");
		String[] commandAsArray = command.split("\\s+");
		
		for (String s : commandAsArray) {
			System.out.println("s = " + s);
			System.out.println();
		}
		
		executeGivenCommand(commandAsArray, listOfStudents, FILE_PATH);
		
//		samo provjera, makni poslije
		System.out.println("FROM LIST:");
		printStudents(listOfStudents);
		
		System.out.println();
		
//		samo provjera, makni poslije
		System.out.println("FROM FILE:");
		listOfStudents = readStudentsFromFile("C:/Users/User/git/Students/Studentss/src/students.csv");
		printStudents(listOfStudents);
		
	}


	
	/////     PRIVATE METHODS     /////
	
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	private static List<StudentDataObject> readStudentsFromFile(String filePath) {
		BufferedReader reader = null;
		List<StudentDataObject> listOfStudents = new ArrayList<StudentDataObject>();

		try {
			String line = "";
			reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();

			while((line = reader.readLine()) != null) {
				String[] fields = line.split(";");

				if(fields.length > 0) {
					StudentDataObject student = new StudentDataObject();
					
					student.setJmbag(Integer.parseInt(fields[0]));
					student.setIme(fields[1]);
					student.setPrezime(fields[2]);
					student.setOcjena(Integer.parseInt(fields[3]));
					
					listOfStudents.add(student);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		return listOfStudents;
	}
	
	
	
	private static void writeNewStudentIntoFile(StudentDataObject newStudent, String filePath) {
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filePath, true);

//			fileWriter.append("jmbag;ime;prezime;ocjena\n");
			
			fileWriter.append(String.valueOf(newStudent.getJmbag()));
			fileWriter.append(";");
			fileWriter.append(newStudent.getIme());
			fileWriter.append(";");
			fileWriter.append(newStudent.getPrezime());
			fileWriter.append(";");
			fileWriter.append(String.valueOf(newStudent.getOcjena()));
			fileWriter.append("\n");
	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	/**
	 * 
	 * @param listOfStudents
	 */
	private static void printStudents(List<StudentDataObject> listOfStudents) {
		for(StudentDataObject s: listOfStudents) {
			System.out.println(s);
		}
	}
	
	
	private static void executeGivenCommand(String[] commandAsArray, List<StudentDataObject> listOfStudents, String filePath) {
		switch(commandAsArray[0].toLowerCase()) {
		case "create":
			createNewStudent(commandAsArray, listOfStudents, filePath);
			break;
		
		case "read":
			// code block
			break;
		
		case "filter-grade":
			break;

		case "filter-name":
			break;
		
		case "exit":
			break;
		}
		
	}
	
	/**
	 * 
	 */
	private static void createNewStudent(String[] commandAsArray, List<StudentDataObject> listOfStudents, String filePath) {
	
		StudentDataObject student = new StudentDataObject();
		
		student.setJmbag(Integer.parseInt(commandAsArray[1]));
		student.setIme(commandAsArray[2]);
		student.setPrezime(commandAsArray[3]);
		student.setOcjena(Integer.parseInt(commandAsArray[4]));
		
		listOfStudents.add(student);
		writeNewStudentIntoFile(student, filePath);
		
	}
	
}
