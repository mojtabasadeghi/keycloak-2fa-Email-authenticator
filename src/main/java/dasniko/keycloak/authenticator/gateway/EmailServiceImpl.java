package dasniko.keycloak.authenticator.gateway;

import org.jboss.logging.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import java.util.Properties;


public class EmailServiceImpl implements EmailService{
	public EmailServiceImpl() {
	}

	public void sendSimpleMessage(String to, String subject, String text)
	{


		String host="smtp.google.com";
		final String user="shobtaki2@google.com";//change accordingly
		final String password="123@shoptaki";//change accordingly

		//String to="sonoojaiswal1987@gmail.com";//change accordingly

		//Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.starttls.enable", "true");


		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user,password);
				}
			});

		//Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);

			//send the message
			Transport.send(message);

			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();}
	}

	public void sendPlainTextEmail(final String userName, final String password, String toAddress,
								   String subject, String message) throws AddressException,
		MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.enabled","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.mail.yahoo.com");

// *** BEGIN CHANGE
		properties.put("mail.smtp.username", userName);

		// creates a new session, no Authenticator (will connect() later)
		Session session = Session.getDefaultInstance(properties);
// *** END CHANGE

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
	//	msg.setSentDate(new Date());
		// set plain text message
		msg.setText(message);

// *** BEGIN CHANGE
		// sends the e-mail
		Transport t = session.getTransport("smtp");
		t.connect(userName, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
// *** END CHANGE

	}


	public void sendEmail(String to, String subject, String text) throws UnsupportedEncodingException, MessagingException {

		// Replace sender@example.com with your "From" address.
		// This address must be verified.
		final String FROM = "shoptaki2@gmail.com";
		final String FROMNAME = "shoptaki Co";

		// Replace recipient@example.com with a "To" address. If your account
		// is still in the sandbox, this address must be verified.
		final String TO = to;

		// Replace smtp_username with your Amazon SES SMTP user name.
		final String SMTP_USERNAME = "AKIA2UGWBI7M3GXCYFOI";

		// Replace smtp_password with your Amazon SES SMTP password.
		final String SMTP_PASSWORD = "BDtzJ2Q90XuFDrC3WEinGhC29YedYupLZOkAgU8o9bb+";

		// The name of the Configuration Set to use for this message.
		// If you comment out or remove this variable, you will also need to
		// comment out or remove the header below.
//	static final String CONFIGSET = "ConfigSet";

		// Amazon SES SMTP host name. This example uses the US West (Oregon) region.
		// See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
		// for more information.
		//static final String HOST = "email-smtp.us-west-2.amazonaws.com";us-east-1
		final String HOST = "email-smtp.us-east-1.amazonaws.com";

		// The port you will connect to on the Amazon SES SMTP endpoint.
		final int PORT = 587;

		final String SUBJECT = subject;

		final String BODY = String.join(
			System.getProperty("line.separator"),
			text
		);



		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");



		// Create a Session object to represent a mail session with the specified properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM,FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY,"text/html");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		//	msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try
		{
			Logger LOG = Logger.getLogger(EmailServiceImpl.class);
			System.out.println("Sending...");
			LOG.warn(String.format("***** test MODE ***** Would send Email to %s with text: %s", to, text));
			// Connect to Amazon SES using the SMTP username and password you specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		}
		catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		}
		finally
		{
			// Close and terminate the connection.
			transport.close();
		}
	}


	public void sendEmailFromSMTP(String to, String subject, String text) throws UnsupportedEncodingException, MessagingException {

		// Replace sender@example.com with your "From" address.
		// This address must be verified.
		final String FROM = "OTP@shoptaki.com";
		final String FROMNAME = " One Time Password";

		// Replace recipient@example.com with a "To" address. If your account
		// is still in the sandbox, this address must be verified.
		final String TO = to;

		// Replace smtp_username with your Amazon SES SMTP user name.
		final String SMTP_USERNAME = "ubuntu";

		// Replace smtp_password with your Amazon SES SMTP password.
		final String SMTP_PASSWORD = "shoptakiubuntu";

		// The name of the Configuration Set to use for this message.
		// If you comment out or remove this variable, you will also need to
		// comment out or remove the header below.
//	static final String CONFIGSET = "ConfigSet";

		// Amazon SES SMTP host name. This example uses the US West (Oregon) region.
		// See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
		// for more information.
		//static final String HOST = "email-smtp.us-west-2.amazonaws.com";us-east-1
		final String HOST = "smtp.shoptaki.com:10000";

		// The port you will connect to on the Amazon SES SMTP endpoint.
		final int PORT = 10000;

		final String SUBJECT = subject;

		final String BODY = String.join(
			System.getProperty("line.separator"),
			text
		);



		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
	//	props.put("mail.smtp.starttls.enable", "true");
	//	props.put("mail.smtp.auth", "true");



		// Create a Session object to represent a mail session with the specified properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM,FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY,"text/html");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		//	msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try
		{
			Logger LOG = Logger.getLogger(EmailServiceImpl.class);
			System.out.println("Sending...");
			LOG.warn(String.format("***** test MODE ***** Would send Email to %s with text: %s", to, text));
			// Connect to Amazon SES using the SMTP username and password you specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
			LOG.warn("Email sent!");
		}
		catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		}
		finally
		{
			// Close and terminate the connection.
			transport.close();
		}
	}

}

