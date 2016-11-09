package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import conectionDB.ConectionDB;

public class ConsultasBasicas {

	private static PreparedStatement pstmt = null;
	private static Statement stmt2 = null;

	public static void insertarAlumno(String nombre, String apellido, int edad) {

		Connection miConexion = ConectionDB.getConection();

		try {
			// String SQL = "INSERT INTO <TABLA> (<CAMPOS A INSERTAR>) VALUES (<VALORES A INSERTAR>)";
			// <TABLA> : Nombre de la tabla
			// <CAMPOS A INSERTAR> : nombres de las columnas separados por comas, ejem: (nombre, apellido, edad)
			// <VALORES A INSERTAR> : valores a insertar separados por comas, tiene que coincidir con los campos, ejem: (Jose, Perez, 22)
			
			String SQL = "INSERT INTO alumno (nombre, apellido, edad)" + "VALUES (?, ?, ?)";
			pstmt = miConexion.prepareStatement(SQL);
			pstmt.setString(1, nombre);
			pstmt.setString(2, apellido);
			pstmt.setInt(3, edad);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al realizar la operacion");
		}

	}
	public static void update(String SQL) {

		Connection miConexion = ConectionDB.getConection();

		try {
			// String SQL = "UPDATE <TABLA> SET <CAMPO N> = <VALOR>";
			// <TABLA> : Nombre de la tabla
			// <CAMPO N> : nombre de la columna que se quiere modificar, ejem: nombre
			// <VALOR> : el nuevo valor que va a tener dicho campo, ejem: 'Jose'

			pstmt = miConexion.prepareStatement(SQL);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al realizar la operacion");
		}

	}

	public static void deleted() {

		Connection miConexion = ConectionDB.getConection();

		try {
			// String SQL = "DELETE FROM <TABLA> WHERE <CONDICION DE ELIMINACION>";
			// <TABLA> : Nombre de la tabla
			// <CAMPO N> : nombre de la columna que se quiere modificar, ejem: nombre
			// <VALOR> : el nuevo valor que va a tener dicho campo, ejem: 'Jose'
			String SQL = "DELETE FROM alumno " + "WHERE id = ?";		
			pstmt = miConexion.prepareStatement(SQL);
			pstmt.setInt(1, 2); // borramos el id = 2
			pstmt.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al realizar la operacion");
		}

	}
	public static ResultSet consultarDatos(String cSQL) 
	{
		try
		{  
			ResultSet datos;
			Connection miConexion= ConectionDB.getConection();
			
			pstmt= miConexion.prepareStatement(cSQL);
			datos = pstmt.executeQuery();// Realiza la consulta y guarda los datos en "datos"

			return datos; // retornadatos;

		} 
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta!");
			return null;
		} 

	}
	
	public static ResultSet consultarUsuario(String cSQL) 
	{
		try
		{  
			ResultSet datos;
			Connection miConexion= ConectionDB.getConection();
			
			pstmt= miConexion.prepareStatement(cSQL);
			datos = pstmt.executeQuery();// Realiza la consulta y guarda los datos en "datos"

			return datos; // retorna datos;

		} 
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Usuario inexistenete, verifica tu usuario y password!");
			return null;
		} 

	}
	


}
