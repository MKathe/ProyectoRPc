package presentacion;

import java.awt.BorderLayout;
import java.lang.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import negocio.JasperReportt;
import negocio.OrdenarReportes;
import negocio.BuscarReportes;

import com.alee.laf.WebLookAndFeel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import org.omg.CORBA.Object;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import conectionDB.ConectionDB;
import datos.ConsultasBasicas;
import entidades.Producto;
import entidades.Reporte;
import modeloTablas.ModeloTablaReporte;

import negocio.ValidarRangoFecha;
import negocio.BuscarReportes;
import negocio.OrdenarReportes;
import negocio.LeerReportes;

import conectionDB.ConectionDB;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;


public class VentanaReportes extends JDialog {

	private VentanaReportes miVentanaReportes;
	private JPanel contentPane;

	private List<Reporte> listadeReportes;
	private JTable TablaDeReportes;
	private JScrollPane spTablaDeReportes;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private ResultSet rss, ResultadodeBusqueda;
	private JButton btnBuscar;
	private JButton btnMostrarReporte;
	private JButton btnGuardar;
	private JCalendar Calendario;
	private JDateChooser dateAl;
	private JDateChooser dateDel;

	private JFileChooser fileChooser;
	private JTextField textBusqueda;
	private Connection db;
	private JasperReportt jr;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReportes frame = new VentanaReportes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/*
	 * Create the frame.
	 */

	
	public VentanaReportes(/*VentanaPrincipal miVentanaPrincipal, boolean modal
							 */) 
	{
		/* super(miVentanaPrincipal, modal); */

		setTitle("Ventana Reportes");
		setBounds(100, 100, 910, 672);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBounds(198, 73, 131, 23);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Buscar por", "Nombre", "Tipo", "Fecha" }));
		comboBox.addItemListener(new ManejadorFecha());

		textBusqueda = new JTextField();
		textBusqueda.setFont(new Font("Tahoma", Font.BOLD, 14));
		textBusqueda.setForeground(new Color(255, 255, 255));
		textBusqueda.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textBusqueda.setBackground(UIManager.getColor("ToolBar.highlight"));
		textBusqueda.setBounds(399, 70, 393, 25);
		contentPane.add(textBusqueda);
		textBusqueda.setColumns(10);
		textBusqueda.setBorder(null);
		textBusqueda.setOpaque(false);
		contentPane.add(comboBox);

		comboBox1 = new JComboBox();
		comboBox1.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBox1.setForeground(SystemColor.desktop);
		comboBox1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setModel(new DefaultComboBoxModel(new String[] { "Ordenar por", "Nombre", "Tipo", "Fecha" }));
		comboBox1.setBounds(30, 73, 145, 23);
		comboBox1.addItemListener(new ManejadorOrdenar());
		contentPane.add(comboBox1);

		btnBuscar = new JButton("");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setPressedIcon(new ImageIcon(VentanaReportes.class.getResource("/resources/lupa2.png")));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBorder(UIManager.getBorder("CheckBox.border"));
		btnBuscar.setIcon(new ImageIcon(VentanaReportes.class.getResource("/resources/lupa3.png")));
		btnBuscar.setFont(new Font("Papyrus", Font.PLAIN, 14));
		btnBuscar.setBounds(808, 53, 53, 56);
		btnBuscar.addActionListener(new ManejadorBuscarPor());

		contentPane.add(btnBuscar);
		

		btnGuardar = new JButton("");
		btnGuardar.setEnabled(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBorderPainted(false);
		btnGuardar.setIcon(new ImageIcon(VentanaReportes.class.getResource("/resources/boton-guardar-reporte.png")));
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBounds(527, 555, 218, 68);
		btnGuardar.addActionListener(new GuardarReporte());
		contentPane.add(btnGuardar);

		btnMostrarReporte = new JButton("");
		btnMostrarReporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarReporte.setBorderPainted(false);
		btnMostrarReporte.setIcon(new ImageIcon(VentanaReportes.class.getResource("/resources/btn_mostrar6.png")));
		btnMostrarReporte.setContentAreaFilled(false);
		btnMostrarReporte.addActionListener(new ManejadorDeMostrar());
		btnMostrarReporte.setBounds(113, 555, 246, 68);
		contentPane.add(btnMostrarReporte);

		JLabel lblReportes = new JLabel("Reportes");
		lblReportes.setBackground(Color.WHITE);
		lblReportes.setForeground(Color.WHITE);
		lblReportes.setFont(new Font("PeacerfulDay", Font.BOLD, 39));
		lblReportes.setBounds(30, 0, 373, 62);
		contentPane.add(lblReportes);

		JLabel lblListadoDeReportes = new JLabel("Listado de reportes:");
		lblListadoDeReportes.setForeground(Color.WHITE);
		lblListadoDeReportes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListadoDeReportes.setBounds(30, 153, 177, 14);
		contentPane.add(lblListadoDeReportes);

		listadeReportes = new ArrayList<Reporte>();
		LeerReportes.CargarListaDeReportes(listadeReportes);
		TableModel tableModel = new ModeloTablaReporte(listadeReportes);

		JLabel lblDel = new JLabel("Del:");
		lblDel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDel.setForeground(Color.WHITE);
		lblDel.setBounds(30, 124, 46, 14);
		contentPane.add(lblDel);

		JLabel lblAl = new JLabel("Al:");
		lblAl.setForeground(Color.WHITE);
		lblAl.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAl.setBounds(210, 119, 32, 25);

		contentPane.add(lblAl);
		
		TablaDeReportes = new JTable(tableModel);
		TablaDeReportes.setFont(new Font("Papyrus", Font.PLAIN, 11));
		TablaDeReportes.setFillsViewportHeight(true);
		TablaDeReportes.setColumnSelectionAllowed(true);
		TablaDeReportes.setCellSelectionEnabled(true);

		// TablaDeReportes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spTablaDeReportes = new JScrollPane();
		spTablaDeReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spTablaDeReportes.setSize(790, 315);
		spTablaDeReportes.setLocation(49, 198);
		spTablaDeReportes.setViewportView(TablaDeReportes);

		contentPane.add(spTablaDeReportes);

		dateDel = new JDateChooser("yyyy/MM/dd", "##/##/####", '_');
		dateDel.setEnabled(false);
		dateDel.setBounds(71, 119, 115, 23);
		contentPane.add(dateDel);
		dateDel.setMaxSelectableDate(new Date());

		dateAl = new JDateChooser("yyyy/MM/dd", "##/##/####", '_');
		dateAl.setEnabled(false);
		dateAl.setBounds(244, 121, 115, 23);
		contentPane.add(dateAl);
		dateAl.setMaxSelectableDate(new Date());

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VentanaReportes.class.getResource("/resources/vista_reporte.png")));
		label.setBounds(0, 0, 894, 634);
		contentPane.add(label);
		
		
		
	}

	public class ManejadorDeMostrar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int filaSeleccionada = TablaDeReportes.getSelectedRow();
			
			if (filaSeleccionada >= 0) {
				Integer a = listadeReportes.get(filaSeleccionada).getIndice();
				Map<String, java.lang.Object> parametros = new HashMap<>();
				parametros.put("Indice", a);
				jr = new JasperReportt();
				jr.CrearReporte(ConectionDB.getConection(),new File(".").getAbsolutePath() + "/src/reportes/reporteR.jasper", parametros);
				jr.VerReporte();
				btnGuardar.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "No ha seleccionado ningun reporte", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
            Insertar();
		}
		
		
	}
	public  void Insertar() {
		
	}

	public class GuardarReporte implements ActionListener {
		public void actionPerformed(ActionEvent e) {
               GuardarReporte();
		}
	}

	public void GuardarReporte() {
		fileChooser = new JFileChooser();
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

	public class ManejadorOrdenar implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			
			OrdenarReportes.OrdenarPor(listadeReportes,comboBox1.getSelectedIndex());
			TableModel tableModel2 = new ModeloTablaReporte(listadeReportes);
			TablaDeReportes.setModel(tableModel2);
			
		}
	
	}

	public class ManejadorBuscarPor implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int indice = comboBox.getSelectedIndex();
			 
			boolean encontrado;
			encontrado=BuscarReportes.BuscarPor(indice,listadeReportes,textBusqueda.getText(),dateDel.getDate(),dateAl.getDate());
			if(encontrado){
		         TableModel tableModel3 = new ModeloTablaReporte(listadeReportes);
		         TablaDeReportes.setModel(tableModel3);
		    }else{
		        	 JOptionPane.showMessageDialog(null, "Busqueda no encontrada","BuscarPor", JOptionPane.INFORMATION_MESSAGE);
		         }
			
			
			
			
			
          
		}

	}

	public class ManejadorFecha implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (comboBox.getSelectedIndex() == 3) {

				dateDel.setEnabled(true);
				dateAl.setEnabled(true);
			}
		}
	}
	
	public void setVentanaPrincipal(VentanaReportes miVentana) {
		miVentanaReportes = miVentana;
	}
}
