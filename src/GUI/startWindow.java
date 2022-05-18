package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;

import Images.sources;
import engine.Game;

public class startWindow implements ActionListener{
    private JTextField playerName;
    private JRadioButton cairo;
    private JRadioButton sparta;
    private JRadioButton rome;
    private String cityName;
	private JButton start;
    private JFrame fram;
	public startWindow() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		//JFrame f = new JFrame();
		fram = new JFrame();
		Dimension sizeOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int height = sizeOfScreen.height;
		int width = sizeOfScreen.width;
		System.out.println(height);
		System.out.println(width);
		JPanel Panel = new JPanel();
		Panel.setBackground(Color.black);
		
		Panel.setLayout(new GridLayout(6,1));
		// Text View Label
	 	
	    sources s = new sources();
		JLabel textView = new JLabel();
		textView.setText("PLEASE ENTER YOUR NAME HERE!!!");
		textView.setForeground(Color.blue);
		textView.setFont(new Font("MV Boli",Font.PLAIN,20));
		Panel.add(textView);
		
		// End of Text View and Border
		
		//Text Edit and Button		
		
		playerName = new JTextField();
		playerName.setFont(new Font(Font.SERIF,Font.PLAIN,50));
		playerName.setForeground(Color.BLACK);
	    Panel.add(playerName);
		
		
		start = new JButton();
		start.setText("START THE GAME");
		start.setFocusable(false);
		start.setBackground(Color.RED);
		start.setSize(50,50);
	    start.addActionListener(this);
		// End
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//fram.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fram.setTitle("Game of Abdelrahman , Micheal and Mohened");
		fram.getContentPane().setBackground(Color.GREEN);
        ImageIcon image = new ImageIcon("game.jpg");
		fram.setIconImage(image.getImage());
		
		ImageResize res = new ImageResize(width,height,"game.jpg");
		JLabel imag = new JLabel();
		imag.setIcon(new ImageIcon(res.dimg));
		
		JLabel textView1 = new JLabel();
		textView1.setText("HERE SELECT ONE CITY!!!");
		textView1.setForeground(Color.blue);
		textView1.setFont(new Font("MV Boli",Font.PLAIN,20));
		Panel.add(textView1);
		
		ButtonGroup group = new ButtonGroup();
		cairo = new JRadioButton("Cairo");
		sparta = new JRadioButton("Sparta");
		rome = new JRadioButton("Rome");
		group.add(cairo);group.add(sparta);group.add(rome);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,3));
		p.add(cairo);
		p.add(sparta);
		p.add(rome);
		Panel.add(p);
		Panel.add(start);
		fram.setLayout(new GridBagLayout());
		fram.setExtendedState(JFrame.MAXIMIZED_BOTH);
		imag.setLayout(new GridBagLayout());
		imag.add(Panel);
		fram.add(imag);
		Adiuo ad = new Adiuo("src/Images/Muusic.wav",10000);
		//fram.pack();
		fram.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e)  {
		
		// TODO Auto-generated method stub
		if(e.getSource()==start) {
			if(cairo.isSelected()) {
				cityName = "Cairo";
			}
			else if(sparta.isSelected()) {
				cityName = "Sparta";
			}
			else if(rome.isSelected()) { 
				cityName = "Rome";
			}
			else {
				JOptionPane.showMessageDialog(null,"Please select one city","error in city selection",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(playerName.getText().length()<=0) {
				JOptionPane.showMessageDialog(null,"Please enter your name","ERROR IN NAME",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			Game game = null;
			try {
				game = new Game(playerName.getText(),cityName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Map map = new Map(game);
			fram.dispose();
		}
        
	}
    public static void main(String [] arges) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        startWindow start = new startWindow();
    }
}
