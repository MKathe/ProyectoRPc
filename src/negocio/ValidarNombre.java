package negocio;

import java.sql.ResultSet;
import java.sql.SQLException;

import datos.ConsultasBasicas;
public class ValidarNombre {
	
	public static boolean ValidarNombreUnico(String nombre){
		ResultSet rss;
		rss = ConsultasBasicas.consultarDatos("SELECT Nombre FROM reportes WHERE Nombre = '"+nombre+"'" );
		boolean existe= false;
		try {
			while(rss.next()){
				existe = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return existe;
		
		
	}

}
