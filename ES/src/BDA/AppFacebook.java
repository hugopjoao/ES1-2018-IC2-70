package BDA;

import java.util.List;
import java.util.ArrayList;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.Thread;
import com.restfb.types.User;

public class AppFacebook extends Thread {

	private Interface_Grafica gui;
	private String accessToken;
	private String appId;
	private String appSecret;
	private FacebookClient facebookClient;
	public ArrayList<Post> listaPosts = new ArrayList<Post>();

	// Access token:
	// accessToken =
	// EAAMZCl2Ln2ZAkBABBhZCCMuq8Q3iKKsZBVNlZBpGnFcrNIPX8hnY9Rt8LRUCv93QyhwZA3iPOIMkZAud2g4w8RPaqgXZBN7HDDcvKzKC1hX7eOv1WmdiFGUFaOhuVaIhVpehZBGJqNnEWPvIqGdi7tnC9V2ZCpvwK2VXp6ZBurZBDXMEauwtP3d8YDRahcnCKZCUeRhtKzEGtbi3lF5eMWxkzF4J2jJq2d9PrEzm6sR1Iq9vGCgZDZD
	// */
	// appId = "914344362105241";
	// appSecret = "306f085aeadb59d88313c12779850a9b"

	/**
	 * 
	 * Construtor da App do Facebook que recebe como argumentos um AccessToken e
	 * fazendo a associa��o a uma dada Interface Gr�fica.
	 */

	public AppFacebook(String accessToken, String appId, String appSecret, Interface_Grafica gui) {
		this.accessToken = accessToken;
		this.appId = appId;
		this.appSecret = appSecret;
		this.gui = gui;
	}

	/**
	 * Implementa��o do m�todo run correspondente � Thread do Facebook que instancia
	 * um novo facebook com o Access Token recebido anteriormente. Em seguida criar�
	 * uma lista com os posts do feed do utilizador e os imprimir� na GUI. Caso n�o
	 * seja poss�vel obter acesso com o Token utilizado, apresentar� uma mensagem de
	 * erro.
	 */

	public void run() {

		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		User me = fbClient.fetchObject("me", User.class);

		AccessToken extendedAcessToken = fbClient.obtainExtendedAccessToken(appId, appSecret);

		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

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
	 * M�todo Like que recebe um post (o que est� apresentado na Gui) e d� like no
	 * mesmo.
	 */

	public void like(Post p) {

		String postId = p.getId();

		DefaultFacebookClient client = new DefaultFacebookClient(accessToken);
		client.publish(postId + "/likes", Boolean.class);

	}

	/**
	 * 
	 * M�todo partilhar (utilizado atrav�s de um actionListener da GUI que
	 * permitiria dar share do post que est� em exibi��o atrav�s da instancia��o do
	 * m�todo publish da API utilizada.
	 */

	public void partilhar(Post p) {

		String postId = p.getId();

		DefaultFacebookClient client = new DefaultFacebookClient(accessToken);
		client.publish("me/feed", FacebookType.class, Parameter.with("link", postId));

	}

	/**
	 * 
	 * Implementa��o do m�todo enviaPosts que recebe um Post e envia para a Gui o Id
	 * do Post e a data em que foi criado.
	 */

	private void enviaPosts(Post p) {
		gui.modelFacebook.addElement(p.getId() + " || " + p.getCreatedTime());
	}

	/**
	 * 
	 * Implementa��o de um m�todo que recebe um ind�ce sob a forma de um inteiro, e
	 * devolve da lista de Posts, o Post correspondente ao �ndice.
	 */

	public Post getIndex(int index) {
		return listaPosts.get(index);
	}

	/**
	 * 
	 * Implementa��o de um m�todo que recebe um ind�ce sob a forma de um inteiro, e
	 * imprime na Gui o post correspondente ao obtido no m�todo getIndex(index).
	 */

	public void imprimeIndex(int index) {
		gui.postAtual.setText(getIndex(index).getMessage());
	}

}
