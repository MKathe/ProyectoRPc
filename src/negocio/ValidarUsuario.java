package negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import datos.ConsultasBasicas;
public class ValidarUsuario {
	
	
	public static boolean verificarUsuario(String user, String pass) {

		boolean encontrado = false;

		ResultSet datos; 

		datos = ConsultasBasicas
				.consultarDatos("SELECT * FROM usuarios WHERE Usuario ='" + user + "' AND Contraseña ='"+pass+"'"); 

		try {

			while (datos.next()) {
				encontrado = true;
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
		}

		return encontrado;
		
	}

}
