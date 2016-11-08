package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conectionDB.ConectionDB;

public class ConsultasAvanzadas {

	PreparedStatement pstmt = null;
	
	
	public void seleccionar(String SQL) {

		Connection miConexion = ConectionDB.getConection();

		try {
			// ejemplo: String SQL = "INSERT INTO <TABLA> (<CAMPOS A INSERTAR>) VALUES (<VALORES A INSERTAR>)";
			// <TABLA> : Nombre de la tabla
			// <CAMPOS A INSERTAR> : nombres de las columnas separados por comas, ejem: (nombre, apellido, edad)
			// <VALORES A INSERTAR> : valores a insertar separados por comas, tiene que coincidir con los campos, ejem: (Jose, Perez, 22)

			pstmt = miConexion.prepareStatement(SQL);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al realizar la operacion");
		}

	}
	
}
