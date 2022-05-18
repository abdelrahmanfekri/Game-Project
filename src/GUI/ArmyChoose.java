package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.City;
import engine.Game;
import engine.Player;
import units.Army;
import units.Status;

public class ArmyChoose implements ActionListener {
	Player player;
	JFrame frame,fram1;
	City c;
	Game game;
    ArrayList<Army> arm1,arm2;
    ArrayList<JButton> b1,b2;
	
	public ArmyChoose(Game game,boolean flag,JFrame frame1) {
		this.player=game.getPlayer();
		this.game=game;
		this.fram1=frame1;
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		int index1=1;
		int index2=1;
		arm1= new ArrayList<Army>();
		arm2= new ArrayList<Army>();
		b1= new ArrayList<JButton>();
		b2= new ArrayList<JButton>();
		for(Army arme:player.getControlledArmies()) {
			JButton button = new JButton();
			button.addActionListener(this);
			button.setFont(new Font(Font.SERIF,Font.PLAIN,60));
			button.setForeground(Color.RED);
			if(arme.getCurrentStatus()==Status.IDLE) {
			   button.setText("Army"+index1++);
			   arm1.add(arme);
			   if(flag) {
				   frame.add(button);
			   }
			   b1.add(button);
			}
			else { 
				button.setText("Army"+index2++);
				arm2.add(arme);
				if(!flag) {
					frame.add(button);
				}
				b2.add(button);
			}
		}
		JLabel label = new JLabel();
		
		String res = "Thera are no ";
		label.setFont(new Font(Font.SERIF,Font.PLAIN,60));
		label.setForeground(Color.RED);
		if(flag) {
			if(arm1.size()==0) {
				res += "Idle controlled army ";
				label.setText(res);
				frame.add(label);		
			}
		}
		else if(arm2.size()==0) {
			res += "marching nor besiegne controlled army";
			label.setText(res);
			frame.add(label);
		}
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
    public static void main(String[] args) throws IOException {
    	 Game game = new Game("abdelrahman","Cairo");
    	 Army a = new Army("Sparta");
    	 game.getPlayer().getControlledArmies().add(new Army("Spart"));
          ArmyChoose army = new ArmyChoose(game, true, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	      for(int i=0;i<b1.size();i++) {
	    	  if(e.getSource()==b1.get(i)) {
	    		  
	    		  frame.dispose();
	    		  fram1.dispose();
	    		  ArmyView a = new ArmyView(game,arm1.get(i),null);
	    	  }
	      }
	      for(int i=0;i<b2.size();i++) {
	    	  if(e.getSource()==b2.get(i)) {
	    		    
	    		    frame.dispose();
	    		    fram1.dispose();
	    		    ArmyView a = new ArmyView(game,arm2.get(i),null);
	    	  }
	      }
	}
}
