import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
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

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import javax.swing.JTextPane;


public class Interface_Grafica {

	private JFrame frame;
	private JPanel panel_0 = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public DefaultListModel<String> modelTwitter= new DefaultListModel<>();
	public JList<String> listaTweets = new JList<String>(modelTwitter);
	public JList<String> listaPosts = new JList<String>();
	public JList<String> listaMails = new JList<String>();
	private AppTwitter twitter;
	JTextPane tweet = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPaneTwitter = new JScrollPane();
	private int indiceTwitter;
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
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(156, Short.MAX_VALUE)
					.addComponent(logInTwitter, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(144))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(97)
					.addComponent(logInTwitter, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		panel_0.setLayout(gl_panel);
		
		
		logInTwitter.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){  
		    	botaoLoginTwitter();
	    }  
	    });  
//		iniciaFace();
//		iniciaMail();
//		
		JButton logInMail = new JButton("Log In");
		GroupLayout g2_panel = new GroupLayout(panel_0);
		g2_panel.setHorizontalGroup(
			g2_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, g2_panel.createSequentialGroup()
					.addContainerGap(156, Short.MAX_VALUE)
					.addComponent(logInMail, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(144))
		);
		g2_panel.setVerticalGroup(
			g2_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(g2_panel.createSequentialGroup()
					.addGap(97)
					.addComponent(logInMail, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		panel_0.setLayout(g2_panel);
		
		
		logInMail.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){  
		    	botaoLoginTwitter();
	    }  
	    }); 
//		
//		
//		
//		
//		POR BOTOES PARA LOG IN
//		
//		
//		
//		
//		
//		
//		
//		
	}
	
	
	public void botaoLoginTwitter() {
		String consumerKey = JOptionPane.showInputDialog("Please insert your Consumer Key");
    	String consumerSecret = JOptionPane.showInputDialog("Please insert your Consumer Secret");
    	String accessToken = JOptionPane.showInputDialog("Please insert your Access Token");
    	String tokenSecret = JOptionPane.showInputDialog("Please insert your Token Secret");
    	AppTwitter twitter = new AppTwitter(consumerKey,consumerSecret,accessToken,tokenSecret, this);
    	this.twitter = twitter;
    	if(twitter.getValidation()) {
    		twitter.run();
    		iniciaTwitter();
    	}
	}
	

	private void iniciaTwitter() {
		
		panel_0.removeAll();
		panel_0.repaint();
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
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPaneTwitter, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addComponent(likeTwitter)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(retwitte)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(scrollPaneTwitter, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE).addGap(18)
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
		
		retwitte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  
			    	twitter.retweet(twitter.getIndex(indiceTwitter));
		    }  
		    }); 

		likeTwitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  
			    	twitter.fav(twitter.getIndex(indiceTwitter));
		    }  
		    });
	}

	private void iniciaFace() {
		
		panel_1.removeAll();
		panel_1.repaint();

		JTextPane post = new JTextPane();

		JButton likeFace = new JButton();
		ImageIcon likeFaceImagem = new ImageIcon(this.getClass().getResource("LikeFacebook.jpg"));
		likeFaceImagem.setImage(getScaledImage(likeFaceImagem.getImage(), 35, 35));
		likeFace.setIcon(likeFaceImagem);
		JButton partilhar = new JButton();
		ImageIcon parilharFace = new ImageIcon(this.getClass().getResource("PartilharFace.jpg"));
		parilharFace.setImage(getScaledImage(parilharFace.getImage(), 35, 35));
		partilhar.setIcon(parilharFace);

		GroupLayout g2_panel = new GroupLayout(panel_1);
		g2_panel.setHorizontalGroup(g2_panel.createParallelGroup(Alignment.LEADING).addGroup(g2_panel
				.createSequentialGroup().addGap(23)
				.addComponent(listaPosts, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 1184, Short.MAX_VALUE)
				.addGroup(g2_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(post, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(g2_panel.createSequentialGroup().addComponent(likeFace)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(partilhar)))
				.addContainerGap()));
		g2_panel.setVerticalGroup(g2_panel.createParallelGroup(Alignment.LEADING).addGroup(g2_panel
				.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
				.addGroup(g2_panel.createParallelGroup(Alignment.LEADING).addGroup(g2_panel.createSequentialGroup()
						.addComponent(post, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(g2_panel.createParallelGroup(Alignment.BASELINE).addComponent(likeFace)
								.addComponent(partilhar)))
						.addComponent(listaPosts, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
				.addGap(286)));
		panel_1.setLayout(g2_panel);
		panel_1.validate();

	}

	private void iniciaMail() {

		panel_2.removeAll();
		panel_2.repaint();

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
				.addComponent(listaMails, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 1184, Short.MAX_VALUE)
				.addGroup(g3_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(mail, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(g3_panel.createSequentialGroup().addComponent(responder)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(encaminhar)))
				.addContainerGap()));
		g3_panel.setVerticalGroup(g3_panel.createParallelGroup(Alignment.LEADING).addGroup(g3_panel
				.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
				.addGroup(g3_panel.createParallelGroup(Alignment.LEADING).addGroup(g3_panel.createSequentialGroup()
						.addComponent(mail, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(g3_panel.createParallelGroup(Alignment.BASELINE).addComponent(responder)
								.addComponent(encaminhar)))
						.addComponent(listaMails, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
				.addGap(286)));
		panel_2.setLayout(g3_panel);
		panel_2.validate();
		
//		responder.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){  
//			    	twitter.responder(twitter.getIndex(indiceMail));
//		    }  
//		    }); 
//
//		encaminhar.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){  
//			    	twitter.encaminhar(twitter.getIndex(indiceMail));
//		    }  
//		    });


	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
}
