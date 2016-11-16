package entidades;

public class Tienda {
	String nombre = null;
	String ubicacion = null;
	String linkDeUbicacion = null;
	String linkDeTienda = null;
	String telefono = null;
	String email = null;
	
	public Tienda(String nombre, String ubicacion, String linkDeUbicacion,
					String linkDeTienda, String telefono, String email){
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.linkDeUbicacion = linkDeUbicacion;
		this.linkDeTienda = linkDeTienda;
		this.telefono = telefono;
		this.email = email;
				
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getLinkDeUbicacion() {
		return linkDeUbicacion;
	}

	public void setLinkDeUbicacion(String linkDeUbicacion) {
		this.linkDeUbicacion = linkDeUbicacion;
	}

	public String getLinkDeTienda() {
		return linkDeTienda;
	}

	public void setLinkDeTienda(String linkDeTienda) {
		this.linkDeTienda = linkDeTienda;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
