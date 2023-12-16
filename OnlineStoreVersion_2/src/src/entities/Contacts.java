package entities;

public class Contacts {
	private int id;
	private String email;
	private long cellNumber;

	public Contacts() {
	}

	public Contacts(int id, String email, long cellNumber) {
		this.id = id;
		this.email = email;
		this.cellNumber = cellNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(long cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	
	

}
