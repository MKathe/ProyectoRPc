package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import entidades.Tienda;
import modeloTablas.ModeloTablaTienda;
import presentacion.VentanaTiendas;

public class OrdenarTiendas {
	public static void ordenar(Object tipoDeOrden, List<Tienda> listaActual){
		
		List<Tienda> aux = new ArrayList(); 
		String menor;
		int indiceDeMenor;
		
		if(tipoDeOrden.equals("Nombre")){
			for(int i = 0; i < listaActual.size(); i++){
				menor = listaActual.get(i).getNombre();
				indiceDeMenor = i;
				for(int j = i + 1; j < listaActual.size(); j++){
					if(menor.compareTo(listaActual.get(j).getNombre()) > 0){
						menor = listaActual.get(j).getNombre();
						indiceDeMenor = j;
					}
				}
				aux.add(listaActual.get(i));
				listaActual.set(i, listaActual.get(indiceDeMenor));
				listaActual.set(indiceDeMenor,aux.get(i));
			}
		}else{
			for(int i = 0; i < listaActual.size(); i++){
				menor = listaActual.get(i).getUbicacion();
				indiceDeMenor = i;
				for(int j = i + 1; j < listaActual.size(); j++){
					if(menor.compareTo(listaActual.get(j).getUbicacion()) > 0){
						menor = listaActual.get(j).getUbicacion();
						indiceDeMenor = j;
					}
				}
				aux.add(listaActual.get(i));
				listaActual.set(i, listaActual.get(indiceDeMenor));
				listaActual.set(indiceDeMenor,aux.get(i));
			}
		}
		
		TableModel modelo = new ModeloTablaTienda(listaActual);
		VentanaTiendas.setModel(modelo);
	}
}
