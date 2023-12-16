package JDBCcONNECTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection connection;

	public DBConnection() {
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinestore2_deglow", "root",
						"Developer@2023");

			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return connection;

	}

}
