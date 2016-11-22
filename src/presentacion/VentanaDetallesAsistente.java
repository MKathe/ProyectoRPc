package presentacion;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import entidades.Producto;

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

import javax.swing.JScrollPane;

public class VentanaDetallesAsistente extends JDialog {
	private JTextField textFieldNombreProducto;
	private JTextField textFieldPrecio;
	private JTextField textFieldTienda;
	private WebTextPane textPaneCaracteristicas;

	public VentanaDetallesAsistente(VentanaAsistente miVentanaAsistente, boolean modal, String enlace, String nombreProducto, String precio, String nomTienda) {
		
		super(miVentanaAsistente, modal);	
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
		
		JButton btnGuardarReporte = new JButton("");
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
		
		procesarEnlace(enlace);

	}

	private void procesarEnlace(String enlace) {
		
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
		        	}
		        	i++;
		        }
			}
			
			textPaneCaracteristicas.setText(info);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudieron leer las caracteristicas");
		}
		
		
	}
}
