package presentacion;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

import datos.ConsultasBasicas;
import java.awt.Component;
import entidades.Reporte;
import modeloTablas.ModeloTablaReporte;
import negocio.ValidarRangoFecha;


public class VentanaReportes extends JDialog {
	
	private VentanaReportes miVentanaReportes;
	private JPanel contentPane;
	private JTextField textBusqueda;
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
    

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaR frame = new VentanaR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}/*

	/**
	 * Create the frame.
	 */
	public VentanaReportes(VentanaPrincipal miVentanaPrincipal, boolean modal) {
		super(miVentanaPrincipal, modal);
		
		setTitle("Ventana Reportes");
		setBounds(100, 100, 905, 692);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Buscar por", "Nombre", "Tipo", "Fecha", "         semana"}));
		comboBox.addItemListener(new ManejadorFecha());
		comboBox.setBounds(196, 101, 108, 23);
		contentPane.add(comboBox);
		
		comboBox1 = new JComboBox();
		comboBox1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Ordenar por","Nombre","Tipo","Fecha"}));
		comboBox1.setBounds(61, 101, 109, 23);
		comboBox1.addItemListener(new ManejadorOrdenar());
		contentPane.add(comboBox1);
		
		textBusqueda = new JTextField();
		textBusqueda.setBounds(328, 101, 323, 22);
		contentPane.add(textBusqueda);
		textBusqueda.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Papyrus", Font.PLAIN, 14));
		btnBuscar.setBounds(673, 92, 152, 41);
		btnBuscar.addActionListener(new ManejadorDeBotones());
		contentPane.add(btnBuscar);
		setLocationRelativeTo(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ManejadorDeBotones());
		btnGuardar.setBounds(519, 572, 168, 35);
		contentPane.add(btnGuardar);
		
		btnMostrarReporte = new JButton("Mostrar Reporte");
		btnMostrarReporte.addActionListener(new ManejadorDeBotones());
		
		btnMostrarReporte.setBounds(197, 572, 168, 35);
		contentPane.add(btnMostrarReporte);
		
		JLabel lblReportes = new JLabel("Reportes");
		lblReportes.setForeground(SystemColor.activeCaptionText);
		lblReportes.setFont(new Font("PeacerfulDay", Font.BOLD, 39));
		lblReportes.setBounds(36, 22, 373, 62);
		contentPane.add(lblReportes);
		
		JLabel lblListadoDeReportes = new JLabel("Listado de reportes:");
		lblListadoDeReportes.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblListadoDeReportes.setBounds(61, 187, 161, 14);
		contentPane.add(lblListadoDeReportes);
		
		
		listadeReportes = new ArrayList<Reporte>();
		CargarListaDeReportes();
		TableModel tableModel = new ModeloTablaReporte(listadeReportes);
		
		JLabel lblDel = new JLabel("Del:");
		lblDel.setBounds(61, 147, 46, 14);
		contentPane.add(lblDel);
		TablaDeReportes = new JTable(tableModel);
		TablaDeReportes.setFont(new Font("Papyrus", Font.PLAIN, 11));
		TablaDeReportes.setFillsViewportHeight(true);
		TablaDeReportes.setColumnSelectionAllowed(true);
		TablaDeReportes.setCellSelectionEnabled(true);
		//TablaDeReportes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spTablaDeReportes = new JScrollPane();
		spTablaDeReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spTablaDeReportes.setSize(762, 335);
		spTablaDeReportes.setLocation(63, 212);
		
		spTablaDeReportes.setViewportView(TablaDeReportes);
     
		contentPane.add(spTablaDeReportes);
	
		dateDel = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dateDel.setEnabled(false);
		dateDel.setBounds(113, 141, 87, 20);
		contentPane.add(dateDel);
		dateDel.setMaxSelectableDate(new Date());
		
	  
        dateAl = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        dateAl.setEnabled(false);
        dateAl.setBounds(256, 141, 87, 20);
        contentPane.add(dateAl);
      
        dateAl.setMaxSelectableDate(new Date());
        
        JLabel lblAl = new JLabel("Al:");
        lblAl.setBounds(221, 147, 46, 14);
        contentPane.add(lblAl);
	}
public class ManejadorDeBotones implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnBuscar){
				ManejadorBuscarPor();
			}else{ 
				if( e.getSource() == btnMostrarReporte){
				  	    
			}else{
				if(e.getSource()== btnGuardar){
					
				}
			}
				
				
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
				                boolean sw = ValidarRangoFecha.validar(del,al);
				                if(sw){
				                	ResultadodeBusqueda = ConsultasBasicas.consultarDatos("SELECT * FROM reportes  WHERE Fecha BETWEEN el AND al ");
		  
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
