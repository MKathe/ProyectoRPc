package negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import datos.ConsultasBasicas;
import entidades.Reporte;
import modeloTablas.ModeloTablaReporte;

public class BuscarReportes {
	
	public static boolean BuscarPor(int indice,List<Reporte> listadeReportes,String textBusqueda,Date dateDel ,Date dateAl) {

		ResultSet ResultadodeBusqueda;
		Reporte r;
		boolean encontrado = false;
		
		listadeReportes.clear();

		switch (indice) {
		case 1:
			ResultadodeBusqueda = ConsultasBasicas.consultarDatos("SELECT * FROM reportes WHERE Nombre ='" + textBusqueda + "'");

			try {
				while (ResultadodeBusqueda.next()) {
					r = new Reporte(ResultadodeBusqueda.getInt("NRO"), ResultadodeBusqueda.getString("Nombre"),
							ResultadodeBusqueda.getString("Tipo"), ResultadodeBusqueda.getDate("Fecha"));
					System.out.println(r.getNombre());
					listadeReportes.add(r);
					encontrado = true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
			break;

		case 2:
			ResultadodeBusqueda = ConsultasBasicas.consultarDatos("SELECT * FROM reportes WHERE Tipo = '" + textBusqueda + "'");

			try {
				while (ResultadodeBusqueda.next()) {
					r = new Reporte(ResultadodeBusqueda.getInt("NRO"), ResultadodeBusqueda.getString("Nombre"),
							ResultadodeBusqueda.getString("Tipo"), ResultadodeBusqueda.getDate("Fecha"));
					listadeReportes.add(r);
					encontrado = true;
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			break;

		case 3:

			Date del = dateDel;
			Date al = dateAl;
			java.sql.Date DEL = new java.sql.Date(del.getTime());
			java.sql.Date AL = new java.sql.Date(al.getTime());
			boolean sw = ValidarRangoFecha.validar(del, al);
			if (sw) {

				ResultadodeBusqueda = ConsultasBasicas.consultarDatos("SELECT * FROM reportes WHERE Fecha>= '" + DEL + "' and Fecha <= '" + AL + "' ");

				try {
					while (ResultadodeBusqueda.next()) {
						r = new Reporte(ResultadodeBusqueda.getInt("NRO"), ResultadodeBusqueda.getString("Nombre"),
								ResultadodeBusqueda.getString("Tipo"), ResultadodeBusqueda.getDate("Fecha"));
						listadeReportes.add(r);
						encontrado = true;
					}
				} catch (SQLException e12) {

					e12.printStackTrace();
				}
			} else {
				 JOptionPane.showMessageDialog(null, "Rango de Fechas no valido", "", JOptionPane.ERROR_MESSAGE);
			}

			break;
		default:
			;
		}
		
        return encontrado;
	}

}
