package group.esig.teste.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:postgresql://localhost/teste_esig?user=postgres&password=12345";
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(url);
	}

}
