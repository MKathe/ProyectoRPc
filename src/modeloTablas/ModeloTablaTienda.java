package modeloTablas;

import entidades.Tienda;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaTienda extends AbstractTableModel{
	private static final int COLUMN_NOMBRE = 0;
	private static final int COLUMN_UBICACION=1;
	private static final int COLUMN_WEB=2;
	
	private String [] columnas = {"NOMBRE","UBICACION","WEB"};
	
	private List<Tienda> listadeTiendas;
	
	public ModeloTablaTienda(List<Tienda> listadeTiendas){
		this.listadeTiendas = listadeTiendas;
		/*int indice = 1;
		for (Tienda e: listadeTiendas) {
			e.setIndice(indice++);
		}*/
	}

	public int getColumnCount() {
		return columnas.length;
		
	}

	public int getRowCount() {
		return listadeTiendas.size();
		
	}
	public String getColumnName(int idColumna){
		return columnas[idColumna];
	}
	
	
	public Class<?> getColumnClass(int idColumna) {
		if (listadeTiendas.isEmpty()) {
			return Object.class;
		}
		return getValueAt(0, idColumna).getClass();
	}


	public Object getValueAt(int rowIndex, int columnIndex) {
		Tienda nuevoreporte = listadeTiendas.get(rowIndex);
		Object returnValue = null;

		switch (columnIndex) {
		case COLUMN_NOMBRE:
			returnValue = nuevoreporte.getNombre();
			break;
		case COLUMN_UBICACION:
			returnValue = nuevoreporte.getUbicacion();
			break;
		case COLUMN_WEB:
			returnValue = nuevoreporte.getLinkDeTienda();
			break;
	
		default:
			throw new IllegalArgumentException("Numero de columna invalido!");
		}

		return returnValue;
	}
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		/*Tienda empresa = listadeTiendas.get(rowIndex);
		if (columnIndex == COLUMN_NO) {
			empresa.setIndice((int) value);
		}*/
	}
}
