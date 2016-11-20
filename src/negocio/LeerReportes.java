package negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import datos.ConsultasBasicas;
import entidades.Reporte;

public class LeerReportes {
	
	public  static void CargarListaDeReportes(List <Reporte> listadeReportes) {
		ResultSet rs;
		rs = ConsultasBasicas.consultarDatos("SELECT* FROM reportes");

		try {
			while (rs.next()) {
				Reporte r;
				r = new Reporte(rs.getInt("NRO"), rs.getString("Nombre"), rs.getString("Tipo"), rs.getDate("Fecha"));
				listadeReportes.add(r);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
