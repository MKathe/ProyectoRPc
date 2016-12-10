package negocio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentacion.VentanaReproductorVideo;

public class Reproductor {
	//La clase Player ayuda a administrar recursos y controladores
	static Player player;
    static Component video;
    static Component controles;
	
	public static void reproducir() {
         //panel principal de video
         JPanel panelVideo = new JPanel();
         panelVideo.setLayout(new BorderLayout());
         
         URL url=null;
         try {	
        	 String rutaVideo = "C:/Users/Lenovo/Documents/GitHub/RantiyPC/tuto.mpg";
             url = new URL("file:///"+rutaVideo);
         } catch (MalformedURLException ex) {
             JOptionPane.showMessageDialog(null, "Error en la ruta");
         }
         try {
         	//Se crea un reproductor para lo que contiene la url (video).
             player = Manager.createRealizedPlayer(new MediaLocator(url));
             
             //componente de visualizacion para el reproductor
             video = player.getVisualComponent();
             video.setSize(800,500);
             video.setVisible(true);
             
             if(video != null)
                 panelVideo.add("Center",video);
             
             	//Se asigna el componente de controles predeterminados de la clase Player
             	controles = player.getControlPanelComponent();
             	controles.setSize(800,100);
             	controles.setVisible(true);
             	
             if(controles != null)
                 panelVideo.add("South",controles);
             
            VentanaReproductorVideo.getPanel(panelVideo);
             
             //inicia el reproductor
             player.start();
             
             //establece la apariencia de la ventana
             panelVideo.updateUI();
         } catch (Exception ex) {
        	 JOptionPane.showMessageDialog(null, "Error creando panel de video");
         }
 }
}
