package datos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import conectionDB.ConectionDB;
import entidades.Reporte;
import entidades.Usuario;

public class ConsultasBasicas {

	private static PreparedStatement pstmt = null;
	private static Statement stmt2 = null;
	
	public void insertarReporte(Reporte nuevoReporte) {

		Connection miConexion = ConectionDB.getConection();

		try {
			
			Date Fecha = nuevoReporte.getFecha();
			java.sql.Date DEL = new java.sql.Date(Fecha.getTime());
			
			String SQL ="INSERT INTO reportes (Nombre,Tipo,Fecha,Procesador,PrecioProcesador,TiendaProcesador,Memoria,PrecioMemoria,TiendaMemoria,Placa,PrecioPlaca,TiendaPlaca,VideoCard,PrecioVideoCard,TiendaVideoCard,HDD,PrecioHDD,TiendaHDD,CaseFuente,PrecioCaseFuente,TiendaCaseFuente) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String Procesador=nuevoReporte.getListaComponentes()[0].getNombre();
			Double PrecioProcesador=nuevoReporte.getListaComponentes()[0].getPrecio();
			String TiendaProcesador = nuevoReporte.getListaComponentes()[0].getTienda();
			 
			String Memoria=nuevoReporte.getListaComponentes()[1].getNombre();
			Double PrecioMemoria=nuevoReporte.getListaComponentes()[1].getPrecio();
			String TiendaMemoria=nuevoReporte.getListaComponentes()[1].getTienda();
			 
			String Placa=nuevoReporte.getListaComponentes()[2].getNombre();
			 Double PrecioPlaca=nuevoReporte.getListaComponentes()[2].getPrecio();
			 String TiendaPlaca=nuevoReporte.getListaComponentes()[2].getTienda();
			 
			 String VideoCard=nuevoReporte.getListaComponentes()[3].getNombre();
			 Double PrecioVideoCard=nuevoReporte.getListaComponentes()[3].getPrecio();
			 String TiendaVideoCard=nuevoReporte.getListaComponentes()[3].getTienda();
			 
			 String HDD=nuevoReporte.getListaComponentes()[4].getNombre();
			 Double PrecioHDD = nuevoReporte.getListaComponentes()[4].getPrecio();
			 String TiendaHDD = nuevoReporte.getListaComponentes()[4].getTienda();
			 
			 String CaseFuente=nuevoReporte.getListaComponentes()[5].getNombre();
			 Double PrecioCaseFuente=nuevoReporte.getListaComponentes()[5].getPrecio();
			 String TiendaCaseFuente=nuevoReporte.getListaComponentes()[5].getTienda();
		
			
			pstmt = miConexion.prepareStatement(SQL);
			pstmt.setString(1,nuevoReporte.getNombre());
			pstmt.setString(2,nuevoReporte.getTipo());
			pstmt.setDate(3,DEL );
			pstmt.setString(4,Procesador);
			pstmt.setDouble(5,PrecioProcesador);
			pstmt.setString(6,TiendaProcesador);
			pstmt.setString(7,Memoria);
			pstmt.setDouble(8,PrecioMemoria);
			pstmt.setString(9,TiendaMemoria);
			pstmt.setString(10,Placa);
			pstmt.setDouble(11,PrecioPlaca);
			pstmt.setString(12,TiendaPlaca);
			pstmt.setString(13,VideoCard);
			pstmt.setDouble(14,PrecioVideoCard);
			pstmt.setString(15,TiendaVideoCard);
			pstmt.setString(16,HDD);
			pstmt.setDouble(17,PrecioHDD);
			pstmt.setString(18,TiendaHDD);
			pstmt.setString(19,CaseFuente);
			pstmt.setDouble(20,PrecioCaseFuente);
			pstmt.setString(21,TiendaCaseFuente);
			
			pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Inserción correcta");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al realizar la operacion");
		}

	}

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
	public static void insertarUsuario(Usuario usuarionuevo){
		
		Connection conexion = ConectionDB.getConection();
		try {
			PreparedStatement pps = conexion.prepareStatement("INSERT INTO usuarios(Nombres,Apellidos,Usuario,Contraseña,Correo) VALUES(?,?,?,?,?)");
			pps.setString(1, usuarionuevo.getNombre());
			pps.setString(2, usuarionuevo.getApellidos());
			pps.setString(3, usuarionuevo.getUsuario());
			pps.setString(4, usuarionuevo.getContraseña());
			pps.setString(5, usuarionuevo.getCorreo());
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Inserción correcta");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
