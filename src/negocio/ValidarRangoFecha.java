package negocio;

import java.util.Date;

public class ValidarRangoFecha {
	public static boolean validar(Date inicio,Date fin){
		
		if( inicio.compareTo(fin)>=0){
			return false;
		}else{
			return true;
		}
		
	}

}
