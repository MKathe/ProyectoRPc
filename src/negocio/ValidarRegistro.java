package negocio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import datos.ConsultasBasicas;

public class ValidarRegistro {
	
	public static boolean ValidarIgualdad( String contraseña1,String contraseña2){
		boolean igual=false;
		if(contraseña1.equals(contraseña2)){
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
    
    public static void validarSoloLetras(JTextField campo){
		campo.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char c = e.getKeyChar();
				int k = (int)e.getKeyChar();
				System.out.println(k);
				if(Character.isDigit(c) || (!(k >= 65 && k <= 90) && !(k >= 97 && k <= 122)
					&& k != 241 && k != 209 && k != 225 && k != 233 && k != 237 && k != 243
					&& k != 250 && k != 193 && k != 201 && k != 205 && k != 211 && k != 218)){
					e.consume();
				}
			}
		});
	}
   
}
