package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import JDBCcONNECTION.DBConnection;
import entities.Client;
import entities.Contacts;
import entities.Product;

public class ClientDao {
	DBConnection dbConnection = new DBConnection();
	Scanner input = new Scanner(System.in);
	ProductDao productDao = new ProductDao();

	public ClientDao() {

	}

	public void signUp(Client client) {

		try {
			String sql1 = "insert into contacts(id, email, cellNumber) values (?,?,?)";
			PreparedStatement ps1 = dbConnection.getConnection().prepareStatement(sql1);
			ps1.setInt(1, client.getClientId());
			ps1.setString(2, client.getContact().getEmail());
			ps1.setLong(3, client.getContact().getCellNumber());

			if (ps1.executeUpdate() > 0) {

				System.out.println(" ");
				System.out.println("=====================================");
				System.out.println("Contacts successfully inserted");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {

			String sql = "insert into clients(id, clientName, surname, age, idNumber, contacts) values(?,?,?,?,?,?)";
			PreparedStatement ps = dbConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, client.getContact().getId());
			ps.setString(2, client.getName());
			ps.setString(3, client.getSurname());
			ps.setInt(4, client.getAge());
			ps.setLong(5, client.getIdNumber());
			ps.setInt(6, client.getContact().getId());

			if (ps.executeUpdate() > 0) {
				System.out.println("Client Successfully registerd");
				System.out.println("Your id is: " + client.getClientId());
				System.out.println("=====================================");
				System.out.println(" ");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void deleteClient(long idNumber) {

		String query = "delete from clients where idNumber=?";

		try {
			PreparedStatement statement2 = dbConnection.getConnection().prepareStatement(query);
			statement2.setLong(1, idNumber);

			if (statement2.executeUpdate() > 0) {
				System.out.println("Deleted");
			} else {
				System.out.println("Failed to delete row in contacts table");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void updateEmail(int id, String newemail) {
		try {
			String updateEmail = "update contacts set email= ? where id = ?";
			PreparedStatement statement = dbConnection.getConnection().prepareStatement(updateEmail);
			statement.setString(1, newemail);
			statement.setInt(2, id);

			if (statement.executeUpdate() > 0) {
				System.out.println("");
				System.out.println("======================================");
				System.out.println("Email successfuly Updated:");
				System.out.println("======================================");
				System.out.println("");
			} else {
				System.out.println("Email failed to update try again");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void UpdateCellNumber(int id, long cellNumber) {
		try {
			String updateCell = "update contacts set cellNumber= ? where id = ?";
			PreparedStatement statement = dbConnection.getConnection().prepareStatement(updateCell);
			statement.setLong(1, cellNumber);
			statement.setInt(2, id);

			if (statement.executeUpdate() > 0) {
				System.out.println("");
				System.out.println("======================================");
				System.out.println("cellNumber successfuly Updated:");
				System.out.println("======================================");
				System.out.println("");
			} else {
				System.out.println("cellNumber failed to update try again");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public Client getClientByIdNumber(long idNumber) {

		String query = "select * from clients where idNumber=?";
		Client client = new Client();

		try {
			PreparedStatement statement2 = dbConnection.getConnection().prepareStatement(query);
			statement2.setLong(1, idNumber);

			try (ResultSet resultset = statement2.executeQuery()) {
				if (resultset.next()) {

					client.setIdNumber(resultset.getLong("idNumber"));
					client.setName(resultset.getString("clientName"));

				}
			}

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println(client.toString());
		return client;

	}

	public List<Client> clients() {
		List<Client> clients = new ArrayList<>();

		String sql = "select id, clientName, surname, age, idNumber from clients";
		String sql2 = "select id, email, cellNumber from contacts";

		try {
			PreparedStatement pps = dbConnection.getConnection().prepareStatement(sql);
			ResultSet result = pps.executeQuery();

			PreparedStatement pps2 = dbConnection.getConnection().prepareStatement(sql2);
			ResultSet result2 = pps2.executeQuery();

			while (result.next() & result2.next()) {

				Client client = new Client();
				client.setClientId(result.getInt(1));
				client.setName(result.getString(2));
				client.setSurname(result.getNString(3));
				client.setAge(result.getInt(4));
				client.setIdNumber(result.getLong(5));

				Contacts contact = new Contacts();
				contact.setId(result2.getInt(1));
				contact.setEmail(result2.getString(2));
				contact.setCellNumber(result2.getLong(3));

				ArrayList<Product> cart = new ArrayList<>();

				client.setContact(contact);
				client.setCart(cart);
				clients.add(client);

			}

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return clients;

	}

	public void addProductToCart2(long idNumber, int productId) {

	}

	public void addProductToCart(long idNumber, int productId) {

		System.out.println("");
		productDao.viewAllProducts();

		Client client = getClientByIdNumber(idNumber);

		System.out.println(client.toString());

		// create a new ArrayList
		// use while loop to add all the products selected by the user
		// save products against the user

		System.out.println("");
		System.out.print("Enter '1' to ad products to the cart and '100' when finish or exit");
		int option = input.nextInt();

		List<Integer> cart = new ArrayList<>();
		while (option == 1) {
			System.out.println("");
			System.out.print("Enter product id: ");
			int productOption = input.nextInt();

			cart.add(productOption);

			if (productOption == 100) {
				break;
			}

		}
		if (option == 0) {
			System.out.println("");
			System.out.println("Thank u ");
			System.out.println("");

		}

		for (int i = 0; i < cart.size() - 1; i++) {
			System.out.println(cart.get(i));
		}

	}

	public void viewCartProduct(int idofsuer) {
		Client client = getClientByIdNumber(idofsuer);

		for (int i = 0; i < client.getCart().size(); i++) {
			System.out.println(client.getCart().get(i).getName() + " " + client.getCart().get(i).getId());
		}

	}

	public ArrayList<Integer> productIdFromCartTable(int userId) {
		ArrayList<Integer> cartProductId = new ArrayList<>();

		try {
			String selectProducts = "select productId from cart where clientId = ?";
			PreparedStatement ps = dbConnection.getConnection().prepareStatement(selectProducts);
			ResultSet result = ps.executeQuery();

			while (result.next()) {

				int productId = result.getInt(2);
				cartProductId.add(productId);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return cartProductId;

	}

	public void tester() {
		for (int i = 0; i < productIdFromCartTable(2).size(); i++) {
			System.out.println(productIdFromCartTable(1).get(i));

		}
	}

	public Client getNewclient() {
		int usersNumber = clients().size() + 1;

		Client client = new Client();
		client.setClientId(usersNumber);

		System.out.print("Enter name:");
		String name = input.nextLine();
		client.setName(name);

		System.out.print("Enter surname: ");
		String surname = input.nextLine();
		client.setSurname(surname);

		System.out.print("Enter age: ");
		int age = input.nextInt();
		client.setAge(age);

		System.out.print("Enter ID Number: ");
		long idNumber = input.nextLong();
		client.setIdNumber(idNumber);

		Contacts contacts = new Contacts();
		contacts.setId(usersNumber);

		System.out.print("Enter email: ");
		String email = input.next();
		contacts.setEmail(email);

		System.out.print("Enter cell number: ");
		long cell = input.nextLong();
		contacts.setCellNumber(cell);

		client.setContact(contacts);

		return client;

	}

}
