package presentacion;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import entidades.Reporte;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.Date;
import java.awt.Font;

public class VentanaMostrarReporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textTipo;
	private JTextField textFecha;
	private JTextField textField;

	public VentanaMostrarReporte(VentanaReportes miVentanaReportes,boolean modal,Reporte r) {
		super(miVentanaReportes,modal);
		setBounds(100, 100, 800, 625);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel lblReporteSeleccionado = new JLabel("Reporte Seleccionado:");
		lblReporteSeleccionado.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReporteSeleccionado.setBounds(46, 49, 228, 33);
		contentPanel.add(lblReporteSeleccionado);
		
		
		JLabel lblNombreDeReporte = new JLabel("Nombre de Reporte:");
		lblNombreDeReporte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreDeReporte.setBounds(46, 99, 150, 14);
		contentPanel.add(lblNombreDeReporte);
		
		JLabel lblTipoDeReporte = new JLabel("Tipo de Reporte:");
		lblTipoDeReporte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoDeReporte.setBounds(46, 124, 150, 14);
		contentPanel.add(lblTipoDeReporte);
		
		JLabel lblFechaDeReporte = new JLabel("Fecha de Reporte:");
		lblFechaDeReporte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeReporte.setBounds(46, 167, 150, 14);
		contentPanel.add(lblFechaDeReporte);
		
		textNombre = new JTextField();
		textNombre.setBounds(258, 93, 249, 20);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		textNombre.setText(r.getNombre());
		
		textTipo = new JTextField();
		textTipo.setBounds(258, 122, 249, 20);
		contentPanel.add(textTipo);
		textTipo.setColumns(10);
		textTipo.setText(r.getTipo());
		
		textFecha = new JTextField();
		textFecha.setBounds(258, 165, 249, 20);
		contentPanel.add(textFecha);
		textFecha.setColumns(10);
		Date d=r.getFecha();
		textFecha.setText(d.toString());
		
		JLabel lblDetalles = new JLabel("Detalles:");
		lblDetalles.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDetalles.setBounds(46, 224, 106, 14);
		contentPanel.add(lblDetalles);
		
		textField = new JTextField();
		textField.setBounds(46, 267, 457, 91);
		contentPanel.add(textField);
		textField.setColumns(10);
	
		setLocationRelativeTo(null);
	}
}
