import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class AppTwitter extends Thread {
	
	public void run() {
        
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
              .setOAuthConsumerKey("EpysCbFJ3KN0X7qoDk5llztD8")
              .setOAuthConsumerSecret("XlVwrmtx9JeQucykuukThiC4nYZa34L4jQJuUwLP4bgXjAnlEu")
              .setOAuthAccessToken("832318636555636737-GxsN6MvuUKHqF8N0hxzmO2w1EpWx4HA")
              .setOAuthAccessTokenSecret("yCbc6VKR1K7TYIjDiidVrlbyUkNX2tNo2QveVKr7ol8WB");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter1 = tf.getInstance();
            List<Status> statuses;
            ArrayList<String> listaUsers = new ArrayList<String>();
            ArrayList<Status> listaStatus = new ArrayList<Status>();            
            listaUsers.add("@ISCTEIUL");
            listaUsers.add("IBSLisbon");
            listaUsers.add("ISTAR_IUL");

          for(String i : listaUsers) {
                statuses = twitter1.getUserTimeline(i);
                listaStatus.addAll(statuses);
            }
          Collections.sort(listaStatus);
          Collections.reverse(listaStatus);
          String texto = new String();
            for (Status status : listaStatus) {
            	
            	texto = concatenar(texto,"@" + status.getUser().getScreenName() + " - " + status.getText()+ "\n\n\n" + status.getCreatedAt() + "\n\n\n");
            }  
            System.out.println(texto);
//            InterfaceGrafica.setTwitter(texto);
            
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
	
	private String concatenar (String inicial, String adicionar) {
		inicial = inicial +  adicionar ;
		return inicial;
	}
}
