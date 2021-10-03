import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoadGame {

	
	public static Connection getConnection() {
		try {
			String url = "jdbc:postgresql://localhost:5432/SDA89";
			String username = "postgres";
			String password = "..";
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Po³¹czono");
			return conn;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
}

}
