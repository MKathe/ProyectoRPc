package presentacion;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import entidades.Producto;
import modeloTablas.ModeloTablaProducto;
import negocio.ObtenerProductos;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMiniProductos extends JDialog {
	private JTable table;

	public VentanaMiniProductos(VentanaProductos miVentanProductos, boolean modal, List<Producto> listaProductos, String source) {
		
		/**Al llamar al constructor super(), le enviamos la ventana Padre y la propiedad booleana que determina
		  * que es hija*/
		super(miVentanProductos, modal);		
		setBounds(100, 100, 735, 550);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, 699, 335);
		getContentPane().add(scrollPane);
			
		TableModel tableModel = new ModeloTablaProducto(listaProductos);	
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(490);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(134);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JButton btnSeleccionarProducto = new JButton("Seleccionar Producto");
		btnSeleccionarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {

				
				switch(source){
				case "procesadores":
					miVentanProductos.getTextFieldProcesador().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					dispose();
					break;
				case "placas-madre":
					miVentanProductos.getTextFieldPlaca().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					dispose();
					break;
				case "memorias":
					miVentanProductos.getTextFieldMemoria().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					dispose();
					break;
				case "video-cards":
					miVentanProductos.getTextFieldVideoCard().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					dispose();
					break;
				case "unidades-almacenamiento":
					miVentanProductos.getTextFieldHDD().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					dispose();
					break;
				case "case-fuentes":
					miVentanProductos.getTextFieldCaseFuente().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					dispose();
					break;
				default:	
				
				}	
				
			}
		});
		btnSeleccionarProducto.setFont(new Font("Roboto Condensed Light", Font.BOLD, 16));
		btnSeleccionarProducto.setBounds(267, 471, 185, 27);
		getContentPane().add(btnSeleccionarProducto);	
		
		
	}
	
	
}
