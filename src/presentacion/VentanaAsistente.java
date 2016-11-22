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

import com.alee.laf.button.WebButton;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import entidades.Producto;
import modeloTablas.ModeloTablaProducto;
import negocio.LeerComputadoras;
import negocio.LeerPCGamer;
import negocio.LeerPCHogarOficina;
import negocio.LeerPCMarca;
import negocio.LeerPCWorkstation;

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
	private WebProgressBar progressBar2;
	private JLabel lblProcesando2;
	private WebScrollPane scrollPane;
	private WebScrollPane scrollPane2;
	private WebTable table;
	private WebTable table2;
	
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
		
		WebButton btnGo = new WebButton(" Filtrar");
		btnGo.setFocusPainted(false);
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
		btnGo.setBounds(847, 28, 117, 41);
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
		
		scrollPane2 = new WebScrollPane(table2);
		scrollPane2.setBounds(27, 99, 632, 452);
		scrollPane2.setVisible(false);
		panelUtilidad.add(scrollPane2);
		
		JButton btnVerDetalles2 = new JButton("");
		btnVerDetalles2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});
		btnVerDetalles2.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/ver-detalles.png")));
		btnVerDetalles2.setFocusPainted(false);
		btnVerDetalles2.setContentAreaFilled(false);
		btnVerDetalles2.setBorderPainted(false);
		btnVerDetalles2.setBorder(null);
		btnVerDetalles2.setBounds(692, 140, 272, 109);
		panelUtilidad.add(btnVerDetalles2);
		
		lblProcesando2 = new JLabel("Procesando...");
		lblProcesando2.setForeground(Color.WHITE);
		lblProcesando2.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblProcesando2.setBounds(719, 501, 97, 17);
		lblProcesando2.setVisible(false);
		panelUtilidad.add(lblProcesando2);
		
		progressBar2 = new WebProgressBar();
		progressBar2.setBounds(719, 529, 230, 17);
		progressBar2.setVisible(false);
		panelUtilidad.add(progressBar2);
		
		
		WebButton btnPCHogarOficina = new WebButton("Hogar y Oficina");
		btnPCHogarOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				if (evento.getSource() == btnPCHogarOficina) {
		  			
		  				progressBar2.setVisible(true);
			  			progressBar2.setIndeterminate(true);
			  			lblProcesando2.setVisible(true);
		  			
			  			LeerPCHogarOficina worker = new LeerPCHogarOficina(miVentanaAsistente,scrollPane2,table2,progressBar2,lblProcesando2);
			  			worker.execute();
		  			
				}
				
				
			}
		});
		btnPCHogarOficina.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		btnPCHogarOficina.setBounds(45, 28, 187, 35);
		panelUtilidad.add(btnPCHogarOficina);
		
		WebButton btnPCGamer = new WebButton("PC Gamer");
		btnPCGamer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				if (evento.getSource() == btnPCGamer) {
		  			
	  				progressBar2.setVisible(true);
		  			progressBar2.setIndeterminate(true);
		  			lblProcesando2.setVisible(true);
	  			
		  			LeerPCGamer worker = new LeerPCGamer(miVentanaAsistente,scrollPane2,table2,progressBar2,lblProcesando2);
		  			worker.execute();
	  			
			}	
				
				
			}
		});
		btnPCGamer.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		btnPCGamer.setBounds(320, 28, 137, 35);
		panelUtilidad.add(btnPCGamer);
		
		WebButton btnDeMarca = new WebButton("PC de Marca");
		btnDeMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				if (evento.getSource() == btnDeMarca) {
		  			
	  				progressBar2.setVisible(true);
		  			progressBar2.setIndeterminate(true);
		  			lblProcesando2.setVisible(true);
	  			
		  			LeerPCMarca worker = new LeerPCMarca(miVentanaAsistente,scrollPane2,table2,progressBar2,lblProcesando2);
		  			worker.execute();
	  			
			}	
				
				
			}
		});
		btnDeMarca.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		btnDeMarca.setBounds(535, 28, 161, 35);
		panelUtilidad.add(btnDeMarca);
		
		WebButton btnPCWorkstation = new WebButton("Workstation");
		btnPCWorkstation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (evento.getSource() == btnPCWorkstation) {

					progressBar2.setVisible(true);
					progressBar2.setIndeterminate(true);
					lblProcesando2.setVisible(true);

					LeerPCWorkstation worker = new LeerPCWorkstation(miVentanaAsistente, scrollPane2, table2, progressBar2,	lblProcesando2);
					worker.execute();

				}
				
				
				
			}
		});
		btnPCWorkstation.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		btnPCWorkstation.setBounds(768, 28, 155, 35);
		panelUtilidad.add(btnPCWorkstation);
		
		JLabel fondoUtilidad = new JLabel("");
		fondoUtilidad.setIcon(new ImageIcon(VentanaAsistente.class.getResource("/resources/vista-asistente-utilidad.jpg")));
		fondoUtilidad.setBounds(0, 0, 974, 583);
		panelUtilidad.add(fondoUtilidad);
		
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

	public WebTable getTable2() {
		return table2;
	}

	public void setTable2(WebTable table2) {
		this.table2 = table2;
	}
	
}
