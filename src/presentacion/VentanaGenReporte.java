package presentacion;

import java.awt.EventQueue;

import javax.swing.JDialog;

import entidades.Producto;
import entidades.Reporte;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class VentanaGenReporte extends JDialog {
	private JTextField textFieldNombreReporte;
	private JTextField textFieldOrigenReporte;
	private JTextField textFieldFecha;
	private JTextArea textAreaComponentes;
	private Reporte nuevoReporte;
	
	


	public VentanaGenReporte(VentanaProductos miVentanaProductos, boolean modal, Reporte nuevoReporte) {
		
		super(miVentanaProductos, modal);
		this.nuevoReporte = nuevoReporte;
		
		setTitle("Reporte Generado");
		setBounds(100, 100, 1031, 790);
		getContentPane().setLayout(null);
		
		textFieldNombreReporte = new JTextField();
		textFieldNombreReporte.setBounds(10, 97, 242, 20);
		getContentPane().add(textFieldNombreReporte);
		textFieldNombreReporte.setColumns(10);
		
		textFieldOrigenReporte = new JTextField();
		textFieldOrigenReporte.setBounds(10, 194, 242, 20);
		getContentPane().add(textFieldOrigenReporte);
		textFieldOrigenReporte.setColumns(10);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(12, 271, 233, 20);
		getContentPane().add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		textAreaComponentes = new JTextArea();
		textAreaComponentes.setBounds(17, 346, 593, 369);
		getContentPane().add(textAreaComponentes);
		
		contruirVentana();

	}




	private void contruirVentana() {
		Producto[] lista = new Producto[6];
		lista = nuevoReporte.getListaComponentes();
		
		textFieldNombreReporte.setText(nuevoReporte.getNombre()); 
			
		textFieldOrigenReporte.setText(nuevoReporte.getTipo()); 
		
		textFieldFecha.setText(String.valueOf(nuevoReporte.getFecha()));
		
		String cadena = "";
		
		for(int i=0;i<lista.length;i++){
			cadena = cadena + lista[i].getNombre() + " " + lista[i].getPrecio() + " " + lista[i].getTienda() + "\n\n";
		}
		
		
		textAreaComponentes.setText(cadena);
		
	}
}
