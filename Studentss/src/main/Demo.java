package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class Demo {

	public static void main(String[] args) {
		System.out.println("Dobrodosao, dragi korisnice...\r\n" + "Studenti su uspjesno ucitani!\n");

		List<StudentDataObject> listOfStudents = new ArrayList<StudentDataObject>();
//		listOfStudents = readStudentsFromFile(args[0]);
		
//		HARKODIRANO - da mogu pokretati iz eclipsa - makni kad predes na komandnu liniju
		listOfStudents = readStudentsFromFile("C:/Users/User/git/Students/Studentss/src/students.csv");
		
		printStudents(listOfStudents);
		System.out.println(listOfStudents);
	}


	
	/////     PRIVATE METHODS     /////
	
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
	
	
	private static void printStudents(List<StudentDataObject> listOfStudents) {
		for(StudentDataObject s: listOfStudents) {
			System.out.println(s);
		}
	}

}
