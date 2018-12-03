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
	
	
	
	
}
