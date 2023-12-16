package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
	private int clientId;
	private String name;
	private String surname;
	private int age;
	private long idNumber;
	private Contacts contact;
	private List<Product> cart;

	public Client() {
	}

	public Client(int clientId, String name, String surname, int age, long idNumber, Contacts contact,
			List<Product> cart) {
		this.clientId = clientId;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.idNumber = idNumber;
		this.contact = contact;
		this.cart = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getCart() {
		return cart;
	}

	public void setCart(List<Product> cart) {
		this.cart = cart;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Contacts getContact() {
		return contact;
	}

	public void setContact(Contacts contact) {
		this.contact = contact;
	}

	public void addProductToCart(Product product) {
		cart.add(product);
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, cart, clientId, contact, idNumber, name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return age == other.age && Objects.equals(cart, other.cart) && clientId == other.clientId
				&& Objects.equals(contact, other.contact) && idNumber == other.idNumber
				&& Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", surname=" + surname + ", age=" + age
				+ ", idNumber=" + idNumber + ", contact=" + contact + ", cart=" + cart + "]";
	}
	
	

}
