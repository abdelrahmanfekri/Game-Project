package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.Position.Bias;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import engine.City;
import engine.Game;
import engine.Player;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Army;
import buildings.MilitaryBuilding;

public class New_Window implements ActionListener {
	JFrame frame=new JFrame();
	JTextArea text=new JTextArea();
	JPanel p;
	JButton Upgrade;
	JButton Recurit;
	Border border;
	Building building;
	Player player;
	City c;
	JLabel label;
	Game game;
	public New_Window(Game game,JLabel label,Player player,Building building,City city){
		this.game=game;
		this.label=label;
		this.c = city;
		this.player = player;
		this.building = building;
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setTitle("Information");
		frame.setLocationRelativeTo(null);

		int level = 0;
		int upgradecost=0;
		int recruitcost=0;
		if(building instanceof ArcheryRange || building instanceof Barracks || building instanceof Stable) {
			
			level=building.getLevel();
			upgradecost=building.getUpgradeCost();
			recruitcost=((MilitaryBuilding) building).getRecruitmentCost();
			Recurit=new JButton();
			Recurit.setSize(150, 70);
			Recurit.setText("Recurit");
			Recurit.setFont(new Font("Comis Sans",Font.BOLD,20));
			Recurit.setFocusable(false);
			Recurit.setBounds(175, 355, 120, 50);
			frame.add(Recurit);
			Recurit.addActionListener(this);
		
			
		
			text.setText(" Type : Military Building \n \n Level = "+ level + " \n \n UpgradeCost = "+ upgradecost +" \n \n RecruitmentCost = " + recruitcost );}
		
		if(building instanceof Market || building instanceof Farm ) {
			level=building.getLevel();
			upgradecost=building.getUpgradeCost();
			text.setText(" Type : Economic Building \n \n Level =" + level+ " \n \n UpgradeCost =" + upgradecost);
		}
			
			
			Upgrade=new JButton();
			Upgrade.setSize(150, 70);
			Upgrade.setText("Upgrade");
			Upgrade.setFont(new Font("Comis Sans",Font.BOLD,20));
			Upgrade.setFocusable(false);
			Upgrade.addActionListener(this);
		
			
			border=BorderFactory.createLineBorder(Color.RED , 10);
			text.setSize(150, 150);
			text.setFont(new Font("MV Boli",Font.BOLD,20));
			text.setBackground(Color.BLACK);
			text.setForeground(Color.yellow); 
			text.setOpaque(true);
			text.setBorder(border);
			text.setEditable(false);
			
			Upgrade.setBounds(175, 300, 120, 50);
			
			text.setBounds(40, 30, 400, 250);
			frame.add(text);
			frame.add(Upgrade);
			
			

	}
	public String inf() {
		return player.inf()+"     Number of turns : "+ game.getCurrentTurnCount();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Upgrade) {
			try {
				player.upgradeBuilding(building);
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Gold","Warning",JOptionPane.ERROR_MESSAGE);
	            return;		
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building in CoolDown","Warning",JOptionPane.ERROR_MESSAGE);
			    return;
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(null, "That is The maxLevel","Warning",JOptionPane.ERROR_MESSAGE);
			    return;
			}
			if(building instanceof MilitaryBuilding) text.setText(" Type : Military Building \n \n Level = "+ building.getLevel() + " \n \n UpgradeCost = "+ building.getUpgradeCost() +" \n \n RecruitmentCost = " + ((MilitaryBuilding)building).getRecruitmentCost());
			else text.setText(" Type : Economic Building \n \n Level = "+ building.getLevel() + " \n \n UpgradeCost = "+ building.getUpgradeCost() );
			label.setText(inf());
		}
		if(e.getSource()==Recurit) {
			try {
				if(building instanceof ArcheryRange)
						player.recruitUnit("Archer", c.getName());
				else if(building instanceof Barracks)
					player.recruitUnit("Infantry", c.getName());
				else player.recruitUnit("Cavalry", c.getName());
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building in CoolDown","Warning",JOptionPane.ERROR_MESSAGE);
			    return;
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(null, "This is Max Recruit","Warning",JOptionPane.ERROR_MESSAGE);
			    return;
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Gold","Warning",JOptionPane.ERROR_MESSAGE);
			    return;
			}
			text.setText(" Type : Military Building \n \n Level = "+ building.getLevel() + " \n \n UpgradeCost = "+ building.getUpgradeCost() +" \n \n RecruitmentCost = " + ((MilitaryBuilding)building).getRecruitmentCost());
			label.setText(inf());
		}
	
	}
}