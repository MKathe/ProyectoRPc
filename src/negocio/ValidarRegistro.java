package negocio;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.ConsultasBasicas;

public class ValidarRegistro {
	
	public static boolean ValidarIgualdad( String contrase�a1,String contrase�a2){
		boolean igual=false;
		if(contrase�a1.equals(contrase�a2)){
			igual = true;
		}
		return igual;
		
	}
    public static boolean  VerificarExitencia(String usuario){
    	ResultSet rss;
		rss = ConsultasBasicas.consultarDatos("SELECT Usuario FROM usuarios WHERE Usuario = '"+ usuario +"'" );
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
