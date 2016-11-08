package negocio;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entidades.Producto;

// Esta clase nos permite obtener los datos de los productos desde diferentes webs

public class ObtenerProductos {

	// Lee y envia a una cola los datos de los procesadores

	public static Queue<Producto> leerProcesadores() throws IOException {

		Queue<Producto> colaProcesadores = new LinkedList<Producto>();

		Document document = Jsoup
				.connect(
						"http://www.computo.com.pe/categoria/5/procesadores?keyBusqueda=&pagina=1&id_busqueda=&orden=1&id_subcategorias%5B%5D=10191&id_subcategorias%5B%5D=10192&id_subcategorias%5B%5D=10193&id_subcategorias%5B%5D=10213&id_subcategorias%5B%5D=10107&id_subcategorias%5B%5D=10100&id_subcategorias%5B%5D=10101&id_subcategorias%5B%5D=10108&id_subcategorias%5B%5D=10160&id_subcategorias%5B%5D=10092&id_subcategorias%5B%5D=10060&id_subcategorias%5B%5D=37&id_subcategorias%5B%5D=10079&id_subcategorias%5B%5D=10143&id_subcategorias%5B%5D=44&id_marcas%5B%5D=35&id_marcas%5B%5D=9&id_marcas%5B%5D=74&id_marcas%5B%5D=139&id_marcas%5B%5D=36&precio_min=11&precio_max=2201")
				.get();

		// Busco todas las entradas que estan dentro de:
		Elements entradas = document.select("div.product_item");
		//System.out.println("Número de entradas en la página: " + entradas.size() + "\n");

		// Parseo cada una de las entradas
		for (Element elem : entradas) {
			String nombreProducto = elem.getElementsByClass("product_title").text();
			String precio = elem.getElementsByClass("product_price bold").text();

			Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

			colaProcesadores.add(nuevo);

		}

		return colaProcesadores;
	}

	// Lee y envia a una cola los datos de las placas madre

	public static Queue<Producto> leerPlacasMadre() throws IOException {

		Queue<Producto> colaPlacasMadre = new LinkedList<Producto>();

		// Leyendo la primera pagina

		Document document = Jsoup.connect("http://www.computo.com.pe/categoria/4/mainboards").get();

		// Busco todas las entradas que estan dentro de:
		Elements entradas = document.select("div.product_item");

		// Parseo cada una de las entradas
		for (Element elem : entradas) {
			String nombreProducto = elem.getElementsByClass("product_title").text();
			String precio = elem.getElementsByClass("product_price bold").text();

			Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

			colaPlacasMadre.add(nuevo);

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

			colaPlacasMadre.add(nuevo);

		}

		return colaPlacasMadre;
	}

	// Lee y envia a una cola los datos de las memorias

	public static Queue<Producto> leerMemorias() throws IOException {

		Queue<Producto> colaMemorias = new LinkedList<Producto>();

		Document document = Jsoup
				.connect(
						"http://www.computo.com.pe/categoria/6/memorias-pc-nb-y-usb?keyBusqueda=&pagina=1&id_busqueda=&orden=1&id_subcategorias%5B%5D=10210&id_subcategorias%5B%5D=10211&id_subcategorias%5B%5D=10157&id_subcategorias%5B%5D=10158&id_subcategorias%5B%5D=70&id_subcategorias%5B%5D=71&id_subcategorias%5B%5D=10067&id_subcategorias%5B%5D=10075&id_marcas%5B%5D=82&id_marcas%5B%5D=127&id_marcas%5B%5D=151&id_marcas%5B%5D=219&id_marcas%5B%5D=68&id_marcas%5B%5D=221&id_marcas%5B%5D=88&id_marcas%5B%5D=237&id_marcas%5B%5D=3&id_marcas%5B%5D=171&id_marcas%5B%5D=36&id_marcas%5B%5D=141&id_marcas%5B%5D=134&id_marcas%5B%5D=41&id_marcas%5B%5D=152&id_marcas%5B%5D=244&precio_min=6&precio_max=265")
				.get();

		// Busco todas las entradas que estan dentro de:
		Elements entradas = document.select("div.product_item");

		// Parseo cada una de las entradas
		for (Element elem : entradas) {
			String nombreProducto = elem.getElementsByClass("product_title").text();
			String precio = elem.getElementsByClass("product_price bold").text();

			Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

			colaMemorias.add(nuevo);

		}

		return colaMemorias;
	}

	// Lee y envia a una cola los datos de las tarjetas de video

	public static Queue<Producto> leerTarjetasVideo() throws IOException {

		Queue<Producto> colaTarjetasVideo = new LinkedList<Producto>();

		Document document = Jsoup.connect("http://www.computo.com.pe/categoria/8/tarjetas-de-video").get();

		// Busco todas las entradas que estan dentro de:
		Elements entradas = document.select("div.product_item");

		// Parseo cada una de las entradas
		for (Element elem : entradas) {
			String nombreProducto = elem.getElementsByClass("product_title").text();
			String precio = elem.getElementsByClass("product_price bold").text();

			Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4,8)), "Computo Nacional");

			colaTarjetasVideo.add(nuevo);

		}

		return colaTarjetasVideo;
	}

	// Lee y envia a una cola los datos de las unidades de almacenamiento

	public static Queue<Producto> leerUnidadesAlmacenamiento() throws IOException {

		Queue<Producto> colaUnidadesAlmacenamiento = new LinkedList<Producto>();

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

			colaUnidadesAlmacenamiento.add(nuevo);

		}

		return colaUnidadesAlmacenamiento;
	}

	// Lee y envia a una cola los datos de los case y fuentes de poder

	public static Queue<Producto> leerCaseFuentes() throws IOException {

		Queue<Producto> colaCaseFuente = new LinkedList<Producto>();

		Document document = Jsoup
				.connect(
						"") // FALTA IMPLEMENTAR
				.get();

		// Busco todas las entradas que estan dentro de:
		Elements entradas = document.select("div.product_item");

		// Parseo cada una de las entradas
		for (Element elem : entradas) {
			String nombreProducto = elem.getElementsByClass("product_title").text();
			String precio = elem.getElementsByClass("product_price bold").text();

			Producto nuevo = new Producto(nombreProducto, Double.parseDouble(precio.substring(4)), "Computo Nacional");

			colaCaseFuente.add(nuevo);

		}

		return colaCaseFuente;
	}

}
