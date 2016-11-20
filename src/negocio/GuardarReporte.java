package negocio;
import entidades.Reporte;
import datos.ConsultasBasicas;

public class GuardarReporte{
 public static void GuardarReporteGenerado(Reporte nuevoReporte){

	 ConsultasBasicas.insertarReporte(nuevoReporte);

 }
}
