package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import engine.City;
import engine.Game;
import engine.Player;

public class GameOverWindow implements ActionListener {
	JButton ReturnToStart;
	Game game;
	Player player;
	JFrame frame;
	JLabel text;
	JLabel imagelabel;
	JPanel panel;
	Border border;
	JFrame past;
	public GameOverWindow(Game game,JFrame past) {
		this.game=game;
		this.player=game.getPlayer();
		this.past = past;
		
		frame = new JFrame();
		text = new JLabel();
		border=BorderFactory.createLineBorder(Color.yellow , 2);
		ReturnToStart=new JButton();
		ReturnToStart.setText("Return To Start View");
		ReturnToStart.setFont(new Font("sansserif",Font.BOLD,17));
		ReturnToStart.setFocusable(false);
		ReturnToStart.setBounds(140, 350, 200, 70);
		ReturnToStart.addActionListener(this);
		ReturnToStart.setBorder(border);
		ReturnToStart.setBackground(Color.BLACK);
		ReturnToStart.setForeground(Color.CYAN);
		
		
		text=new JLabel();
		text.setFont(new Font("MV Boli",Font.BOLD,20));
		text.setOpaque(true);
		
		ImageIcon imagelose = new ImageIcon("src/Images/lose5.jpeg");
		ImageIcon imagewin = new ImageIcon("src/Images/win3.jpg");
		
		imagelabel=new JLabel();
		imagelabel.setBounds(104, 70, 270, 270);
		
		
		if(player.getControlledCities().size()!=game.getAvailableCities().size()){
			text.setBounds(187, 30, 200, 30);
			text.setText("Game Over");
			imagelabel.setIcon(imagelose);
		}
		else {
			text.setBounds(105, 30, 315, 40);
			text.setText("You Win .. Congratulations ");
			imagelabel.setIcon(imagewin);
		}
	
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setTitle("This is the End of Game");
		frame.setLocationRelativeTo(null);
		frame.add(ReturnToStart);
		frame.add(text);
		frame.add(imagelabel);
	}
	
		
		public static void main(String[]arges) throws IOException {
			Game game = new Game("Micheal","Cairo");
			//GameOverWindow gamestart = new GameOverWindow(game);
			//startWindow window = new startWindow();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==ReturnToStart) {
				
				try {
					startWindow window = new startWindow();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				past.dispose();
			}
			
		}
		
	
}
