import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.Thread;
import com.restfb.types.User;

public class Facebook extends Thread {

	private Interface_Grafica gui;
	private String accessToken;
	private FacebookClient facebookClient;
	public ArrayList <Post> listaPosts = new ArrayList <Post>();
	
	/* Access token:
	 EAAMZCl2Ln2ZAkBAG7R2cqKbcuNtedERPNUpLZCczaQtFxZB6E4dZAnMZAP4xB7ZBBwWxdAgmAbfZCGGWk5F4PRDZAFSI9ZCpbGCqbjCtI1hKLnItg0CQkR6cqxvEyo0c2Olv1w6X8DQjZAdZAMfjxZB6AWxQiQ4EARmon12NA4Es9iq4sMzOxla05nqDw33Hote0OyP08dJsWfJyy9Ohye2HM1ZBo3fchnpEsmH8irTxEYKAfLAQlK2TAUsEyF */
	
	/* https://www.facebook.com/Istar-Iul-1008842092466001/ 
	 * https://www.facebook.com/ISCTEIUL/?ref=br_rs
	 * https://www.facebook.com/ISCTEBusinessSchool/?ref=br_rs
	 */
	
	public Facebook (String accessToken, Interface_Grafica gui) {
		this.accessToken = accessToken;
		this.gui = gui;
	}
	
	/**
	 *  Implementação do método run correspondente à Thread do Facebook que instancia um novo facebook com o Access Token recebido
	 * anteriormente. Em seguida criará uma lista com os posts do utilizador para que depois possa filtrar por posts relacionados com o ISCTE.
	 * Nesta filtragem, adiciona os posts relacionados com o ISCTE numa ArrayList, será organizado por ordem cronológica e impressa na GUI.
	 * Caso não seja possível obter acesso com o Token utilizado, apresentará uma mensagem de erro.
	 */
	
	public void run () {
		
		 facebookClient = new DefaultFacebookClient(accessToken);
		    User user = facebookClient.fetchObject("me", User.class); 
		    System.out.println("User name: " + user.getName()); 
		    
		    Connection<Post> page1 = facebookClient.fetchConnection("Istar-Iul-1008842092466001"+"/feed", Post.class,Parameter.with("limit",999));
		    List<Post> pagePosts1 = page1.getData();
	
		    
		    Connection<Post> page2 = facebookClient.fetchConnection("ISCTEIUL/?ref=br_rs"+"/feed", Post.class,Parameter.with("limit",999));
		    List<Post> pagePosts2 = page2.getData();
		    
		    Connection<Post> page3 = facebookClient.fetchConnection("ISCTEBusinessSchool/?ref=br_rs"+"/feed", Post.class,Parameter.with("limit",999));
		    List<Post> pagePosts3 = page3.getData();
		   
		    listaPosts.addAll(pagePosts1);
		    listaPosts.addAll(pagePosts2);
		    listaPosts.addAll(pagePosts3);
		    
		    Collections.sort(listaPosts);
		    Collections.reverse(listaPosts);
	}
	
	
	public void Like () {
		
	}
	
	public void partilhar() {
		
	}
	
}
