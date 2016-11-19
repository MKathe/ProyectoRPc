package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alee.laf.progressbar.WebProgressBar;

import entidades.Producto;
import presentacion.VentanaMiniProductos;
import presentacion.VentanaProductos;

public class LeerMemorias extends SwingWorker<Integer, Void> {

	public static Queue<Producto> listaMemorias = new LinkedList<Producto>();
	public static boolean band = false;
	private WebProgressBar jProgressBar;
	private VentanaProductos miVentanaProductos;
	private JLabel lblProcesando;

	public LeerMemorias(VentanaProductos miVentanaProductos, WebProgressBar jProgressBar, JLabel lblProcesando) {
		this.jProgressBar = jProgressBar;
		this.miVentanaProductos = miVentanaProductos;
		this.lblProcesando = lblProcesando;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		Document document;
		
		if (!band) {
			
			/* Leyendo de Computo Nacional */
			
			document = Jsoup
					.connect(
							"http://www.computo.com.pe/categoria/6/memorias-pc-nb-y-usb?keyBusqueda=&pagina=1&id_busqueda=&orden=1&id_subcategorias%5B%5D=10210&id_subcategorias%5B%5D=10211&id_subcategorias%5B%5D=10157&id_subcategorias%5B%5D=10158&id_subcategorias%5B%5D=70&id_subcategorias%5B%5D=71&id_subcategorias%5B%5D=10067&id_subcategorias%5B%5D=10075&id_marcas%5B%5D=82&id_marcas%5B%5D=127&id_marcas%5B%5D=151&id_marcas%5B%5D=219&id_marcas%5B%5D=68&id_marcas%5B%5D=221&id_marcas%5B%5D=88&id_marcas%5B%5D=237&id_marcas%5B%5D=3&id_marcas%5B%5D=171&id_marcas%5B%5D=36&id_marcas%5B%5D=141&id_marcas%5B%5D=134&id_marcas%5B%5D=41&id_marcas%5B%5D=152&id_marcas%5B%5D=244&precio_min=6&precio_max=265")
					.get();
			
			parseoComputoNacional(document);
			
			
			/* Leyendo de MemoryKings */
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/119/memorias-ddr3-1600mhz-gt-gt-para-desktop")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/353/memorias-ddr3-1866mhz-gt-gt-para-desktop")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/352/memorias-ddr3-2133-2400mhz-gt-gt-para-desktop")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/668/memorias-dimm-gt-gt-para-desktop")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/562/memorias-ram-ddr4-2133-2400-2666-2800mhz-gt-gt-para-desktop")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/646/memorias-ram-ddr4-3000-3200-3133mhz-gt-gt-para-desktop")
					.get();
			
			parseoMemoryKings(document);
			
			band = true;
			construirVentana();

		} else {
			construirVentana();
		}

		return 0;
	}
	
	private void parseoComputoNacional(Document document){

		// Busco todas las entradas que estan dentro de:
		Elements entradas = document.select("div.product_item");

		// Parseo cada una de las entradas
		for (Element elem : entradas) {
			String nombreProducto = elem.getElementsByClass("product_title").text();
			String precio = elem.getElementsByClass("product_price bold").text();

			Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

			listaMemorias.add(nuevo);

		}
		
	}

	private void parseoMemoryKings(Document document) {

		// Busca todas las entradas que estan dentro de:
		Elements entradas = document.select("li.col-md-3.col-sm-6.col-xs-12.product");

		// Parseo cada una de las entradas
		for (Element elem : entradas) {
			String nombreProducto = elem.getElementsByClass("alto_65").text();
			String precio = elem.getElementsByClass("amount").text();
			Double precio1;
			precio = precio.substring(2, precio.indexOf("ó") - 1);

			try {
				precio1 = Double.parseDouble(precio);
			} catch (NumberFormatException e) {
				precio = precio.replace(",", "");
				precio1 = Double.parseDouble(precio);
			}

			Producto nuevo = new Producto(nombreProducto, precio1, "MemoryKings");

			listaMemorias.add(nuevo);

		}

	}
	
	@Override
	protected void done() {
		lblProcesando.setVisible(false);
		jProgressBar.setIndeterminate(false);
		jProgressBar.setVisible(false);

	}

	private void construirVentana() {
		List<Producto> listaProductos = new ArrayList<Producto>();

		listaProductos.addAll(listaMemorias);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "memorias");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}
}
