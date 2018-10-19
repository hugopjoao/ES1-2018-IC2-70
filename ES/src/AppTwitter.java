import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class AppTwitter {
	
	public static void main(String[] args) {
        // gets Twitter instance with default credentials
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
            listaUsers.add("@ISCTEIUL");
            listaUsers.add("IBSLisbon");
            listaUsers.add("ISTAR_IUL");
     
          for(String i : listaUsers) {
        	  if (args.length == 1) {
               
                statuses = twitter1.getUserTimeline(i);
            } else {
                statuses = twitter1.getUserTimeline(i);
            }
            System.out.println("Showing @" + i + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
          }
            
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
	
	
	
}
