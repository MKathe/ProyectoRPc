package presentacion;

import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import entidades.Producto;
import entidades.Reporte;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextPane;

import conectionDB.ConectionDB;
import datos.ConsultasBasicas;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import negocio.GuardarReporte;
import negocio.JasperReportt;


public class VentanaDetallesAsistente extends JDialog {
	private JTextField textFieldNombreProducto;
	private JTextField textFieldPrecio;
	private JTextField textFieldTienda;
	private WebTextPane textPaneCaracteristicas;
	private List<String> lineas;
	private String nomProducto, nomTienda, enlace;
	private VentanaDetallesAsistente miVentanaDetallesAsistente;
	private JasperReportt jr;
	
	String correoTienda;
	
	public VentanaDetallesAsistente(VentanaAsistente miVentanaAsistente, boolean modal, String enlace, String nombreProducto, String precio, String nomTienda) {
		
		super(miVentanaAsistente, modal);	
		this.nomProducto = nombreProducto;
		this.nomTienda = nomTienda;
		this.enlace = enlace;
		setTitle("Detalles Producto Seleccionado");
		setResizable(false);
		setBounds(100, 100, 1032, 790);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		textPaneCaracteristicas = new WebTextPane();
		textPaneCaracteristicas.setOpaque(false);
		textPaneCaracteristicas.setFont(new Font("Berlin Sans FB", Font.PLAIN, 22));

		WebScrollPane scrollPane = new WebScrollPane(textPaneCaracteristicas);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(53, 227, 560, 412);
		getContentPane().add(scrollPane);
		//scrollPane.setViewportView(textPaneCaracteristicas);
		
		
		JButton btnContactoTienda = new JButton("");
		btnContactoTienda.setFocusPainted(false);
		btnContactoTienda.setContentAreaFilled(false);
		btnContactoTienda.setBorderPainted(false);
		btnContactoTienda.setBorder(null);
		btnContactoTienda.setIcon(new ImageIcon(VentanaDetallesAsistente.class.getResource("/resources/btn_contactoTienda.png")));
		btnContactoTienda.setBounds(649, 150, 377, 95);
		getContentPane().add(btnContactoTienda);
		btnContactoTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
	                    Reporte nuevoReporte = filtrarCampos();
				        GuardarReporte.GuardarReporteGenerado(nuevoReporte);
				        
				        String ruta = GenerarPDF(nuevoReporte.getNombre());
				        
				        
				        if(!ruta.equals(null)){
				        ruta = ruta+".pdf";
				        VentanaContactoTienda miVentanaContactoTienda = new VentanaContactoTienda(miVentanaDetallesAsistente, true,ruta,correoTienda);
						miVentanaContactoTienda.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						miVentanaContactoTienda.setVisible(true);
						
				        }
				        
						
				
			}
		});
		
		
		JButton btnGuardarReporte = new JButton("");
		btnGuardarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreReporte = JOptionPane.showInputDialog("Ingresa un nombre para este reporte");
				Reporte nuevo = filtrarCampos();
				nuevo.setNombre(nombreReporte);
				VentanaGenReporteAsistente miVentanaGenReporte = new VentanaGenReporteAsistente (miVentanaDetallesAsistente, true, nuevo);
				miVentanaGenReporte.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                miVentanaGenReporte.setVisible(true);
				
			}
		});
		btnGuardarReporte.setFocusPainted(false);
		btnGuardarReporte.setContentAreaFilled(false);
		btnGuardarReporte.setBorderPainted(false);
		btnGuardarReporte.setBorder(null);
		btnGuardarReporte.setIcon(new ImageIcon(VentanaDetallesAsistente.class.getResource("/resources/btn_guardarv3.png")));
		btnGuardarReporte.setBounds(700, 320, 282, 102);
		getContentPane().add(btnGuardarReporte);
		
		textFieldNombreProducto = new JTextField();
		textFieldNombreProducto.setEditable(false);
		textFieldNombreProducto.setBorder(null);
		textFieldNombreProducto.setForeground(Color.WHITE);
		textFieldNombreProducto.setOpaque(false);
		textFieldNombreProducto.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 35));
		textFieldNombreProducto.setBounds(32, 55, 956, 45);
		textFieldNombreProducto.setColumns(10);
		textFieldNombreProducto.setText(nombreProducto);
		textFieldNombreProducto.setCaretPosition(0); // Para que se muestre el texto desde el inicio
		getContentPane().add(textFieldNombreProducto);
		
		
		JLabel lblPrecio = new JLabel("Precio:   S/.");
		lblPrecio.setForeground(Color.LIGHT_GRAY);
		lblPrecio.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		lblPrecio.setBounds(53, 173, 135, 34);
		getContentPane().add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setForeground(Color.WHITE);
		textFieldPrecio.setBorder(null);
		textFieldPrecio.setOpaque(false);
		textFieldPrecio.setFont(new Font("Berlin Sans FB", Font.PLAIN, 29));
		textFieldPrecio.setBounds(198, 174, 240, 32);
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setText(precio);
		getContentPane().add(textFieldPrecio);
		
		JLabel lblTienda = new JLabel("Tienda:");
		lblTienda.setForeground(Color.LIGHT_GRAY);
		lblTienda.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		lblTienda.setBounds(53, 667, 91, 34);
		getContentPane().add(lblTienda);
		
		textFieldTienda = new JTextField();
		textFieldTienda.setOpaque(false);
		textFieldTienda.setBorder(null);
		textFieldTienda.setForeground(Color.WHITE);
		textFieldTienda.setEditable(false);
		textFieldTienda.setFont(new Font("Berlin Sans FB", Font.PLAIN, 28));
		textFieldTienda.setColumns(10);
		textFieldTienda.setBounds(168, 668, 240, 32);
		textFieldTienda.setText(nomTienda);
		getContentPane().add(textFieldTienda);
		
		JLabel fondoDetallesAsistente = new JLabel("");
		fondoDetallesAsistente.setIcon(new ImageIcon(VentanaDetallesAsistente.class.getResource("/resources/vista_asist_mini_detalles.jpg")));
		fondoDetallesAsistente.setBounds(0, 0, 1026, 762);
		getContentPane().add(fondoDetallesAsistente);
		
		procesarEnlace();
		
		obtenerCorreo();
		
		System.out.println("Correo de la tienda: "+correoTienda);

	}

	private void procesarEnlace() {
		lineas = new ArrayList<String>();
		
		Document document;
		String info = "";
		 try {
			 		 
			document = Jsoup.connect(enlace).get();
		
			Elements tablas = document.select("table.data-table");			
			int i=0;
			for (Element tabla : tablas) {
		        for (Element fila : tabla.select("tr")) {	        	
		        	if(i>1){
		        		info = info + fila.text() + "\n";
		        		lineas.add(fila.text());
		        	}
		        	i++;
		        }
			}
			
			textPaneCaracteristicas.setText(info);
			//filtrarCampos(lineas);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudieron leer las caracteristicas");
		}
		
		 
		 
		 
		
	}

	private Reporte filtrarCampos() {
		
		Producto[] listaComponentes = new Producto[6];
		String modelo = "";
		String procesador = "";
		String placa = "";
		String memoria = "";
		String hdd = "";
		String video = "";
		String fuente = "";
		Producto nuevo;
		
		for (String linea : lineas) {
			if (linea.indexOf("Modelo") != -1) {
				modelo = linea.substring(7);
			}
			if (linea.indexOf("Placa") != -1) {
				placa = linea.substring(6);
			}
			if (linea.indexOf("Procesador") != -1) {
				procesador = linea.substring(11);
			}

			if (linea.indexOf("Memoria") != -1) {
				memoria = linea.substring(12);
			}
			if (linea.indexOf("Disco Duro") != -1) {
				hdd = linea.substring(11);
			}

			if (linea.indexOf("Tarjeta de video") != -1) {

				if (linea.substring(17).equals("No")) {
					video = "Integrado";
				} else {
					video = linea.substring(17);
				}

			}

			if (linea.indexOf("Case") != -1) {

				fuente = linea.substring(5);
			}

			if (linea.indexOf("Fuente") != -1) {

				fuente = fuente + " " + linea.substring(16);
			}

		}
		
		if(placa.equals("")){
			placa = modelo;
		}
		Double precio = 0.0;
		
		// PROCESADOR
		nuevo = new Producto(procesador,precio, nomTienda);
		listaComponentes[0] = nuevo;
		// MEMORIA
		nuevo = new Producto(memoria,precio , nomTienda);
		listaComponentes[1] = nuevo;
		// PLACA
		nuevo = new Producto(placa,precio, nomTienda);
		listaComponentes[2] = nuevo;
		// VIDEOCARD
		nuevo = new Producto(video, precio, nomTienda);
		listaComponentes[3] = nuevo;
		// HDD
		nuevo = new Producto(hdd,precio, nomTienda);
		listaComponentes[4] = nuevo;
		// CASE-FUENTE
		nuevo = new Producto(fuente,precio, nomTienda);
		listaComponentes[5] = nuevo;
		
		Date fecha = new Date();
		
		Reporte nuevoReporte = new Reporte(nomProducto, "Modulo Asistente",fecha, listaComponentes);
		
		return nuevoReporte;
	}
	public String GenerarPDF (String nombre){
		
		String Nombre= nombre;
		Map<String, java.lang.Object> parametros = new HashMap<>();
		parametros.put("Nombre",Nombre);
		jr = new JasperReportt();
		jr.CrearReporte(ConectionDB.getConection(),new File(".").getAbsolutePath() + "/src/reportes/ReporteG.jasper", parametros);
		String ruta = GuardarReporte();
		return ruta;
			
	}
	
	public String GuardarReporte() {
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
		ruta = null;
		}
		
		return ruta;
		
	}
	
	public void obtenerCorreo(){

		ResultSet datos; 

		datos = ConsultasBasicas
				.consultarDatos("SELECT * FROM tiendas WHERE nombre ='" + nomTienda + "'"); 

		try {
			
			while (datos.next()) {
				correoTienda = datos.getString("email");
			}
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
		}

	}
}
