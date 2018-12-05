import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.Thread;
import com.restfb.types.User;

import twitter4j.Status;

public class Facebook extends Thread{

	private Interface_Grafica gui;
	private String accessToken = "EAAMZCl2Ln2ZAkBACjFPMwoMN9zPqZAjiEx6ZChZCWDAWj56zJjXJTq15DkcLZBZCCGXpvb2Ih79VWZB4Vdk0jwQ7vvEVPsYbimEcgJC5x8tU3hU7i4DL1ET2e0pNuZCuFsYYy8ESf6FGanwMJbxkzC0bQdgrUNA4VSHW0JZAqLyTOawHZAGFNkc7BhSBZC5cHF8m3iInOQeCrr20Ka5GmsJSZBOSy7SbnVZCxXpLwmJ3ok9tuxuZBDGEPydKWEI";
	private FacebookClient facebookClient;
	public ArrayList <Post> listaPosts = new ArrayList <Post>();
	
	/* Access token:
	 EAAMZCl2Ln2ZAkBACjFPMwoMN9zPqZAjiEx6ZChZCWDAWj56zJjXJTq15DkcLZBZCCGXpvb2Ih79VWZB4Vdk0jwQ7vvEVPsYbimEcgJC5x8tU3hU7i4DL1ET2e0pNuZCuFsYYy8ESf6FGanwMJbxkzC0bQdgrUNA4VSHW0JZAqLyTOawHZAGFNkc7BhSBZC5cHF8m3iInOQeCrr20Ka5GmsJSZBOSy7SbnVZCxXpLwmJ3ok9tuxuZBDGEPydKWEI */
	
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
		    
		    // listaPosts.sort(c); 
		    Collections.sort(listaPosts, new CustomComparator());
		    Collections.reverse(listaPosts); 
		    
		    for (Post post : listaPosts) {
		    	enviaPosts(post);
		    }
	}
	
	
	public void like (Post p) {
		
		String postId = p.getId();
		
		DefaultFacebookClient client = new DefaultFacebookClient(accessToken);
		client.publish(postId+"/likes", Boolean.class); 
		
	}
	
	public void partilhar(Post p) {
		
		String postId = p.getId();
		
		DefaultFacebookClient client = new DefaultFacebookClient(accessToken);
		client.publish("me/feed", FacebookType.class, Parameter.with("link", postId));
		
	}
	
	private void enviaPosts(Post p) {
		gui.modelFacebook.addElement(p.getName() + " || " + p.getCreatedTime());
	}
	
	public Post getIndex(int index) {
		return listaPosts.get(index);
	}

	/**
	 * Implementação de um método que recebe um indíce sob a forma de um inteiro, e
	 * imprime na Gui o status correspondente ao obtido no método getIndex(index).
	 */

	public void imprimeIndex(int index) {
		gui.postAtual.setText(getIndex(index).getCaption());
	}
	
}
