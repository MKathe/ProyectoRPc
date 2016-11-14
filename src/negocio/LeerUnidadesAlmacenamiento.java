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

public class LeerUnidadesAlmacenamiento extends SwingWorker<Integer, Void>{

	public static Queue<Producto> listaUnidadesAlmacenamiento = new LinkedList<Producto>();
	public static boolean band = false;
	private WebProgressBar jProgressBar;
	private VentanaProductos miVentanaProductos;
	private JLabel lblProcesando;

	public LeerUnidadesAlmacenamiento(VentanaProductos miVentanaProductos, WebProgressBar jProgressBar, JLabel lblProcesando) {
		this.jProgressBar = jProgressBar;
		this.miVentanaProductos = miVentanaProductos;
		this.lblProcesando = lblProcesando;
	}

	@Override
	protected Integer doInBackground() throws Exception {

		if (!band) {
			Document document = Jsoup
					.connect(
							"http://www.computo.com.pe/categoria/7/discos-duros?keyBusqueda=&pagina=1&id_busqueda=&orden=1&id_subcategorias%5B%5D=83&id_subcategorias%5B%5D=82&id_subcategorias%5B%5D=10058&id_marcas%5B%5D=68&id_marcas%5B%5D=130&id_marcas%5B%5D=87&id_marcas%5B%5D=145&id_marcas%5B%5D=141&id_marcas%5B%5D=82&id_marcas%5B%5D=139&id_marcas%5B%5D=151&id_marcas%5B%5D=290&id_marcas%5B%5D=127&id_marcas%5B%5D=3&id_marcas%5B%5D=74&id_marcas%5B%5D=316&id_marcas%5B%5D=166&precio_min=5&precio_max=3499")
					.get();

			// Busco todas las entradas que estan dentro de:
			Elements entradas = document.select("div.product_item");

			// Parseo cada una de las entradas
			for (Element elem : entradas) {
				String nombreProducto = elem.getElementsByClass("product_title").text();
				String precio = elem.getElementsByClass("product_price bold").text();

				Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

				listaUnidadesAlmacenamiento.add(nuevo);

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

		listaProductos.addAll(listaUnidadesAlmacenamiento);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "unidades-almacenamiento");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}
	
}
