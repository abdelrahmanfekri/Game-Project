package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Images.sources;
import engine.City;
import engine.Game;
import engine.Player;
import exceptions.MaxCapacityException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class ArmyView extends JFrame implements ActionListener {

	Army ar;
	JComboBox targetbox;
	Game game;
	JButton reArcher, reInfantry, reCavalary;
	JLabel label1, label2, label3, label4, label5, label6;
	ArrayList<JRadioButton> Archers, Cavalaries, Infantaries;
	ArrayList<Unit> arc, cav, inf;
	JTextArea infotext;
	ButtonGroup group1, group2, group3;
	JLabel label;
	JButton endTurn,Back;
	JPanel panel1,panel2,panel3;
	boolean flag;
    City city;
	ArmyView(Game game, Army ar,City city) {
		this.city = city;
		this.ar = ar;
		this.game = game;
		this.setBackground(Color.black);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Empire");
		ImageIcon img = new ImageIcon("Army.jpg");
		this.setIconImage(img.getImage());
		this.getContentPane().setBackground(Color.lightGray);

		/// Panels
        sources source = new sources();
		JPanel Info = new JPanel();
		JPanel photos = new JPanel();
		JPanel ExtraInfo = new JPanel();

		Info.setLayout(new GridLayout(2, 1));
		photos.setLayout(new GridLayout(1, 3, 20, 20));
		ExtraInfo.setLayout(new GridLayout(1, 3, 20, 20));

		Info.setBackground(Color.black);
		photos.setBackground(Color.black);
		ExtraInfo.setBackground(Color.black);

		Info.setPreferredSize(new Dimension(10, 100));
		photos.setPreferredSize(new Dimension(800, 500));
		ExtraInfo.setPreferredSize(new Dimension(280, 380));

		// Organize Info Panel
		infotext = new JTextArea();
		infotext.setBackground(Color.yellow);
		infotext.setLayout(new BorderLayout());

	
		infotext.setText(information(game, ar));

		infotext.setFont(new Font("Comic Sans", Font.BOLD, 25));

		// Frist panel in Info Panel

		ArrayList<String> cities = new ArrayList<String>();
		if (!ar.getCurrentLocation().equalsIgnoreCase("Cairo")) {
			cities.add("Cairo");
		}
		if (!ar.getCurrentLocation().equalsIgnoreCase("Sparta")) {
			cities.add("Sparta");
		}
		if (!ar.getCurrentLocation().equalsIgnoreCase("Rome")) {
			cities.add("Rome");
		}
		targetbox = new JComboBox(cities.toArray());
		targetbox.addActionListener(this);
		targetbox.setLayout(new FlowLayout());
		JLabel t = new JLabel();
		t.setText("setTarget");
		t.setFont(new Font("MV Boli",Font.PLAIN,20));
		targetbox.add(t);
		
		targetbox.setBounds(1500, 0, 350, 40);
		targetbox.setFont(new Font("Comic Sans", Font.BOLD, 25));
		targetbox.addActionListener(this);

		ImageIcon imga = new ImageIcon("src/Images/archerarmy.jpeg");
		ImageIcon imgag = new ImageIcon(source.getArmy()[2]);
		ImageIcon mgag = new ImageIcon("src/Images/cavalryarmy.jpeg");
		JLabel picLabel = new JLabel();
		picLabel.setText("Archer");
		picLabel.setIcon(imga);
		picLabel.setVerticalTextPosition(JLabel.CENTER);
		picLabel.setHorizontalAlignment(JLabel.CENTER);
		photos.add(picLabel);

		JLabel picLabe = new JLabel();
		picLabe.setText("Cavalry");
		picLabe.setIcon(imgag);
		picLabe.setVerticalTextPosition(JLabel.CENTER);
		picLabe.setHorizontalAlignment(JLabel.CENTER);
		photos.add(picLabe);

		JLabel pice = new JLabel();
		pice.setText("Infantry");
		pice.setIcon(mgag);
		pice.setVerticalTextPosition(JLabel.CENTER);
		pice.setHorizontalAlignment(JLabel.CENTER);
		photos.add(pice);

		// relocate Buttons

		// add items to the photo panel
		// Last panel
		arc = new ArrayList<Unit>();
		cav = new ArrayList<Unit>();
		inf = new ArrayList<Unit>();
		Archers = new ArrayList<JRadioButton>();
		Cavalaries = new ArrayList<JRadioButton>();
		Infantaries = new ArrayList<JRadioButton>();

		reArcher = new JButton();
		reCavalary = new JButton();
		reInfantry = new JButton();
		reArcher.setText("ReLocateUnit Archer");
		reCavalary.setText("ReLocateUnit Cavalry");
		reInfantry.setText("ReLocateUnit Infantry");

		reArcher.setFont(new Font("Comic Sans", Font.BOLD, 15));
		reCavalary.setFont(new Font("Comic Sans", Font.BOLD, 15));
		reInfantry.setFont(new Font("Comic Sans", Font.BOLD, 15));

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		this.Infounit(ar);

		group1 = new ButtonGroup();
		group2 = new ButtonGroup();
		group3 = new ButtonGroup();

		for (JRadioButton i : Archers) {
			panel1.add(i);
			group1.add(i);
		}
		for (JRadioButton i : Cavalaries) {
			panel2.add(i);
			group2.add(i);
		}
		for (JRadioButton i : Infantaries) {
			panel3.add(i);
			group3.add(i);
		}
		Border border = BorderFactory.createLineBorder(Color.black,5);
		JPanel pan1=new JPanel();
		pan1.setLayout(new GridLayout(1,3));
		Back=new JButton();
		Back.addActionListener(this);
		Back.setText("Back");
		Back.setFont(new Font("Comis Sans",Font.BOLD,10));
		Back.setBounds(0,0,60,55);
		Back.setFocusable(false);
		label = new JLabel();
		label.add(Back);
		label.setText(this.inf());
		label.setBorder(border);
		label.setBackground(Color.white);
		label.setOpaque(true);
		endTurn = new JButton();
		endTurn.addActionListener(this);
		endTurn.setText("EndTurn");
		endTurn.setFocusable(false);
		pan1.add(label);
		//pan1.add(endTurn);
		Info.add(pan1);
		Info.add(infotext);
		targetbox.setToolTipText("setTarget");
		if(city==null)pan1.add(targetbox);

//		label7.setText(res[1]);
//		label8.setText(res[2]);
//		label9.setText(res[3]);
		panel1.setPreferredSize(new Dimension(500, 330));
		panel2.setPreferredSize(new Dimension(500, 330));
		panel3.setPreferredSize(new Dimension(500, 330));
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();

		reArcher.addActionListener(this);
		reCavalary.addActionListener(this);
		reInfantry.addActionListener(this);

		panel4.setLayout(new BorderLayout());
		panel5.setLayout(new BorderLayout());
		panel6.setLayout(new BorderLayout());

		panel4.add(panel1, BorderLayout.NORTH);
		panel5.add(panel2, BorderLayout.NORTH);
		panel6.add(panel3, BorderLayout.NORTH);

		panel4.add(reArcher, BorderLayout.CENTER);
		panel5.add(reCavalary, BorderLayout.CENTER);
		panel6.add(reInfantry, BorderLayout.CENTER);

		ExtraInfo.add(panel4);
		ExtraInfo.add(panel5);
		ExtraInfo.add(panel6);
        if(city==null) {
        	reArcher.setEnabled(false);
        	reCavalary.setEnabled(false);
        	reInfantry.setEnabled(false);
        }
		this.add(Info, BorderLayout.NORTH);
		this.add(ExtraInfo, BorderLayout.SOUTH);
		this.add(photos, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void Infounit(Army ar) {
		if (ar == null)
			throw new NullPointerException("Please enter Valid Army");

		for (Unit u : ar.getUnits()) {
			if (u instanceof Archer) {
				JRadioButton button = new JRadioButton();
				button.setText("Archer Level: " + u.getLevel() + " CurrentSoldierCount " + u.getCurrentSoldierCount()+" maxsoldierCount :"+u.getMaxSoldierCount());
				Archers.add(button);
				arc.add(u);
			}
			if (u instanceof Infantry) {
				JRadioButton button = new JRadioButton();
				button.setText(
						"Infantry Level: " + u.getLevel() + " CurrentSoldierCount " + u.getCurrentSoldierCount()+" maxsoldierCount :"+u.getMaxSoldierCount());
				Infantaries.add(button);
				inf.add(u);
			}
			if (u instanceof Cavalry) {
				JRadioButton button = new JRadioButton();
				button.setText("Cavalry Level: " + u.getLevel() + " CurrentSoldierCount " + u.getCurrentSoldierCount()+ " maxsoldierCount :"+u.getMaxSoldierCount());
				Cavalaries.add(button);
				cav.add(u);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == reArcher) {
			for (int i = 0; i < arc.size(); i++) {
				if (Archers.get(i).isSelected()) {
					//this.dispose();
					relocateUnit u = new relocateUnit(game, arc.get(i),Archers.get(i),city.getName());
				}
			}
		}
		if (e.getSource() == reCavalary) {
			for (int i = 0; i < cav.size(); i++) {
				if (Cavalaries.get(i).isSelected()) {
					//this.dispose();
					relocateUnit u = new relocateUnit(game, cav.get(i),Cavalaries.get(i),city.getName());
				}
			}
		}
		if (e.getSource() == reInfantry) {
			for (int i = 0; i < inf.size(); i++) {
				if (Infantaries.get(i).isSelected()) {
					//this.dispose();
					relocateUnit u = new relocateUnit(game, inf.get(i),Infantaries.get(i),city.getName());
				}
				
			}
		}
		if (e.getSource() == targetbox) {
			// System.out.println((String)targetbox.getSelectedItem());
			game.targetCity(ar,(String) targetbox.getSelectedItem());
			this.infotext.setText(information(game, ar));
		}
		if(e.getSource()==Back) {
			if(city != null) {
				CityView cityView = new CityView(game,city);
				this.dispose();
			}
			else {
				Map map = new Map(game);
				this.dispose();
			}
		}

	}

	public String information(Game game, Army ar) {
		Player player = game.getPlayer();
		String res = "";
		if (ar.getCurrentStatus() == Status.IDLE) {
			res += "Status : " + ar.getCurrentStatus()
					+ "                    The target is : " + ar.getTarget()
					+ "                    CurrLocation:  " + ar.getCurrentLocation()+ "                    Number of Units: "+ ar.getUnits().size();

		} else {
			if (ar.getCurrentStatus() == Status.MARCHING) {
				res += "Status :       " + ar.getCurrentStatus()
						+ "                    The target is: "
						+ ar.getTarget() + "                    CurrLocation:  "
						+ ar.getCurrentLocation()+"                    The Turns until reaching : "+ar.getDistancetoTarget()+"                    Number of Units: "+ ar.getUnits().size();

			}
			if (ar.getCurrentStatus() == Status.BESIEGING) {
				res += "Status : " + ar.getCurrentStatus()
						+ "                    The target is: "
						+ ar.getTarget() + " beseiging city is " + ar.getCurrentLocation()
						+ "                    The Turns until reaching : " + getCity(ar, game)+"                     Number of Units: "+ ar.getUnits().size();

			}

		}
		return res;

	}

	public static int getCity(Army ar, Game game) {
		for (City i : game.getAvailableCities()) {
			if (ar.getTarget().equalsIgnoreCase(i.getName())) {
				return 3 - i.getTurnsUnderSiege();
			}
		}
		return -1;
	}
	public String inf() {
		return game.getPlayer().inf()+"     Number of turns : "+ game.getCurrentTurnCount();
	}
	public static void main(String[] aregs) throws IOException {
		Game game = new Game("Mohaned", "Sparta");
		// System.out.println(game.getAvailableCities().get(2).getDefendingArmy().getCurrentLocation());
		ArmyView army = new ArmyView(game, game.getAvailableCities().get(2).getDefendingArmy(),game.getAvailableCities().get(1));
		System.out.println(game.getAvailableCities().get(2).getDefendingArmy().getUnits().size());
	}
}
