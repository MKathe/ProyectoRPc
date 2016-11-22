package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conectionDB.ConectionDB;
import datos.ConsultasBasicas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import entidades.Usuario;
import negocio.ValidarRegistro;
import java.awt.Color;
import java.awt.Cursor;

public class UsuarioNuevo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldUsuario;
	private JTextField textFieldContraseña;
	private JTextField textFieldConfirmarContraseña;
	private JTextField textfieldCorreo;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			UsuarioNuevo dialog = new UsuarioNuevo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}/*

	/**
	 * Create the dialog.
	 */
	public UsuarioNuevo(/*Login miventanalogin,boolean modal*/) {
		//super(miventanalogin,modal);
		setBounds(100, 100, 461, 598);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldNombre.setForeground(Color.WHITE);
		textFieldNombre.setBorder(null);
		textFieldNombre.setOpaque(false);
		textFieldNombre.setBounds(200, 102, 190, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldApellidos.setForeground(Color.WHITE);
		textFieldApellidos.setOpaque(false);
		textFieldApellidos.setBorder(null);
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(200, 167, 190, 20);
		contentPanel.add(textFieldApellidos);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldUsuario.setForeground(Color.WHITE);
		textFieldUsuario.setBorder(null);
		textFieldUsuario.setOpaque(false);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBounds(200, 306, 190, 20);
		contentPanel.add(textFieldUsuario);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldContraseña.setForeground(Color.WHITE);
		textFieldContraseña.setBorder(null);
		textFieldContraseña.setOpaque(false);
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(200, 379, 190, 20);
		contentPanel.add(textFieldContraseña);
		
		JButton btnGuardar = new JButton("");
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setIcon(new ImageIcon(UsuarioNuevo.class.getResource("/resources/btn_reg.png")));
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBounds(141, 504, 179, 45);
		contentPanel.add(btnGuardar);
		btnGuardar.addActionListener(new ManejadorRegistrarUsuario());
		
		textFieldConfirmarContraseña = new JTextField();
		textFieldConfirmarContraseña.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldConfirmarContraseña.setForeground(Color.WHITE);
		textFieldConfirmarContraseña.setBorder(null);
		textFieldConfirmarContraseña.setOpaque(false);
		textFieldConfirmarContraseña.setColumns(10);
		textFieldConfirmarContraseña.setBounds(200, 446, 190, 20);
		contentPanel.add(textFieldConfirmarContraseña);
		
		textfieldCorreo = new JTextField();
		textfieldCorreo.setFont(new Font("Tahoma", Font.BOLD, 11));
		textfieldCorreo.setForeground(Color.WHITE);
		textfieldCorreo.setBorder(null);
		textfieldCorreo.setOpaque(false);
		textfieldCorreo.setBounds(200, 233, 190, 20);
		contentPanel.add(textfieldCorreo);
		textfieldCorreo.setColumns(10);
		
		JLabel LabelFondo = new JLabel("New label");
		LabelFondo.setIcon(new ImageIcon(UsuarioNuevo.class.getResource("/resources/vista_registrar4.png")));
		LabelFondo.setBounds(0, 0, 453, 560);
		contentPanel.add(LabelFondo);
	}
	
	public class ManejadorRegistrarUsuario implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			
			if(textFieldNombre.getText().length()!= 0 &&textFieldApellidos.getText().length()!= 0 && textFieldUsuario .getText().length()!= 0 && textFieldContraseña.getText().length()!= 0 && textfieldCorreo.getText().length()!= 0){
				 boolean existe = ValidarRegistro.VerificarExitencia(textFieldUsuario.getText());
		         if(!existe){
		             if(!ValidarRegistro.ValidarIgualdad(textFieldContraseña.getText(),textFieldConfirmarContraseña.getText())){
		            	 JOptionPane.showMessageDialog(null, "las contraseña ingresadas no coinciden", "",JOptionPane.INFORMATION_MESSAGE);
		             }else{
		            	 Usuario usuarionuevo = new Usuario();
		            	 usuarionuevo.setNombre(textFieldNombre.getText());
		            	 usuarionuevo.setApellidos(textFieldApellidos.getText());
		            	 usuarionuevo.setUsuario(textFieldUsuario.getText());
		            	 usuarionuevo.setContraseña(textFieldContraseña.getText());
		            	 usuarionuevo.setCorreo(textfieldCorreo.getText());
		            	 ConsultasBasicas.insertarUsuario(usuarionuevo);
		             }

		         }else{
		    	JOptionPane.showMessageDialog(null, "Ingrese otro nombre-Usuario Existente", "",JOptionPane.INFORMATION_MESSAGE);
		        }
			 	 
		       
			}else{
				JOptionPane.showMessageDialog(null, "Faltan completar datos", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		   
			}
			
		}
		
	
}
