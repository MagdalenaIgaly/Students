package dataObjects;

public class StudentDataObject {

	private String jmbag;
	private String name;
	private String surname;
	private String grade;
	
	public StudentDataObject(String jmbag, String name, String surname, String grade) {
		super();
		this.jmbag = jmbag;
		this.name = name;
		this.surname = surname;
		this.grade = grade;
	}
	
	public String getJmbag() {
		return jmbag;
	}
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}
	public String getName() {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	@Override
	public String toString() {
		return "{jmbag: " + jmbag + ", name: " + name + ", surname: " + surname + ", grade: " + grade
				+ "}";
	}
}
