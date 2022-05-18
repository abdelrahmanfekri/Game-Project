package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public MyFrame() {
    	Dimension sizeOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int height = sizeOfScreen.height * 3 / 2;
		int width = sizeOfScreen.width * 3 / 2;
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBounds(-10, -10, width, height);
		super.setTitle("Game of Abdelrahman , Micheal and Mohened");
		super.getContentPane().setBackground(Color.white);
		
    }
	
}
