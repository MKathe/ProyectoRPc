package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Cursor;
import com.alee.laf.WebLookAndFeel;

import entidades.Usuario;
import negocio.ValidarUsuario;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textPass;
	private JTextField textUser;
	
	public static Usuario usuarioIngresado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					WebLookAndFeel.setDecorateFrames(true);
					WebLookAndFeel.setDecorateDialogs(true);
					
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Login() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Calibri", Font.BOLD, 13));
		lblUsuario.setForeground(SystemColor.window);
		lblUsuario.setBounds(162, 224, 56, 17);
		contentPane.add(lblUsuario);
		
		JLabel lblnoEresUsuario = new JLabel("\u00BFNo eres Usuario?");
		lblnoEresUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblnoEresUsuario.setFont(new Font("Calibri", Font.BOLD, 13));
		lblnoEresUsuario.setForeground(SystemColor.window);
		lblnoEresUsuario.setBounds(162, 356, 127, 14);
		contentPane.add(lblnoEresUsuario);
		
		JLabel lblTuAsistente = new JLabel("Tu asistente t\u00E9cnico");
		lblTuAsistente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTuAsistente.setForeground(SystemColor.window);
		lblTuAsistente.setBounds(199, 197, 142, 9);
		contentPane.add(lblTuAsistente);
		
		textPass = new JPasswordField();
		textPass.setBorder(null);
		textPass.setColumns(20);
		textPass.setBounds(162, 291, 127, 20);
		contentPane.add(textPass);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Calibri", Font.BOLD, 13));
		lblContrasea.setBounds(162, 275, 78, 17);
		contentPane.add(lblContrasea);
		
		JButton btnRegistrate = new JButton("Registrate");
		btnRegistrate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrate.setForeground(Color.WHITE);
		btnRegistrate.setFont(new Font("Calibri", Font.BOLD, 14));
		btnRegistrate.setBorder(UIManager.getBorder("RadioButton.border"));
		btnRegistrate.setBackground(Color.LIGHT_GRAY);
		btnRegistrate.setBounds(172, 390, 106, 23);
		
		contentPane.add(btnRegistrate);
		btnRegistrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioNuevo miVentanaUsuario = new UsuarioNuevo();					
				miVentanaUsuario.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				miVentanaUsuario.setVisible(true);
				
				
			}
		});
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean encontrado = false;

				String user = textUser.getText();
				String pass = String.valueOf(textPass.getPassword());

				encontrado = ValidarUsuario.verificarUsuario(user, pass);

				if (encontrado == true) {

					JOptionPane.showMessageDialog(null, "Bienvenido!");
					
					VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
					miVentanaPrincipal.setVentanaPrincipal(miVentanaPrincipal);
					miVentanaPrincipal.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Error!");
				}
				
			}
		});
		
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Calibri", Font.BOLD, 14));
		btnIngresar.setBorder(UIManager.getBorder("RadioButton.border"));
		btnIngresar.setBackground(Color.LIGHT_GRAY);
		btnIngresar.setBounds(172, 322, 106, 23);
		contentPane.add(btnIngresar);
		
		textUser = new JTextField();
		textUser.setBorder(null);
		textUser.setBounds(162, 252, 127, 20);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/vista_login3.jpg")));
		lblNewLabel.setBounds(0, -13, 443, 597);
		contentPane.add(lblNewLabel);
	}
	public class ManejadorRegistrarse implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void setUsuario(Usuario usuario){
		usuarioIngresado = usuario;
	}
	
}
