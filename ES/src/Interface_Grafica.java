import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.restfb.exception.FacebookException;

import twitter4j.TwitterException;

import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JTextPane;

/**
 * Construtor da Interface Gr�fica com sistema de TabbedPane e com ScrollPane
 * para a sela��o de elementos respetivos a Tweets, Mails e posts de Facebook
 */
public class Interface_Grafica {

	private JFrame frame;
	private JPanel panel_0 = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public DefaultListModel<String> modelTwitter = new DefaultListModel<>();
	public DefaultListModel<String> modelMail = new DefaultListModel<>();
	public DefaultListModel<String> modelFacebook = new DefaultListModel<>();

	public JList<String> listaTweets = new JList<String>(modelTwitter);
	public JList<String> listaPosts = new JList<String>(modelFacebook);
	public JList<String> listaMails = new JList<String>(modelMail);

	private AppTwitter twitter;
	JTextPane tweet = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPaneTwitter = new JScrollPane();
	private int indiceTwitter;
	private JTextField pesquisaTwitter = new JTextField();

	private AppFacebook face;
	JTextPane postAtual = new JTextPane();
	private JScrollPane scrollPaneFace = new JScrollPane();
	private JScrollPane scrollPaneFacePost = new JScrollPane();
	private int indiceFace;
	private JTextField comentarioFacebook = new JTextField();

	private Mail mailApp;
	JTextPane mail = new JTextPane();
	private JScrollPane scrollPaneMails = new JScrollPane();
	private JScrollPane scrollPaneMailEspecifico = new JScrollPane();
	private int indiceMail;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_Grafica window = new Interface_Grafica();
					window.initialize();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * M�todo initialize que cria a JFrame, personaliza as TabPanes, cria os botoes
	 * de log in e em seguida inicializam as respetivas APIs
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		ImageIcon twitter = new ImageIcon(this.getClass().getResource("twitter.jpg"));
		twitter.setImage(getScaledImage(twitter.getImage(), 10, 10));
		ImageIcon facebook = new ImageIcon(this.getClass().getResource("facebook.png"));
		facebook.setImage(getScaledImage(facebook.getImage(), 10, 10));
		ImageIcon mailImagem = new ImageIcon(this.getClass().getResource("mail.png"));
		mailImagem.setImage(getScaledImage(mailImagem.getImage(), 10, 10));
		tabbedPane.addTab("Twitter", twitter, panel_0);
		tabbedPane.addTab("Facebook", facebook, panel_1);
		tabbedPane.addTab("E-Mail", mailImagem, panel_2);

		JButton logInTwitter = new JButton("Log In");
		GroupLayout gl_panel = new GroupLayout(panel_0);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(156, Short.MAX_VALUE)
						.addComponent(logInTwitter, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addGap(144)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(97)
						.addComponent(logInTwitter, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(101, Short.MAX_VALUE)));
		panel_0.setLayout(gl_panel);

		logInTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botaoLoginTwitter();
			}
		});

		JButton logInFace = new JButton("Log In");
		GroupLayout g2_panel = new GroupLayout(panel_1);
		g2_panel.setHorizontalGroup(g2_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				g2_panel.createSequentialGroup().addContainerGap(156, Short.MAX_VALUE)
						.addComponent(logInFace, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addGap(144)));
		g2_panel.setVerticalGroup(g2_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(g2_panel.createSequentialGroup().addGap(97)
						.addComponent(logInFace, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(101, Short.MAX_VALUE)));
		panel_1.setLayout(g2_panel);

		logInFace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botaoLoginFace();
			}
		});

		JButton logInMail = new JButton("Log In");
		GroupLayout g3_panel = new GroupLayout(panel_2);
		g3_panel.setHorizontalGroup(g3_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				g3_panel.createSequentialGroup().addContainerGap(156, Short.MAX_VALUE)
						.addComponent(logInMail, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addGap(144)));
		g3_panel.setVerticalGroup(g3_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(g3_panel.createSequentialGroup().addGap(97)
						.addComponent(logInMail, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(101, Short.MAX_VALUE)));
		panel_2.setLayout(g3_panel);

		logInMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botaoLoginMail();
			}
		});
	}

	/**
	 * Quando premido � executado o m�todo de log in que pede as credenciais e
	 * inicia uma nova AppTwitter, sendo esta uma Thread que corre de seguida o seu
	 * m�todo run(), executando em seguida o m�todo iniciaTwitter().
	 */

	public void botaoLoginTwitter() {
		String consumerKey = JOptionPane.showInputDialog("Please insert your Consumer Key");
		String consumerSecret = JOptionPane.showInputDialog("Please insert your Consumer Secret");
		String accessToken = JOptionPane.showInputDialog("Please insert your Access Token");
		String tokenSecret = JOptionPane.showInputDialog("Please insert your Token Secret");
		AppTwitter twitter = new AppTwitter(consumerKey, consumerSecret, accessToken, tokenSecret, this);
		this.twitter = twitter;
		twitter.run();
		iniciaTwitter();

	}

	/**
	 * Quando premido � executado o m�todo de log in que pede as credenciais e
	 * inicia um novo Facebook, sendo esta uma Thread que corre de seguida o seu
	 * m�todo run(), executando em seguida o m�todo iniciaFace().
	 */

	public void botaoLoginFace() {
		String accessToken = JOptionPane.showInputDialog("Please insert your Access Token");
		AppFacebook face = new AppFacebook(accessToken, this);
		this.face = face;
		face.run();
		iniciaFace();

	}

	/**
	 * Quando premido � executado o m�todo de log in que pede as credenciais e
	 * inicia um novo Mail, sendo esta uma Thread que corre de seguida o seu m�todo
	 * run(), executando em seguida o m�todo iniciaMail(), sendo que este fecha a
	 * conec��o com o servidor quando � fechada a aplica��o.
	 */

	public void botaoLoginMail() {
		String email = JOptionPane.showInputDialog("Please insert your Mail");
		String password = JOptionPane.showInputDialog("Please insert your Password");
		Mail mailNovo = new Mail(email, password, this);
		this.mailApp = mailNovo;
		mailApp.run();
		iniciaMail();
	}

	/**
	 * M�todo iniciaTwitter() que faz uma limpeza da JFrame e inicia a nova
	 * Interface do Twitter que apresenta uma lista de Posts, o Post que o
	 * utilizador pretende observar e que permite ao utilizador dar Fav ou Retweetar
	 * posts.
	 */

	private void iniciaTwitter() {

		panel_0.removeAll();
		panel_0.repaint();

		pesquisaTwitter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					twitter.pesquisa(pesquisaTwitter.getText());
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(listaTweets);
		scrollPaneTwitter.setViewportView(tweet);
		JButton likeTwitter = new JButton();
		ImageIcon twitterLikeImage = new ImageIcon(this.getClass().getResource("LikeTwitter.png"));
		twitterLikeImage.setImage(getScaledImage(twitterLikeImage.getImage(), 35, 35));
		likeTwitter.setIcon(twitterLikeImage);
		JButton retwitte = new JButton();
		ImageIcon retweetImage = new ImageIcon(this.getClass().getResource("Retweet.png"));
		retweetImage.setImage(getScaledImage(retweetImage.getImage(), 35, 35));
		retwitte.setIcon(retweetImage);
		GroupLayout gl_panel = new GroupLayout(panel_0);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(23)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 1184, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addComponent(pesquisaTwitter)
						.addComponent(scrollPaneTwitter, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addComponent(likeTwitter)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(retwitte)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addComponent(pesquisaTwitter)
								.addComponent(
										scrollPaneTwitter, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(likeTwitter)
										.addComponent(retwitte)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
				.addGap(286)));

		panel_0.setLayout(gl_panel);

		listaTweets.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					int index = listaTweets.locationToIndex(evt.getPoint());
					twitter.imprimeIndex(index);
					indiceTwitter = index;
				}
			}
		});

		retwitte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitter.retweet(twitter.getIndex(indiceTwitter));
			}
		});

		likeTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitter.fav(twitter.getIndex(indiceTwitter));
			}
		});
	}

	/**
	 * M�todo iniciaFace() que faz uma limpeza da JFrame e inicia a nova Interface
	 * do Facebook que apresenta uma lista de Posts, o Post que o utilizador
	 * pretende observar e que permite ao utilizador dar like ou partilhar posts.
	 */

	private void iniciaFace() {

		panel_1.removeAll();
		panel_1.repaint();
		scrollPaneFace.setViewportView(listaPosts);
		scrollPaneFacePost.setViewportView(postAtual);

		JButton likeFace = new JButton();
		ImageIcon likeFaceImagem = new ImageIcon(this.getClass().getResource("LikeFacebook.jpg"));
		likeFaceImagem.setImage(getScaledImage(likeFaceImagem.getImage(), 35, 35));
		likeFace.setIcon(likeFaceImagem);
		
		 JButton comentar = new JButton(); 
		 ImageIcon comentarFace = new ImageIcon(this.getClass().getResource("CommentFacebook.png"));
		 comentarFace.setImage(getScaledImage(comentarFace.getImage(), 35, 35));
		 comentar.setIcon(comentarFace);
		 

		 GroupLayout g2_panel = new GroupLayout(panel_1);
			g2_panel.setHorizontalGroup(g2_panel.createParallelGroup(Alignment.LEADING).addGroup(g2_panel
					.createSequentialGroup().addGap(23)
					.addComponent(scrollPaneFace, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 1184, Short.MAX_VALUE)
					.addGroup(g2_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(scrollPaneFacePost, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addGroup(g2_panel.createSequentialGroup().addComponent(likeFace)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comentar)))
					.addContainerGap()));
			g2_panel.setVerticalGroup(g2_panel.createParallelGroup(Alignment.LEADING).addGroup(g2_panel
					.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
					.addGroup(g2_panel.createParallelGroup(Alignment.LEADING).addGroup(g2_panel.createSequentialGroup()
							.addComponent(scrollPaneFacePost, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE).addGap(18)
							.addGroup(g2_panel.createParallelGroup(Alignment.BASELINE).addComponent(likeFace)
									.addComponent(comentar)))
							.addComponent(scrollPaneFace, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
					.addGap(286)));

		listaPosts.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					int index = listaPosts.locationToIndex(evt.getPoint());
					face.imprimeIndex(index);
					indiceFace = index;
				}
			}
		});

		/*comentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = JOptionPane.showInputDialog("Please insert your Comment");
				face.comment(texto, face.getIndex(indiceFace));				
			}
		}); */
		
		likeFace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				face.like(face.getIndex(indiceFace));
			}
		});

		panel_1.setLayout(g2_panel);
		panel_1.validate();

	}

	/**
	 * M�todo iniciaMail() que faz uma limpeza da JFrame e inicia a nova Interface
	 * do Mail que apresenta uma lista de e-mails recebidos, o e-mail que o
	 * utilizador pretende observar e que permite ao utilizador responder ou
	 * reencaminhar o e-mail selecionado.
	 */

	private void iniciaMail() {

		panel_2.removeAll();
		panel_2.repaint();

		scrollPaneMails.setViewportView(listaMails);
		scrollPaneMailEspecifico.setViewportView(mail);

		JButton responder = new JButton();
		ImageIcon mailResponderImagem = new ImageIcon(this.getClass().getResource("ResponderMail.png"));
		mailResponderImagem.setImage(getScaledImage(mailResponderImagem.getImage(), 35, 35));
		responder.setIcon(mailResponderImagem);
		JButton encaminhar = new JButton();
		ImageIcon encaminharImagem = new ImageIcon(this.getClass().getResource("EncaminharMail.png"));
		encaminharImagem.setImage(getScaledImage(encaminharImagem.getImage(), 35, 35));
		encaminhar.setIcon(encaminharImagem);

		GroupLayout g3_panel = new GroupLayout(panel_2);
		g3_panel.setHorizontalGroup(g3_panel.createParallelGroup(Alignment.LEADING).addGroup(g3_panel
				.createSequentialGroup().addGap(23)
				.addComponent(scrollPaneMails, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 1184, Short.MAX_VALUE)
				.addGroup(g3_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPaneMailEspecifico, GroupLayout.PREFERRED_SIZE, 212,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(g3_panel.createSequentialGroup().addComponent(responder)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(encaminhar)))
				.addContainerGap()));
		g3_panel.setVerticalGroup(g3_panel.createParallelGroup(Alignment.LEADING).addGroup(g3_panel
				.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
				.addGroup(g3_panel.createParallelGroup(Alignment.LEADING).addGroup(g3_panel.createSequentialGroup()
						.addComponent(
								scrollPaneMailEspecifico, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(g3_panel.createParallelGroup(Alignment.BASELINE).addComponent(responder)
								.addComponent(encaminhar)))
						.addComponent(scrollPaneMails, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
				.addGap(286)));

		listaMails.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					int index = listaMails.locationToIndex(evt.getPoint());
					indiceMail = index;
					try {
						mailApp.imprimeMail(index);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		responder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = JOptionPane.showInputDialog("Please insert your text");
				String[] divide;
				try {
					divide = mailApp.messages[indiceMail].getFrom()[0].toString().split("<");
					String[] to = divide[1].split(">");
					ReplyEmail responder = new ReplyEmail(mailApp.username, mailApp.password, to[0],
							mailApp.messages[indiceMail].getSubject(), texto);
					responder.run();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		encaminhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String destinatario = JOptionPane.showInputDialog("Please insert destination email");
				try {
					ReplyEmail responder = new ReplyEmail(mailApp.username, mailApp.password, destinatario,
							mailApp.messages[indiceMail].getSubject(),
							mailApp.devolveTexto(indiceMail));
					responder.run();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel_2.setLayout(g3_panel);
		panel_2.validate();

	}

	/**
	 * M�todo para reajustar o tamanho das imagens ao tamanho dispon�vel pela
	 * formata��o dos bot�es
	 */

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
}
