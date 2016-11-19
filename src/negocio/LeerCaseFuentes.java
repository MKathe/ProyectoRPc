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

public class LeerCaseFuentes extends SwingWorker<Integer, Void>{

	public static Queue<Producto> listaCaseFuentes = new LinkedList<Producto>();
	public static boolean band = false;
	private WebProgressBar jProgressBar;
	private VentanaProductos miVentanaProductos;
	private JLabel lblProcesando;

	public LeerCaseFuentes(VentanaProductos miVentanaProductos, WebProgressBar jProgressBar, JLabel lblProcesando) {
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
							"http://www.computo.com.pe/categoria/13/cases-fuentes?keyBusqueda=&pagina=1&id_busqueda=&orden=1&id_subcategorias%5B%5D=10034&id_subcategorias%5B%5D=10035&id_subcategorias%5B%5D=121&id_subcategorias%5B%5D=122&id_marcas%5B%5D=174&id_marcas%5B%5D=61&id_marcas%5B%5D=35&id_marcas%5B%5D=71&id_marcas%5B%5D=55&id_marcas%5B%5D=36&id_marcas%5B%5D=139&id_marcas%5B%5D=68&id_marcas%5B%5D=178&id_marcas%5B%5D=300&id_marcas%5B%5D=88&id_marcas%5B%5D=318&id_marcas%5B%5D=317&id_marcas%5B%5D=66&precio_min=5&precio_max=520")
					.get();

			parseoComputoNacional(document);
			
			/* Leyendo de MemoryKings */
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/306/case-con-fuente-de-poder-gt-gt-300w-350w-400w-450w-600w-etc").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/392/fuentes-con-certificacion").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/199/fuentes-con-certificacion-80plus-white").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/277/fuentes-con-certificacion-80plus-bronze").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/461/fuentes-con-certificacion-80plus-gold").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/229/fuentes-con-certificacion-80plus-platinum").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/590/fuentes-con-certificacion-80plus-titanium").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/276/case-mid-tower-gt-gt-sin-fuente-de-poder").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/276/case-mid-tower-sin-fuente-de-poder/2").get();		
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/276/case-mid-tower-sin-fuente-de-poder/3").get();		
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
			Double precio1;
			
			try {
				precio1 = Double.parseDouble(precio.substring(4));
			} catch (NumberFormatException e) {
				precio1 = Double.parseDouble(precio.substring(16));
			}

			Producto nuevo = new Producto(nombreProducto, precio1, "Computo Nacional");

			listaCaseFuentes.add(nuevo);

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

			listaCaseFuentes.add(nuevo);

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

		listaProductos.addAll(listaCaseFuentes);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "case-fuentes");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}

}
