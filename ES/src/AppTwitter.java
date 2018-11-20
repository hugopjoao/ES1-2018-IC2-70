import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	public boolean accessGranted = false;
	private ArrayList<String> listaUsers = new ArrayList<String>();
	public ArrayList<Status> listaStatus = new ArrayList<Status>();
	private Twitter twitter = new TwitterFactory().getInstance();

	public AppTwitter(String consumerKey, String consumerSecret, String accessToken, String tokenSecret,
			Interface_Grafica gui) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.tokenSecret = tokenSecret;
		
//		this.consumerKey = "EpysCbFJ3KN0X7qoDk5llztD8";
//		this.consumerSecret = "XlVwrmtx9JeQucykuukThiC4nYZa34L4jQJuUwLP4bgXjAnlEu";
//		this.accessToken = "832318636555636737-GxsN6MvuUKHqF8N0hxzmO2w1EpWx4HA";
//		this.tokenSecret = "yCbc6VKR1K7TYIjDiidVrlbyUkNX2tNo2QveVKr7ol8WB";
		
		
		this.gui = gui;
		this.accessGranted = true;
		
		
	}

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

	private void enviaTitulos(Status status) {
		gui.modelTwitter.addElement(status.getUser().getScreenName() + " || " + status.getCreatedAt());
	}

	public void setAccess() {
		this.accessGranted = true;
	}

	public boolean getValidation() {
		return accessGranted;
	}

	public Status getIndex(int index) {
		return listaStatus.get(index);
	}

	public void imprimeIndex(int index) {
		gui.tweet.setText(getIndex(index).getText());
	}
}
