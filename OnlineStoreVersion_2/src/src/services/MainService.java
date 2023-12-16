package services;

import java.util.Scanner;

import Dao.ClientDao;
import Dao.ProductDao;

public class MainService {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		blank();
		welcome();
		int choice = input.nextInt();

		switch (choice) {

		case 1:
			welcomeClient();
			break;

		case 0:
			welcomeManage();

			break;
		}

	}

	private static void welcome() {
		System.out.println("===========Welcome to deglow.store===========");
		blank();
		System.out.println("Enter '1' if your a client or '0' if you are the Manager ");
		blank();
		System.out.print("Enter Option: ");

	}

	private static void blank() {
		System.out.println(" ");
	}

	private static void welcomeClient() {
		blank();
		System.out.println("==========Welcome to Deglow.Store Client Menu==========");
		blank();
		System.out.println("What would you like to do choose below: ");
		System.out.println("1. Register");
		System.out.println("2. delete your account");
		System.out.println("3. Update email");
		System.out.println("4. Update cell Number");
		System.out.println("5. add Product to cart");
		System.out.println("6. View products ");
		System.out.println("7. view all my products in cart");
		System.out.println("8. view my cart Balance");
		System.out.println("9. Tester");
		blank();
		System.out.print("Enter your Option: ");
		int clientoption = input.nextInt();

		ClientDao clientDao = new ClientDao();
		ProductDao productDao = new ProductDao();

		switch (clientoption) {

		case 1:
			clientDao.signUp(clientDao.getNewclient());
			break;
		case 2:
			blank();
			System.out.print("Enter your user Id Number: ");
			long idNumber = input.nextLong();
			clientDao.deleteClient(idNumber);
			break;

		case 3:
			blank();
			System.out.print("Enter your user Id: ");
			int clientID2 = input.nextInt();
			System.out.print("Enter new Email Address: ");
			String email = input.next();

			clientDao.updateEmail(clientID2, email);

			break;

		case 4:
			blank();
			System.out.print("Enter your user Id: ");
			int clientID22 = input.nextInt();
			System.out.print("Enter new cell Number: ");
			long cell = input.nextLong();

			clientDao.UpdateCellNumber(clientID22, cell);
			break;

		case 5:
			blank();
			System.out.print("Enter your user Id: ");
			int clientID23 = input.nextInt();
			System.out.print("Enter product id: ");
			int productID33 = input.nextInt();

			clientDao.addProductToCart(clientID23, productID33);

			break;

		case 6:
			blank();
			productDao.viewAllProducts();

			break;

		case 7:
			blank();
			System.out.print("Enter your user Id: ");
			int clientID24 = input.nextInt();

			clientDao.viewCartProduct(clientID24);
			break;
		case 9:

			clientDao.getClientByIdNumber(6488746614L);
			break;
		}

	}

	private static void welcomeManage() {
		blank();
		System.out.println("+++++++Welcome manager++++++++++++");
		blank();
		System.out.println("What would you like to do, choose below:");
		blank();
		System.out.println("1. Add product to the database:");
		System.out.println("2. Remove product from the database.");
		System.out.println("3. Update product price");
		blank();
		System.out.print("Enter your choice:");
		int choice1 = input.nextInt();

		ProductDao productDao = new ProductDao();
		switch (choice1) {

		case 1:
			productDao.addProduct(productDao.getNewProductDetails());
			break;

		case 2:
			blank();
			System.out.print("Enter the id of the product you want to delete: ");
			int productId = input.nextInt();
			productDao.deletProduct(productId);
			break;

		case 3:
			blank();
			System.out.print("Enter the id of the Product you want to update Price of: ");
			int productId2 = input.nextInt();
			System.out.print("Enter the new Price of the Product: ");
			double newPrice = input.nextDouble();

			productDao.updatePrice(productId2, newPrice);
			break;

		}

	}

}
