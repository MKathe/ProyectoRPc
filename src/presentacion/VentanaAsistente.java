package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaAsistente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAsistente frame = new VentanaAsistente();
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
	public VentanaAsistente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 115, 166, 20);
		contentPane.add(comboBox);
		
		JLabel lblPcLowCost = new JLabel("PC ULTRA LOW COST");
		lblPcLowCost.setBounds(10, 82, 104, 14);
		contentPane.add(lblPcLowCost);
		
		JLabel lblPcGamaUltra = new JLabel("PC GAMA ULTRA BAJA");
		lblPcGamaUltra.setBounds(10, 174, 108, 14);
		contentPane.add(lblPcGamaUltra);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 220, 166, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblPcGamaBaja = new JLabel("PC GAMA BAJA");
		lblPcGamaBaja.setBounds(10, 280, 73, 14);
		contentPane.add(lblPcGamaBaja);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 319, 166, 20);
		contentPane.add(comboBox_2);
		
		JLabel label = new JLabel("PC ULTRA LOW COST");
		label.setBounds(10, 376, 104, 14);
		contentPane.add(label);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(10, 412, 166, 20);
		contentPane.add(comboBox_3);
		
		JLabel lblProcesador = new JLabel("PROCESADOR");
		lblProcesador.setBounds(261, 82, 69, 14);
		contentPane.add(lblProcesador);
		
		JLabel lblPlacaMadre = new JLabel("PLACA MADRE");
		lblPlacaMadre.setBounds(261, 158, 70, 14);
		contentPane.add(lblPlacaMadre);
		
		JLabel lblMemoria = new JLabel("MEMORIA");
		lblMemoria.setBounds(261, 230, 48, 14);
		contentPane.add(lblMemoria);
		
		JLabel lblVideoCard = new JLabel("VIDEO CARD");
		lblVideoCard.setBounds(261, 295, 62, 14);
		contentPane.add(lblVideoCard);
		
		JLabel lblAlmacenamiento = new JLabel("ALMACENAMIENTO");
		lblAlmacenamiento.setBounds(261, 381, 93, 14);
		contentPane.add(lblAlmacenamiento);
		
		JLabel lblFuentecase = new JLabel("FUENTE/CASE");
		lblFuentecase.setBounds(262, 471, 68, 14);
		contentPane.add(lblFuentecase);
		
		textField = new JTextField();
		textField.setBounds(261, 115, 323, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(261, 193, 323, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(261, 255, 323, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(261, 326, 323, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(261, 418, 323, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(261, 511, 323, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(623, 565, 89, 23);
		contentPane.add(btnGuardar);
	}
}
