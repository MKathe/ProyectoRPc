package presentacion;

import java.awt.BorderLayout;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.EnviarEmailConAdjunto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaContactoTienda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDest;
	private JTextField textFieldAsunto;
	
	private String rutaReporte;
    
	
	public VentanaContactoTienda(VentanaDetallesAsistente miVentanaDetallesAsistente, boolean modal,String ruta)  {
		
		super(miVentanaDetallesAsistente, modal);
		setResizable(false);
		
		this.rutaReporte = ruta;
		
		setBounds(100, 100, 1030, 770);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textFieldDest = new JTextField();
		textFieldDest.setBorder(null);
		textFieldDest.setOpaque(false);
		textFieldDest.setBounds(217, 152, 652, 32);
		contentPanel.add(textFieldDest);
		textFieldDest.setColumns(10);
		
		textFieldAsunto = new JTextField();
		textFieldAsunto.setBorder(null);
		textFieldAsunto.setOpaque(false);
		textFieldAsunto.setBounds(217, 236, 652, 32);
		contentPanel.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);
		
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
					//llama al método que va enviar el email
					
					EnviarEmailConAdjunto.enviarEmail(textFieldDest.getText(), textFieldAsunto.getText(), textPaneMensaje.getText(), rutaReporte);
					JOptionPane.showMessageDialog(null, "Mensaje enviado");
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
