package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import JDBCcONNECTION.DBConnection;
import entities.Product;

public class ProductDao {
	DBConnection connection = new DBConnection();
	Scanner input = new Scanner(System.in);

	public void addProduct(Product product) {
		String sql = "insert into products (id, productType, productname, sizeOrQuantity, isbn, price, stockQuantity ) values (?,?,?,?,?,?,?)";

		try {
			PreparedStatement statement = connection.getConnection().prepareCall(sql);
			statement.setInt(1, product.getId());
			statement.setString(2, product.getProductType());
			statement.setString(3, product.getName());
			statement.setString(4, product.getSizeOrQuantity());
			statement.setString(5, product.getIsbn());
			statement.setDouble(6, product.getPrice());
			statement.setInt(7, product.getStockQuantity());

			if (statement.executeUpdate() > 0) {
				System.out.println("Product successfully Added");
			}

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void deletProduct(int id) {

		try {
			String deleteProductsql = "delete from products where id=?";
			PreparedStatement statement = connection.getConnection().prepareStatement(deleteProductsql);
			statement.setInt(1, id);

			if (statement.executeUpdate() > 0) {

				System.out.println("Product deleted successfully");
			} else {
				System.out.println("Failed to delete Product");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void updatePrice(int id, double newprice) {

		try {
			String updatePrice = "update products set price =? where id =?";
			PreparedStatement statement = connection.getConnection().prepareStatement(updatePrice);
			statement.setDouble(1, newprice);
			statement.setInt(2, id);

			if (statement.executeUpdate() > 0) {
				System.out.println("Price updated succesfully");
			} else {
				System.out.println("Price not update check your id and try again");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void updateStockQuantity(int id, int newStockQuantity) {
		String updateStockQuantity = "update products set stockQuantity =? where id =?";

		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(updateStockQuantity);
			statement.setInt(1, id);
			statement.setInt(2, newStockQuantity);

			if (statement.executeUpdate() > 0) {
				System.out.println("newStockQuantity Update");
			} else {
				System.out.println("newStockQuantity not update try again recheck your id");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public Product getProductById(int id, List<Product> productList) {

		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getId() == id) {
				return productList.get(i);
			}
		}

		return new Product();

	}

	public List<Product> productsList() {
		List<Product> productList = new ArrayList<>();

		String sql = "select id, productType, productname, sizeOrQuantity, isbn, price, stockQuantity from products";
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				Product product = new Product();
				product.setId(results.getInt(1));
				product.setProductType(results.getString(2));
				product.setName(results.getString(3));
				product.setSizeOrQuantity(results.getString(4));
				product.setIsbn(results.getString(5));
				product.setPrice(results.getDouble(6));
				product.setStockQuantity(results.getInt(7));

				productList.add(product);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return productList;

	}

	public void viewAllProducts() {

		for (int i = 0; i < productsList().size(); i++) {
			System.out.println("Product id: " + productsList().get(i).getId() + "   " + " Product name: "
					+ productsList().get(i).getName() + " " + "Price: R" + productsList().get(i).getPrice());
		}
	}

	public Product getNewProductDetails() {
		Product product = new Product();
		product.setId(setidProduct());

		System.out.print("Enter product type: ");
		String productType = input.nextLine();
		product.setProductType(productType);

		System.out.print("Enter product name: ");
		String productname = input.nextLine();
		product.setName(productname);

		System.out.print("Enter size or weight with units: ");
		String sizeQuantity = input.nextLine();
		product.setSizeOrQuantity(sizeQuantity);

		System.out.print("Enter isbn :");
		String isbn = input.nextLine();
		product.setIsbn(isbn);

		System.out.print("Enter price R");
		double price = input.nextDouble();
		product.setPrice(price);

		System.out.print("Enter stork quantity: ");
		int stockQuantity = input.nextInt();
		product.setStockQuantity(stockQuantity);

		return product;

	}

	private int setidProduct() {

		int max = productsList().get(0).getId();

		for (int i = 0; i < productsList().size() - 1; i++) {
			int currentId = productsList().get(i).getId();

			if (currentId > max) {

				max = currentId;

			}

		}

		return max + 1;

	}

}
