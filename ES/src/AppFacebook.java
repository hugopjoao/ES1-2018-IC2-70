import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import com.restfb.exception.FacebookException;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.conf.ConfigurationBuilder;

public class AppFacebook extends Thread{

	private Interface_Grafica gui;
	private String accessToken = "EAAMZCl2Ln2ZAkBACjFPMwoMN9zPqZAjiEx6ZChZCWDAWj56zJjXJTq15DkcLZBZCCGXpvb2Ih79VWZB4Vdk0jwQ7vvEVPsYbimEcgJC5x8tU3hU7i4DL1ET2e0pNuZCuFsYYy8ESf6FGanwMJbxkzC0bQdgrUNA4VSHW0JZAqLyTOawHZAGFNkc7BhSBZC5cHF8m3iInOQeCrr20Ka5GmsJSZBOSy7SbnVZCxXpLwmJ3ok9tuxuZBDGEPydKWEI";
	private Facebook facebook;
	public ArrayList <Post> listaPosts = new ArrayList <Post>();
	
	/* Access token:
	 EAAMZCl2Ln2ZAkBACjFPMwoMN9zPqZAjiEx6ZChZCWDAWj56zJjXJTq15DkcLZBZCCGXpvb2Ih79VWZB4Vdk0jwQ7vvEVPsYbimEcgJC5x8tU3hU7i4DL1ET2e0pNuZCuFsYYy8ESf6FGanwMJbxkzC0bQdgrUNA4VSHW0JZAqLyTOawHZAGFNkc7BhSBZC5cHF8m3iInOQeCrr20Ka5GmsJSZBOSy7SbnVZCxXpLwmJ3ok9tuxuZBDGEPydKWEI */
	
	/* https://www.facebook.com/Istar-Iul-1008842092466001/ 
	 * https://www.facebook.com/ISCTEIUL/?ref=br_rs
	 * https://www.facebook.com/ISCTEBusinessSchool/?ref=br_rs
	 */
	
	public AppFacebook (String accessToken, Interface_Grafica gui) {
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
		configuracao();
		feed();
		
	}
	
	public void configuracao() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setRestBaseURL("https://graph.facebook.com/v2.0/");  // <- set v2.0
		FacebookFactory ff = new FacebookFactory(cb.build());
		facebook = ff.getInstance();
	}
	
	public void feed() {
		gui.modelFacebook.removeAllElements();
		listaPosts.removeAll(listaPosts);
		
		try {
			listaPosts.addAll(facebook.getHome());
		} catch (facebook4j.FacebookException e) {
			e.printStackTrace();
		}
	
		
		//Collections.sort(listaPosts, new CustomComparator());
		
		for (Post post : listaPosts) {
			enviaPosts(post);
		}
		
	}
	
	public void comment(String comentario, Post p) {
		String postId = p.getId();
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setRestBaseURL("https://graph.facebook.com/v2.0/");  // <- set v2.0
		FacebookFactory ff = new FacebookFactory(cb.build());
		facebook = ff.getInstance();
		
		try {
			facebook.commentPost(postId, comentario);
		} catch (facebook4j.FacebookException e) {
			e.printStackTrace();
		}
	}
		
		
		
	public void like(Post p) {
		String postId = p.getId();
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setRestBaseURL("https://graph.facebook.com/v2.0/");  // <- set v2.0
		FacebookFactory ff = new FacebookFactory(cb.build());
		facebook = ff.getInstance();
			try {
				facebook.likePost(postId);
			} catch (facebook4j.FacebookException e) {
				e.printStackTrace();
			}
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
