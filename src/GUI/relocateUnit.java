package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.MaxCapacityException;
import units.Archer;
import units.Army;
import units.Unit;

public class relocateUnit implements ActionListener {
	JButton submit;
	Game game;
	Unit unit;
	JFrame frame=new JFrame();
	JLabel text=new JLabel();
	JLabel imagelabel=new JLabel();
	JPanel panel;
	Border border;
	ButtonGroup armies;
	ArrayList<JRadioButton> controlledarmies;// Cavalaries, Infantaries;
	ArrayList<Army> arc;
	ButtonGroup group1;
	boolean flag=false;
	JRadioButton but;
	
	public relocateUnit(Game game,Unit unit,JRadioButton b,String city) {
		this.game=game;
		this.unit=unit;
		but = b;
		
		
		border=BorderFactory.createLineBorder(Color.yellow , 2);
		submit=new JButton();
		submit.setText("Submit");
		submit.setFont(new Font("sansserif",Font.BOLD,30));
		submit.setFocusable(false);
		submit.addActionListener(this);
		submit.setBorder(border);
		submit.setBackground(Color.BLACK);
		submit.setForeground(Color.CYAN);
		submit.addActionListener(this);
		
		
		
		controlledarmies = new ArrayList<JRadioButton>();
		arc = new ArrayList<Army>();
		group1 = new ButtonGroup(); 
	    panel = new JPanel();
	    panel.setLayout(new FlowLayout());
	    panel.setPreferredSize(new Dimension(500,300));
	    int count =1;
		for (Army arr : game.getPlayer().getControlledArmies()) {
			if(arr.getCurrentLocation().equalsIgnoreCase(city)) {
			       JRadioButton button = new JRadioButton();
                   button.setText("Army"+(count++)+inf(arr));
                   button.setFont(new Font("sansserif",Font.BOLD,15));
                   controlledarmies.add(button);
                   arc.add(arr);
			}
		}
		for(JRadioButton i: controlledarmies) {
	    	panel.add(i);
	    	group1.add(i);
		}
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("This relocate of unit");
		frame.add(submit,BorderLayout.CENTER);
		frame.add(panel,BorderLayout.NORTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		//frame.add(imagelabel);
	}
	
		
		public static void main(String[]arges) throws IOException {
			//relocateUnit main = new relocateUnit(game,a);
		}

        public static String inf(Army ar) {
        	return " Location : "+ ar.getCurrentLocation() +" Num Of Units : "+ ar.getUnits().size()+" Status : "+ ar.getCurrentStatus();
        }
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==submit) {
				for(int i=0;i<controlledarmies.size();i++) {
					if(controlledarmies.get(i).isSelected()) {
						try {
							arc.get(i).relocateUnit(unit);
							but.setEnabled(false);
							frame.dispose();
						} catch (MaxCapacityException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "MaxCapacity Units in this army","Exceeption",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			
		}
		
		
	
}
