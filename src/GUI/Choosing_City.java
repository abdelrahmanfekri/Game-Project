package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import engine.City;

public class Choosing_City implements ActionListener{
    private String nameOfCity;
    private ArrayList<City> cities;
	public Choosing_City(ArrayList<City> cities) {
    	this.cities = cities;
		Dimension sizeOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int height = sizeOfScreen.height*3/2;
		int width = sizeOfScreen.width*3/2;
		JFrame fram;
		fram = new JFrame();
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setBounds(-10,-10,width,height);
		fram.setTitle("Game of Abdelrahman , Micheal and Mohened");
		fram.getContentPane().setBackground(Color.GREEN);
        ImageIcon image = new ImageIcon("game.jpg");
		fram.setIconImage(image.getImage());
		fram.setLayout(null);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("game.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(fram.getWidth(), fram.getHeight(),Image.SCALE_SMOOTH);
		JLabel imag = new JLabel(new ImageIcon(dimg));
		imag.setBounds(0,0,width,height);
		fram.add(imag);	
		JPanel panel = new JPanel();
    	//panel.setPreferredSize(new Dimension(500,200));
    	Border border = BorderFactory.createLineBorder(Color.BLACK);
    	panel.setBounds(500,200,500,200);
    	panel.setLayout(new FlowLayout());
    	panel.setBackground(Color.RED);
	    for(City x : cities) {
	    	JButton button = new JButton();
	    	button.setText(x.getName());
	    	ImageIcon imageIcon = new ImageIcon("game.jpg"); // load the image to a imageIcon
	    	Image image1 = imageIcon.getImage(); // transform it 
	    	Image newimg = image1.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	    	imageIcon = new ImageIcon(newimg);
	    	button.setIcon(imageIcon);
	    	button.setForeground(Color.blue);
			button.setFont(new Font("MV Boli",Font.PLAIN,20));
	    	button.setVerticalTextPosition(JLabel.BOTTOM);
	    	button.setHorizontalTextPosition( JLabel.CENTER);
	    	button.setBackground(Color.black);
	    	button.setOpaque(true);
	    	panel.add(button);
	    }
	    fram.add(panel);
	    fram.setVisible(true);
	}
    public static void main(String[] arges) {
    	
    	
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (!(source instanceof JButton))return ;
		
		String name = ((JButton) source).getName();
		for(City x:cities) {
			
		}
	}
}
