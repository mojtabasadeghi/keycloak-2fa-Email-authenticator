package dasniko.keycloak.authenticator.gateway;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
	void sendSimpleMessage(String to,
						   String subject,
						   String text);
	void sendPlainTextEmail(final String userName, final String password, String toAddress,
							String subject, String message) throws AddressException,
		MessagingException;

	void sendEmail(String to, String subject, String text) throws UnsupportedEncodingException, MessagingException;

	void sendEmailFromSMTP(String to, String subject, String text) throws UnsupportedEncodingException, MessagingException;
}
