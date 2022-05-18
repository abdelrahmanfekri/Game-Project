package engine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

public class City {
	private String name;
private ArrayList<EconomicBuilding> economicalBuildings;
private ArrayList<MilitaryBuilding> militaryBuildings;
private Army defendingArmy;
private int turnsUnderSiege;
private boolean underSiege;
	public City(String name) {
		this.name=name;
		economicalBuildings= new ArrayList<EconomicBuilding>();
		militaryBuildings= new ArrayList<MilitaryBuilding>();
		defendingArmy= new Army(name);
		turnsUnderSiege=-1;
	}
	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return economicalBuildings;
	}
	
	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return militaryBuildings;
	}
	
	public Army getDefendingArmy() {
		return defendingArmy;
	}
	//to be removed
	public void setDefendingArmy(Army defendingArmy)  {
		this.defendingArmy = defendingArmy;
	}
	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}
	public void setTurnsUnderSiege(int turnsUnderSiege) {
		this.turnsUnderSiege = turnsUnderSiege;
	}
	public String getName()
	{
		return this.name;
	}
	public boolean isUnderSiege() {
		return underSiege;
	}
	public void setUnderSiege(boolean underSiege) {
		this.underSiege = underSiege;
	}
	
    public JPanel show(int x,int y) {
    	JPanel panel = new JPanel();
    	panel.setPreferredSize(new Dimension(x,y));
    	Border border = BorderFactory.createLineBorder(Color.BLACK);
    	panel.setBorder(border);
    	JLabel label = new JLabel();
    	label.setText(name);
    	ImageIcon imageIcon = new ImageIcon("game.jpg"); // load the image to a imageIcon
    	Image image = imageIcon.getImage(); // transform it 
    	Image newimg = image.getScaledInstance(x*2/3, y*2/3,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon = new ImageIcon(newimg);
    	label.setIcon(imageIcon);
    	label.setForeground(Color.blue);
		label.setFont(new Font("MV Boli",Font.PLAIN,20));
    	label.setVerticalTextPosition(JLabel.BOTTOM);
    	label.setHorizontalTextPosition(JLabel.CENTER);
    	panel.add(label);
    	label.setBackground(Color.black);
    	label.setOpaque(true);
    	panel.setBackground(Color.RED);
    	return panel;
    }
    public static void main ( String[]arges) {
    	
    	City city = new City("Cairo");
    	JFrame fram = new JFrame();
    	fram.add(city.show(300, 300));
    	fram.setVisible(true);
    	fram.pack();
    }
}
