import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_Grafica window = new Interface_Grafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interface_Grafica() {
		initialize();
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
//		iniciaTwitter();
//		iniciaFace();
//		iniciaMail();
//		
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

	private void iniciaTwitter() {

		JList<String> listaTweets = new JList<String>();

		JTextPane tweet = new JTextPane();

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
				.addComponent(listaTweets, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 1184, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tweet, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addComponent(likeTwitter)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(retwitte)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(tweet, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(likeTwitter)
								.addComponent(retwitte)))
						.addComponent(listaTweets, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
				.addGap(286)));
		panel_0.setLayout(gl_panel);

	}

	private void iniciaFace() {

		JList<String> listaPosts = new JList<String>();

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

	}

	private void iniciaMail() {

		JList<String> listaMails = new JList<String>();

		JTextPane mail = new JTextPane();

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
