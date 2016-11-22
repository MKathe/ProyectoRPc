package negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import datos.ConsultasBasicas;
import entidades.Usuario;
import presentacion.Login;
public class ValidarUsuario {
	
	
	public static boolean verificarUsuario(String user, String pass) {

		boolean encontrado = false;

		ResultSet datos; 

		datos = ConsultasBasicas
				.consultarDatos("SELECT * FROM usuarios WHERE Usuario ='" + user + "' AND Contraseña ='"+pass+"'"); 

		try {
			
			//creamos el objeto del usuario que ingresó
			Usuario usuarioIngresado = new Usuario();
			
			while (datos.next()) {
				encontrado = true;
				
				usuarioIngresado.setNombre(datos.getString("Nombres"));
				usuarioIngresado.setApellidos(datos.getString("Apellidos"));
				usuarioIngresado.setUsuario(datos.getString("Usuario"));
				usuarioIngresado.setContraseña(datos.getString("Contraseña"));
				usuarioIngresado.setCorreo(datos.getString("Correo"));
			}
			
			Login.setUsuario(usuarioIngresado);
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
		}

		return encontrado;
		
	}

}
