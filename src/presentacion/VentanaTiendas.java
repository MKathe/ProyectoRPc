package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import conectionDB.ConectionDB;
import datos.ConsultasBasicas;
import entidades.Tienda;
import modeloTablas.ModeloTablaTienda;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class VentanaTiendas extends JDialog {
	private JTextField textFieldBusqueda;
	private JTable tableTiendas;
	private VentanaTiendas miVentanaTiendas;
	private List<Tienda> listaDeTiendas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public VentanaTiendas(VentanaPrincipal miVentanaPrincipal, boolean modal) {
		
		super(miVentanaPrincipal, modal);
		setResizable(false);
		setTitle("Tiendas - RantiyPC");
		setBounds(100, 100, 1027, 775);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPaneTiendas = new JScrollPane();
		scrollPaneTiendas.setBounds(49, 193, 938, 397);
		getContentPane().add(scrollPaneTiendas);
		
		listaDeTiendas = new ArrayList<Tienda>();
		CargarListaDeTiendas(); 
		TableModel tableModel = new ModeloTablaTienda(listaDeTiendas);
		
		tableTiendas = new JTable(tableModel); //asigna el modelo a la tabla
		
		scrollPaneTiendas.setViewportView(tableTiendas);
		
		JRadioButton rdbtnNombre = new JRadioButton("");
		rdbtnNombre.setContentAreaFilled(false);
		rdbtnNombre.setSelected(true);
		buttonGroup.add(rdbtnNombre);
		rdbtnNombre.setBounds(524, 41, 21, 23);
		getContentPane().add(rdbtnNombre);
		
		JRadioButton rdbtnLugar = new JRadioButton("");
		rdbtnLugar.setContentAreaFilled(false);
		buttonGroup.add(rdbtnLugar);
		rdbtnLugar.setBounds(747, 41, 21, 23);
		getContentPane().add(rdbtnLugar);
		
		textFieldBusqueda = new JTextField();
		textFieldBusqueda.setForeground(Color.WHITE);
		textFieldBusqueda.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		textFieldBusqueda.setOpaque(false);
		textFieldBusqueda.setBorder(null);
		textFieldBusqueda.setBounds(487, 88, 435, 30);
		getContentPane().add(textFieldBusqueda);
		textFieldBusqueda.setColumns(10);
		
		JButton buttonBuscar = new JButton("");
		buttonBuscar.setContentAreaFilled(false);
		buttonBuscar.setBorder(null);
		buttonBuscar.setIcon(new ImageIcon(VentanaTiendas.class.getResource("/resources/seleccionarItem.png")));
		buttonBuscar.setSelectedIcon(new ImageIcon(VentanaTiendas.class.getResource("/resources/lupa.png")));
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//el nombre en minusculas
				String textoIngresado = textFieldBusqueda.getText().toLowerCase();
				int numElementos = listaDeTiendas.size();
				int i = 0;
				boolean encontrado = false;
				List<Tienda> tiendaEncontrada = new ArrayList();
				
				if(rdbtnNombre.isSelected()){ //Busca por nombre
					//itera buscando en la lista
					while(i < numElementos){
						//compara el texto ingresado con el de la lista en la posicion i
						if( textoIngresado.equals(listaDeTiendas.get(i).getNombre().toLowerCase()) ){
							encontrado = true;
							tiendaEncontrada.add(listaDeTiendas.get(i));
						}
						
						i++;
						
					}
				}
				else{	//Busca por Ubicacion
					//itera buscando en la lista
					String ubicacion;
					while(i < numElementos){
						
						ubicacion = listaDeTiendas.get(i).getUbicacion().toLowerCase();
						//compara el texto ingresado con el de la lista en la posicion i
						if(ubicacion.contains(textoIngresado)){
							System.out.println("encuentra");
							encontrado = true;
							tiendaEncontrada.add(listaDeTiendas.get(i));
						}
						
						i++;
						
					}
				}
				
				if(encontrado){
					//Nuevo modelo para la tabla
					TableModel modelo = new ModeloTablaTienda(tiendaEncontrada);
					tableTiendas.setModel(modelo);
				}
				else{
					JOptionPane.showMessageDialog(null, "Búsqueda no encontrada");
				}
			}
		});
		buttonBuscar.setBounds(947, 77, 53, 41);
		getContentPane().add(buttonBuscar);
		
		JButton buttonMostrar = new JButton("Mostrar");
		buttonMostrar.setBorderPainted(false);
		buttonMostrar.setContentAreaFilled(false);
		buttonMostrar.setIcon(new ImageIcon(VentanaTiendas.class.getResource("/resources/BotonMostrarTienda.png")));
		buttonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == buttonMostrar){
					if(tableTiendas.getSelectedRow() > -1){
						VentanaTiendaElegida miVentanaTiendaElegida = new VentanaTiendaElegida(miVentanaTiendas, true, tableTiendas.getSelectedRow(), listaDeTiendas);
						miVentanaTiendaElegida.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						miVentanaTiendaElegida.setVisible(true);
					}
					
				}
			}
		});
		buttonMostrar.setBounds(311, 641, 395, 95);
		getContentPane().add(buttonMostrar);
		
		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(VentanaTiendas.class.getResource("/resources/fondo-Tienda.png")));
		labelFondo.setBounds(0, 0, 1034, 747);
		getContentPane().add(labelFondo);
		
	}
	
	//llena la lista de tiendas
	public void CargarListaDeTiendas(){
		//conectamos la base de datos
		ConectionDB.getConection();
		ResultSet datos;
		datos = ConsultasBasicas.consultarDatos("SELECT* FROM tiendas");
		try {
			
			while(datos.next()){
				Tienda t;
				t = new Tienda(datos.getString("nombre"), datos.getString("ubicacion"), datos.getString("linkDeUbicacion"),
						datos.getString("linkDeTienda"), datos.getString("telefono"), datos.getString("email"));
				//llenamos la lista de tiendas
				listaDeTiendas.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setVentanaPrincipal(VentanaTiendas miVentana) {
		miVentanaTiendas = miVentana;
	}
}
