package presentacion;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JToggleButton;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import entidades.Producto;
import modeloTablas.ModeloTablaProducto;
import negocio.LeerComputadoras;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;


public class VentanaAsistente extends JDialog {

	private VentanaAsistente miVentanaAsistente;
	private JTextField textFieldPrecioDe;
	private JTextField textFieldPrecioA;
	private JPanel panelVariable;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private WebProgressBar progressBar;
	private JLabel lblProcesando;
	private WebScrollPane scrollPane;
	private WebTable table;
	
	public VentanaAsistente(VentanaPrincipal miVentanaPrincipal, boolean modal) {
		super(miVentanaPrincipal, modal);
		setTitle("Componentes - RantiyPC");
		setResizable(false);
		setBounds(100, 100, 1032, 790);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JToggleButton tglbtnPrecio = new JToggleButton("");
		tglbtnPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				((CardLayout)panelVariable.getLayout()).show(panelVariable,"panel-precio");
				
			}
		});
		buttonGroup.add(tglbtnPrecio);
		tglbtnPrecio.setSelectedIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/btn_precio_hover.png")));
		tglbtnPrecio.setContentAreaFilled(false);
		tglbtnPrecio.setBorder(null);
		tglbtnPrecio.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/btn_precio.png")));
		tglbtnPrecio.setBounds(562, 77, 220, 50);
		getContentPane().add(tglbtnPrecio);	
		tglbtnPrecio.setSelected(true);
		
		JToggleButton tglbtnUtilidad = new JToggleButton("");
		tglbtnUtilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				((CardLayout)panelVariable.getLayout()).show(panelVariable,"panel-utilidad");
				
			}
		});
		
		buttonGroup.add(tglbtnUtilidad);
		tglbtnUtilidad.setSelectedIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/btn_utilidad_hover.png")));
		tglbtnUtilidad.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/btn_utilidad.png")));
		tglbtnUtilidad.setFocusPainted(false);
		tglbtnUtilidad.setContentAreaFilled(false);
		tglbtnUtilidad.setBorderPainted(false);
		tglbtnUtilidad.setBorder(null);
		tglbtnUtilidad.setBounds(780, 77, 220, 50);
		getContentPane().add(tglbtnUtilidad);
		
		panelVariable = new JPanel();
		panelVariable.setBounds(26, 128, 974, 583);
		getContentPane().add(panelVariable);
		panelVariable.setLayout(new CardLayout(0, 0));
		
		JPanel panelPrecio = new JPanel();
		panelVariable.add(panelPrecio, "panel-precio");
		panelPrecio.setLayout(null);
		
		textFieldPrecioDe = new JTextField();
		textFieldPrecioDe.setBounds(497, 29, 110, 40);
		panelPrecio.add(textFieldPrecioDe);
		textFieldPrecioDe.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		textFieldPrecioDe.setColumns(10);
		
		textFieldPrecioA = new JTextField();
		textFieldPrecioA.setBounds(705, 29, 110, 40);
		panelPrecio.add(textFieldPrecioA);
		textFieldPrecioA.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		textFieldPrecioA.setColumns(10);
		
		JButton btnVerdetalles = new JButton("");
		btnVerdetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1){
					List<Producto> listaPCs = new ArrayList<Producto>();
					List<String> listaEnlaces = new ArrayList<String>();
					
					listaPCs.addAll(LeerComputadoras.listaComputadoras);
					listaEnlaces.addAll(LeerComputadoras.listaEnlaces);
		
					String enlace = listaEnlaces.get(table.getSelectedRow());
					String nombreProducto = listaPCs.get(table.getSelectedRow()).getNombre();
					String precio = Double.toString(listaPCs.get(table.getSelectedRow()).getPrecio());
					String nombreTienda = listaPCs.get(table.getSelectedRow()).getTienda();
					
					VentanaDetallesAsistente miVentanaDetalles = new VentanaDetallesAsistente(miVentanaAsistente, true, enlace, nombreProducto, precio, nombreTienda);
					miVentanaDetalles.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					miVentanaDetalles.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Selecciona un producto!");
				}
				
				
				
			}
		});
		btnVerdetalles.setBounds(692, 140, 272, 109);
		panelPrecio.add(btnVerdetalles);
		btnVerdetalles.setFocusPainted(false);
		btnVerdetalles.setContentAreaFilled(false);
		btnVerdetalles.setBorderPainted(false);
		btnVerdetalles.setBorder(null);
		btnVerdetalles.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/ver-detalles.png")));
		
		JButton btnGo = new JButton(" Filtrar");
		btnGo.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		btnGo.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/search-icon2.png")));
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
		  		if (evento.getSource() == btnGo) {
		  			
		  			Double precioDe, precioA;
		  			
		  			precioDe = Double.parseDouble(textFieldPrecioDe.getText());
		  			precioA = Double.parseDouble(textFieldPrecioA.getText());
		  			
		  			if(precioDe < precioA){
		  				progressBar.setVisible(true);
			  			progressBar.setIndeterminate(true);
			  			lblProcesando.setVisible(true);
		  			
			  			LeerComputadoras worker = new LeerComputadoras(miVentanaAsistente,scrollPane,table,progressBar,lblProcesando,precioDe,precioA);
			  			worker.execute();
		  			}else{
		  				JOptionPane.showMessageDialog(null, "Rango de precios erróneo!");
		  			}
 			
				}
		
			}
		});
		btnGo.setBounds(847, 29, 117, 41);
		panelPrecio.add(btnGo);
	
		scrollPane = new WebScrollPane(table);
		scrollPane.setBounds(27, 99, 632, 452);
		scrollPane.setVisible(false);
		panelPrecio.add(scrollPane);	
		
		lblProcesando = new JLabel("Procesando...");
		lblProcesando.setForeground(Color.WHITE);
		lblProcesando.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblProcesando.setBounds(719, 501, 97, 17);
		lblProcesando.setVisible(false);
		panelPrecio.add(lblProcesando);
		
		progressBar = new WebProgressBar();
		progressBar.setBounds(719, 529, 230, 17);
		progressBar.setVisible(false);
		panelPrecio.add(progressBar);

		JLabel fondoPanelPrecio = new JLabel("");
		fondoPanelPrecio.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/vista-asistente-panelPrecio.jpg")));
		fondoPanelPrecio.setBounds(0, 0, 974, 583);
		panelPrecio.add(fondoPanelPrecio);
		
		JPanel panelUtilidad = new JPanel();
		panelVariable.add(panelUtilidad, "panel-utilidad");
		panelUtilidad.setLayout(null);
		
		JLabel fondoPrincipal = new JLabel("");
		fondoPrincipal.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/vista-asistente4.jpg")));
		fondoPrincipal.setBounds(0, 0, 1026, 762);
		getContentPane().add(fondoPrincipal);

	}
	
	public void setVentanaPrincipal(VentanaAsistente miVentana) {
		   this.miVentanaAsistente = miVentana;
	}

	public WebTable getTable() {
		return table;
	}

	public void setTable(WebTable table) {
		this.table = table;
	}
	
	
	
}
