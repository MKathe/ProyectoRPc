package negocio;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import conectionDB.ConectionDB;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportt {
	  private  JasperPrint jasperPrint;
	  private  JasperViewer jasperViewer;
	  private  JasperReport jasperReport;  
	  
	public void CrearReporte(Connection c,String patch, Map Parametros){
		
		try {
			jasperPrint = JasperFillManager.fillReport(patch,Parametros,c);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void VerReporte(){
		jasperViewer = new JasperViewer(jasperPrint,false);
        jasperViewer.setVisible(true);

	}
	public void GuardarReporte(String ruta){
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint,ruta+".pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
