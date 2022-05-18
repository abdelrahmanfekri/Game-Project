package GUI;

import buildings.*;
import Images.*;
import engine.City;
import engine.Game;
import engine.Player;
import exceptions.NotEnoughGoldException;
import units.Army;
import units.Infantry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CityView implements ActionListener  {
	JButton Abutton; JButton Abutton2; JButton Bbutton; JButton Bbutton2; JButton Sbutton; JButton Sbutton2; JButton Fbutton; JButton Fbutton2;JButton Mbutton;JButton Mbutton2;JButton GoToDefendingArmyButton;
	JFrame frame;
	JPanel ArchaeryRange;JPanel Barracks;JPanel Stable;JPanel farm;JPanel market;JPanel defendingArmy;
	City c;
	Building[] arrBuilding;
	Player player;
	Game game;
	JLabel label;
	JButton endTurn;
	JButton playerArmy,intiate,Back;
	public CityView(Game g ,City city) {
		Dimension sizeOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int height = sizeOfScreen.height;
		int width = sizeOfScreen.width;
		this.player = g.getPlayer();
		game = g;
		this.c = city;
		boolean[] arr= new boolean[5];
		arrBuilding = new Building[5];
		for(Building i:city.getEconomicalBuildings()) {
			if(i instanceof Farm) {
				arr[3]=true;
				arrBuilding[3]=i;
			}
			if(i instanceof Market) {
				arr[4]=true;
				arrBuilding[4]=i;
			}
		}
		
		for(Building i:city.getMilitaryBuildings()) {
			if(i instanceof ArcheryRange) {
				arr[0]=true;
				arrBuilding[0]=i;
				
			}
			if(i instanceof Barracks) {
				arr[1]=true;
				arrBuilding[1]=i;
			
			}
			if(i instanceof Stable) {
				arr[2]=true;
				arrBuilding[2]=i;
			}
		}
		
		Border border=BorderFactory.createLineBorder(Color.yellow ,3);
		ArchaeryRange = new JPanel();
		ArchaeryRange.setBackground(Color.black);
		ArchaeryRange.setLayout(new BorderLayout());

		Barracks = new JPanel();
		Barracks.setBackground(Color.black);
		Barracks.setLayout(new BorderLayout());

		Stable = new JPanel();
		Stable.setBackground(Color.black);
		Stable.setLayout(new BorderLayout());
		
		farm = new JPanel();
		farm.setBackground(Color.black);
		farm.setLayout(new BorderLayout());
		
		market = new JPanel();
		market.setBackground(Color.black);
		market.setLayout(new BorderLayout());
		
		defendingArmy = new JPanel();
		defendingArmy.setBackground(Color.black);
		defendingArmy.setLayout(new BorderLayout());


		frame = new JFrame();
		frame.setResizable(true);
		frame.setBackground(Color.white);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		//frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Empire");
		frame.getContentPane().setBackground(Color.lightGray);
		frame.pack();
		//frame.setLayout(null);
       
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,4));
		Back=new JButton();
		Back.addActionListener(this);
		Back.setText("Back");
		Back.setFont(new Font("Comis Sans",Font.BOLD,10));
		Back.setBounds(0,0,60,40);
		Back.setFocusable(false);
		label = new JLabel();
		label.add(Back);
		label.setText(this.inf());
		label.setBorder(border);
		label.setBackground(Color.white);
		label.setOpaque(true);
		intiate = new JButton();
		intiate.setText("IntiateArmy");
		intiate.addActionListener(this);
		endTurn = new JButton();
		endTurn.addActionListener(this);
		endTurn.setText("EndTurn");
		endTurn.setFocusable(false);
		playerArmy = new JButton();
		playerArmy.addActionListener(this);
		playerArmy.setText("Player Army Located In "+ city.getName());
		playerArmy.setFocusable(false);
		panel.add(label);
		panel.add(intiate);
		panel.add(playerArmy);
		panel.add(endTurn);
		
		
		
	
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1,3));
		//north.setBounds(0,30,1536,372);
		north.add(ArchaeryRange);
		north.add(Barracks);
		north.add(Stable);
		//north.setPreferredSize(new Dimension(width,0));
		
		
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(1,3));
		//south.setPreferredSize(new Dimension(width,height/2));
		//south.setBounds(0,402,1536,400);
		south.add(market);
		south.add(farm);
		south.add(defendingArmy);
		
		
		// Images in panels
		
		
		sources source = new sources();
		ImageIcon imageArcher = new ImageIcon("src/Images/archerr.jpg");
		JLabel picLabel = new JLabel(imageArcher);
		ArchaeryRange.add(picLabel, BorderLayout.CENTER);

		ImageIcon imageBarracks = new ImageIcon("src/Images/Barracks (2).jpg");
		JLabel barrackslabel = new JLabel(imageBarracks);
		Barracks.add(barrackslabel, BorderLayout.CENTER);

		ImageIcon imageStable = new ImageIcon("src/Images/sstable.jpg");
		JLabel labelstable = new JLabel(imageStable);
		Stable.add(labelstable, BorderLayout.CENTER);
		
		
		ImageIcon imageFarm = new ImageIcon("src/Images/farmsss.jpg");
		JLabel farmlabel = new JLabel(imageFarm);
		farm.add(farmlabel, BorderLayout.CENTER);

		ImageIcon imageMarket = new ImageIcon("src/Images/gool.jpg");
		JLabel labelMarket = new JLabel(imageMarket);
		market.add(labelMarket, BorderLayout.CENTER);
		
		ImageIcon imageArmy = new ImageIcon("src/Images/army (1).jpg");
		JLabel labelarmy = new JLabel(imageArmy);
		
		defendingArmy.add(labelarmy, BorderLayout.CENTER);	

		
		Abutton=new JButton();
		Abutton.setText("Build ArcheryRange -- Cost = 1500  ");
		Abutton.setFont(new Font("Comis Sans",Font.BOLD,20));
		Abutton.setFocusable(false);
		Abutton2=new JButton();
		Abutton2.setText("Show Information");
		Abutton2.setFont(new Font("Comis Sans",Font.BOLD,20));
		Abutton2.setFocusable(false);
		Abutton.addActionListener(this);
		Abutton2.addActionListener(this);
		
		
		Bbutton=new JButton();
		Bbutton.setText("Build Barracks -- Cost = 2000");
		Bbutton.setFont(new Font("Comis Sans",Font.BOLD,20));
		Bbutton.setFocusable(false);
		Bbutton2=new JButton();
		Bbutton2.setText("Show Information");
		Bbutton2.setFont(new Font("Comis Sans",Font.BOLD,20));
		Bbutton2.setFocusable(false);
		Bbutton.addActionListener(this);
		Bbutton2.addActionListener(this);
		
		 
		Sbutton=new JButton();
		Sbutton.setText("Build Stable -- Cost = 2500");
		Sbutton.setFont(new Font("Comis Sans",Font.BOLD,20));
		Sbutton.setFocusable(false);
		Sbutton2=new JButton();
		Sbutton2.setText("Show Information");
		Sbutton2.setFont(new Font("Comis Sans",Font.BOLD,20));
		Sbutton2.setFocusable(false);
		Sbutton.addActionListener(this);
		Sbutton2.addActionListener(this);
		
		
		Fbutton=new JButton();
		Fbutton.setText("Build Farm -- Cost = 1000");
		Fbutton.setFont(new Font("Comis Sans",Font.BOLD,20));
		Fbutton.setFocusable(false);
		Fbutton2=new JButton();
		Fbutton2.setText("Show Information");
		Fbutton2.setFont(new Font("Comis Sans",Font.BOLD,20));
		Fbutton2.setFocusable(false);
		Fbutton.addActionListener(this);
		Fbutton2.addActionListener(this);
		
		 
		
		Mbutton=new JButton();
		Mbutton.setText("Build Market -- Cost = 1500 ");
		Mbutton.setFont(new Font("Comis Sans",Font.BOLD,20));
		Mbutton.setFocusable(false);
		Mbutton2=new JButton();
		Mbutton2.setText("Show Information");
		Mbutton2.setFont(new Font("Comis Sans",Font.BOLD,20));
		Mbutton2.setFocusable(false);
		Mbutton.addActionListener(this);
		Mbutton2.addActionListener(this);
		
		GoToDefendingArmyButton=new JButton();
		GoToDefendingArmyButton.setText("Defending Army");
		GoToDefendingArmyButton.setFont(new Font("Comis Sans",Font.BOLD,20));
		GoToDefendingArmyButton.setFocusable(false);
		GoToDefendingArmyButton.addActionListener(this);
		
		
		
		if(!arr[0])ArchaeryRange.add(Abutton, BorderLayout.SOUTH);
		else ArchaeryRange.add(Abutton2, BorderLayout.SOUTH);
		
		if (!arr[1])Barracks.add(Bbutton, BorderLayout.SOUTH);
		else Barracks.add(Bbutton2, BorderLayout.SOUTH);
		
		if(!arr[2])Stable.add(Sbutton, (BorderLayout.SOUTH));
		else Stable.add(Sbutton2, (BorderLayout.SOUTH));
		
		if(!arr[4])market.add(Mbutton, (BorderLayout.SOUTH));
		else market.add(Mbutton2, (BorderLayout.SOUTH));
		
		if(!arr[3])farm.add(Fbutton, (BorderLayout.SOUTH));
		else farm.add(Fbutton2, (BorderLayout.SOUTH));
		
		defendingArmy.add(GoToDefendingArmyButton, (BorderLayout.SOUTH));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(panel,BorderLayout.NORTH);
		frame.add(north, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Abutton) {
			try {
				String name = c.getName();
				player.build("archeryrange" , name);
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Gold","Warning",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Abutton.setVisible(false);
		    Building get = getBuilding("ArcheryRange",c);
			arrBuilding[0]=get;
			ArchaeryRange.add(Abutton2, BorderLayout.SOUTH);
			label.setText(this.inf());
		}
		
		if(e.getSource()==Abutton2) {
			New_Window mywindow= new New_Window(game,label,player,arrBuilding[0],c);
			label.setText(this.inf());
		}

		
		if(e.getSource()==Bbutton){
			try {
				player.build("barracks" , c.getName());
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Gold","Warning",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Bbutton.setVisible(false);
			Building get = getBuilding("Barracks",c);
			arrBuilding[1]=get;
			Barracks.add(Bbutton2, BorderLayout.SOUTH);
			label.setText(this.inf());
			}
		
		if(e.getSource()==Bbutton2) {
			New_Window mywindow= new New_Window(game,label,player,arrBuilding[1],c);
			label.setText(this.inf());
		}
		
		if(e.getSource()==Sbutton){
			try {
				player.build("stable" , c.getName());
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Gold","Warning",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Sbutton.setVisible(false);
			Building get = getBuilding("Stable",c);
			arrBuilding[2]=get;
			Stable.add(Sbutton2, BorderLayout.SOUTH);
			label.setText(this.inf());
			}
		
		if(e.getSource()==Sbutton2) {
			New_Window mywindow= new New_Window(game,label,player,arrBuilding[2],c);
			label.setText(this.inf());
		}
		
		if(e.getSource()==Fbutton){
			try {
				player.build("Farm" , c.getName());
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Gold","Warning",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Fbutton.setVisible(false);
			Building get = getBuilding("Farm",c);
			arrBuilding[3]=get;
			farm.add(Fbutton2, BorderLayout.SOUTH);
			label.setText(this.inf());
		}
		
		if(e.getSource()==Fbutton2) {
			New_Window mywindow= new New_Window(game,label,player,arrBuilding[3],c);
			label.setText(this.inf());
		}
		if(e.getSource()==Mbutton){
			try {
				player.build("Market" , c.getName());
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Gold","Warning",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Mbutton.setVisible(false);
			Building get = getBuilding("Market",c);
			arrBuilding[4]=get;
			market.add(Mbutton2, BorderLayout.SOUTH);
			label.setText(this.inf());
			}
		
		if(e.getSource()==Mbutton2) {
			New_Window mywindow= new New_Window(game,label,player,arrBuilding[4],c);
			label.setText(this.inf());
		}
		
		
		if(e.getSource()==endTurn) {
			game.setPast(frame);
			game.endTurn();
		   if(!game.isGameOver()) {
			  label.setText(this.inf());
		   }
		}
		if(e.getSource()==playerArmy) {
			ChooseArmy a = new ChooseArmy(game,c);
		}
		if(e.getSource()==GoToDefendingArmyButton) {
			ArmyView a = new ArmyView(game, c.getDefendingArmy(),c);
			frame.dispose();
		}
		if(e.getSource()==intiate) {
			intiateArmyView a = new intiateArmyView(game,c);
		}
		if(e.getSource()==Back) {
			Map map = new Map(game);
			frame.dispose();
		}
	}
	public String inf() {
		return player.inf()+"    Nturns : "+ game.getCurrentTurnCount();
	}
	public Building getBuilding(String type,City city) {
		if(type.equals("ArcheryRange")) {
			for(MilitaryBuilding i : city.getMilitaryBuildings()) {
				if(i instanceof ArcheryRange) return i;
			}
		}
		if(type.equals("Barracks")) {
			for(MilitaryBuilding i : city.getMilitaryBuildings()) {
				if(i instanceof Barracks) return i;
			}
		}
		
		if(type.equals("Stable")) {
			for(MilitaryBuilding i : city.getMilitaryBuildings()) {
				if(i instanceof Stable) return i;
			}
		}
		
		if(type.equals("Farm")) {
			for(EconomicBuilding i : city.getEconomicalBuildings()) {
				if(i instanceof Farm) return i;
			}
		}
		
		if(type.equals("Market")) {
			for(EconomicBuilding i : city.getEconomicalBuildings()) {
				if(i instanceof Market) return i;
			}
		}
		return null;
	}
	public static void main(String[]arges) throws IOException {
		City c=new City("cairo");
		
		Game game = new Game("Micheal","Cairo");
		CityView main = new CityView(game,game.getAvailableCities().get(0));
	}
}