package entidades;

import java.util.Date;

public class Reporte {
	
	int indice;
    String nombre;
	String tipo;
	Date fecha;

	public Reporte(int indice,String nombre, String tipo,Date fecha) {
		this.indice=indice;
		this.nombre = nombre;
		this.tipo = tipo;
		this.fecha = fecha;
	}
	public Reporte() {
	
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

}
