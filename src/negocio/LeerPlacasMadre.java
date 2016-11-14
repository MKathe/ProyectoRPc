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

		if (!band) {
			Document document = Jsoup.connect("http://www.computo.com.pe/categoria/4/mainboards").get();

			// Busco todas las entradas que estan dentro de:
			Elements entradas = document.select("div.product_item");

			// Parseo cada una de las entradas
			for (Element elem : entradas) {
				String nombreProducto = elem.getElementsByClass("product_title").text();
				String precio = elem.getElementsByClass("product_price bold").text();

				Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

				listaPlacasMadre.add(nuevo);

			}

			// Leyendo la segunda pagina

			Document document2 = Jsoup
					.connect(
							"http://www.computo.com.pe/categoria/4/mainboards?keyBusqueda=&pagina=2&id_busqueda=&orden=1&id_subcategorias%5B%5D=10190&id_subcategorias%5B%5D=10102&id_subcategorias%5B%5D=10159&id_subcategorias%5B%5D=10080&id_subcategorias%5B%5D=10144&id_subcategorias%5B%5D=29&id_subcategorias%5B%5D=10104&id_subcategorias%5B%5D=33&id_marcas%5B%5D=17&id_marcas%5B%5D=63&id_marcas%5B%5D=47&id_marcas%5B%5D=100&precio_min=35&precio_max=529")
					.get();

			// Busco todas las entradas que estan dentro de:
			Elements entradas2 = document2.select("div.product_item");

			// Parseo cada una de las entradas
			for (Element elem : entradas2) {
				String nombreProducto = elem.getElementsByClass("product_title").text();
				String precio = elem.getElementsByClass("product_price bold").text();

				Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

				listaPlacasMadre.add(nuevo);

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

		listaProductos.addAll(listaPlacasMadre);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "placas-madre");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}
}
