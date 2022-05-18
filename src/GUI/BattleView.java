package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.OptionPaneUI;
import javax.xml.transform.Source;

import Images.sources;
import engine.City;
import engine.Game;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class BattleView  implements ActionListener{
	private JButton attack;
	private ArrayList<JRadioButton> buttons;
	private ArrayList<JRadioButton> defbuttons;
	private JLabel l1,l2;
	private Army army1;
	private Army army2;
	private JTextArea textArea;
	private JPanel defunits,units;
	private MyFrame fram;
	private sources imag;
	private ButtonGroup group1;
	private ButtonGroup group2;
	private Game game;
	private City city;
	private int height,width;
	public BattleView(Army playerArmy,Army defArmy,Game game)  {
		this.game = game;
		fram = new MyFrame();
		Dimension sizeOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
		height = sizeOfScreen.height;
		width = sizeOfScreen.width;
		fram.setLayout(null);
		fram.setTitle(game.getPlayer().inf()+ "        TurnCount :   "+game.getCurrentTurnCount());
		army1 = playerArmy;
		army2 = defArmy;
		imag = new sources();
		
		for(City city:game.getAvailableCities()) {
			if(city.getDefendingArmy()==defArmy) {
				this.city=city;
				break;
			}
		}
		
		sources source = new sources();
		Border border = BorderFactory.createLineBorder(Color.black,5);
		JLabel player = new JLabel();
		player.setBounds(150*width/1920,10*height/1080,300*width/1920,200*height/1080);
		player.setBorder(border);
		player.setText("Player Army");
		ImageResize resize = new ImageResize(300*width/1920,300*height/1080,source.getArmy()[2]);
		player.setIcon(new ImageIcon(resize.dimg));
		player.setFont(new Font(Font.SERIF,Font.PLAIN,30));
		player.setVerticalTextPosition(JLabel.CENTER);
		player.setHorizontalTextPosition(JLabel.CENTER);
	    player.setForeground(Color.RED);
		
		this.setTextArea1();
		fram.add(units);
		
		JLabel def = new JLabel();
		def.setText("Defending Army");
		def.setBounds(150*width/1920,220*height/1080,300*width/1920,200*height/1080);
		def.setBorder(border);
		ImageResize resize1 = new ImageResize(300*width/1920,300*height/1080,source.getArmy()[1]);
		def.setIcon(new ImageIcon(resize1.dimg));
		def.setFont(new Font(Font.SERIF,Font.PLAIN,30));
		def.setVerticalTextPosition(JLabel.CENTER);
		def.setHorizontalTextPosition(JLabel.CENTER);
	    def.setForeground(Color.RED);
		
		
		this.setTextArea2();
		fram.add(defunits);
		attack = new JButton();
		attack.setText("Attack");
		attack.addActionListener(this);
		attack.setBounds(1550*width/1920,60*height/1080,350*width/1920,300*height/1080);
		attack.setFont(new Font(Font.SERIF,Font.PLAIN,60));
		attack.setForeground(Color.RED);
		attack.setBackground(Color.blue);
		attack.setFocusable(false);
		
		
		textArea = new JTextArea();
	    textArea.setBackground(Color.BLACK);
	    textArea.setForeground(Color.LIGHT_GRAY);
	    textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 5));
	    System.setOut(new PrintStream(new OutputStream() {
	      @Override
	      public void write(int b) throws IOException {
	        textArea.append(String.valueOf((char) b));
	      }
	    }));
	    JScrollPane sp = new JScrollPane(textArea);
	    sp.setBounds(450*width/1920,500*height/1080,1100*width/1920,450*height/1080);
	    textArea.setFont(new Font(Font.SERIF,Font.PLAIN,40));
		textArea.setForeground(Color.RED);
	    
	    l1 = new JLabel();
	    l1.setBounds(100*width/1920,500*height/1080,300*width/1920,400*height/1080);
		l1.setBorder(border);
		l1.setText("player army unit");
		//ImageResize resize2 = new ImageResize(300,400,"game.jpg");
		//l1.setIcon(new ImageIcon(resize2.dimg));
		l1.setFont(new Font(Font.SERIF,Font.PLAIN,30));
		l1.setVerticalTextPosition(JLabel.CENTER);
		l1.setHorizontalTextPosition(JLabel.CENTER);
	    l1.setForeground(Color.RED);
	    
	    l2 = new JLabel();
	    l2.setBounds(1600*width/1920,500*height/1080,300*width/1920,400*height/1080);
		l2.setBorder(border);
	    l2.setText("Defending army unit");
		//ImageResize resize3 = new ImageResize(300,400,"game.jpg");
		//l2.setIcon(new ImageIcon(resize3.dimg));
		l2.setFont(new Font(Font.SERIF,Font.PLAIN,30));
		l2.setVerticalTextPosition(JLabel.CENTER);
		l2.setHorizontalTextPosition(JLabel.CENTER);
	    l2.setForeground(Color.RED);
	    
	    fram.getContentPane().setBackground(Color.gray);
	    fram.add(l1);
	    fram.add(l2);
	    fram.add(player);
	    //fram.add(units);
	    fram.add(attack);
	    fram.add(def);
	    //fram.add(defunits);
	    fram.add(sp);
	    fram.setVisible(true);
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
	public void setTextArea1() {
		units = new JPanel();
		units.setLayout(new FlowLayout());
		group1 = new ButtonGroup();
		buttons = new ArrayList<>();
		for(Unit i : army1.getUnits()) {
			JRadioButton button= new JRadioButton();
			button.setText(getUnit(i));
			button.setFont(new Font(Font.SERIF,Font.PLAIN,12));
			button.addActionListener(this);
			buttons.add(button);
			units.add(button);
			group1.add(button);
		}
		units.setBounds(500*width/1920,10*height/1080,1000*width/1920,200*height/1080);
	}
	public void setTextArea2() {
		defunits = new JPanel();
		defunits.setLayout(new FlowLayout());
		group2 = new ButtonGroup();
		defbuttons = new ArrayList<>();
		for(Unit i : army2.getUnits()) {
			JRadioButton button= new JRadioButton();
			button.setText(getUnit(i));
			button.setFont(new Font(Font.SERIF,Font.PLAIN,12));
			button.addActionListener(this);
			defbuttons.add(button);
			defunits.add(button);
			group2.add(button);
		}
		defunits.setBounds(500*width/1920,220*height/1080,1000*width/1920,200*height/1080);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<buttons.size();i++) {
			if(e.getSource()==buttons.get(i)) {
				if(army1.getUnits().get(i) instanceof Archer) {
					ImageResize resize2 = new ImageResize(300,400,imag.getArcher()[2]);
					l1.setIcon(new ImageIcon(resize2.dimg));
				}
				else if(army1.getUnits().get(i) instanceof Cavalry) {
					ImageResize resize2 = new ImageResize(300,400,imag.getCavalry()[3]);
					l1.setIcon(new ImageIcon(resize2.dimg));
				}
				else {
					ImageResize resize2 = new ImageResize(300,400,imag.getInfantary()[2]);
					l1.setIcon(new ImageIcon(resize2.dimg));	
				}
			}
		}
		for(int i=0;i<defbuttons.size();i++) {
			if(e.getSource()==defbuttons.get(i)) {
				if(army2.getUnits().get(i) instanceof Archer) {
					ImageResize resize2 = new ImageResize(300,400,imag.getArcher()[0]);
					l2.setIcon(new ImageIcon(resize2.dimg));
				}
				else if(army2.getUnits().get(i) instanceof Cavalry) {
					ImageResize resize2 = new ImageResize(300,400,imag.getCavalry()[4]);
					l2.setIcon(new ImageIcon(resize2.dimg));
				}
				else {
					ImageResize resize2 = new ImageResize(300,400,imag.getInfantary()[1]);
					l2.setIcon(new ImageIcon(resize2.dimg));	
				}
			}
		}
		
		if(e.getSource()==attack) {
			Unit i1 = null;
			Unit i2 = null;
			for(int i=0;i<buttons.size();i++) {
				if(buttons.get(i).isSelected()) {
					buttons.get(i).setSelected(false);
					i1 = army1.getUnits().get(i);
					break;
				}	
			}
			int indexof2 = 0;
			for(int i=0;i<defbuttons.size();i++) {
				if(defbuttons.get(i).isSelected()) {
					defbuttons.get(i).setSelected(false);
					i2 = army2.getUnits().get(i);
					indexof2 = i;
					break;
				}	
			}
			
			if(i1==null||i2==null) {
				textArea.append("Please Select One Unit from each Army  \n");
				return;
			}
			
		    if(i1 instanceof Infantry) {
		    	System.out.print("Infantry Unti is attacking ");
		    }
		    else if(i1 instanceof Cavalry) {
		    	System.out.print("Cavalry Unti is attacking ");
		    }
		    else {
		    	System.out.print("ArcherRange Unti is attacking ");
		    }
		    
		    if(i2 instanceof Infantry) {
		    	System.out.println("Infantry");
		    }
		    else if(i2 instanceof Cavalry) {
		    	System.out.println("Cavalry");	
		    }
		    else {
		    	System.out.println("ArchryRange");
		    }
		    int r1 = i2.getCurrentSoldierCount();
		    
		    try {
				i1.attack(i2);
			} catch (FriendlyFireException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//this.setTextArea1();
			//this.setTextArea2();
		    defbuttons.get(indexof2).setText(this.getUnit(i2));
		    if(i2.getCurrentSoldierCount()==0) {
		    	defbuttons.get(indexof2).setEnabled(false);
		    	defbuttons.remove(indexof2);
		    }
		    //group1.clearSelection();
		    //group2.clearSelection();
		    System.out.println("the attacked unit lost "+(r1-i2.getCurrentSoldierCount())+" Soldier");  
		    if(army2.getUnits().size()==0) {
		    	//game.getPlayer().getControlledCities().add(city);
		    	game.occupy(army1, army2.getCurrentLocation());
		    	endBattle end = new endBattle(true,game,fram);
		        return ;	
		    }
		    int index1 =0 ;
		    int index2 =0;
		    i2 = army1.getUnits().get(index1);
		    i1 = army2.getUnits().get(index2);
		    //buttons.get(index1).setSelected(true);
		    //defbuttons.get(index2).setSelected(true);
		    
		    System.out.println();
		    System.out.print(" The Computer Chooses ");
		    if(i1 instanceof Infantry) {
		    	System.out.print("Infantry Unti to attacke ");
		    }
		    else if(i1 instanceof Cavalry) {
		    	System.out.print("Cavalry Unti to attacke ");
		    }
		    else {
		    	System.out.print("ArcherRange Unti to attacke ");
		    }
		    
		    if(i2 instanceof Infantry) {
		    	System.out.println("Infantry");
		    }
		    else if(i2 instanceof Cavalry) {
		    	System.out.println("Cavalry");	
		    }
		    else {
		    	System.out.println("ArchryRange");
		    }
		    r1 = i2.getCurrentSoldierCount();
		    try {
				i1.attack(i2);
			} catch (FriendlyFireException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    buttons.get(index1).setText(this.getUnit(i2));
		    if(i2.getCurrentSoldierCount()==0) {
		    	buttons.get(index1).setEnabled(false);
		    	buttons.remove(index1);
		    }
		   // group1.clearSelection();
		    //group2.clearSelection();
		   
		    System.out.println("the attacked unit lost "+(r1-i2.getCurrentSoldierCount())+" Soldier");  
		    if(army1.getUnits().size()==0) {
		    	game.getPlayer().getControlledArmies().remove(army1);
		    	endBattle end = new endBattle(false,game,fram);
		        return ;	
		    }
		    System.out.println();
		}
	}
	public static void main(String[]arges) throws IOException {
        Game game = new Game("abdelrahman","Cairo");
		BattleView view = new BattleView(game.getAvailableCities().get(1).getDefendingArmy(),game.getAvailableCities().get(2).getDefendingArmy(),game);
	}
}
