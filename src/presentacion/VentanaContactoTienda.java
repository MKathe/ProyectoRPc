package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.EnviarEmailConAdjunto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaContactoTienda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUser;
	private JTextField textFieldDest;
	private JTextField textFieldAsunto;
	private JPasswordField passwordFieldPass;
	
	private String rutaReporte;
	private String nombreReporte;

	
	public VentanaContactoTienda(VentanaReportes miVentanaReportes, boolean modal, String ruta, String nombre) {
		
		super(miVentanaReportes, modal);
		setResizable(false);
		
		this.rutaReporte = ruta;
		this.nombreReporte = nombre;
		
		setBounds(100, 100, 1030, 770);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textFieldUser = new JTextField();
		textFieldUser.setBorder(null);
		textFieldUser.setOpaque(false);
		textFieldUser.setBounds(217, 120, 652, 32);
		contentPanel.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		textFieldDest = new JTextField();
		textFieldDest.setBorder(null);
		textFieldDest.setOpaque(false);
		textFieldDest.setBounds(217, 247, 652, 32);
		contentPanel.add(textFieldDest);
		textFieldDest.setColumns(10);
		
		textFieldAsunto = new JTextField();
		textFieldAsunto.setBorder(null);
		textFieldAsunto.setOpaque(false);
		textFieldAsunto.setBounds(217, 309, 652, 32);
		contentPanel.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);
		
		passwordFieldPass = new JPasswordField();
		passwordFieldPass.setOpaque(false);
		passwordFieldPass.setBorder(null);
		passwordFieldPass.setBounds(217, 184, 652, 32);
		contentPanel.add(passwordFieldPass);
		
		JTextPane textPaneMensaje = new JTextPane();
		textPaneMensaje.setBorder(null);
		textPaneMensaje.setOpaque(false);
		textPaneMensaje.setBounds(67, 370, 896, 239);
		contentPanel.add(textPaneMensaje);
		
		JButton btnEnviar = new JButton("");
		btnEnviar.setContentAreaFilled(false);
		btnEnviar.setBorder(null);
		btnEnviar.setIcon(new ImageIcon(VentanaContactoTienda.class.getResource("/resources/Bot\u00F3n enviar - modulo contacto.png")));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EnviarEmailConAdjunto.enviarEmail(textFieldUser.getText(), passwordFieldPass.getText(), textFieldDest.getText(), textFieldAsunto.getText(), textPaneMensaje.getText(), rutaReporte, nombreReporte);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(354, 645, 319, 86);
		contentPanel.add(btnEnviar);
		
		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(VentanaContactoTienda.class.getResource("/resources/fondo modulo contacto tienda.jpg")));
		labelFondo.setBounds(0, 0, 1028, 750);
		contentPanel.add(labelFondo);
	}
}
