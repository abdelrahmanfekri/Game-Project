package GUI;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Images.sources;
import engine.City;
import engine.Game;
import units.Army;
import units.Status;
import GUI.ArmyChoose;

public class Map implements ActionListener{
	
	JButton attackS,attackC,attackR,laySiegeS,laySiegeR,laySiegeC,sparta,cairo,rome,controlledArmies,controlledCities,controlledArmies1;
	Army arm[];
	City city[];
	Game game;
	JFrame fram;
	JLabel label;
	JButton endTurn,Back;
	public Map(Game game) {
		this.game=game;
		JFrame fram = new JFrame();
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fram.setTitle("Game of Abdelrahman , Micheal and Mohened");
		fram.getContentPane().setBackground(Color.white);
		this.fram = fram;
		
		sources source = new sources();
		
		sparta = new JButton("Sparta");
	
		ImageResize imag = new ImageResize(650,630,source.getCity()[0]);
		
		sparta.setIcon(new ImageIcon(imag.dimg));
		sparta.setFocusable(false);
		sparta.setBackground(Color.RED);
	    sparta.addActionListener(this);
	    sparta.setVerticalTextPosition(JLabel.CENTER);
	    sparta.setHorizontalTextPosition(JLabel.CENTER);
	    sparta.setFont(new Font(Font.SERIF,Font.PLAIN,100));
		sparta.setForeground(Color.RED);
		if(!checkCity(game.getPlayer().getControlledCities(), "sparta")) {
			attackS = new JButton();
			laySiegeS = new JButton();
			attackS.setText("Attack");
			laySiegeS.setText("laySiege");
			attackS.addActionListener(this);
			laySiegeS.addActionListener(this);
			attackS.setSize(200,200);
			attackS.setFont(new Font(Font.SERIF,Font.PLAIN,30));
			laySiegeS.setFont(new Font(Font.SERIF,Font.PLAIN,30));
			laySiegeS.setSize(400,200);
			sparta.setLayout(new BorderLayout());
			sparta.setEnabled(false);
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(2,1));
			p.setPreferredSize(new Dimension(20,100));
			p.add(attackS);
			p.add(laySiegeS);
			sparta.add(p,BorderLayout.SOUTH);
		}
		
		cairo = new JButton("Cairo");
		imag = new ImageResize(650,630,source.getCity()[1]);
		cairo.setIcon(new ImageIcon(imag.dimg));
		cairo.setFocusable(false);
		cairo.setBackground(Color.RED);
	    cairo.addActionListener(this);
	    cairo.setVerticalTextPosition(JLabel.CENTER);
	    cairo.setHorizontalTextPosition(JLabel.CENTER);
	    cairo.setFont(new Font(Font.SERIF,Font.PLAIN,100));
		cairo.setForeground(Color.RED);
		if(!checkCity(game.getPlayer().getControlledCities(), "cairo")) {
			attackC = new JButton();
			laySiegeC = new JButton();
			attackC.setText("Attack");
			laySiegeC.setText("laySiege");
			attackC.addActionListener(this);
			laySiegeC.addActionListener(this);
			cairo.setLayout(new BorderLayout());
			attackC.setSize(200,200);
			laySiegeC.setSize(400,300);
			attackC.setFont(new Font(Font.SERIF,Font.PLAIN,30));
			laySiegeC.setFont(new Font(Font.SERIF,Font.PLAIN,30));
			cairo.setEnabled(false);
			cairo.setLayout(new BorderLayout());
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(2,1));
			p.setPreferredSize(new Dimension(20,100));
			p.add(attackC);
			p.add(laySiegeC);
			cairo.add(p,BorderLayout.SOUTH);
		}
		
		
		rome = new JButton("rome");
		imag = new ImageResize(650,630,source.getCity()[2]);
		rome.setIcon(new ImageIcon(imag.dimg));
		rome.setFocusable(false);
		rome.setBackground(Color.RED);
	    rome.addActionListener(this);
	    rome.setVerticalTextPosition(JLabel.CENTER);
	    rome.setHorizontalTextPosition(JLabel.CENTER);
	    rome.setFont(new Font(Font.SERIF,Font.PLAIN,100));
		rome.setForeground(Color.RED);
		if(!checkCity(game.getPlayer().getControlledCities(), "rome")) {
			rome.setEnabled(false);
			attackR= new JButton();
			laySiegeR = new JButton();
			attackR.setText("Attack");
			laySiegeR.setText("laySiege");
			attackR.addActionListener(this);
			laySiegeR.addActionListener(this);
			rome.setLayout(new BorderLayout());
			attackR.setSize(200,200);
			laySiegeR.setSize(400,400);
			attackR.setFont(new Font(Font.SERIF,Font.PLAIN,30));
			laySiegeR.setFont(new Font(Font.SERIF,Font.PLAIN,30));
			rome.setLayout(new BorderLayout());
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(2,1));
			p.setPreferredSize(new Dimension(20,100));
			p.add(attackR);
			p.add(laySiegeR);
			rome.add(p,BorderLayout.SOUTH);
		}
		
		Border border = BorderFactory.createLineBorder(Color.black,5);
	    controlledCities = new JButton("Player Contolled Cities");
		imag = new ImageResize(650,630,source.getCity()[3]);
		controlledCities.setIcon(new ImageIcon(imag.dimg));
		controlledCities.setBorder(border);
		controlledCities.setPreferredSize(new Dimension(50,530));
		controlledCities.setFocusable(false);
		controlledCities.setBackground(Color.RED);
	    controlledCities.addActionListener(this);
	    controlledCities.setVerticalTextPosition(JLabel.CENTER);
	    controlledCities.setHorizontalTextPosition(JLabel.CENTER);
	    controlledCities.setFont(new Font(Font.SERIF,Font.PLAIN,60));
		controlledCities.setForeground(Color.RED);
		
		controlledArmies = new JButton("Marching Or Besieging Armies");	
		controlledArmies.setSize(100,100);
		imag = new ImageResize(650,630,source.getArmy()[3]);
		controlledArmies.setIcon(new ImageIcon(imag.dimg));
		controlledArmies.setFocusable(false);
		controlledArmies.setBackground(Color.RED);
	    controlledArmies.addActionListener(this);
	    controlledArmies.setVerticalTextPosition(JLabel.CENTER);
	    controlledArmies.setHorizontalTextPosition(JLabel.CENTER);
	    controlledArmies.setFont(new Font(Font.SERIF,Font.PLAIN,55));
		controlledArmies.setForeground(Color.RED);
		
		controlledArmies1 = new JButton("Idle Armies");
		controlledArmies1.setFocusable(false);
		imag = new ImageResize(650,630,source.getArmy()[0]);
		controlledArmies1.setIcon(new ImageIcon(imag.dimg));
		controlledArmies1.setBackground(Color.RED);
	    controlledArmies1.addActionListener(this);
	    controlledArmies1.setVerticalTextPosition(JLabel.CENTER);
	    controlledArmies1.setHorizontalTextPosition(JLabel.CENTER);
	    controlledArmies1.setFont(new Font(Font.SERIF,Font.PLAIN,55));
		controlledArmies1.setForeground(Color.RED);
		
		JPanel panel1=new JPanel();
		panel1.setLayout(new GridLayout(1,3));
		label = new JLabel();
		label.setText(this.inf());
		label.setBorder(border);
		label.setBackground(Color.white);
		label.setOpaque(true);
		endTurn = new JButton();
		endTurn.addActionListener(this);
		endTurn.setText("EndTurn");
		endTurn.setFocusable(false);
		panel1.add(label);
		panel1.add(endTurn);
		
		JPanel panel = new JPanel();
		controlledArmies.setBorder(border);
		controlledArmies1.setBorder(border);
		controlledCities.setBorder(border);
		sparta.setBorder(border);
		rome.setBorder(border);
		cairo.setBorder(border);
		
		panel.setPreferredSize(new Dimension(1000,1000));
		panel.setLayout(new GridLayout(2,3,20,20));
		panel.setBackground(Color.green);
        panel.add(controlledArmies);
        panel.add(controlledArmies1);
        panel.add(controlledCities);
        panel.add(sparta);
        panel.add(rome);
        panel.add(cairo);
        panel1.setPreferredSize(new Dimension(15,50));
        fram.add(panel1,BorderLayout.NORTH);
        fram.add(panel,BorderLayout.CENTER);
        fram.setVisible(true);
        
        arm = new Army[3];
        city = new City[3];
		for(City i:game.getAvailableCities()) {
			if(i.getName().equalsIgnoreCase("Cairo")) {
				arm[0] = i.getDefendingArmy();
				city[0]=i;
			}
			if(i.getName().equalsIgnoreCase("Rome")) {
				arm[1] = i.getDefendingArmy();
				city[1]=i;
			}
			if(i.getName().equalsIgnoreCase("Sparta")) {
				arm[2] = i.getDefendingArmy();
				city[2]=i;
			}
		}
	}
    public boolean checkCity(ArrayList<City> cities,String name) {
    	for(City i:cities) {
    		if(i.getName().equalsIgnoreCase(name)) {
    			return true;
    		}
    	}
    	return false;
    }
	public static void main(String[] arges) throws IOException {
        Map m = new Map(new Game("abdelrahman","Cairo"));
	}
	public String inf() {
		return game.getPlayer().inf()+"     Number of turns : "+ game.getCurrentTurnCount();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==attackC) {
			boolean f = false;
			for(Army i:game.getPlayer().getControlledArmies()) {
				if(i.getCurrentLocation().equalsIgnoreCase("Cairo")) {
					BattleView battle = new BattleView(i,arm[0],game);
					f = true;
					fram.dispose();
					break;
				}
			}
			if(!f) {
				JOptionPane.showMessageDialog(null,"Please Set This City a Target To an Army ","error there are no army in this city ",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==laySiegeC) {
			boolean f = false;
			for(Army i:game.getPlayer().getControlledArmies()) {
				if(i.getCurrentLocation().equalsIgnoreCase("Cairo")) {
	                i.setCurrentStatus(Status.BESIEGING);
	                city[0].setUnderSiege(true);
	                laySiegeC.setEnabled(false);
					f = true;
				}
			}
			if(!f) {
				JOptionPane.showMessageDialog(null,"Please Set This City a Target To an Army ","error there are no army in this city ",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==attackS) {
			boolean f= false;
			for(Army i:game.getPlayer().getControlledArmies()) {
				if(i.getCurrentLocation().equalsIgnoreCase("Sparta")) {
					BattleView battle = new BattleView(i,arm[2],game);
					f= true;
					fram.dispose();
					break;
				}
			}
			if(!f) {
				JOptionPane.showMessageDialog(null,"Please Set This City a Target To an Army ","error there are no army in this city ",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==laySiegeS) {
			boolean f= false;
			for(Army i:game.getPlayer().getControlledArmies()) {
				if(i.getCurrentLocation().equalsIgnoreCase("Sparta")) {
					i.setCurrentStatus(Status.BESIEGING);
	                city[2].setUnderSiege(true);
	                laySiegeS.setEnabled(false);
					f = true;
				}
			}
			if(!f) {
				JOptionPane.showMessageDialog(null,"Please Set This City a Target To an Army ","error there are no army in this city ",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==attackR) {
			boolean f = false;
			for(Army i:game.getPlayer().getControlledArmies()) {
				if(i.getCurrentLocation().equalsIgnoreCase("Rome")) {
					BattleView battle = new BattleView(i,arm[1],game);
					f = true;
					fram.dispose();
					break;
				}
			}
			if(!f) {
				JOptionPane.showMessageDialog(null,"Please Set This City a Target To an Army ","error there are no army in this city ",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==laySiegeR) {
			boolean f = false;
			for(Army i:game.getPlayer().getControlledArmies()) {
				if(i.getCurrentLocation().equalsIgnoreCase("Sparta")) {
					i.setCurrentStatus(Status.BESIEGING);
	                city[1].setUnderSiege(true);
	                laySiegeR.setEnabled(false);
					f = true;
				}
			}
			if(!f) {
				JOptionPane.showMessageDialog(null,"Please Set This City a Target To an Army ","error there are no army in this city ",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==sparta) {
	    	CityView view = new CityView(game,city[2]);
	    	fram.dispose();
	    }
		else if(e.getSource()==cairo) {
	    	CityView view = new CityView(game,city[0]);
	    	fram.dispose();
	    }
		else if(e.getSource()==rome) {
	    	CityView view = new CityView(game,city[1]);
	    	fram.dispose();
	    }
		else if(e.getSource()==controlledCities) {
			Citiess city = new Citiess(game); 
		}
		else if(e.getSource()==endTurn) {
			game.setPast(fram);
			game.endTurn();
		   if(!game.isGameOver()) {
			  label.setText(this.inf());
			}
		}
		else if(e.getSource()==this.controlledArmies) {
			ArmyChoose armyView = new ArmyChoose(game,false,fram);
		}
		else if(e.getSource()==this.controlledArmies1) {
			ArmyChoose armyView = new ArmyChoose(game,true,fram);
		}
	}
}
