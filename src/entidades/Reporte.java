package entidades;

import java.time.LocalDate;
import java.util.Date;

public class Reporte {
	
	private int indice;
	private String nombre;
	private String tipo;
	private Date fecha;
	private Producto[] listaComponentes;

	public Reporte(int indice,String nombre, String tipo,Date fecha) {
		this.indice=indice;
		this.nombre = nombre;
		this.tipo = tipo;
		this.fecha = fecha;
	}
	
	public Reporte() {
	
	}
	
	public Reporte(String nombre, String tipo, Date fecha, Producto[] lista) {
		listaComponentes = new Producto[6];
		this.nombre = nombre;
		this.tipo = tipo;
		this.fecha = fecha;
		this.listaComponentes = lista;
	}
	
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Producto[] getListaComponentes() {
		return listaComponentes;
	}

	public void setListaComponentes(Producto[] listaComponentes) {
		this.listaComponentes = listaComponentes;
	}

	
}
