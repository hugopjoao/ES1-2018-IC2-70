import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.lang.model.element.VariableElement;
import javax.swing.JOptionPane;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class AppTwitter extends Thread {

	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String tokenSecret;
	private Interface_Grafica gui;
	private ArrayList<String> listaUsers = new ArrayList<String>();
	public ArrayList<Status> listaStatus = new ArrayList<Status>();
	private Twitter twitter = new TwitterFactory().getInstance();

	/**
	 * Construtor da App do Twitter que recebe como argumentos uma Consumer Key,
	 *  um consumer Secret, um Access Token e um Token Secret, fazendo a associação 
	 *  a uma dada Interface Gráfica.
	 */

	
	public AppTwitter(String consumerKey, String consumerSecret, String accessToken, String tokenSecret,
			Interface_Grafica gui) {
//		this.consumerKey = consumerKey;
//		this.consumerSecret = consumerSecret;
//		this.accessToken = accessToken;
//		this.tokenSecret = tokenSecret;
		
		this.consumerKey = "EpysCbFJ3KN0X7qoDk5llztD8";
		this.consumerSecret = "XlVwrmtx9JeQucykuukThiC4nYZa34L4jQJuUwLP4bgXjAnlEu";
		this.accessToken = "832318636555636737-GxsN6MvuUKHqF8N0hxzmO2w1EpWx4HA";
		this.tokenSecret = "yCbc6VKR1K7TYIjDiidVrlbyUkNX2tNo2QveVKr7ol8WB";
			
		this.gui = gui;		
		
	}
	
	
	/**
	 * Implementação do método run correspondente à Thread AppTwitter que cria o ConfigurationBuilder
	 * e instancia um novo twitter com os Tokens de acesso recebidos anteriormente. Em seguida cria uma lista de status
	 * e procura todos os posts de contas relacionadas diretamente com o ISCTE, adiciona à ArrayList de Status, organiza por 
	 * ordem temporal e imprime na InterfaceGrafica. Caso não consiga acesso com os tokens dados pelo utilizador, apresenta uma
	 * mensagem de erro, apaga o processo e instancia-se novamente.
	 */

	public void run() {

		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
					 .setOAuthConsumerKey(consumerKey)
					 .setOAuthConsumerSecret(consumerSecret)
					 .setOAuthAccessToken(accessToken)
					 .setOAuthAccessTokenSecret(tokenSecret);
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter1 = tf.getInstance();
			List<Status> statuses;
			
			listaUsers.add("@ISCTEIUL");
			listaUsers.add("IBSLisbon");
			listaUsers.add("ISTAR_IUL");

			for (String i : listaUsers) {
				statuses = twitter1.getUserTimeline(i);
				listaStatus.addAll(statuses);
			}
			Collections.sort(listaStatus);
			Collections.reverse(listaStatus);

			for (Status status : listaStatus) {
				enviaTitulos(status);
			}

		} catch (TwitterException te) {
			JOptionPane.showMessageDialog(null, "Credenciais incorretas, por favor insira novamente");
			gui.botaoLoginTwitter();
			System.exit(-1);
		}

	}

	/**
	 * Implementação do método retweet (utilizado através de um actionListener da Interface Gráfica que
	 * permite dar retweet do post que está em exibição através da instanciação do método retweetStatus
	 * da API utilizada (Twitter4j).
	 */
	
	public void retweet(Status s) {

		long tweetId = s.getId();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				 .setOAuthConsumerKey(consumerKey)
				 .setOAuthConsumerSecret(consumerSecret)
				 .setOAuthAccessToken(accessToken)
				 .setOAuthAccessTokenSecret(tokenSecret);
		TwitterFactory factory = new TwitterFactory(cb.build());
		Twitter twitter = factory.getInstance();
		try {
			twitter.retweetStatus(tweetId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Implementação do método fav que recebe um status (o que está apresentado na Gui) e 
	 * dá Fav (like) no tweet publicado.
	 */
	

	public void fav(Status s) {
		long tweetId = s.getId();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				 .setOAuthConsumerKey(consumerKey)
				 .setOAuthConsumerSecret(consumerSecret)
				 .setOAuthAccessToken(accessToken)
				 .setOAuthAccessTokenSecret(tokenSecret);
		TwitterFactory factory = new TwitterFactory(cb.build());
		Twitter twitter = factory.getInstance();
		try {
			twitter.createFavorite(tweetId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Implementação do método enviaTitulos que recebe um Status e envia para a Gui o user que publicou o tweet
	 * e a data em que foi criado.
	 */

	private void enviaTitulos(Status status) {
		gui.modelTwitter.addElement(status.getUser().getScreenName() + " || " + status.getCreatedAt());
	}
	

	/**
	 * Implementação de um método que recebe um indíce sob a forma de um inteiro, e devolve da lista de Status, o status
	 * correspondente ao índice.
	 */	
	
	public Status getIndex(int index) {
		return listaStatus.get(index);
	}
	
	/**
	 * Implementação de um método que recebe um indíce sob a forma de um inteiro, e imprime na Gui o status
	 * correspondente ao obtido no método getIndex(index).
	 */	

	public void imprimeIndex(int index) {
		gui.tweet.setText(getIndex(index).getText());
	}
}
