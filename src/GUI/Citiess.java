package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.City;
import engine.Game;
import engine.Player;

public class Citiess implements ActionListener {
	Player player;
	JFrame frame,fram1;
	JLabel CairoArmy;
	JLabel RomaArmy;
	JLabel SpartaArmy;
	City c;
	Game game;
	City[] city;
	
	public Citiess(Game game) {
		this.player=game.getPlayer();
		this.game=game;
		frame = new JFrame();
		city = new  City[3];
	 	for(City c : player.getControlledCities()) {
	        
	 		frame.setLayout(new FlowLayout());
			if(c.getName().equalsIgnoreCase("cairo") ){
				CairoArmy = new JLabel();
				CairoArmy.setText("  Cairo  ");
				CairoArmy.setFont(new Font(Font.SERIF,Font.PLAIN,60));
				CairoArmy.setForeground(Color.RED);
				CairoArmy.setSize(100,100);
				city[0]=c;
				frame.add(CairoArmy);
			}
			if(c.getName().equalsIgnoreCase("rome")) {
				RomaArmy = new JLabel();
				RomaArmy.setText("  Rome  ");
				RomaArmy.setSize(100,100);
				RomaArmy.setFont(new Font(Font.SERIF,Font.PLAIN,60));
				RomaArmy.setForeground(Color.RED);
				frame.add(RomaArmy);
				city[1]=c;
			}
			if(c.getName().equalsIgnoreCase("sparta")){
				SpartaArmy = new JLabel();
				SpartaArmy.setText("  Sparta  ");
				SpartaArmy.setSize(100,100);
				SpartaArmy.setFont(new Font(Font.SERIF,Font.PLAIN,60));
				SpartaArmy.setForeground(Color.RED);
				frame.add(SpartaArmy);
				city[2]=c;
			}
				
		}
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	}
	
    public static void main(String[] args) throws IOException {
    	Game game = new Game("abdelrahman","Rome");
    	game.getPlayer().getControlledCities().add(new City("Sparta"));
    	Citiess city = new Citiess(game);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
}
