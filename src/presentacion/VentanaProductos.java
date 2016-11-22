package presentacion;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.alee.laf.progressbar.WebProgressBar;

import entidades.Producto;
import entidades.Reporte;
import negocio.LeerCaseFuentes;
import negocio.LeerMemorias;
import negocio.LeerPlacasMadre;
import negocio.LeerProcesadores;
import negocio.LeerTarjetasVideo;
import negocio.LeerUnidadesAlmacenamiento;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Date;
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
	private String[] tiendas;
	private JTextField textFieldPrecioProcesador;
	private JTextField textFieldPrecioMemoria;
	private JTextField textFieldPrecioPlaca;
	private JTextField textFieldPrecioVideoCard;
	private JTextField textFieldPrecioHDD;
	private JTextField textFieldPrecioCaseFuente;
	private JButton btnResetProcesador;
	private JButton btnResetMemoria;
	private JButton btnResetPlaca;
	private JButton btnResetVideoCard;
	private JButton btnResetHDD;
	private JButton btnResetCaseFuente;
	private JTextField textFieldPrecioTotal;
	private WebProgressBar jprogressBar1;
	private JLabel lblProcesando;
	
	public VentanaProductos(VentanaPrincipal miVentanaPrincipal, boolean modal){
	 /**Al llamar al constructor super(), le enviamos el
	  * JFrame Padre y la propiedad booleana que determina
	  * que es hija*/
	  super(miVentanaPrincipal, modal);
	  setResizable(false);
	  setBounds(100, 100, 1032, 790);
	  contenedor = new JPanel();
	  contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
	  setContentPane(contenedor);
	  contenedor.setLayout(null);
	  setLocationRelativeTo(null);
	  setTitle("Componentes - RantiyPC");
	  
	  tiendas = new String[6];
	  
	  JButton btnSeleccionarProcesador = new JButton("");
	  btnSeleccionarProcesador.setContentAreaFilled(false);
	  btnSeleccionarProcesador.setFocusPainted(false);
	  btnSeleccionarProcesador.setBorderPainted(false);
	  btnSeleccionarProcesador.setBorder(null);
	  btnSeleccionarProcesador.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/seleccionarItem.png")));
	  btnSeleccionarProcesador.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
	  		if (evento.getSource() == btnSeleccionarProcesador) {
	  			
	  			jprogressBar1.setVisible(true);
	  			jprogressBar1.setIndeterminate(true);
	  			lblProcesando.setVisible(true);
  			
	  			LeerProcesadores worker = new LeerProcesadores(miVentanaProductos,jprogressBar1,lblProcesando);
	  			worker.execute();
	  			
				/*VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "procesadores");
				miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				miVentanaMini.setVisible(true);*/
			}
		
	  		
	  	}
	  });
	  
	  
	  JButton button = new JButton("");
	  button.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
				if (textFieldProcesador.getText().length() != 0 && textFieldMemoria.getText().length() != 0
						&& textFieldPlaca.getText().length() != 0 && textFieldVideoCard.getText().length() != 0
						&& textFieldHDD.getText().length() != 0 && textFieldCaseFuente.getText().length() != 0) {
					
					String nombreReporte = JOptionPane.showInputDialog("Ingresa un nombre para este reporte");

					Producto[] listaComponentes = new Producto[6];
					Producto nuevo;

					String procesador, placa, memoria, videoCard, hdd, caseFuente;
					Double precioProcesador, precioPlaca, precioMemoria, precioVideoCard, precioHDD, precioCaseFuete, suma;

					procesador = textFieldProcesador.getText();
					placa = textFieldPlaca.getText();
					memoria = textFieldMemoria.getText();
					videoCard = textFieldVideoCard.getText();
					hdd = textFieldHDD.getText();
					caseFuente = textFieldCaseFuente.getText();

					precioProcesador = Double.parseDouble(textFieldPrecioProcesador.getText());
					precioPlaca = Double.parseDouble(textFieldPrecioPlaca.getText());
					precioMemoria = Double.parseDouble(textFieldPrecioMemoria.getText());
					precioVideoCard = Double.parseDouble(textFieldPrecioVideoCard.getText());
					precioHDD = Double.parseDouble(textFieldPrecioHDD.getText());
					precioCaseFuete = Double.parseDouble(textFieldPrecioCaseFuente.getText());

					suma = precioProcesador + precioPlaca + precioMemoria + precioVideoCard + precioHDD
							+ precioCaseFuete;

					// PROCESADOR
					nuevo = new Producto(procesador,precioProcesador, tiendas[0]);
					listaComponentes[0] = nuevo;
					// MEMORIA
					nuevo = new Producto(memoria, precioMemoria, tiendas[1]);
					listaComponentes[1] = nuevo;
					// PLACA
					nuevo = new Producto(placa, precioPlaca, tiendas[2]);
					listaComponentes[2] = nuevo;
					// VIDEOCARD
					nuevo = new Producto(videoCard, precioHDD, tiendas[3]);
					listaComponentes[3] = nuevo;
					// HDD
					nuevo = new Producto(hdd, precioHDD, tiendas[4]);
					listaComponentes[4] = nuevo;
					// CASE-FUENTE
					nuevo = new Producto(caseFuente, precioCaseFuete, tiendas[5]);
					listaComponentes[5] = nuevo;

					textFieldPrecioTotal.setText(Double.toString(suma));

					Date fecha = new Date();

					Reporte nuevoReporte = new Reporte(nombreReporte, "Modulo Productos", fecha, listaComponentes);

					VentanaGenReporte miVentanaGenReporte = new VentanaGenReporte (miVentanaProductos, true, nuevoReporte);
					miVentanaGenReporte.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    miVentanaGenReporte.setVisible(true);
					

				} else {
					JOptionPane.showMessageDialog(null, "Faltan seleccionar componentes!");
				}

	  	}
	  });
	  button.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/btn_enviar_v3.png")));
	  button.setFocusPainted(false);
	  button.setContentAreaFilled(false);
	  button.setBorderPainted(false);
	  button.setBorder(null);
	  button.setBounds(809, 663, 195, 71);
	  contenedor.add(button);
	  btnSeleccionarProcesador.setBounds(241, 200, 40, 33);
	  contenedor.add(btnSeleccionarProcesador);
	  
	  textFieldProcesador = new JTextField();
	  textFieldProcesador.setEditable(false);
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
					
					jprogressBar1.setVisible(true);
		  			jprogressBar1.setIndeterminate(true);
		  			lblProcesando.setVisible(true);
	  			
		  			LeerPlacasMadre worker = new LeerPlacasMadre(miVentanaProductos,jprogressBar1,lblProcesando);
		  			worker.execute();
				}
	  		
	  	}
	  });
	  btnSeleccionarPlaca.setBounds(241, 346, 40, 33);
	  contenedor.add(btnSeleccionarPlaca);
	  
	  textFieldPlaca = new JTextField();
	  textFieldPlaca.setEditable(false);
	  textFieldPlaca.setForeground(Color.WHITE);
	  textFieldPlaca.setBorder(null);
	  textFieldPlaca.setOpaque(false);
	  textFieldPlaca.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPlaca.setBounds(304, 343, 480, 38);
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

				jprogressBar1.setVisible(true);
	  			jprogressBar1.setIndeterminate(true);
	  			lblProcesando.setVisible(true);
  			
	  			LeerMemorias worker = new LeerMemorias(miVentanaProductos,jprogressBar1,lblProcesando);
	  			worker.execute();
			}
	  		
	  	}
	  });
	  btnSeleccionarMemoria.setBounds(241, 274, 40, 33);
	  contenedor.add(btnSeleccionarMemoria);
	  
	  textFieldMemoria = new JTextField();
	  textFieldMemoria.setEditable(false);
	  textFieldMemoria.setForeground(Color.WHITE);
	  textFieldMemoria.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldMemoria.setBorder(null);
	  textFieldMemoria.setOpaque(false);
	  textFieldMemoria.setBounds(304, 273, 480, 35);
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

					jprogressBar1.setVisible(true);
		  			jprogressBar1.setIndeterminate(true);
		  			lblProcesando.setVisible(true);
	  			
		  			LeerTarjetasVideo worker = new LeerTarjetasVideo(miVentanaProductos,jprogressBar1,lblProcesando);
		  			worker.execute();
			}
	  		
	  	}
	  });
	  btnSeleccionarVideoCard.setBounds(241, 419, 40, 33);
	  contenedor.add(btnSeleccionarVideoCard);
	  
	  textFieldVideoCard = new JTextField();
	  textFieldVideoCard.setEditable(false);
	  textFieldVideoCard.setForeground(Color.WHITE);
	  textFieldVideoCard.setBorder(null);
	  textFieldVideoCard.setOpaque(false);
	  textFieldVideoCard.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldVideoCard.setBounds(304, 418, 480, 35);
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
	  			
				jprogressBar1.setVisible(true);
	  			jprogressBar1.setIndeterminate(true);
	  			lblProcesando.setVisible(true);
  			
	  			LeerUnidadesAlmacenamiento worker = new LeerUnidadesAlmacenamiento(miVentanaProductos,jprogressBar1,lblProcesando);
	  			worker.execute();
			}
	  		
	  	}
	  });
	  btnSeleccionarHDD.setBounds(241, 493, 40, 33);
	  contenedor.add(btnSeleccionarHDD);
	  
	  textFieldHDD = new JTextField();
	  textFieldHDD.setEditable(false);
	  textFieldHDD.setForeground(Color.WHITE);
	  textFieldHDD.setBorder(null);
	  textFieldHDD.setOpaque(false);
	  textFieldHDD.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldHDD.setBounds(304, 492, 480, 35);
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

	  			jprogressBar1.setVisible(true);
	  			jprogressBar1.setIndeterminate(true);
	  			lblProcesando.setVisible(true);
  			
	  			LeerCaseFuentes worker = new LeerCaseFuentes(miVentanaProductos,jprogressBar1,lblProcesando);
	  			worker.execute();
			}
	  		
	  	}
	  });
	  btnSeleccionarCaseFuente.setBounds(241, 565, 40, 33);
	  contenedor.add(btnSeleccionarCaseFuente);
	  
	  textFieldCaseFuente = new JTextField();
	  textFieldCaseFuente.setEditable(false);
	  textFieldCaseFuente.setForeground(Color.WHITE);
	  textFieldCaseFuente.setBorder(null);
	  textFieldCaseFuente.setOpaque(false);
	  textFieldCaseFuente.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldCaseFuente.setBounds(304, 564, 480, 35);
	  contenedor.add(textFieldCaseFuente);
	  textFieldCaseFuente.setColumns(10);
	  
	  textFieldPrecioProcesador = new JTextField();
	  textFieldPrecioProcesador.setEditable(false);
	  textFieldPrecioProcesador.setForeground(Color.WHITE);
	  textFieldPrecioProcesador.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecioProcesador.setOpaque(false);
	  textFieldPrecioProcesador.setBorder(null);
	  textFieldPrecioProcesador.setBounds(822, 199, 120, 35);
	  contenedor.add(textFieldPrecioProcesador);
	  textFieldPrecioProcesador.setColumns(10);
	  
	  textFieldPrecioMemoria = new JTextField();
	  textFieldPrecioMemoria.setEditable(false);
	  textFieldPrecioMemoria.setForeground(Color.WHITE);
	  textFieldPrecioMemoria.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecioMemoria.setOpaque(false);
	  textFieldPrecioMemoria.setBorder(null);
	  textFieldPrecioMemoria.setColumns(10);
	  textFieldPrecioMemoria.setBounds(822, 273, 120, 35);
	  contenedor.add(textFieldPrecioMemoria);
	  
	  textFieldPrecioPlaca = new JTextField();
	  textFieldPrecioPlaca.setEditable(false);
	  textFieldPrecioPlaca.setForeground(Color.WHITE);
	  textFieldPrecioPlaca.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecioPlaca.setOpaque(false);
	  textFieldPrecioPlaca.setBorder(null);
	  textFieldPrecioPlaca.setColumns(10);
	  textFieldPrecioPlaca.setBounds(822, 346, 120, 35);
	  contenedor.add(textFieldPrecioPlaca);
	  
	  textFieldPrecioVideoCard = new JTextField();
	  textFieldPrecioVideoCard.setEditable(false);
	  textFieldPrecioVideoCard.setForeground(Color.WHITE);
	  textFieldPrecioVideoCard.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecioVideoCard.setOpaque(false);
	  textFieldPrecioVideoCard.setBorder(null);
	  textFieldPrecioVideoCard.setColumns(10);
	  textFieldPrecioVideoCard.setBounds(822, 418, 120, 35);
	  contenedor.add(textFieldPrecioVideoCard);
	  
	  textFieldPrecioHDD = new JTextField();
	  textFieldPrecioHDD.setEditable(false);
	  textFieldPrecioHDD.setForeground(Color.WHITE);
	  textFieldPrecioHDD.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecioHDD.setOpaque(false);
	  textFieldPrecioHDD.setBorder(null);
	  textFieldPrecioHDD.setColumns(10);
	  textFieldPrecioHDD.setBounds(822, 492, 120, 35);
	  contenedor.add(textFieldPrecioHDD);
	  
	  textFieldPrecioCaseFuente = new JTextField();
	  textFieldPrecioCaseFuente.setEditable(false);
	  textFieldPrecioCaseFuente.setForeground(Color.WHITE);
	  textFieldPrecioCaseFuente.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
	  textFieldPrecioCaseFuente.setOpaque(false);
	  textFieldPrecioCaseFuente.setBorder(null);
	  textFieldPrecioCaseFuente.setColumns(10);
	  textFieldPrecioCaseFuente.setBounds(822, 564, 120, 35);
	  contenedor.add(textFieldPrecioCaseFuente);
	  
	  btnResetProcesador = new JButton("");
	  btnResetProcesador.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent arg0) {
	  		
	  		textFieldProcesador.setText("");
	  		textFieldPrecioProcesador.setText("");
	  		
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
	  		textFieldPrecioMemoria.setText("");
	  		
	  	}
	  });
	  btnResetMemoria.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetMemoria.setFocusPainted(false);
	  btnResetMemoria.setContentAreaFilled(false);
	  btnResetMemoria.setBorderPainted(false);
	  btnResetMemoria.setBorder(null);
	  btnResetMemoria.setBounds(959, 269, 39, 43);
	  contenedor.add(btnResetMemoria);
	  
	  btnResetPlaca = new JButton("");
	  btnResetPlaca.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldPlaca.setText("");
	  		textFieldPrecioPlaca.setText("");
	  		
	  	}
	  });
	  btnResetPlaca.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetPlaca.setFocusPainted(false);
	  btnResetPlaca.setContentAreaFilled(false);
	  btnResetPlaca.setBorderPainted(false);
	  btnResetPlaca.setBorder(null);
	  btnResetPlaca.setBounds(959, 339, 39, 43);
	  contenedor.add(btnResetPlaca);
	  
	  btnResetVideoCard = new JButton("");
	  btnResetVideoCard.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldVideoCard.setText("");
	  		textFieldPrecioVideoCard.setText("");
	  		
	  	}
	  });
	  btnResetVideoCard.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetVideoCard.setFocusPainted(false);
	  btnResetVideoCard.setContentAreaFilled(false);
	  btnResetVideoCard.setBorderPainted(false);
	  btnResetVideoCard.setBorder(null);
	  btnResetVideoCard.setBounds(959, 414, 39, 43);
	  contenedor.add(btnResetVideoCard);
	  
	  btnResetHDD = new JButton("");
	  btnResetHDD.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldHDD.setText("");
	  		textFieldPrecioHDD.setText("");
	  		
	  	}
	  });
	  btnResetHDD.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetHDD.setFocusPainted(false);
	  btnResetHDD.setContentAreaFilled(false);
	  btnResetHDD.setBorderPainted(false);
	  btnResetHDD.setBorder(null);
	  btnResetHDD.setBounds(959, 488, 39, 43);
	  contenedor.add(btnResetHDD);
	  
	  btnResetCaseFuente = new JButton("");
	  btnResetCaseFuente.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		textFieldCaseFuente.setText("");
	  		textFieldPrecioCaseFuente.setText("");
	  		
	  	}
	  });
	  btnResetCaseFuente.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/resetItem.png")));
	  btnResetCaseFuente.setFocusPainted(false);
	  btnResetCaseFuente.setContentAreaFilled(false);
	  btnResetCaseFuente.setBorderPainted(false);
	  btnResetCaseFuente.setBorder(null);
	  btnResetCaseFuente.setBounds(959, 560, 39, 43);
	  contenedor.add(btnResetCaseFuente);
	  
	  textFieldPrecioTotal = new JTextField();
	  textFieldPrecioTotal.setForeground(Color.WHITE);
	  textFieldPrecioTotal.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 22));
	  textFieldPrecioTotal.setOpaque(false);
	  textFieldPrecioTotal.setBorder(null);
	  textFieldPrecioTotal.setBounds(495, 681, 194, 35);
	  contenedor.add(textFieldPrecioTotal);
	  textFieldPrecioTotal.setColumns(10);
	  
	  jprogressBar1 = new WebProgressBar();
	  jprogressBar1.setBounds(26, 701, 225, 17);
	  jprogressBar1.setVisible(false);
	  contenedor.add(jprogressBar1);
	  
	  lblProcesando = new JLabel("Procesando...");
	  lblProcesando.setForeground(Color.WHITE);
	  lblProcesando.setFont(new Font("Roboto Light", Font.BOLD, 14));
	  lblProcesando.setBounds(26, 681, 97, 17);
	  lblProcesando.setVisible(false);
	  contenedor.add(lblProcesando);
	  
	  /*JButton btnNewButton = new JButton("New button");
	  btnNewButton.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent evento) {
	  		
	  		if (evento.getSource() == btnNewButton) {
	  			
	  			jprogressBar1.setVisible(true);
	  			jprogressBar1.setIndeterminate(true);
	  			lblProcesando.setVisible(true);
  			
	  			LeerComputadoras worker = new LeerComputadoras(miVentanaProductos,jprogressBar1,lblProcesando);
	  			worker.execute();
			}
	  		
	  	}
	  });
	  btnNewButton.setBounds(217, 129, 89, 23);
	  contenedor.add(btnNewButton);*/
	  
	  
	  JLabel label = new JLabel("");
	  label.setIcon(new ImageIcon(VentanaProductos.class.getResource("/resources/vista-producto-v3.jpg")));
	  label.setBounds(0, 0, 1024, 760);
	  contenedor.add(label);
	  
	  
	 }
	

	public void setTiendas(String tienda, int indice) {
		this.tiendas[indice] = tienda;
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


	public JTextField getTextFieldPrecioProcesador() {
		return textFieldPrecioProcesador;
	}


	public void setTextFieldPrecioProcesador(JTextField textFieldPrecioProcesador) {
		this.textFieldPrecioProcesador = textFieldPrecioProcesador;
	}


	public JTextField getTextFieldPrecioMemoria() {
		return textFieldPrecioMemoria;
	}


	public void setTextFieldPrecioMemoria(JTextField textFieldPrecioMemoria) {
		this.textFieldPrecioMemoria = textFieldPrecioMemoria;
	}


	public JTextField getTextFieldPrecioPlaca() {
		return textFieldPrecioPlaca;
	}


	public void setTextFieldPrecioPlaca(JTextField textFieldPrecioPlaca) {
		this.textFieldPrecioPlaca = textFieldPrecioPlaca;
	}


	public JTextField getTextFieldPrecioVideoCard() {
		return textFieldPrecioVideoCard;
	}


	public void setTextFieldPrecioVideoCard(JTextField textFieldPrecioVideoCard) {
		this.textFieldPrecioVideoCard = textFieldPrecioVideoCard;
	}


	public JTextField getTextFieldPrecioHDD() {
		return textFieldPrecioHDD;
	}


	public void setTextFieldPrecioHDD(JTextField textFieldPrecioHDD) {
		this.textFieldPrecioHDD = textFieldPrecioHDD;
	}


	public JTextField getTextFieldPrecioCaseFuente() {
		return textFieldPrecioCaseFuente;
	}


	public void setTextFieldPrecioCaseFuente(JTextField textFieldPrecioCaseFuente) {
		this.textFieldPrecioCaseFuente = textFieldPrecioCaseFuente;
	}

	
}
