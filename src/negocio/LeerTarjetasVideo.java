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

		if (!band) {
			Document document = Jsoup.connect("http://www.computo.com.pe/categoria/8/tarjetas-de-video").get();

			// Busco todas las entradas que estan dentro de:
			Elements entradas = document.select("div.product_item");

			// Parseo cada una de las entradas
			for (Element elem : entradas) {
				String nombreProducto = elem.getElementsByClass("product_title").text();
				String precio = elem.getElementsByClass("product_price bold").text();

				Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4,8)), "Computo Nacional");

				listaTarjetasVideo.add(nuevo);

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

		listaProductos.addAll(listaTarjetasVideo);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "video-cards");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}
	
}
