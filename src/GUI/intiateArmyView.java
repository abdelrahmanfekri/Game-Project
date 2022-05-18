package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import engine.City;
import engine.Game;
import engine.Player;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Status;
import units.Unit;
public class intiateArmyView implements ActionListener{
	JFrame frame;
	ArrayList<JRadioButton> arr;
	JButton submit;
	Game game;City city;
	public intiateArmyView(Game game,City city) {
		this.game = game;
		this.city= city;
	    frame = new JFrame();
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(city.getDefendingArmy().getUnits().size(),1,20,20));
	    ButtonGroup group = new ButtonGroup();
	    
	    arr = new ArrayList<>();
	    for(Unit i : city.getDefendingArmy().getUnits()) {
	    	JRadioButton b = new JRadioButton();
	    	b.setText(getUnit(i));
	    	group.add(b);
	    	panel.add(b);
	    	arr.add(b);
	    }
	    submit = new JButton();
	    submit.setText("Submit");
	    submit.addActionListener(this);
	    frame.add(panel,BorderLayout.CENTER);
	    frame.add(submit,BorderLayout.SOUTH);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	public String getUnit(Unit i) {
		String res="";
		if(i instanceof Archer) {
			res ="Archer";
		}
		else if(i instanceof Cavalry) {
			res = "Cavalry";			
		}
		else res = "Infantry";
		
		return res + " Level : "+ i.getLevel()+" CurrentSoldierCount : "+i.getCurrentSoldierCount();
	}
    public static void main(String[] args) throws IOException {
 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit) {
			for (int i = 0; i < arr.size(); i++) {
				if(arr.get(i).isSelected()) {
					game.getPlayer().initiateArmy(city, city.getDefendingArmy().getUnits().get(i));
					frame.dispose();
				}
			}
		}
		
	}
}
