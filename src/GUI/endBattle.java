package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import engine.City;
import engine.Game;

public class endBattle implements ActionListener{
	Game game;
	JButton button;
	JFrame fram,bast;
	public endBattle(boolean f,Game game,JFrame bast) {
		this.bast=bast;
		this.game=game;
		fram = new JFrame();
	    fram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    fram.setLayout(new BorderLayout());
	    button= new JButton();
	    button.setText("Return To The Map");
	    button.setSize(200,200);
	    button.addActionListener(this);
	    JLabel label = new JLabel();
	    label.setSize(400,400);
	    if(f) {
	         label.setText("Congratulation you won the battle ");	
	         ImageResize imag = new ImageResize(300,300,"src/Images/smileIcon.png");
	         label.setIcon(new ImageIcon(imag.dimg));
	    }
	    else {
	    	label.setText("Sorry the lost the battle");
	    	ImageResize imag = new ImageResize(300,300,"src/Images/sad.jfif");
	        label.setIcon(new ImageIcon(imag.dimg));
	    }
	    label.setVerticalTextPosition(JLabel.BOTTOM);
	    label.setHorizontalTextPosition(JLabel.CENTER);
	    fram.add(label,BorderLayout.NORTH);
	    fram.add(button,BorderLayout.SOUTH);
	    fram.pack();
	    fram.setLocationRelativeTo(null);
	    fram.setVisible(true);
	}
	public static void main(String[]arges) throws IOException {
		//Game game = new Game("Abdelrahman","Cairo");
		//endBattle b = new endBattle(true,game);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==button) {			
			Map map = new Map(game);
			bast.dispose();
			fram.dispose();	
		}
	}

}
