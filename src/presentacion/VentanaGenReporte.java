package presentacion;

import negocio.JasperReportt;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entidades.Producto;
import entidades.Reporte;
import negocio.GuardarReporte;
import conectionDB.ConectionDB;

public class VentanaGenReporte extends JDialog {

		private final JPanel contentPanel = new JPanel();
    	private JTextField textFieldNombreReporte;
		private JTextField textFieldOrigenReporte;
		private JTextField textFieldFecha;
		private JTextArea textAreaComponentes;
		private Reporte nuevoReporte;
		private JasperReportt jr;
		private JButton btnGuardarEnPdf;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VentanaGenerarReporte dialog = new VentanaGenerarReporte();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public VentanaGenReporte(VentanaProductos miVentanaProductos, boolean modal, Reporte nuevoReporte) {
		
		
		super(miVentanaProductos, modal);
		this.nuevoReporte = nuevoReporte;
		
		setTitle("Reporte Generado");
		setBounds(100, 100, 1031, 790);
		getContentPane().setLayout(null);
		
		textFieldNombreReporte = new JTextField();
		textFieldNombreReporte.setBounds(10, 82, 242, 20);
		getContentPane().add(textFieldNombreReporte);
		textFieldNombreReporte.setColumns(10);
		
		textFieldOrigenReporte = new JTextField();
		textFieldOrigenReporte.setBounds(10, 127, 242, 20);
		getContentPane().add(textFieldOrigenReporte);
		textFieldOrigenReporte.setColumns(10);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(10, 175, 242, 20);
		getContentPane().add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		textAreaComponentes = new JTextArea();
		textAreaComponentes.setBounds(95, 272, 834, 317);
		getContentPane().add(textAreaComponentes);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(176, 652, 119, 52);
		btnGuardar.addActionListener(new ManejadorGuardar());
		getContentPane().add(btnGuardar);
		
		JButton btnGuardarEnPdf = new JButton("Guardar en PDF");
		btnGuardarEnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Nombre= textFieldNombreReporte.getText();
				Map<String, java.lang.Object> parametros = new HashMap<>();
				parametros.put("Nombre",Nombre);
				jr = new JasperReportt();
				jr.CrearReporte(ConectionDB.getConection(),new File(".").getAbsolutePath() + "/src/reportes/ReporteGenerado.jasper", parametros);
				GuardarReporte();
			}
		});
		
		btnGuardarEnPdf.setBounds(354, 652, 127, 52);
		getContentPane().add(btnGuardarEnPdf);
		
		contruirVentana();

	}
	public void GuardarReporte() {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showSaveDialog(this);
		String ruta = null;
		File guardar1;
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			guardar1 = fileChooser.getSelectedFile();
			ruta = guardar1.getAbsolutePath();
		    jr.GuardarReporte(ruta);
		    JOptionPane.showMessageDialog(null,"El archivo se a guardado Exitosamente",
				             "Información",JOptionPane.INFORMATION_MESSAGE);
	
		}else{
		JOptionPane.showMessageDialog(null, "Operacion de guardar cancelada", "",JOptionPane.INFORMATION_MESSAGE);
		}
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
	public class ManejadorGuardar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			GuardarReporte.GuardarReporteGenerado(nuevoReporte);
		
		}
		
	}
}
