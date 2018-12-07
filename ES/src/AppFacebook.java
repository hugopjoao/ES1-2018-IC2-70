import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.Thread;
import com.restfb.types.User;

public class AppFacebook extends Thread{

	private Interface_Grafica gui;
	private String accessToken;
	private FacebookClient facebookClient;
	public ArrayList <Post> listaPosts = new ArrayList <Post>();
	
	/* Access token:
	 EAAMZCl2Ln2ZAkBAOOClBI6wcfLKsnpGH19OvGbNA0YRVYy8114crcX6GTPW42O1fHQkzficIkcCvxL4h8Tf1hg6ZCWZAmyE1aAzz1dV71ImS2uZCMKdH40DZCChF0CpW9Wb1V4UIEZB0BKiTaMwcblVXmAVUFo6YMTWhdsP5mlZBztAsMORkrZBwDYDAu7Etobj64ZCd9exjSVTV24OKGZBsCZC93DkqQZBVnT6hLFWCpVTyJrQZDZD */
	
	
	/**
	 * 
	 * Construtor da App do Facebook que recebe como argumentos um AccessToken e 
	 * fazendo a associação a uma dada Interface Gráfica.
	 */
	
	public AppFacebook (String accessToken, Interface_Grafica gui) {
		this.accessToken = "EAAMZCl2Ln2ZAkBAFQEnKOR1nLdPleQa1HrZC5z3lZBYiyLfZBEZCmjt9timCqeSZAjKOn8HZCUkqYwqx3Kxb34niwJ9EWbVDAMKTM5opjsSiJJ8mRSmNI8ZAunXscFcfJELOwyrkTpTCkkCWcV1EsZAB277KFZARi6EZAD5zNiekCPKUIocFKyZBT1qTJ6Xz6EWDsIxyMgAIB2SgG3wBlscMs9vAjnRyb4mTZAA1nzNM7rdt9O3QZDZD";
		this.gui = gui;
	}
	
	/**
	 *  Implementação do método run correspondente à Thread do Facebook que instancia um novo facebook com o Access Token recebido
	 * anteriormente. Em seguida criará uma lista com os posts do feed do utilizador e os imprimirá na GUI.
	 * Caso não seja possível obter acesso com o Token utilizado, apresentará uma mensagem de erro.
	 */
	
	public void run () {
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		User me = fbClient.fetchObject("me", User.class);
		
		AccessToken extendedAcessToken = fbClient.obtainExtendedAccessToken("914344362105241", "306f085aeadb59d88313c12779850a9b");
		
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		
		for (List<Post> page : result) {
			for (Post aPost : page)
				if (aPost.getMessage() != null) {
					listaPosts.add(aPost);
				}
		}
	
		for (Post post : listaPosts) {
	    	enviaPosts(post);
	    } 
		
	}
	
	/**
	 * 
	 * Método Like que recebe um post (o que está apresentado na
	 * Gui) e dá like no mesmo.
	 */
	
	public void like (Post p) {
		
		String postId = p.getId();
		
		DefaultFacebookClient client = new DefaultFacebookClient(accessToken);
		client.publish(postId+"/likes", Boolean.class); 
		
	}
	
	/**
	 * 
	 * Método partilhar (utilizado através de um actionListener da GUI
	 * que permitiria dar share do post que está em exibição
	 * através da instanciação do método publish da API utilizada.
	 */
	
	public void partilhar(Post p) {
		
		String postId = p.getId();
		
		DefaultFacebookClient client = new DefaultFacebookClient(accessToken);
		client.publish("me/feed", FacebookType.class, Parameter.with("link", postId));
		
	}
	
	/**
	 * 
	 * Implementação do método enviaPosts que recebe um Post e envia para a Gui
	 * o Id do Post e a data em que foi criado.
	 */
	
	private void enviaPosts(Post p) {
		gui.modelFacebook.addElement(p.getId() + " || " + p.getCreatedTime());
	}
	
	/**
	 * 
	 * Implementação de um método que recebe um indíce sob a forma de um inteiro, e
	 * devolve da lista de Posts, o Post correspondente ao índice.
	 */
	
	public Post getIndex(int index) {
		return listaPosts.get(index);
	}

	/**
	 * 
	 * Implementação de um método que recebe um indíce sob a forma de um inteiro, e
	 * imprime na Gui o post correspondente ao obtido no método getIndex(index).
	 */

	public void imprimeIndex(int index) {
		gui.postAtual.setText(getIndex(index).getMessage());
	}
	
}
