import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	public ArrayList<Status> listaStatus = new ArrayList<Status>();
	private Twitter twitter1;

	/**
	 * Construtor da App do Twitter que recebe como argumentos uma Consumer Key, um
	 * consumer Secret, um Access Token e um Token Secret, fazendo a associa��o a
	 * uma dada Interface Gr�fica.
	 */

	public AppTwitter(String consumerKey, String consumerSecret, String accessToken, String tokenSecret,
			Interface_Grafica gui) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.tokenSecret = tokenSecret;

		// this.consumerKey = "EpysCbFJ3KN0X7qoDk5llztD8";
		// this.consumerSecret = "XlVwrmtx9JeQucykuukThiC4nYZa34L4jQJuUwLP4bgXjAnlEu";
		// this.accessToken = "832318636555636737-GxsN6MvuUKHqF8N0hxzmO2w1EpWx4HA";
		// this.tokenSecret = "yCbc6VKR1K7TYIjDiidVrlbyUkNX2tNo2QveVKr7ol8WB";

		this.gui = gui;

	}

	/**
	 * Implementa��o do m�todo run correspondente � Thread AppTwitter implementa o
	 * m�todo configuracao() para fazer a liga��o ao servidor e o m�todo timeLine()
	 * para imprimir a timeLine do utilizador.
	 */

	public void run() {
		configuracao();
		timeLine();

	}

	/**
	 * Implementa��o do m�todo configuracao que atrav�s dos tokens atribuidos, faz a
	 * liga��o ao servidor do twitter
	 */

	public void configuracao() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(tokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter1 = tf.getInstance();
	}

	/**
	 * Implementa��o do m�todo timeLine que imprime na gui toda a TimeLine do
	 * utilizador
	 */

	public void timeLine() {
		gui.modelTwitter.removeAllElements();
		listaStatus.removeAll(listaStatus);
		try {
			listaStatus.addAll(twitter1.getHomeTimeline());
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		Collections.sort(listaStatus);
		Collections.reverse(listaStatus);

		for (Status status : listaStatus) {
			enviaTitulos(status);
		}
	}

	/**
	 * Implementa��o do m�todo retweet (utilizado atrav�s de um actionListener da
	 * Interface Gr�fica que permite dar retweet do post que est� em exibi��o
	 * atrav�s da instancia��o do m�todo retweetStatus da API utilizada (Twitter4j).
	 */

	public void retweet(Status s) {

		long tweetId = s.getId();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(tokenSecret);
		TwitterFactory factory = new TwitterFactory(cb.build());
		Twitter twitter = factory.getInstance();
		try {
			twitter.retweetStatus(tweetId);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementa��o do m�todo fav que recebe um status (o que est� apresentado na
	 * Gui) e d� Fav (like) no tweet publicado.
	 */

	public void fav(Status s) {
		long tweetId = s.getId();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(tokenSecret);
		TwitterFactory factory = new TwitterFactory(cb.build());
		Twitter twitter = factory.getInstance();
		try {
			twitter.createFavorite(tweetId);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementa��o do m�todo enviaTitulos que recebe um Status e envia para a Gui
	 * o user que publicou o tweet e a data em que foi criado.
	 */

	private void enviaTitulos(Status status) {
		gui.modelTwitter.addElement(status.getUser().getScreenName() + " || " + status.getCreatedAt());
	}

	/**
	 * Implementa��o de um m�todo que recebe um ind�ce sob a forma de um inteiro, e
	 * devolve da lista de Status, o status correspondente ao �ndice.
	 */

	public Status getIndex(int index) {
		return listaStatus.get(index);
	}

	/**
	 * Implementa��o de um m�todo que recebe um ind�ce sob a forma de um inteiro, e
	 * imprime na Gui o status correspondente ao obtido no m�todo getIndex(index).
	 */

	public void imprimeIndex(int index) {
		gui.tweet.setText(getIndex(index).getText());
	}

	/**
	 * Implementa��o do m�todo pesquisa que recebe uma string e permite ao
	 * utilizador pesquisar por esse termo em tweets da sua TimeLine
	 */

	public void pesquisa(String text) throws TwitterException {
		gui.modelTwitter.removeAllElements();
		listaStatus.removeAll(listaStatus);

		List<Status> statuses = twitter1.getHomeTimeline();

		String palavraIsolada = " " + text + " ";

		for (Status s : statuses) {
			if (s.getText().toLowerCase().contains(palavraIsolada.toLowerCase())) {
				listaStatus.add(s);
			}
		}

		Collections.sort(listaStatus);
		Collections.reverse(listaStatus);

		for (Status status : listaStatus) {
			enviaTitulos(status);
		}

	}

}
