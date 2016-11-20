package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import entidades.Tienda;
import modeloTablas.ModeloTablaTienda;
import presentacion.VentanaTiendas;

public class BuscarTiendas {
	
	public static void buscar(String textoIngresado, boolean busquedaNombre, List<Tienda> listaDeTiendas){
		
		int numElementos = listaDeTiendas.size();
		int i = 0;
		boolean encontrado = false;
		List<Tienda> tiendaEncontrada = new ArrayList();
		
		if(busquedaNombre){
			//itera buscando en la lista
			while(i < numElementos){
				//compara el texto ingresado con el de la lista en la posicion i
				if( textoIngresado.equals(listaDeTiendas.get(i).getNombre().toLowerCase()) ){
					encontrado = true;
					tiendaEncontrada.add(listaDeTiendas.get(i));
				}
				
				i++;
				
			}
		}else{
			//itera buscando en la lista
			String ubicacion;
			while(i < numElementos){
				
				ubicacion = listaDeTiendas.get(i).getUbicacion().toLowerCase();
				//compara el texto ingresado con el de la lista en la posicion i
				if(ubicacion.contains(textoIngresado)){
					encontrado = true;
					tiendaEncontrada.add(listaDeTiendas.get(i));
				}
				
				i++;
				
			}
		}
		
		
		if(encontrado){
			//Nuevo modelo para la tabla
			TableModel modelo = new ModeloTablaTienda(tiendaEncontrada);
			VentanaTiendas.setModel(modelo);
		}
		else{
			JOptionPane.showMessageDialog(null, "Búsqueda no encontrada");
		}
	}
}
