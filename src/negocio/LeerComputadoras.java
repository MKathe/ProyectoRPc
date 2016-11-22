package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import entidades.Producto;
import modeloTablas.ModeloTablaProducto;
import presentacion.VentanaAsistente;
import presentacion.VentanaMiniProductos;
import presentacion.VentanaProductos;

public class LeerComputadoras extends SwingWorker<Integer, Void>{
		
	public static Queue<Producto> listaComputadoras;
	public static Queue<String> listaEnlaces;
	//public static boolean band = false;
	private WebProgressBar jProgressBar;
	private VentanaAsistente miVentanaAsistente;
	private JLabel lblProcesando;
	private  WebScrollPane scrollPane;
	private WebTable table;
	private Double precioDe, precioA;

	public LeerComputadoras( VentanaAsistente miVentanaAsistente,WebScrollPane scrollPane, WebTable table, WebProgressBar jProgressBar, JLabel lblProcesando, Double precioDe, Double precioA) {
		listaComputadoras = new LinkedList<Producto>();
		listaEnlaces = new LinkedList<String>();
		this.miVentanaAsistente = miVentanaAsistente;
		this.scrollPane = scrollPane;
		this.table = table;
		this.jProgressBar = jProgressBar;
		this.lblProcesando = lblProcesando;
		this.precioDe = precioDe;
		this.precioA = precioA;
		
	}

	@Override
	protected Integer doInBackground() throws Exception {
		
		String[] enlaces = new String[5];
		enlaces[0] = "http://www.magitech.pe/computadoras/pc-compatibles.html";
		enlaces[1] = "http://www.magitech.pe/computadoras/hogar-oficina.html";
		enlaces[2] = "http://www.magitech.pe/computadoras/pc-de-marca.html";
		enlaces[3] = "http://www.magitech.pe/computadoras/pc-gamer.html";
		enlaces[4] = "http://www.magitech.pe/computadoras/workstation.html";
		
		for(String url: enlaces){
			
			Document document = Jsoup.connect(url).get();
		    
		    Elements entradas = document.select("li.item");	
		    Elements links = document.select("h2.product-name > a");
		    Element elem,link;
		    

		    for (int i=0;i<entradas.size();i++) {
		    	
		    	elem = entradas.get(i);
		    	link = links.get(i);
		    	
		    	String enlace = link.absUrl("href");
		    	
				String nombreProducto = elem.getElementsByClass("product-name").text();
				String precio = elem.getElementsByClass("special-price").text();
				Double precio2;

				try {
					precio = precio.substring(11);
					precio = precio.replace(",", "");
					precio2 = Double.parseDouble(precio);
				} catch (Exception e) {
					precio = elem.getElementsByClass("regular-price").text();
					precio = precio.substring(4);
					precio = precio.replace(",", "");
					precio2 = Double.parseDouble(precio);
				}
				
				if (precio2 >= precioDe && precio2 <= precioA) {					
					Producto nuevo = new Producto(nombreProducto, precio2, "MAGITECH S.A.C");
					listaComputadoras.add(nuevo);
					listaEnlaces.add(enlace);
				}
		
		
		    }
		}


		construirVentana();

		return 0;
	}

	@Override
	protected void done() {
		lblProcesando.setVisible(false);
		jProgressBar.setIndeterminate(false);
		jProgressBar.setVisible(false);
		miVentanaAsistente.setTable(table);

	}

	private void construirVentana() {

		List<Producto> listaPCs = new ArrayList<Producto>();
		
		listaPCs.addAll(listaComputadoras);
		
		ModeloTablaProducto tableModel = new ModeloTablaProducto(listaPCs);		
		table = new WebTable(tableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(432);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);

		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);	
		
	}

}
