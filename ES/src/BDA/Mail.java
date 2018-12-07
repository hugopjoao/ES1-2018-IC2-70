package BDA;

import java.io.IOException;
import java.util.ArrayList;
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
	private String server;
	private Store store;
	public ArrayList<Message> pesquisas = new ArrayList<Message>();

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
			store.close();
		} catch (MessagingException e) {
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
					System.out.println(m);
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
		gui.modelMail.addElement(origem[0] + " || " + messages.getReceivedDate());

	}

	public String devolveTexto(int index) {

		try {
			String content;
			Message message = pesquisas.get(index);
			content = convert(message);

			return content;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public void pesquisa(String texto) {
		gui.modelMail.removeAllElements();
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
			if (pesquisas != null)
				if (!pesquisas.isEmpty())
					pesquisas.removeAll(pesquisas);
			try {
				for (Message m : messages) {
					String[] origem = m.getFrom()[0].toString().split("<");
					if (origem[0].contains(texto)) {
						pesquisas.add(m);
						enviaTitulos(m);
					}
				}
			} catch (MessagingException | IOException e) {
				e.printStackTrace();
			}
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void imprimeMail(int index) throws IOException, MessagingException {
		Message message = messages[index];
		String content = convert(message);
		gui.mail.setText(content);

	}

	private String convert(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = convert(mimeMultipart);
		}
		return result;
	}

	private String convert(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + convert((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

}
