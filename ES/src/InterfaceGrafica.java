import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
 

public class InterfaceGrafica extends JFrame {
	
    static JTextArea textAreaTwitter = new JTextArea();
    static JTextArea textAreaFacebook = new JTextArea();
    static JTextArea textAreaMail = new JTextArea();
    
    public InterfaceGrafica() {
       final JFrame f = new JFrame();
       final JTabbedPane tabs = new JTabbedPane();
       JPanel tab1 = new JPanel();
       JPanel tab2 = new JPanel();
       JPanel tab3 = new JPanel();
       ImageIcon twitter = new ImageIcon (this.getClass().getResource("twitter.jpg"));
       twitter.setImage(getScaledImage(twitter.getImage(), 10,10));
       ImageIcon facebook = new ImageIcon (this.getClass().getResource("facebook.png"));
       facebook.setImage(getScaledImage(facebook.getImage(), 10,10));
       ImageIcon mail = new ImageIcon (this.getClass().getResource("mail.png"));
       mail.setImage(getScaledImage(mail.getImage(), 10,10));
       JScrollPane sT = new JScrollPane(textAreaTwitter); 
       JScrollPane sF = new JScrollPane(textAreaFacebook);
       JScrollPane sM = new JScrollPane(textAreaMail);
       tab1.add(sT);
       tab2.add(sF);
       tab3.add(sM);
       tabs.addTab("Twitter", twitter, tab1);
       tabs.addTab("Facebook", facebook, tab2);
       tabs.addTab("Email", mail, tab3);
       f.setContentPane(tabs);
       f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
       f.setLocationRelativeTo(null);
       f.setExtendedState(JFrame.MAXIMIZED_BOTH);
       f.setVisible(true);
   }
    
    public static void setTwitter(String define) {
    	textAreaTwitter.setText(define);        
    }
    
    private void setFacebook(String define) {
    	this.textAreaFacebook.setText(define);
    }
    
    private void setMail(String define) {
    	this.textAreaMail.setText(define);
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public static void main(String[] args) {         
        InterfaceGrafica gui = new InterfaceGrafica();
        AppTwitter twitter = new AppTwitter();
        twitter.run();
    }
    
}