package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Producto;
import negocio.ObtenerProductos;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaProductos extends JDialog {
	private JPanel contenedor;
	private JTextField textFieldProcesador;
	private JTextField textFieldPlaca;
	private JTextField textFieldMemoria;
	private JTextField textFieldVideoCard;
	private JTextField textFieldHDD;
	private JTextField textFieldCaseFuente;
	
	private VentanaProductos miVentanaProductos;
	  
	public VentanaProductos(VentanaPrincipal miVentanaPrincipal, boolean modal){
	 /**Al llamar al constructor super(), le enviamos el
	  * JFrame Padre y la propiedad booleana que determina
	  * que es hija*/
	  super(miVentanaPrincipal, modal);
	  
	  setBounds(100, 100, 768, 736);
	  contenedor = new JPanel();
	  contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
	  setContentPane(contenedor);
	  contenedor.setLayout(null);
	  setLocationRelativeTo(null);
	  setTitle("Componentes - RantiyPC");
	  
	  JLabel lblProcesador = new JLabel("Procesador:");
	  lblProcesador.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
	  lblProcesador.setBounds(10, 66, 86, 19);
	  contenedor.add(lblProcesador);
	  
	  JButton btnSeleccionarProcesador = new JButton("Seleccionar...");
	  btnSeleccionarProcesador.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
	  		if (evento.getSource() == btnSeleccionarProcesador) {
				
				// Enviamos la instancia de la ventana principal para que esta sea Padre de la ventana de dialogo
	  			
	  			List<Producto> listaProductos = new ArrayList<Producto>();	
	  			
	  			try {
	  				listaProductos.addAll(ObtenerProductos.leerProcesadores());
	  			} catch (IOException e) {
	  				// TODO Bloque catch generado automáticamente
	  				e.printStackTrace();
	  			}
	
				VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "procesadores");
				miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				miVentanaMini.setVisible(true);
			}
	  		
	  		
	  		
	  		
	  	}
	  });
	  btnSeleccionarProcesador.setBounds(225, 66, 99, 23);
	  contenedor.add(btnSeleccionarProcesador);
	  
	  textFieldProcesador = new JTextField();
	  textFieldProcesador.setBounds(10, 103, 314, 20);
	  contenedor.add(textFieldProcesador);
	  textFieldProcesador.setColumns(10);
	  
	  JLabel lblMotherboard = new JLabel("Motherboard:");
	  lblMotherboard.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
	  lblMotherboard.setBounds(10, 147, 97, 19);
	  contenedor.add(lblMotherboard);
	  
	  JButton btnSeleccionarPlaca = new JButton("Seleccionar...");
	  btnSeleccionarPlaca.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
				if (evento.getSource() == btnSeleccionarPlaca) {
					
					List<Producto> listaProductos = new ArrayList<Producto>();	
		  			
		  			try {
		  				listaProductos.addAll(ObtenerProductos.leerPlacasMadre());
		  			} catch (IOException e) {
		  				// TODO Bloque catch generado automáticamente
		  				e.printStackTrace();
		  			}
					

					VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "placas-madre");
					miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					miVentanaMini.setVisible(true);
				}
	  		
	  	}
	  });
	  btnSeleccionarPlaca.setBounds(225, 143, 99, 23);
	  contenedor.add(btnSeleccionarPlaca);
	  
	  textFieldPlaca = new JTextField();
	  textFieldPlaca.setBounds(10, 177, 314, 20);
	  contenedor.add(textFieldPlaca);
	  textFieldPlaca.setColumns(10);
	  
	  JLabel lblMemoria = new JLabel("Memoria:");
	  lblMemoria.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
	  lblMemoria.setBounds(10, 221, 66, 19);
	  contenedor.add(lblMemoria);
	  
	  JButton btnSeleccionarMemoria = new JButton("Seleccionar...");
	  btnSeleccionarMemoria.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
	  		if (evento.getSource() == btnSeleccionarMemoria) {

					List<Producto> listaProductos = new ArrayList<Producto>();

					try {
						listaProductos.addAll(ObtenerProductos.leerMemorias());
					} catch (IOException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}
	  			
				VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos,"memorias");
				miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				miVentanaMini.setVisible(true);
			}
	  		
	  	}
	  });
	  btnSeleccionarMemoria.setBounds(225, 221, 99, 23);
	  contenedor.add(btnSeleccionarMemoria);
	  
	  textFieldMemoria = new JTextField();
	  textFieldMemoria.setBounds(10, 261, 314, 20);
	  contenedor.add(textFieldMemoria);
	  textFieldMemoria.setColumns(10);
	  
	  JLabel lblVideoCard = new JLabel("Video Card:");
	  lblVideoCard.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
	  lblVideoCard.setBounds(13, 303, 83, 19);
	  contenedor.add(lblVideoCard);
	  
	  JButton btnSeleccionarVideoCard = new JButton("Seleccionar...");
	  btnSeleccionarVideoCard.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
				if (evento.getSource() == btnSeleccionarVideoCard) {

					List<Producto> listaProductos = new ArrayList<Producto>();

					try {
						listaProductos.addAll(ObtenerProductos.leerTarjetasVideo());
					} catch (IOException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}

				VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos,"video-cards");
				miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				miVentanaMini.setVisible(true);
			}
	  		
	  	}
	  });
	  btnSeleccionarVideoCard.setBounds(225, 303, 99, 23);
	  contenedor.add(btnSeleccionarVideoCard);
	  
	  textFieldVideoCard = new JTextField();
	  textFieldVideoCard.setBounds(10, 352, 314, 20);
	  contenedor.add(textFieldVideoCard);
	  textFieldVideoCard.setColumns(10);
	  
	  JLabel lblAlmacenamientohdd = new JLabel("Almacenamiento (HDD):");
	  lblAlmacenamientohdd.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
	  lblAlmacenamientohdd.setBounds(10, 401, 171, 19);
	  contenedor.add(lblAlmacenamientohdd);
	  
	  JButton btnSeleccionarHDD = new JButton("Seleccionar...");
	  btnSeleccionarHDD.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
	  		if (evento.getSource() == btnSeleccionarHDD) {
	  			
					List<Producto> listaProductos = new ArrayList<Producto>();

					try {
						listaProductos.addAll(ObtenerProductos.leerUnidadesAlmacenamiento());
					} catch (IOException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}

				VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos,"unidades-almacenamiento");
				miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				miVentanaMini.setVisible(true);
			}
	  		
	  	}
	  });
	  btnSeleccionarHDD.setBounds(225, 401, 99, 23);
	  contenedor.add(btnSeleccionarHDD);
	  
	  textFieldHDD = new JTextField();
	  textFieldHDD.setBounds(10, 443, 314, 20);
	  contenedor.add(textFieldHDD);
	  textFieldHDD.setColumns(10);
	  
	  JLabel lblCaseFuentes = new JLabel("Case / Fuentes:");
	  lblCaseFuentes.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
	  lblCaseFuentes.setBounds(10, 490, 113, 19);
	  contenedor.add(lblCaseFuentes);
	  
	  JButton btnSeleccionarCaseFuente = new JButton("Seleccionar...");
	  btnSeleccionarCaseFuente.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
	  		if (evento.getSource() == btnSeleccionarCaseFuente) {

					List<Producto> listaProductos = new ArrayList<Producto>();

					try {
						listaProductos.addAll(ObtenerProductos.leerCaseFuentes());
					} catch (IOException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}

				VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos,"case-fuentes");
				miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				miVentanaMini.setVisible(true);
			}
	  		
	  	}
	  });
	  btnSeleccionarCaseFuente.setBounds(225, 490, 99, 23);
	  contenedor.add(btnSeleccionarCaseFuente);
	  
	  textFieldCaseFuente = new JTextField();
	  textFieldCaseFuente.setBounds(13, 534, 311, 20);
	  contenedor.add(textFieldCaseFuente);
	  textFieldCaseFuente.setColumns(10);
	  
	  
	 }
	
	public void setVentanaPrincipal(VentanaProductos miVentana) {
		   miVentanaProductos = miVentana;
	}

	public JTextField getTextFieldProcesador() {
		return textFieldProcesador;
	}

	public void setTextFieldProcesador(JTextField textFieldProcesador) {
		this.textFieldProcesador = textFieldProcesador;
	}

	public JTextField getTextFieldPlaca() {
		return textFieldPlaca;
	}

	public void setTextFieldPlaca(JTextField textFieldPlaca) {
		this.textFieldPlaca = textFieldPlaca;
	}

	public JTextField getTextFieldMemoria() {
		return textFieldMemoria;
	}

	public void setTextFieldMemoria(JTextField textFieldMemoria) {
		this.textFieldMemoria = textFieldMemoria;
	}

	public JTextField getTextFieldVideoCard() {
		return textFieldVideoCard;
	}

	public void setTextFieldVideoCard(JTextField textFieldVideoCard) {
		this.textFieldVideoCard = textFieldVideoCard;
	}

	public JTextField getTextFieldHDD() {
		return textFieldHDD;
	}

	public void setTextFieldHDD(JTextField textFieldHDD) {
		this.textFieldHDD = textFieldHDD;
	}

	public JTextField getTextFieldCaseFuente() {
		return textFieldCaseFuente;
	}

	public void setTextFieldCaseFuente(JTextField textFieldCaseFuente) {
		this.textFieldCaseFuente = textFieldCaseFuente;
	}


	
	
	

}
