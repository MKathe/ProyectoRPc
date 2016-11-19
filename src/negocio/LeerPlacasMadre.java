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

public class LeerPlacasMadre extends SwingWorker<Integer, Void>{
	
	public static Queue<Producto> listaPlacasMadre = new LinkedList<Producto>();
	public static boolean band = false;
	private WebProgressBar jProgressBar;
	private VentanaProductos miVentanaProductos;
	private JLabel lblProcesando;

	public LeerPlacasMadre(VentanaProductos miVentanaProductos, WebProgressBar jProgressBar, JLabel lblProcesando) {
		this.jProgressBar = jProgressBar;
		this.miVentanaProductos = miVentanaProductos;
		this.lblProcesando = lblProcesando;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		Document document;
		
		if (!band) {
			
			/* Leyendo de Computo Nacional */
			
			document = Jsoup.connect("http://www.computo.com.pe/categoria/4/mainboards").get();

			parseoComputoNacional(document);
			
			document = Jsoup
					.connect(
							"http://www.computo.com.pe/categoria/4/mainboards?keyBusqueda=&pagina=2&id_busqueda=&orden=1&id_subcategorias%5B%5D=10190&id_subcategorias%5B%5D=10102&id_subcategorias%5B%5D=10159&id_subcategorias%5B%5D=10080&id_subcategorias%5B%5D=10144&id_subcategorias%5B%5D=29&id_subcategorias%5B%5D=10104&id_subcategorias%5B%5D=33&id_marcas%5B%5D=17&id_marcas%5B%5D=63&id_marcas%5B%5D=47&id_marcas%5B%5D=100&precio_min=35&precio_max=529")
					.get(); // Pag. 2

			parseoComputoNacional(document);
			
			/* Leyendo de MemoryKings */
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/449/placas-amd-gt-gt-con-procesador-amd-apu-integrado")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/560/placas-amd-socket-am1-gt-gt-con-video-integrado")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/168/placas-amd-socket-am3-fx-series-gt-gt-con-video-integrado")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/435/placas-amd-socket-am3-fx-series-gt-gt-linea-pro-amp-extrema")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/489/placas-amd-socket-fm2-apu-series-gt-gt-con-video-integrado")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/313/placas-intel-gt-gt-con-procesador-intel-celeron-integrado")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/505/placas-intel-lga-1150-4ta-generacion-gt-gt-linea-alta-extrema")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/273/placas-intel-lga-1150-4ta-generacion-gt-gt-office-amp-bussines")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/599/placas-intel-lga-1151-6ta-generacion-gt-gt-linea-alta-amp-extrema")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/612/placas-intel-lga-1151-6ta-generacion-gt-gt-linea-pro-amp-gaming")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/613/placas-intel-lga-1151-6ta-generacion-gt-gt-office-amp-bussines")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/566/placas-intel-lga-2011-v3-gt-gt-linea-extrema-profesional-ddr4")
					.get();
			
			parseoMemoryKings(document);
			
			document = Jsoup
					.connect(
							"http://www.memorykings.com.pe/listados/167/placas-intel-lga1151-6ta-generacion-gt-gt-gaming-ultra-compactos-mini-itx")
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

			listaPlacasMadre.add(nuevo);

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

			listaPlacasMadre.add(nuevo);

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

		listaProductos.addAll(listaPlacasMadre);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "placas-madre");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}
}
