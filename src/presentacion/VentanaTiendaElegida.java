package presentacion;

import entidades.Tienda;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class VentanaTiendaElegida extends JDialog {
	private JTextField textFieldUbicacion;
	private JTextField textFieldTelefono;
	private JTextField textFieldEmail;
	private JTable table;

	public VentanaTiendaElegida(VentanaTiendas miVentanaTiendas, boolean modal, int indice, java.util.List<Tienda> listaDeTiendas) {
		
		super(miVentanaTiendas, modal);
		setResizable(false);
		setBounds(100, 100, 1029, 777);
		setLocationRelativeTo(null);
		setTitle("Tienda "+ listaDeTiendas.get(indice).getNombre()+" - RantiyPC");
		getContentPane().setLayout(null);
		
		//Nombre de la tienda
		JLabel label = new JLabel(listaDeTiendas.get(indice).getNombre());
		label.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
		label.setForeground(Color.LIGHT_GRAY);
		label.setBounds(26, 22, 489, 33);
		getContentPane().add(label);
		
		//Ubicacion de tienda
		textFieldUbicacion = new JTextField();
		textFieldUbicacion.setForeground(Color.BLACK);
		textFieldUbicacion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 23));
		textFieldUbicacion.setBorder(null);
		textFieldUbicacion.setOpaque(false);
		textFieldUbicacion.setBounds(601, 524, 412, 33);
		getContentPane().add(textFieldUbicacion);
		textFieldUbicacion.setColumns(10);
		textFieldUbicacion.setText(listaDeTiendas.get(indice).getUbicacion());
		
		//url de ubicacion en google mapsen jlabel
		JLabel lblLinkDeUbicación = new JLabel("Ubicalo en Google Maps");
		lblLinkDeUbicación.setForeground(Color.WHITE);
		lblLinkDeUbicación.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
		lblLinkDeUbicación.setBounds(35, 705, 393, 33);
		lblLinkDeUbicación.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					Desktop.getDesktop().browse(new URI(listaDeTiendas.get(indice).getLinkDeUbicacion()));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
		});
		getContentPane().add(lblLinkDeUbicación);
		
		//url de tienda en jlabel
		JLabel lblLinkDeTienda = new JLabel("");
		lblLinkDeTienda.setIcon(new ImageIcon(VentanaTiendaElegida.class.getResource("/resources/botonSitioWeb.png")));
		lblLinkDeTienda.setBounds(439, 409, 256, 91);
		lblLinkDeTienda.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					Desktop.getDesktop().browse(new URI(listaDeTiendas.get(indice).getLinkDeTienda()));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
		});
		getContentPane().add(lblLinkDeTienda);
		
		//telefono de tienda
		textFieldTelefono = new JTextField();
		textFieldTelefono.setForeground(Color.BLACK);
		textFieldTelefono.setFont(new Font("Berlin Sans FB", Font.PLAIN, 23));
		textFieldTelefono.setOpaque(false);
		textFieldTelefono.setBorder(null);
		textFieldTelefono.setBounds(601, 588, 412, 33);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setText(listaDeTiendas.get(indice).getTelefono());
		
		//email de tienda
		textFieldEmail = new JTextField();
		textFieldEmail.setForeground(Color.BLACK);
		textFieldEmail.setFont(new Font("Berlin Sans FB", Font.PLAIN, 23));
		textFieldEmail.setBorder(null);
		textFieldEmail.setOpaque(false);
		textFieldEmail.setBounds(601, 647, 412, 33);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setText(listaDeTiendas.get(indice).getEmail());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 77, 969, 269);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"Equipos"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnElegir = new JButton("");
		btnElegir.setContentAreaFilled(false);
		btnElegir.setBorderPainted(false);
		btnElegir.setBorder(null);
		btnElegir.setIcon(new ImageIcon(VentanaTiendaElegida.class.getResource("/resources/botonElegirEquipo.png")));
		btnElegir.setBounds(744, 357, 225, 57);
		getContentPane().add(btnElegir);
		
		JLabel labelMapa = new JLabel("");
		labelMapa.setBounds(25, 459, 356, 235);
		labelMapa.setIcon(new ImageIcon(VentanaTiendaElegida.class.getResource("/resources/"+listaDeTiendas.get(indice).getNombre()+".png")));
		getContentPane().add(labelMapa);
		
		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(VentanaTiendaElegida.class.getResource("/resources/fondo-TiendaElegida.png")));
		labelFondo.setBounds(0, 0, 1023, 750);
		getContentPane().add(labelFondo);
	}
	
}
