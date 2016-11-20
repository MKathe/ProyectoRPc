package negocio;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import datos.ConsultasBasicas;
import entidades.Reporte;
import modeloTablas.ModeloTablaReporte;


public class OrdenarReportes {
	
	public static void OrdenarPor(List<Reporte> listadeReportes ,int opc){
		ResultSet rss;
		Reporte r;
		listadeReportes.clear();
		
		switch (opc) {
		case 1:

			rss = ConsultasBasicas.consultarDatos("SELECT* FROM reportes ORDER BY Nombre");

			try {
				while (rss.next()) {
					r = new Reporte(rss.getInt("NRO"), rss.getString("Nombre"), rss.getString("Tipo"),
							rss.getDate("Fecha"));
					listadeReportes.add(r);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			break;
		case 2:
			rss = ConsultasBasicas.consultarDatos("SELECT* FROM reportes ORDER BY Tipo");

			try {
				while (rss.next()) {
					r = new Reporte(rss.getInt("NRO"), rss.getString("Nombre"), rss.getString("Tipo"),
							rss.getDate("Fecha"));
					listadeReportes.add(r);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			break;
		case 3:
			rss = ConsultasBasicas.consultarDatos("SELECT* FROM reportes ORDER BY Fecha");

			try {
				while (rss.next()) {
					r = new Reporte(rss.getInt("NRO"), rss.getString("Nombre"), rss.getString("Tipo"),
							rss.getDate("Fecha"));
					listadeReportes.add(r);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			break;

		default:
			;

		}

		
	}

}
