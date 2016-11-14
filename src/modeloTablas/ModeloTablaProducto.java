package modeloTablas;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import entidades.Producto;

public class ModeloTablaProducto extends AbstractTableModel {
	
	//private static final int COLUMN_NO = 1;
	private static final int COLUMN_PRODUCTO = 0;
	private static final int COLUMN_PRECIO = 1;
	private static final int COLUMN_TIENDA = 2;

	private String[] columnas = { "PRODUCTO", "PRECIO $", "TIENDA" };

	private List<Producto> listaProductos;

	public ModeloTablaProducto(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;

	}

	public void addElemento(Producto nuevo) {
        // Añade el elemento al final de la lista enlazada
		listaProductos.add(nuevo);
		fireTableRowsInserted(listaProductos.size()-1, listaProductos.size()-1);
    }
	
	// Definiendo los metodos de la interfaz AbstractTableModel

	@Override
	public int getColumnCount() { // Obtenemos el numero de columnas, es decir,
									// la longitud del arreglo de strings
									// "columnas"
		return columnas.length;
	}

	@Override
	public int getRowCount() { // Obtenemos el numero de filas, que vendria a
								// ser el numero de elementos de la lista
		return listaProductos.size();
	}

	@Override
	public String getColumnName(int idColumna) { // Obtenemos los nombres de las
													// columnas
		return columnas[idColumna];
	}

	@Override
	public Class<?> getColumnClass(int idColumna) {
		if (listaProductos.isEmpty()) {
			return Object.class;
		}
		return getValueAt(0, idColumna).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Producto producto = listaProductos.get(rowIndex);
		Object returnValue = null;

		switch (columnIndex) {
		case COLUMN_PRODUCTO:
			returnValue = producto.getNombre();
			break;
		case COLUMN_PRECIO:
			returnValue = producto.getPrecio();
			break;
		case COLUMN_TIENDA:
			returnValue = producto.getTienda();
			break;
		default:
			throw new IllegalArgumentException("Numero de columna invalido!");
		}

		return returnValue;
	}



}
