import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class ReplyEmail implements Runnable {

	private String subject;
	private String to;
	private String text;
	private String user;
	private String password;

	public ReplyEmail(String user, String password, String to, String subject, String text) {
		this.password = password;
		this.user = user;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public void run() {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.localhost", "127.0.0.1");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
