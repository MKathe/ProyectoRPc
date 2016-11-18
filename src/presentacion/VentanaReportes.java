package presentacion;

import java.awt.BorderLayout;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import conectionDB.ConectionDB;
import datos.ConsultasBasicas;
import entidades.Reporte;
import modeloTablas.ModeloTablaReporte;
import negocio.ValidarRangoFecha;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class VentanaReportes extends JDialog {

	private VentanaReportes miVentanaReportes;
	private JPanel contentPane;
	private JTable table;
	private List<Reporte> listadeReportes;
	private JTable TablaDeReportes;
	private JScrollPane spTablaDeReportes;
    private JComboBox comboBox;
    private JComboBox comboBox1 ;
    private ResultSet rss,ResultadodeBusqueda;
    private JButton btnBuscar;
    private JButton btnMostrarReporte;
    private JButton btnGuardar;
    private JCalendar Calendario;
    private JDateChooser dateAl;
    private JDateChooser dateDel;
    private JasperPrint jasperPrint;
    private JasperViewer jasperViewer;
    private JFileChooser fileChooser;
    private JTextField textBusqueda;

	/**
	 * Launch the application.
	 
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
	}*/

	/**
	 * Create the frame.
	 */
	public VentanaReportes(VentanaPrincipal miVentanaPrincipal, boolean modal) {
		super(miVentanaPrincipal, modal);
		Connection db= ConectionDB.getConection();
		try {
			jasperPrint = JasperFillManager.fillReport(new File(".").getAbsolutePath()+"/src/reportes/reporteR.jasper", null, db);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileChooser = new JFileChooser();
		setTitle("Ventana Reportes");
		setBounds(100, 100, 910, 672);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBounds(198, 73, 131, 23);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Buscar por", "Nombre", "Tipo", "Fecha"}));
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
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Ordenar por","Nombre","Tipo","Fecha"}));
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
		btnBuscar.addActionListener(new ManejadorDeBotones());
		contentPane.add(btnBuscar);
		setLocationRelativeTo(null);
		
		btnGuardar = new JButton("");
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBorderPainted(false);
		btnGuardar.setIcon(new ImageIcon(VentanaReportes.class.getResource("/resources/boton-guardar-reporte.png")));
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.addActionListener(new ManejadorDeBotones());
		btnGuardar.setBounds(527, 555, 218, 68);
		contentPane.add(btnGuardar);
		
		btnMostrarReporte = new JButton("");
		btnMostrarReporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarReporte.setBorderPainted(false);
		btnMostrarReporte.setIcon(new ImageIcon(VentanaReportes.class.getResource("/resources/btn_mostrar6.png")));
		btnMostrarReporte.setContentAreaFilled(false);
		btnMostrarReporte.addActionListener(new ManejadorDeBotones());
		
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
		CargarListaDeReportes();
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
		//TablaDeReportes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
public class ManejadorDeBotones implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnBuscar){
				ManejadorBuscarPor();
			}else{ 
				if( e.getSource() == btnMostrarReporte){
					
				  MostrarReporte();
			}else{
				if(e.getSource()== btnGuardar){
					GuardarReporte();
				}
			}
				
				
			}
			
		}
	
	}
    public void MostrarReporte(){
			jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
			jasperViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
    }
    public void GuardarReporte(){
        int seleccion = fileChooser.showSaveDialog(null);
		contentPane.add(fileChooser);
		comboBox.setBounds(196, 101, 108, 23);
		String ruta =null;
		File guardar1;
		if (seleccion == JFileChooser.APPROVE_OPTION)
		{   
			guardar1 = fileChooser.getSelectedFile();
			if(guardar1!= null){
				ruta= guardar1.getAbsolutePath();
	        try {
			JasperExportManager.exportReportToPdfFile(jasperPrint,ruta+".pdf");
	        } catch (JRException e) {
			
						e.printStackTrace();
	        }
			}else{
			    JOptionPane.showMessageDialog(null,"Destino a guardar no seleccionado","",JOptionPane.ERROR_MESSAGE);
			}
	        
		}
		
		
    }
	public class  ManejadorOrdenar implements ItemListener{
	
		public void itemStateChanged(ItemEvent e) {

	    Reporte r;
	    listadeReportes.clear();
		int opc = comboBox1.getSelectedIndex();
		
		switch(opc){
		case 1 :  
		    	       
		         rss = ConsultasBasicas.consultarDatos("SELECT* FROM reportes ORDER BY Nombre");
		        
				try {
					while(rss.next()){
						 r = new Reporte(rss.getString("Nombre"),rss.getString("Tipo"),rss.getDate("Fecha"));		                  
						 listadeReportes.add(r);
		              }
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		             
		          break;
		case 2: 
			rss = ConsultasBasicas.consultarDatos("SELECT* FROM reportes ORDER BY Tipo");
        
		    try {
			while(rss.next()){
            	   r = new Reporte(rss.getString("Nombre"),rss.getString("Tipo"),rss.getDate("Fecha"));
            	   listadeReportes.add(r);
              }
		    } catch (SQLException e1) {
			
			e1.printStackTrace();
		    }
             
          break;
		case 3: 
			rss = ConsultasBasicas.consultarDatos("SELECT* FROM reportes ORDER BY Fecha");
        
		try {
			while(rss.next()){
				 r = new Reporte(rss.getString("Nombre"),rss.getString("Tipo"),rss.getDate("Fecha"));                   
				 listadeReportes.add(r);
              }
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
             
          break;
		
		  default:;
		       
		   
		  }
		    
		   TableModel tableModel2 = new ModeloTablaReporte(listadeReportes);
		   TablaDeReportes.setModel(tableModel2); 
	    }
			
	
		}
	
	public class ManejadorFecha implements ItemListener{

		
		public void itemStateChanged(ItemEvent e) {
			if(comboBox.getSelectedIndex()==3){
				
				dateDel.setEnabled(true);
                dateAl.setEnabled(true);
			}
			
		}
		
	}
	public void ManejadorBuscarPor() {

			
			
			Reporte r ;
			int indice= comboBox.getSelectedIndex();
			listadeReportes.clear();
			
			switch (indice){
			case 1:		   		ResultadodeBusqueda = ConsultasBasicas.consultarDatos("SELECT * FROM reportes WHERE Nombre ='"+textBusqueda.getText()+"'");
			
			       				try {
			       				while (ResultadodeBusqueda.next()){
			       					r = new Reporte(ResultadodeBusqueda.getString("Nombre"),ResultadodeBusqueda.getString("Tipo"),ResultadodeBusqueda.getDate("Fecha"));                  
			       					System.out.println(r.getNombre());
			       					listadeReportes.add(r);
			       					} 
			        	   		}catch (SQLException e) {
							
			        	   			e.printStackTrace();
			        	   		}
			       				break;
			       				
			case 2:     		ResultadodeBusqueda = ConsultasBasicas.consultarDatos("SELECT * FROM reportes WHERE Tipo = '"+textBusqueda.getText()+"'");
		    
			           			try {
			           			while (ResultadodeBusqueda.next()){
			           				r = new Reporte(ResultadodeBusqueda.getString("Nombre"),ResultadodeBusqueda.getString("Tipo"),ResultadodeBusqueda.getDate("Fecha"));                   
			           				listadeReportes.add(r);
			           			} 
			      
			           			}catch (SQLException e1) {
				
			           				e1.printStackTrace();
			           			}
			           		   break;
			           		   
			case 3: 	       
				                
				                Date del = dateDel.getDate();
				                Date al = dateAl.getDate();
				                java.sql.Date DEL = new java.sql.Date(del.getTime());
				                java.sql.Date AL = new java.sql.Date(al.getTime());
				                boolean sw = ValidarRangoFecha.validar(del,al);
				                if(sw){
				                	
				                	ResultadodeBusqueda = ConsultasBasicas.consultarDatos("SELECT * FROM reportes WHERE Fecha>= '"+DEL+"' and Fecha <= '"+AL+"' ");
		  
			   				try {
			   					while (ResultadodeBusqueda.next()){
			   						r = new Reporte(ResultadodeBusqueda.getString("Nombre"),ResultadodeBusqueda.getString("Tipo"),ResultadodeBusqueda.getDate("Fecha"));                  
			   						listadeReportes.add(r);
			   					} 
			   					}catch (SQLException e12) {
				
			   						e12.printStackTrace();
			   					}
				                }else{
				                	JOptionPane.showMessageDialog(null,"Rango de Fechas no valido","",JOptionPane.ERROR_MESSAGE);
				                }
				                
				                
			   				break;
			default:;
			}
			TableModel tableModel3 = new ModeloTablaReporte(listadeReportes);
				
			TablaDeReportes.setModel(tableModel3);
			
			
		}
	
	
	public void CargarListaDeReportes(){
		ResultSet rs;
		rs = ConsultasBasicas.consultarDatos("SELECT* FROM reportes");
		
	    try {
			while (rs.next()){
				Reporte r;
				r = new Reporte(rs.getString("Nombre"),rs.getString("Tipo"),rs.getDate("Fecha"));
				listadeReportes.add(r);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void setVentanaPrincipal(VentanaReportes miVentana) {
		   miVentanaReportes = miVentana;
	}
}
