package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.FriendlyFireException;
import units.Army;

public class OptionAfterLaysige implements ActionListener {
	JButton attack,autoresolve;
	Game game;
	Player player;
	JFrame frame=new JFrame();
	JPanel panel;
	Border border;
	Army a,b;
	JFrame past;
	public OptionAfterLaysige(Game game,Army a,Army b,String s,JFrame f) {
		this.game=game;
		this.a=a;
		this.b=b;
		this.past =f;	
		border=BorderFactory.createLineBorder(Color.yellow , 2);
		attack=new JButton();
		attack.setText("Manual Attack");
		attack.setFont(new Font("sansserif",Font.BOLD,17));
		attack.setFocusable(false);
		attack.addActionListener(this);
		attack.setBorder(border);
		attack.setBackground(Color.BLACK);
		attack.setForeground(Color.CYAN);
		
		
		autoresolve=new JButton();
		autoresolve.setText("AutoResolve");
		autoresolve.setFont(new Font("sansserif",Font.BOLD,17));
		autoresolve.setFocusable(false);
		autoresolve.addActionListener(this);
		autoresolve.setBorder(border);
		autoresolve.setBackground(Color.BLACK);
		autoresolve.setForeground(Color.CYAN);
		
		
	
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(600, 100);
		frame.add(attack);
		frame.add(autoresolve);
		frame.setLayout(new GridLayout(1,2));
		frame.setTitle(s+ " is under siege for three turn you have to choose one option");
		//frame.pack();
		frame.setLocationRelativeTo(null);
		
	}
	
		
		public static void main(String[]arges) throws IOException {
			//Game g=new Game("michael","Cairo");
			
			//OptionAfterLaysige a = new OptionAfterLaysige(null,null,null,"kjsdk");
			
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==autoresolve) {
				try {
					game.autoResolve(a,b);
					if(a.getUnits().size()==0) {
						JOptionPane.showMessageDialog(null, "You lose the Battle ", "Losing the battle ", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Congratulations! You won the battle ", "Wonning the battle ", JOptionPane.INFORMATION_MESSAGE);
					}
					Map map = new Map(game);
					frame.dispose();
					past.dispose();
				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(null, "The two armies is Friendly armies", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == attack) {
				BattleView battle = new BattleView(a, b, game);
				frame.dispose();
				past.dispose();
			}
		}
		
		
	
}
