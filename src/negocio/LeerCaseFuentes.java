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

		if (!band) {
			Document document = Jsoup
					.connect(
							"http://www.rdperu.com/category/items/247") // FALTA IMPLEMENTAR
					.get();

			// Busco todas las entradas que estan dentro de:
			Elements entradas = document.select("div.module");

			// Parseo cada una de las entradas
			for (Element elem : entradas) {
				String nombreProducto = elem.getElementsByClass("product_name").text();
				String precio = elem.getElementsByClass("product_price").text();

				Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "REDSERCOM S.A.C");

				listaCaseFuentes.add(nuevo);

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

		listaProductos.addAll(listaCaseFuentes);

		VentanaMiniProductos miVentanaMini = new VentanaMiniProductos(miVentanaProductos, true, listaProductos, "case-fuentes");
		miVentanaMini.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		miVentanaMini.setVisible(true);
	}

}
