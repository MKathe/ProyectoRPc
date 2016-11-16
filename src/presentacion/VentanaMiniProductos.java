package presentacion;

import java.util.List;
import javax.swing.JDialog;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import entidades.Producto;
import modeloTablas.ModeloTablaProducto;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class VentanaMiniProductos extends JDialog {
	private WebTable table;

	public VentanaMiniProductos(VentanaProductos miVentanProductos, boolean modal, List<Producto> listaProductos, String source) {
		
		/**Al llamar al constructor super(), le enviamos la ventana Padre y la propiedad booleana que determina
		  * que es hija*/
		super(miVentanProductos, modal);		
		setResizable(false);
		setTitle("Elegir Producto - RantiyPC");
		setBounds(100, 100, 1031, 790);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		ModeloTablaProducto tableModel = new ModeloTablaProducto(listaProductos);	
		table = new WebTable(tableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(490);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(134);
		table.setAutoCreateRowSorter(true);
		
		WebScrollPane scrollPane = new WebScrollPane(table);
		scrollPane.setBounds(43, 139, 940, 453);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);	
		
		JButton btnSeleccionarProducto = new JButton("");
		btnSeleccionarProducto.setContentAreaFilled(false);
		btnSeleccionarProducto.setBorderPainted(false);
		btnSeleccionarProducto.setIcon(new ImageIcon(VentanaMiniProductos.class.getResource("/resources/seleccionar-producto.png")));
		btnSeleccionarProducto.setBorder(null);
		btnSeleccionarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {

				
				switch(source){
				case "procesadores":
					miVentanProductos.getTextFieldProcesador().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecio1().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					dispose();
					break;
				case "memorias":
					miVentanProductos.getTextFieldMemoria().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecio2().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					dispose();
					break;	
				case "placas-madre":
					miVentanProductos.getTextFieldPlaca().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecio3().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					dispose();
					break;
				case "video-cards":
					miVentanProductos.getTextFieldVideoCard().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecio4().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					dispose();
					break;
				case "unidades-almacenamiento":
					miVentanProductos.getTextFieldHDD().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecio5().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					dispose();
					break;
				case "case-fuentes":
					miVentanProductos.getTextFieldCaseFuente().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecio6().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					dispose();
					break;
				case "computadoras":
					JOptionPane.showMessageDialog(null, "hola mundo");
					break;
				default:	
				
				}	
				
			}
		});
		btnSeleccionarProducto.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
		btnSeleccionarProducto.setBounds(298, 645, 429, 89);
		getContentPane().add(btnSeleccionarProducto);	
		
		JLabel fondoMiniProductos = new JLabel("");
		fondoMiniProductos.setIcon(new ImageIcon(VentanaMiniProductos.class.getResource("/resources/vista-miniproducto.jpg")));
		fondoMiniProductos.setBounds(0, 0, 1024, 760);
		getContentPane().add(fondoMiniProductos);
		
		
	}
}
