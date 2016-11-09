package presentacion;
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
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal miVentanaPrincipal;
	private JButton btnProductos;
	private JButton btnReportes;
	private JLabel lblFondo;
	private JButton btnTienda;
	private JButton btnAsistente;
	private JButton btnAyuda;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					WebLookAndFeel.setDecorateFrames(true);
					WebLookAndFeel.setDecorateDialogs(true);
					
					VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
					miVentanaPrincipal.setVentanaPrincipal(miVentanaPrincipal);
					miVentanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public VentanaPrincipal() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/resources/icon-app.png")));
		setTitle("RantiyPC - Tu asistente de tcnico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		setResizable(false);
		
		btnProductos = new JButton("");
		btnProductos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/resources/icono productos.png")));
		btnProductos.setContentAreaFilled(false);
		btnProductos.setBorderPainted(false);
		btnProductos.setBorder(null);
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()== btnProductos){
					VentanaProductos miVentanaProductos = new VentanaProductos(miVentanaPrincipal, true);					
							miVentanaProductos.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							miVentanaProductos.setVentanaPrincipal(miVentanaProductos);
							miVentanaProductos.setVisible(true);
				}
			}
		});
        
        btnAsistente = new JButton("");
        btnAsistente.setBorderPainted(false);
        btnAsistente.setContentAreaFilled(false);
        btnAsistente.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/resources/icono asistente.png")));
        btnAsistente.setBorder(null);
        btnAsistente.setBounds(35, 512, 113, 97);
        contentPane.add(btnAsistente);
        btnProductos.setBounds(189, 512, 167, 109);
		contentPane.add(btnProductos);
		btnReportes = new JButton ("");
		btnReportes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/resources/icono reporte.png")));
		btnReportes.setBorder(null);
		btnReportes.setBorderPainted(false);
		btnReportes.setContentAreaFilled(false);
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 
					 /*VentanaReportes miVentanaReportes =new VentanaReportes(miVentanaPrincipal,true);
					 miVentanaReportes.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					 miVentanaReportes.setVentanaPrincipal(miVentanaReportes);
					 miVentanaReportes.setVisible(true);*/
			
			}
		});
        
        btnTienda = new JButton("");
        btnTienda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/resources/icono tiendas.png")));
        btnTienda.setContentAreaFilled(false);
        btnTienda.setBorderPainted(false);
        btnTienda.setBorder(null);
        btnTienda.setBounds(399, 512, 109, 109);
        contentPane.add(btnTienda);
        btnReportes.setBounds(578,512,94, 109);
		contentPane.add(btnReportes);
		
		btnAyuda = new JButton("");
		btnAyuda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/resources/icono ayuda.png")));
		btnAyuda.setContentAreaFilled(false);
		btnAyuda.setBorderPainted(false);
		btnAyuda.setBorder(null);
		btnAyuda.setBounds(763, 512, 94, 97);
		contentPane.add(btnAyuda);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/resources/fondo RantiyPC.jpg")));
		lblFondo.setBounds(0, 0, 904, 668);
		contentPane.add(lblFondo);
	}
	
	public void setVentanaPrincipal(VentanaPrincipal miVentana) {
		   miVentanaPrincipal = miVentana;
	}
	
}
