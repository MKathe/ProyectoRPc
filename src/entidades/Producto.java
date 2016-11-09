package entidades;

public class Producto {
	
	private String nombre;
	private double precio;
	private String tienda;
	
	public Producto(String nombre, double precio, String tienda) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.tienda = tienda;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getTienda() {
		return tienda;
	}
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

}