package negocio;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarEmailConAdjunto {
	
	public static void enviarEmail (String dest, String asunto, String mensaje, String rutaReporte) throws MessagingException{
		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port","587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", "rantiypc@gmail.com");

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props);
		//session.setDebug(true);
		
		//texto del mensaje
		BodyPart texto = new MimeBodyPart();
		texto.setText(mensaje);
		
		//ruta y nombre del archivo adjunto
		BodyPart adjunto = new MimeBodyPart();
		adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaReporte)));
		//adjunto.setFileName(nombreReporte);
		
		//juntar texto y archivo adjunto
		MimeMultipart multiParte = new MimeMultipart();
		multiParte.addBodyPart(texto);
		multiParte.addBodyPart(adjunto);
		
		MimeMessage message = new MimeMessage(session);

		// Se rellena el From
		message.setFrom(new InternetAddress("rantiypc@gmail.com"));

		// Se rellenan los destinatarios
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));

		// Se rellena el subject
		message.setSubject(asunto);

		// Se mete el texto y la foto adjunta.
		message.setContent(multiParte);
		
		Transport t = session.getTransport("smtp");
		t.connect("rantiypc@gmail.com","rantiypc123");
		t.sendMessage(message,message.getAllRecipients());
		t.close();
	}
	
}
