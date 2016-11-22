package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.Reproductor;

import java.awt.Color;

public class VentanaReproductorVideo extends JFrame {

	private static JPanel contentPane;
	VentanaReproductorVideo miVentanaReproductorVideo;

	public VentanaReproductorVideo(VentanaPrincipal miVentanaPrincipal, boolean modal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 523);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//metodo de reproductor
		Reproductor.reproducir();
	}
	
	public static void getPanel(JPanel panelVideo){
		contentPane.add(panelVideo);
	}

	public void setVentanaPrincipal(VentanaReproductorVideo miVentanaReproductor) {
		miVentanaReproductorVideo = miVentanaReproductor;
		
	}
}
