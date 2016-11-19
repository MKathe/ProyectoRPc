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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaMiniProductos extends JDialog {
	private WebTable table;
	private JTextField txtBuscar;
	private TableRowSorter<ModeloTablaProducto> trsFiltro;

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
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				txtFiltroKeyTyped(arg0);
					
			}
		});
		txtBuscar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 19));
		txtBuscar.setBackground(Color.WHITE);
		txtBuscar.setBounds(353, 134, 380, 30);
		getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 21));
		lblBuscar.setBounds(276, 137, 67, 25);
		getContentPane().add(lblBuscar);
		table.setAutoCreateRowSorter(true);
		
		WebScrollPane scrollPane = new WebScrollPane(table);
		scrollPane.setBounds(43, 179, 940, 413);
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
					miVentanProductos.getTextFieldPrecioProcesador().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					miVentanProductos.setTiendas(listaProductos.get(table.getSelectedRow()).getTienda(), 0);
					dispose();
					break;
				case "memorias":
					miVentanProductos.getTextFieldMemoria().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecioMemoria().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					miVentanProductos.setTiendas(listaProductos.get(table.getSelectedRow()).getTienda(), 1);
					dispose();
					break;	
				case "placas-madre":
					miVentanProductos.getTextFieldPlaca().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecioPlaca().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					miVentanProductos.setTiendas(listaProductos.get(table.getSelectedRow()).getTienda(), 2);
					dispose();
					break;
				case "video-cards":
					miVentanProductos.getTextFieldVideoCard().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecioVideoCard().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					miVentanProductos.setTiendas(listaProductos.get(table.getSelectedRow()).getTienda(), 3);
					dispose();
					break;
				case "unidades-almacenamiento":
					miVentanProductos.getTextFieldHDD().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecioHDD().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					miVentanProductos.setTiendas(listaProductos.get(table.getSelectedRow()).getTienda(), 4);
					dispose();
					break;
				case "case-fuentes":
					miVentanProductos.getTextFieldCaseFuente().setText(listaProductos.get(table.getSelectedRow()).getNombre());
					miVentanProductos.getTextFieldPrecioCaseFuente().setText(Double.toString(listaProductos.get(table.getSelectedRow()).getPrecio()));
					miVentanProductos.setTiendas(listaProductos.get(table.getSelectedRow()).getTienda(), 5);
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
	
	public void filtro() {
		
        int columnaABuscar = 0;
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), columnaABuscar));
	}
	
	private void txtFiltroKeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText());
                txtBuscar.setText(cadena);
                repaint();
                filtro();
            }
        });
        trsFiltro = new TableRowSorter(table.getModel());
        table.setRowSorter(trsFiltro);

	}
	
}
