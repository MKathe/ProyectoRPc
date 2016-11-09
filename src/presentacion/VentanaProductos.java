package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;

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
import javax.swing.ImageIcon;

public class VentanaProductos extends JDialog {
	private JPanel contenedor;
	private JTextField textFieldProcesador;
	private JTextField textFieldPlaca;
	private JTextField textFieldMemoria;
	private JTextField textFieldVideoCard;
	private JTextField textFieldHDD;
	private JTextField textFieldCaseFuente;
	
	private VentanaProductos miVentanaProductos;
	private JTextField textFieldPrecio1;
	private JTextField textFieldPrecio2;
	private JTextField textFieldPrecio3;
	private JTextField textFieldPrecio4;
	private JTextField textFieldPrecio5;
	private JTextField textFieldPrecio6;
	private JButton btnResetProcesador;
	private JButton btnResetMemoria;
	private JButton btnResetPlaca;
	private JButton btnResetVideoCard;
	private JButton btnResetHDD;
	private JButton btnResetCaseFuente;
	private JTextField textFieldPrecioTotal;
	  
	public VentanaProductos(VentanaPrincipal miVentanaPrincipal, boolean modal){
	 /**Al llamar al constructor super(), le enviamos el
	  * JFrame Padre y la propiedad booleana que determina
	  * que es hija*/
	  super(miVentanaPrincipal, modal);
	  setBounds(100, 100, 1038, 790);
	  contenedor = new JPanel();
	  contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
	  setContentPane(contenedor);
	  contenedor.setLayout(null);
	  setLocationRelativeTo(null);
	  setTitle("Componentes - RantiyPC");
	  
	  JButton btnSeleccionarProcesador = new JButton("");
	  btnSeleccionarProcesador.setFocusPainted(false);
	  btnSeleccionarProcesador.setBorderPainted(false);
	  btnSeleccionarProcesador.setBorder(null);
	  btnSeleccionarProcesador.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/seleccionarItem.png")));
	  btnSeleccionarProcesador.setContentAreaFilled(false);
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
	  
	  JButton button = new JButton("");
	  button.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {

	  		if(textFieldProcesador.getText().length() != 0 && textFieldMemoria.getText().length() != 0 && textFieldPlaca.getText().length() != 0 &&  textFieldVideoCard.getText().length() != 0 && textFieldHDD.getText().length() != 0 && textFieldCaseFuente.getText().length() != 0 ){
	  			
	  			Double suma;  			
	  			suma = Double.parseDouble(textFieldPrecio1.getText()) + 
	  					Double.parseDouble(textFieldPrecio2.getText()) + 
	  					Double.parseDouble(textFieldPrecio3.getText()) + 
	  					Double.parseDouble(textFieldPrecio4.getText()) + 
	  					Double.parseDouble(textFieldPrecio5.getText()) + 
	  					Double.parseDouble(textFieldPrecio6.getText());
	  			
	  			textFieldPrecioTotal.setText(Double.toString(suma));
	  			JOptionPane.showMessageDialog(null, "Procesando...");
	  		}else{
	  			JOptionPane.showMessageDialog(null, "Faltan seleccionar componetes!");
	  		}
	  		
	  		
	  	}
	  });
	  button.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/send.png")));
	  button.setFocusPainted(false);
	  button.setContentAreaFilled(false);
	  button.setBorderPainted(false);
	  button.setBorder(null);
	  button.setBounds(828, 672, 184, 62);
	  contenedor.add(button);
	  btnSeleccionarProcesador.setBounds(241, 199, 40, 33);
	  contenedor.add(btnSeleccionarProcesador);
	  
	  textFieldProcesador = new JTextField();
	  textFieldProcesador.setColumns(10);
	  textFieldProcesador.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldProcesador.setForeground(Color.WHITE);
	  textFieldProcesador.setBorder(null);
	  textFieldProcesador.setOpaque(false);
	  //textFieldProcesador.setCaretColor(new Color(0, 0, 0, 0));
	  textFieldProcesador.setBounds(304, 199, 480, 35);
	  contenedor.add(textFieldProcesador);
	  
	  JButton btnSeleccionarPlaca = new JButton("");
	  btnSeleccionarPlaca.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/seleccionarItem.png")));
	  btnSeleccionarPlaca.setFocusPainted(false);
	  btnSeleccionarPlaca.setContentAreaFilled(false);
	  btnSeleccionarPlaca.setBorderPainted(false);
	  btnSeleccionarPlaca.setBorder(null);
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
	  btnSeleccionarPlaca.setBounds(241, 342, 40, 33);
	  contenedor.add(btnSeleccionarPlaca);
	  
	  textFieldPlaca = new JTextField();
	  textFieldPlaca.setForeground(Color.WHITE);
	  textFieldPlaca.setBorder(null);
	  textFieldPlaca.setOpaque(false);
	  textFieldPlaca.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPlaca.setBounds(304, 340, 480, 38);
	  contenedor.add(textFieldPlaca);
	  textFieldPlaca.setColumns(10);
	  
	  JButton btnSeleccionarMemoria = new JButton("");
	  btnSeleccionarMemoria.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/seleccionarItem.png")));
	  btnSeleccionarMemoria.setFocusPainted(false);
	  btnSeleccionarMemoria.setContentAreaFilled(false);
	  btnSeleccionarMemoria.setBorderPainted(false);
	  btnSeleccionarMemoria.setBorder(null);
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
	  btnSeleccionarMemoria.setBounds(241, 270, 40, 33);
	  contenedor.add(btnSeleccionarMemoria);
	  
	  textFieldMemoria = new JTextField();
	  textFieldMemoria.setForeground(Color.WHITE);
	  textFieldMemoria.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldMemoria.setBorder(null);
	  textFieldMemoria.setOpaque(false);
	  textFieldMemoria.setBounds(304, 270, 480, 35);
	  contenedor.add(textFieldMemoria);
	  textFieldMemoria.setColumns(10);
	  
	  JButton btnSeleccionarVideoCard = new JButton("");
	  btnSeleccionarVideoCard.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/seleccionarItem.png")));
	  btnSeleccionarVideoCard.setFocusPainted(false);
	  btnSeleccionarVideoCard.setContentAreaFilled(false);
	  btnSeleccionarVideoCard.setBorder(null);
	  btnSeleccionarVideoCard.setBorderPainted(false);
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
	  btnSeleccionarVideoCard.setBounds(241, 412, 40, 33);
	  contenedor.add(btnSeleccionarVideoCard);
	  
	  textFieldVideoCard = new JTextField();
	  textFieldVideoCard.setForeground(Color.WHITE);
	  textFieldVideoCard.setBorder(null);
	  textFieldVideoCard.setOpaque(false);
	  textFieldVideoCard.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldVideoCard.setBounds(304, 412, 480, 35);
	  contenedor.add(textFieldVideoCard);
	  textFieldVideoCard.setColumns(10);
	  
	  JButton btnSeleccionarHDD = new JButton("");
	  btnSeleccionarHDD.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/seleccionarItem.png")));
	  btnSeleccionarHDD.setFocusPainted(false);
	  btnSeleccionarHDD.setContentAreaFilled(false);
	  btnSeleccionarHDD.setBorder(null);
	  btnSeleccionarHDD.setBorderPainted(false);
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
	  btnSeleccionarHDD.setBounds(241, 483, 40, 33);
	  contenedor.add(btnSeleccionarHDD);
	  
	  textFieldHDD = new JTextField();
	  textFieldHDD.setForeground(Color.WHITE);
	  textFieldHDD.setBorder(null);
	  textFieldHDD.setOpaque(false);
	  textFieldHDD.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldHDD.setBounds(304, 483, 480, 35);
	  contenedor.add(textFieldHDD);
	  textFieldHDD.setColumns(10);
	  
	  JButton btnSeleccionarCaseFuente = new JButton("");
	  btnSeleccionarCaseFuente.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/seleccionarItem.png")));
	  btnSeleccionarCaseFuente.setFocusPainted(false);
	  btnSeleccionarCaseFuente.setContentAreaFilled(false);
	  btnSeleccionarCaseFuente.setBorder(null);
	  btnSeleccionarCaseFuente.setBorderPainted(false);
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
	  btnSeleccionarCaseFuente.setBounds(241, 554, 40, 33);
	  contenedor.add(btnSeleccionarCaseFuente);
	  
	  textFieldCaseFuente = new JTextField();
	  textFieldCaseFuente.setForeground(Color.WHITE);
	  textFieldCaseFuente.setBorder(null);
	  textFieldCaseFuente.setOpaque(false);
	  textFieldCaseFuente.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldCaseFuente.setBounds(304, 554, 480, 35);
	  contenedor.add(textFieldCaseFuente);
	  textFieldCaseFuente.setColumns(10);
	  
	  textFieldPrecio1 = new JTextField();
	  textFieldPrecio1.setForeground(Color.WHITE);
	  textFieldPrecio1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecio1.setOpaque(false);
	  textFieldPrecio1.setBorder(null);
	  textFieldPrecio1.setBounds(820, 199, 120, 35);
	  contenedor.add(textFieldPrecio1);
	  textFieldPrecio1.setColumns(10);
	  
	  textFieldPrecio2 = new JTextField();
	  textFieldPrecio2.setForeground(Color.WHITE);
	  textFieldPrecio2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecio2.setOpaque(false);
	  textFieldPrecio2.setBorder(null);
	  textFieldPrecio2.setColumns(10);
	  textFieldPrecio2.setBounds(820, 270, 120, 35);
	  contenedor.add(textFieldPrecio2);
	  
	  textFieldPrecio3 = new JTextField();
	  textFieldPrecio3.setForeground(Color.WHITE);
	  textFieldPrecio3.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecio3.setOpaque(false);
	  textFieldPrecio3.setBorder(null);
	  textFieldPrecio3.setColumns(10);
	  textFieldPrecio3.setBounds(820, 340, 120, 35);
	  contenedor.add(textFieldPrecio3);
	  
	  textFieldPrecio4 = new JTextField();
	  textFieldPrecio4.setForeground(Color.WHITE);
	  textFieldPrecio4.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecio4.setOpaque(false);
	  textFieldPrecio4.setBorder(null);
	  textFieldPrecio4.setColumns(10);
	  textFieldPrecio4.setBounds(820, 412, 120, 35);
	  contenedor.add(textFieldPrecio4);
	  
	  textFieldPrecio5 = new JTextField();
	  textFieldPrecio5.setForeground(Color.WHITE);
	  textFieldPrecio5.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecio5.setOpaque(false);
	  textFieldPrecio5.setBorder(null);
	  textFieldPrecio5.setColumns(10);
	  textFieldPrecio5.setBounds(820, 483, 120, 35);
	  contenedor.add(textFieldPrecio5);
	  
	  textFieldPrecio6 = new JTextField();
	  textFieldPrecio6.setForeground(Color.WHITE);
	  textFieldPrecio6.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecio6.setOpaque(false);
	  textFieldPrecio6.setBorder(null);
	  textFieldPrecio6.setColumns(10);
	  textFieldPrecio6.setBounds(820, 552, 120, 35);
	  contenedor.add(textFieldPrecio6);
	  
	  btnResetProcesador = new JButton("");
	  btnResetProcesador.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent arg0) {
	  		
	  		textFieldProcesador.setText("");
	  		textFieldPrecio1.setText("");
	  		
	  	}
	  });
	  btnResetProcesador.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetProcesador.setFocusPainted(false);
	  btnResetProcesador.setContentAreaFilled(false);
	  btnResetProcesador.setBorderPainted(false);
	  btnResetProcesador.setBorder(null);
	  btnResetProcesador.setBounds(959, 195, 39, 43);
	  contenedor.add(btnResetProcesador);
	  
	  btnResetMemoria = new JButton("");
	  btnResetMemoria.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldMemoria.setText("");
	  		textFieldPrecio2.setText("");
	  		
	  	}
	  });
	  btnResetMemoria.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetMemoria.setFocusPainted(false);
	  btnResetMemoria.setContentAreaFilled(false);
	  btnResetMemoria.setBorderPainted(false);
	  btnResetMemoria.setBorder(null);
	  btnResetMemoria.setBounds(959, 266, 39, 43);
	  contenedor.add(btnResetMemoria);
	  
	  btnResetPlaca = new JButton("");
	  btnResetPlaca.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldPlaca.setText("");
	  		textFieldPrecio3.setText("");
	  		
	  	}
	  });
	  btnResetPlaca.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetPlaca.setFocusPainted(false);
	  btnResetPlaca.setContentAreaFilled(false);
	  btnResetPlaca.setBorderPainted(false);
	  btnResetPlaca.setBorder(null);
	  btnResetPlaca.setBounds(959, 338, 39, 43);
	  contenedor.add(btnResetPlaca);
	  
	  btnResetVideoCard = new JButton("");
	  btnResetVideoCard.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldVideoCard.setText("");
	  		textFieldPrecio4.setText("");
	  		
	  	}
	  });
	  btnResetVideoCard.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetVideoCard.setFocusPainted(false);
	  btnResetVideoCard.setContentAreaFilled(false);
	  btnResetVideoCard.setBorderPainted(false);
	  btnResetVideoCard.setBorder(null);
	  btnResetVideoCard.setBounds(959, 408, 39, 43);
	  contenedor.add(btnResetVideoCard);
	  
	  btnResetHDD = new JButton("");
	  btnResetHDD.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldHDD.setText("");
	  		textFieldPrecio5.setText("");
	  		
	  	}
	  });
	  btnResetHDD.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetHDD.setFocusPainted(false);
	  btnResetHDD.setContentAreaFilled(false);
	  btnResetHDD.setBorderPainted(false);
	  btnResetHDD.setBorder(null);
	  btnResetHDD.setBounds(959, 479, 39, 43);
	  contenedor.add(btnResetHDD);
	  
	  btnResetCaseFuente = new JButton("");
	  btnResetCaseFuente.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldCaseFuente.setText("");
	  		textFieldPrecio6.setText("");
	  		
	  	}
	  });
	  btnResetCaseFuente.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetCaseFuente.setFocusPainted(false);
	  btnResetCaseFuente.setContentAreaFilled(false);
	  btnResetCaseFuente.setBorderPainted(false);
	  btnResetCaseFuente.setBorder(null);
	  btnResetCaseFuente.setBounds(959, 550, 39, 43);
	  contenedor.add(btnResetCaseFuente);
	  
	  textFieldPrecioTotal = new JTextField();
	  textFieldPrecioTotal.setForeground(Color.WHITE);
	  textFieldPrecioTotal.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 22));
	  textFieldPrecioTotal.setOpaque(false);
	  textFieldPrecioTotal.setBorder(null);
	  textFieldPrecioTotal.setBounds(550, 686, 194, 35);
	  contenedor.add(textFieldPrecioTotal);
	  textFieldPrecioTotal.setColumns(10);
	  
	  JLabel label = new JLabel("");
	  label.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/vista-producto-v2.jpg")));
	  label.setBounds(0, 0, 1024, 760);
	  contenedor.add(label);
	  
	  
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

	public JTextField getTextFieldPrecio1() {
		return textFieldPrecio1;
	}

	public void setTextFieldPrecio1(JTextField textFieldPrecio1) {
		this.textFieldPrecio1 = textFieldPrecio1;
	}

	public JTextField getTextFieldPrecio2() {
		return textFieldPrecio2;
	}

	public void setTextFieldPrecio2(JTextField textFieldPrecio2) {
		this.textFieldPrecio2 = textFieldPrecio2;
	}

	public JTextField getTextFieldPrecio3() {
		return textFieldPrecio3;
	}

	public void setTextFieldPrecio3(JTextField textFieldPrecio3) {
		this.textFieldPrecio3 = textFieldPrecio3;
	}

	public JTextField getTextFieldPrecio4() {
		return textFieldPrecio4;
	}

	public void setTextFieldPrecio4(JTextField textFieldPrecio4) {
		this.textFieldPrecio4 = textFieldPrecio4;
	}

	public JTextField getTextFieldPrecio5() {
		return textFieldPrecio5;
	}

	public void setTextFieldPrecio5(JTextField textFieldPrecio5) {
		this.textFieldPrecio5 = textFieldPrecio5;
	}

	public JTextField getTextFieldPrecio6() {
		return textFieldPrecio6;
	}

	public void setTextFieldPrecio6(JTextField textFieldPrecio6) {
		this.textFieldPrecio6 = textFieldPrecio6;
	}
}
