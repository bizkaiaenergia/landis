


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;




public class SendEmail {
	//static String email;
	//public static void main(String [] args)
	public static void main (String fallo,String host, String from, String to,String cc)
	   {    
	      // Recipient's email ID needs to be mentioned.
	      //String to = "deflagmator@gmail.com";

	      // Sender's email ID needs to be mentioned
	      //String from = "administrador@bizkaiaenergia.com";

	      // Assuming you are sending email from localhost
	      //String host = "smtp-relay.gmail.com";
	      //String host = "10.34.1.58";
	      

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      //properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	         // SEt CC: 
	         
	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
	         
	         
	         // Set Subject: header field
	         message.setSubject("Fallo en importación LANDIS");

	         // Now set the actual message
	         message.setText("Se ha producido un fallo en la importación: el fallo es "+ fallo);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      //return email;
	   }
	
}	