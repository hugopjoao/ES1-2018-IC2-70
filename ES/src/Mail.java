import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Mail extends Thread{

	private String username;
	private String password;
	private Interface_Grafica gui;
	private Message[] messages;
	private Folder emailFolder;
	private Store store;
	private Session session;
	private String server;
	private Properties connectionProperties;

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
			connectionProperties = new Properties();
			session = Session.getDefaultInstance(connectionProperties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			String storeName = isImap ? "imaps" : "pop3";
			store = session.getStore(storeName);
			server = isImap ? "imap.outlook.com" : "pop3.outlook.com";
			connectionProperties.put("mail.pop3.host", server);
			connectionProperties.put("mail.pop3.port", "995");
			connectionProperties.put("mail.pop3.starttls.enable", "true");
			connectionProperties.put("mail.smtp.socketFactory.port", "465");
			connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			connectionProperties.put("mail.smtp.auth", "true");
			connectionProperties.put("mail.smtp.port", "465");
			store.connect(server, username, password);
			emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			messages = emailFolder.getMessages();
			for (int i = 0, n = messages.length; i < n; i++) {
				enviaTitulos(messages[i]);
			}
		} catch (MessagingException | IOException e) {
			JOptionPane.showMessageDialog(null, "Credenciais incorretas, por favor insira novamente");
			gui.botaoLoginMail();
			System.exit(-1);
		}
	}

	/**
	 * metódo para responder a um mail. username para quem se responde, origem da mensagem e string texto com 
	 * texto da mensagem a ser enviada
	 */
	
	
	@SuppressWarnings("static-access")
		public void responder(int index) throws MessagingException {
		Message mensagem = messages[index];
		String to = username;
		String[] origem = mensagem.getFrom()[0].toString().split("<");
		String[] origem2 = origem[1].split(">");
		String from = origem2[0];
		String subject = mensagem.getSubject();
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			String text = JOptionPane.showInputDialog("Please insert your text");
			msg.setText(text);
			Transport transport = session.getTransport();
			transport.send(msg);
			System.out.println("E-mail sent !");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void enviaTitulos(Message messages) throws MessagingException, IOException {

		String[] origem = messages.getFrom()[0].toString().split("<");
		String[] origem2 = origem[1].split(">");
		gui.modelMail.addElement(origem2[0] + " || " + messages.getSubject());
	}

	public void encaminhar() {
		// TODO Auto-generated method stub

	}

	/**
	 * imprimir mail. aparece na gui o texto
	 */

	public void imprimeMail(int index) throws IOException, MessagingException {
		Message message = messages[index];
		Object content = message.getContent();
		if (content instanceof MimeMultipart) {
			MimeMultipart multipart = (MimeMultipart) content;

			if (multipart.getCount() > 0) {
				BodyPart part = multipart.getBodyPart(0);
				content = part.getContent();
			}
		}
		if (content != null) {
			gui.mail.setText(content.toString());
		}
	}

}
