package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import engine.City;
import engine.Game;
import engine.Player;
import units.Army;
import units.Status;

public class ChooseArmy{
	Player player;
	JFrame frame;
	public ChooseArmy(Game game,City city) {
		this.player=game.getPlayer();
	    int index =1;
	    frame = new JFrame();
	    Border  bourder = BorderFactory.createLineBorder(Color.black,5); 
		frame.setLayout(new GridLayout(game.getPlayer().getControlledArmies().size(),1,20,20));
		for(Army ar :game.getPlayer().getControlledArmies() ) {
			if(ar.getCurrentLocation().equalsIgnoreCase(city.getName())) {
				JLabel b = new JLabel();
				b.setText("Army"+(index++) +" "+ inf(ar));
				b.setFont(new Font(Font.SERIF,Font.PLAIN,20));
				b.setForeground(Color.RED);
				frame.add(b);
			}
		}
		if(index==1) {
			JLabel label= new JLabel();
			label.setText("There are No ControlledArmies in this City");
			label.setFont(new Font(Font.SERIF,Font.PLAIN,30));
			label.setForeground(Color.RED);
			frame.add(label);
		}
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	}
	public String inf( Army ar) {
		String res = "";
		res += "Status : " + ar.getCurrentStatus()
					+ " The target is :" + ar.getTarget()
					+ " CurrentLocation:  " + ar.getCurrentLocation()+ "  Number of Units: "+ ar.getUnits().size();

		return res;

	}
    public static void main(String[] args) throws IOException {
    	Game game = new Game("abdelrahman","Rome");
    	game.getPlayer().getControlledCities().add(new City("Sparta"));
    	game.getPlayer().getControlledArmies().add(new Army("Sparta"));
    	game.getPlayer().getControlledArmies().add(new Army("Sparta"));
    	ChooseArmy city = new ChooseArmy(game,game.getAvailableCities().get(1));
	}
}
