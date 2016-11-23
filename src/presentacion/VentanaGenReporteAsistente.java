package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conectionDB.ConectionDB;
import entidades.Producto;
import entidades.Reporte;
import negocio.GuardarReporte;
import negocio.JasperReportt;
import negocio.ValidarNombre;
import presentacion.VentanaGenReporte.GenerarPDF;
import presentacion.VentanaGenReporte.ManejadorGuardarEnBD;

public class VentanaGenReporteAsistente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombreReporte;
	private JTextField textFieldOrigenReporte;
	private JTextField textFieldFecha;
	private Reporte nuevoReporte;
	private JasperReportt jr;
	private JButton btnGenerarPDF;
	private JButton btnGuardar;
	List list;
	private JLabel labelFondo;
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
public VentanaGenReporteAsistente(VentanaDetallesAsistente miVentanaDetalles, boolean modal, Reporte nuevoReporte) {
	
	
	super(miVentanaDetalles, modal);
	this.nuevoReporte = nuevoReporte;
	
	setTitle("Reporte Generado");
	setBounds(100, 100, 794, 630);
	getContentPane().setLayout(null);
	
	textFieldNombreReporte = new JTextField();
	textFieldNombreReporte.setFont(new Font("Tahoma", Font.BOLD, 12));
	textFieldNombreReporte.setForeground(Color.WHITE);
	textFieldNombreReporte.setBorder(null);
	textFieldNombreReporte.setOpaque(false);
	textFieldNombreReporte.setBounds(171, 80, 333, 20);
	getContentPane().add(textFieldNombreReporte);
	textFieldNombreReporte.setColumns(10);
	
	textFieldOrigenReporte = new JTextField();
	textFieldOrigenReporte.setForeground(Color.WHITE);
	textFieldOrigenReporte.setFont(new Font("Tahoma", Font.BOLD, 12));
	textFieldOrigenReporte.setBorder(null);
	textFieldOrigenReporte.setOpaque(false);
	textFieldOrigenReporte.setBounds(171, 139, 333, 20);
	getContentPane().add(textFieldOrigenReporte);
	textFieldOrigenReporte.setColumns(10);
	
	textFieldFecha = new JTextField();
	textFieldFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
	textFieldFecha.setForeground(Color.WHITE);
	textFieldFecha.setBorder(null);
	textFieldFecha.setOpaque(false);
	textFieldFecha.setBounds(171, 202, 333, 20);
	getContentPane().add(textFieldFecha);
	textFieldFecha.setColumns(10);
	
	btnGuardar = new JButton("");
	btnGuardar.setIcon(new ImageIcon(VentanaGenReporte.class.getResource("/resources/btngenerar.png")));
	btnGuardar.setContentAreaFilled(false);
	btnGuardar.setBorder(null);
	btnGuardar.setBounds(558, 314, 210, 61);

	getContentPane().add(btnGuardar);
	
	btnGenerarPDF = new JButton("");
	btnGenerarPDF.setEnabled(false);
	btnGenerarPDF.setIcon(new ImageIcon(VentanaGenReporte.class.getResource("/resources/geneerarpfs.png")));
	btnGenerarPDF.setContentAreaFilled(false);
	btnGenerarPDF.setBounds(560, 413, 191, 99);
	
	getContentPane().add(btnGenerarPDF);
	
	List list = new List();
	list.setBackground(Color.LIGHT_GRAY);
	list.setBounds(43, 274, 461, 272);
	getContentPane().add(list);
	
	btnGuardar.addActionListener(new ManejadorGuardarEnBD());
	btnGenerarPDF.addActionListener(new GenerarPDF());
	
	Producto[] lista = new Producto[6];
	lista = nuevoReporte.getListaComponentes();
	
	textFieldNombreReporte.setText(nuevoReporte.getNombre()); 
		
	textFieldOrigenReporte.setText(nuevoReporte.getTipo()); 
	
	
	textFieldFecha.setText(String.valueOf(nuevoReporte.getFecha()));
	
	labelFondo = new JLabel("");
	labelFondo.setIcon(new ImageIcon(VentanaGenReporte.class.getResource("/resources/vista_ReporteGenerado.png")));
	labelFondo.setBounds(0, 0, 778, 592);
	getContentPane().add(labelFondo);
	
	String cadena = "";
	
	for(int i=0;i<lista.length;i++){
		cadena =  lista[i].getNombre() + " " + lista[i].getPrecio() + " " + lista[i].getTienda();
	    list.add(cadena);
	}
	
	

}
public class ManejadorGuardarEnBD implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String Nombre = textFieldNombreReporte.getText();
		boolean existe;
		existe = ValidarNombre.ValidarNombreUnico(Nombre);
		if(!existe){
			
			nuevoReporte.setNombre(Nombre);
			GuardarReporte.GuardarReporteGenerado(nuevoReporte);
			
		}else{
			JOptionPane.showMessageDialog(null, "Ingrese otro nombre para guardar el reporte", "",JOptionPane.INFORMATION_MESSAGE);
		}
	
		// TODO Auto-generated method stub
		btnGenerarPDF.setEnabled(true);
		
	}
	
}

public class GenerarPDF implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
	String Nombre= textFieldNombreReporte.getText();
	Map<String, java.lang.Object> parametros = new HashMap<>();
	parametros.put("Nombre",Nombre);
	jr = new JasperReportt();
	jr.CrearReporte(ConectionDB.getConection(),new File(".").getAbsolutePath() + "/src/reportes/ReporteG.jasper", parametros);
	GuardarReporte();
	}
	
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
}
