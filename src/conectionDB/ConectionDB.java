package conectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectionDB {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/rantiypc";

	// Credenciales para acceder a la base de datos
	static final String USER = "root";
	static final String PASS = "";
	
	static Connection miConexion = null;
	
	public ConectionDB() {

	}

	public static Connection getConection() {
		
		// PASO 1: Carga el driver dinamicamente
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException ex) {
			
			JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
			
		}

		// PASO 2: Establece la conexion
		try {

			//JOptionPane.showMessageDialog(null, "Conectando..");
			miConexion = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Conexión fallida!");
			miConexion = null;

		}

		return miConexion;
	}

}
