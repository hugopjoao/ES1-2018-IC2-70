import com.restfb.types.Thread;

public class Facebook extends Thread {

	private Interface_Grafica gui;
	private String accessToken;
	
	
	/* Access token:
	 EAAMZCl2Ln2ZAkBABIv1qIFGFXd93JrHkt9Qpwhlsx2MZA4UcIw2OJMOtqRSGMqluWdqvdgwfNEeTdnnb82tcqeh7Ns3WlKF1aHZBNEmoJv9zhfIdG97hTROZBhVGgnAl5z3JnvHZAjy14m79Iu9CYqwlOwktBAd6j394w5ISnJdtpSiqQZCfKWbYn1YjvAjZBNzWXeEoDFmMNYrzMyUQFxgWxlSPZCLth204qhKyrzVfjygZDZD */
	
	public Facebook (String accessToken, Interface_Grafica gui) {
		this.accessToken = accessToken;
		this.gui = gui;
	}
	
	/* Implementa��o do m�todo run correspondente � Thread do Facebook que instancia um novo facebook com o Access Token recebido
	 * anteriormente. Em seguida criar� uma lista com os posts do utilizador para que depois possa filtrar por posts relacionados com o ISCTE.
	 * Nesta filtragem, adiciona os posts relacionados com o ISCTE numa ArrayList, ser� organizado por ordem cronol�gica e impressa na GUI.
	 * Caso n�o seja poss�vel obter acesso com o Token utilizado, apresentar� uma mensagem de erro.
	 */
	
	public void run () {
		
	}
	
	
	public void Like () {
		
	}
	
	public void comentar() {
		
	}
	
}
