package entities;

public class Product {
	private int id;
	private String productType;
	private String name;
	private String sizeOrQuantity;
	private String isbn;
	private double price;
	private int stockQuantity;

	public Product() {
	}

	public Product(int id, String productType, String name, String sizeOrQuantity, String isbn, double price,
			int stockQuantity) {
		this.id = id;
		this.productType = productType;
		this.name = name;
		this.sizeOrQuantity = sizeOrQuantity;
		this.isbn = isbn;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSizeOrQuantity() {
		return sizeOrQuantity;
	}

	public void setSizeOrQuantity(String sizeOrQuantity) {
		this.sizeOrQuantity = sizeOrQuantity;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}
