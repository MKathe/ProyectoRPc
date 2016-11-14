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

public class LeerProcesadores extends SwingWorker<Integer, Void> {

	public static Queue<Producto> listaProcesadores = new LinkedList<Producto>();
	public static boolean band = false;
	private WebProgressBar jProgressBar;
	private VentanaProductos miVentanaProductos;
	private JLabel lblProcesando;

	public LeerProcesadores(VentanaProductos miVentanaProductos, WebProgressBar jProgressBar, JLabel lblProcesando) {
		this.jProgressBar = jProgressBar;
		this.miVentanaProductos = miVentanaProductos;
		this.lblProcesando = lblProcesando;
	}

	@Override
	protected Integer doInBackground() throws Exception {

		if (!band) {
			Document document = Jsoup
					.connect(
							"http://www.computo.com.pe/categoria/5/procesadores?keyBusqueda=&pagina=1&id_busqueda=&orden=1&id_subcategorias%5B%5D=10191&id_subcategorias%5B%5D=10192&id_subcategorias%5B%5D=10193&id_subcategorias%5B%5D=10213&id_subcategorias%5B%5D=10107&id_subcategorias%5B%5D=10100&id_subcategorias%5B%5D=10101&id_subcategorias%5B%5D=10108&id_subcategorias%5B%5D=10160&id_subcategorias%5B%5D=10092&id_subcategorias%5B%5D=10060&id_subcategorias%5B%5D=37&id_subcategorias%5B%5D=10079&id_subcategorias%5B%5D=10143&id_subcategorias%5B%5D=44&id_marcas%5B%5D=35&id_marcas%5B%5D=9&id_marcas%5B%5D=74&id_marcas%5B%5D=139&id_marcas%5B%5D=36&precio_min=11&precio_max=2201")
					.get();

			// Busca todas las entradas que estan dentro de:
			Elements entradas = document.select("div.product_item");

			// Parseo cada una de las entradas
			for (Element elem : entradas) {
				String nombreProducto = elem.getElementsByClass("product_title").text();
				String precio = elem.getElementsByClass("product_price bold").text();

				Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)),
						"Computo Nacional");

				listaProcesadores.add(nuevo);

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

		listaProductos.addAll(listaProcesadores);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "procesadores");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}

}
