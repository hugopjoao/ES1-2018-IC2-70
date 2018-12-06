import java.io.IOException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

public class Mail extends Thread {

	public String username;
	public String password;
	private Interface_Grafica gui;
	public Message[] messages;
	private Folder emailFolder;
	private String server;
	private Store store;

	/**
	 * construtor para o mail com username e respetiva password. inicialização da
	 * gui.
	 */

	public Mail(String username, String password, Interface_Grafica gui) {
		this.username = username;
		this.password = password;
		this.gui = gui;
	}

	/**
	 * termina aplicação
	 */

	public void fechar() {
		try {
			emailFolder.close(false);
			store.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		try {
			boolean isImap = true;
			Properties props = new Properties();
			server = isImap ? "imap.outlook.com" : "pop3.outlook.com";
			Session session = Session.getDefaultInstance(props, null);
			String storeName = isImap ? "imaps" : "pop3";
			Store store = session.getStore(storeName);
			props.put("mail.pop3.host", server);
			props.put("mail.pop3.port", "995");
			props.put("mail.pop3.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			store.connect(server, username, password);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			messages = inbox.getMessages();
			for (Message m : messages) {
				try {
					enviaTitulos(m);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (

		NoSuchProviderException nspe) {
			System.err.println("invalid provider name");
		} catch (MessagingException me) {
			System.err.println("messaging exception");
			me.printStackTrace();
		}
	}

	private void enviaTitulos(Message messages) throws MessagingException, IOException {

		String[] origem = messages.getFrom()[0].toString().split("<");
		gui.modelMail.addElement(origem[0] + " || " + messages.getSubject());

	}

	public String devolveTexto(int index) {

		Message message = messages[index];
		String content = convert(message);
		return content;

	}

	public void imprimeMail(int index) throws IOException, MessagingException {
		Message message = messages[index];
		String content = convert(message);
		gui.mail.setText(content);

	}

	public static String convert(Message message) {
		Object content;
		try {
			content = message.getContent();
			if (content instanceof MimeMultipart) {
				MimeMultipart multipart = (MimeMultipart) content;
				if (multipart.getCount() > 0) {
					BodyPart part = multipart.getBodyPart(0);
					content = part.getContent();
				}
			}
			if (content != null) {
				return content.toString();
			}
		} catch (IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
