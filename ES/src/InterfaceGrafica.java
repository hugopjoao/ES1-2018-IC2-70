import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
 

public class InterfaceGrafica extends JFrame {
	
	private static int maxW = 400;
    private static int maxH = 300;
     
    public InterfaceGrafica() {
         
    	
    	
       final JFrame f = new JFrame();
       final JTabbedPane tabs = new JTabbedPane();
       JPanel tab1 = new JPanel();
       JPanel tab2 = new JPanel();
       JPanel tab3 = new JPanel();
       tab1.setPreferredSize( new Dimension(maxH,maxW));
       tab2.setPreferredSize( new Dimension(maxH,maxW));
       tab3.setPreferredSize( new Dimension(maxH,maxW));
       ImageIcon twitter = new ImageIcon (this.getClass().getResource("twitter.jpg"));
       twitter.setImage(getScaledImage(twitter.getImage(), 10,10));
       ImageIcon facebook = new ImageIcon (this.getClass().getResource("facebook.png"));
       facebook.setImage(getScaledImage(facebook.getImage(), 10,10));
       ImageIcon mail = new ImageIcon (this.getClass().getResource("mail.png"));
       mail.setImage(getScaledImage(mail.getImage(), 10,10));
       tabs.addTab("Twitter", twitter, tab1);
       tabs.addTab("Facebook", facebook, tab2);
       tabs.addTab("Email", mail, tab3);
       f.setContentPane(tabs);
       f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
       f.pack();
       f.setLocationRelativeTo(null);
       f.setVisible(true);


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
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
         
    }
    
}