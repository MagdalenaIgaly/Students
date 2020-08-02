package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataObjects.StudentDataObject;

public class CsvOperations {
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public List<StudentDataObject> readStudentsFromFile(String filePath) {
		BufferedReader reader = null;
		List<StudentDataObject> listOfStudents = new ArrayList<StudentDataObject>();

		try {
			String line = "";
			reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();

			while((line = reader.readLine()) != null) {
				String[] fields = line.split(";");

				if(fields.length > 0) {
					String jmbag = fields[0];
					String name = fields[1];
					String surname = fields[2];
					int grade = Integer.parseInt(fields[3]);
					
					StudentDataObject student = new StudentDataObject(jmbag, name, surname, grade);
					
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
	
	
	
	public void writeNewStudentIntoFile(StudentDataObject newStudent, String filePath) {
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filePath, true);
			
			fileWriter.append(newStudent.getJmbag());
			fileWriter.append(";");
			fileWriter.append(newStudent.getName());
			fileWriter.append(";");
			fileWriter.append(newStudent.getSurname());
			fileWriter.append(";");
			fileWriter.append(String.valueOf(newStudent.getGrade()));
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
}
