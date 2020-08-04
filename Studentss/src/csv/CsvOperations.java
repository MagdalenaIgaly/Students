package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class CsvOperations {
	/**
	 * Method reads all Students from file and saves them into the List that represents database
	 * 
	 * @param filePath   path to csv file with Students
	 * @return           list of Students from database
	 */
	public List<StudentDataObject> readStudentsFromFile(String filePath) throws FileNotFoundException, IOException {
		BufferedReader reader = null;
		List<StudentDataObject> listOfStudents = new ArrayList<StudentDataObject>();

		String line = "";
		reader = new BufferedReader(new FileReader(filePath));
		reader.readLine();

		while((line = reader.readLine()) != null) {
			String[] fields = line.split(";");

			if(fields.length > 0) {
				String jmbag = fields[0];
				String name = fields[1];
				String surname = fields[2];
				String grade = fields[3];

				StudentDataObject student = new StudentDataObject(jmbag, name, surname, grade);

				listOfStudents.add(student);
			}
		}
		
		reader.close();
		
		return listOfStudents;
	}
	
	
	/**
	 * Method adds new Student into the csv file
	 * 
	 * @param newStudent     Student that will be added to csv file
	 * @param filePath       path to csv file with Students
	 * @throws IOException
	 */
	public void writeNewStudentIntoFile(StudentDataObject newStudent, String filePath) throws IOException {
		
		FileWriter fileWriter = null;
		
		fileWriter = new FileWriter(filePath, true);

		fileWriter.append(newStudent.getJmbag());
		fileWriter.append(";");
		fileWriter.append(newStudent.getName());
		fileWriter.append(";");
		fileWriter.append(newStudent.getSurname());
		fileWriter.append(";");
		fileWriter.append(newStudent.getGrade());
		fileWriter.append("\n");


		fileWriter.flush();
		fileWriter.close();
		
	}
}
