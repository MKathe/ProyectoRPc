package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal miVentanaPrincipal;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					WebLookAndFeel.install();
					//WebLookAndFeel.setDecorateAllWindows(true);
					
					VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
					miVentanaPrincipal.setVentanaPrincipal(miVentanaPrincipal);
					miVentanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/resources/icon-app.png")));
		setTitle("RantiyPC - Tu asistente de compras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btnProductos = new JButton("PRODUCTOS");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {

				if (evento.getSource() == btnProductos) {
					
					// Enviamos la instancia de la ventana principal para que esta sea Padre de la ventana de dialogo
					 
					VentanaProductos miVentanaProductos = new VentanaProductos(miVentanaPrincipal, true);					
					miVentanaProductos.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					miVentanaProductos.setVentanaPrincipal(miVentanaProductos);
					miVentanaProductos.setVisible(true);
				}
			}
		});
		btnProductos.setBounds(317, 424, 161, 78);
		contentPane.add(btnProductos);
	}
	
	public void setVentanaPrincipal(VentanaPrincipal miVentana) {
		   miVentanaPrincipal = miVentana;
	}
	
}
