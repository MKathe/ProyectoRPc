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

public class LeerTarjetasVideo extends SwingWorker<Integer, Void> {

	public static Queue<Producto> listaTarjetasVideo = new LinkedList<Producto>();
	public static boolean band = false;
	private WebProgressBar jProgressBar;
	private VentanaProductos miVentanaProductos;
	private JLabel lblProcesando;

	public LeerTarjetasVideo(VentanaProductos miVentanaProductos, WebProgressBar jProgressBar, JLabel lblProcesando) {
		this.jProgressBar = jProgressBar;
		this.miVentanaProductos = miVentanaProductos;
		this.lblProcesando = lblProcesando;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		
		Document document;
		
		if (!band) {
			
			/* Leyendo de Computo Nacional */
			
			document = Jsoup.connect("http://www.computo.com.pe/categoria/8/tarjetas-de-video").get();

			parseoComputoNacional(document);
			
			/* Leyendo de MemoryKings */
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/364/video-amd-radeon-5000-series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/564/video-amd-radeon-r7-gt-gt-200series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/584/video-amd-radeon-r7-gt-gt-300series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/523/video-amd-radeon-r9-gt-gt-200series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/585/video-amd-radeon-r9-gt-gt-380-390series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/673/video-amd-radeon-rx-gt-gt-460-470-480series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/308/videos-nvidia-geforce-gt200series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/680/videos-nvidia-geforce-gt400series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/483/videos-nvidia-geforce-gt600series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/558/videos-nvidia-geforce-gt700series").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/688/videos-nvidia-geforce-gtx1060-gt-gt-ultra-extremas").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/666/videos-nvidia-geforce-gtx1070-gt-gt-ultra-extremas").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/644/videos-nvidia-geforce-gtx1080-gt-gt-ultra-extremas").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/509/videos-nvidia-geforce-gtx750-gt-gt-gaming").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/565/videos-nvidia-geforce-gtx950-gt-gt-gaming").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/614/videos-nvidia-geforce-gtx980-gt-gt-ultra-extremas").get();
			parseoMemoryKings(document);
			
			document = Jsoup.connect("http://www.memorykings.com.pe/listados/503/videos-nvidia-geforce-gtxtitan-gt-gt-ultra-extremas").get();
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

			Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4, 8)),
					"Computo Nacional");

			listaTarjetasVideo.add(nuevo);

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

			listaTarjetasVideo.add(nuevo);

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

		listaProductos.addAll(listaTarjetasVideo);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "video-cards");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}
	
}
