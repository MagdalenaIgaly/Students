package dataObjects;

public class StudentDataObject {

	private int jmbag;
	private String ime;
	private String prezime;
	private int ocjena;
	
	public int getJmbag() {
		return jmbag;
	}
	public void setJmbag(int jmbag) {
		this.jmbag = jmbag;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public int getOcjena() {
		return ocjena;
	}
	public void setOcjena(int ocjena) {
		this.ocjena = ocjena;
	}
	
	
	@Override
	public String toString() {
		return "{jmbag: " + jmbag + ", ime: " + ime + ", prezime: " + prezime + ", ocjena: " + ocjena
				+ "}";
	}
}
