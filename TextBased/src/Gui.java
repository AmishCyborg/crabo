import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame implements ActionListener {
	private int width;
	private int height;
	private JButton Start, HScore;
	private JFrame frame;

	Gui(int w, int h) {
		width = w;
		height = h;
		Start = new JButton("Start");
		Start.setBounds(160,100,100,25);
		Start.setFocusable(false);
		Start.addActionListener(this);
		HScore = new JButton("High Score");
		HScore.setBounds(160, 200, 100, 25);
		HScore.setFocusable(false);
		HScore.addActionListener(this);
		
		frame = new JFrame("Main Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(Start);
		frame.add(HScore);
		
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Start) {
			frame.dispose();
			GameFrame nW = new GameFrame(500,100);
			nW.nickWindow();
		}
		if(e.getSource()==HScore) {
			HighScore hScore= new HighScore();
		}
	}
	
	
}
