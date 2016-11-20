package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import entidades.Producto;
import modeloTablas.ModeloTablaProducto;
import presentacion.VentanaAsistente;
import presentacion.VentanaMiniProductos;
import presentacion.VentanaProductos;

public class LeerComputadoras extends SwingWorker<Integer, Void>{
		
	public static Queue<Producto> listaComputadoras = new LinkedList<Producto>();
	public static boolean band = false;
	private WebProgressBar jProgressBar;
	//private VentanaAsistente miVentanaAsistente;
	private JLabel lblProcesando;
	private  WebScrollPane scrollPane;
	private WebTable table;

	public LeerComputadoras( WebScrollPane scrollPane, WebTable table, WebProgressBar jProgressBar, JLabel lblProcesando) {
		this.scrollPane = scrollPane;
		this.table = table;
		this.jProgressBar = jProgressBar;
		this.lblProcesando = lblProcesando;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		
		if (!band) {			
			Document document = Jsoup
					.connect(
							"http://www.magitech.pe/computadoras/pc-compatibles.html")
					.get();

			// Busco todas las entradas que estan dentro de:
			Elements entradas = document.select("li.item");

			for (Element elem : entradas) {
				String nombreProducto = elem.getElementsByClass("product-name").text();
				String precio = elem.getElementsByClass("special-price").text();
				Double precio2;
				
				try{
					precio = precio.substring(11);
					precio = precio.replace(",", "");
					precio2 = Double.parseDouble(precio);
				}catch(Exception e){
					precio = elem.getElementsByClass("regular-price").text();
					precio = precio.substring(4);
					precio = precio.replace(",", "");
					precio2 = Double.parseDouble(precio);
				}

				Producto nuevo = new Producto(nombreProducto, precio2, "MAGITECH S.A.C");

				listaComputadoras.add(nuevo);
			}
						
			band = true;
			construirVentana();

		} else {
			construirVentana();
		}

		return 0;
	}

	@Override
	protected void done() {
		lblProcesando.setVisible(false);
		jProgressBar.setIndeterminate(false);
		jProgressBar.setVisible(false);

	}

	private void construirVentana() {

		List<Producto> listaProductos = new ArrayList<Producto>();

		listaProductos.addAll(listaComputadoras);
		
		ModeloTablaProducto tableModel = new ModeloTablaProducto(listaProductos);		
		table = new WebTable(tableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(432);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);

		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);	
		
	}	

}
