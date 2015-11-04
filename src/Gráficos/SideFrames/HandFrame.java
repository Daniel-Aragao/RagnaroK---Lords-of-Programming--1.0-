package Gráficos.SideFrames;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import tabuleiro.Jogador;
import Gráficos.SideFrames.Panels.ScrollingCardPanel;

public class HandFrame {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int SCALE = 1;
	
	JFrame frame;
	Jogador jogador;
	
	ScrollingCardPanel cardPanel;
	
	public HandFrame(Jogador jogador){
		this.jogador = jogador;
		createFrame();
	}

	private void createFrame() {
		frame = new JFrame("Mão");
		frame.setPreferredSize(new Dimension(HandFrame.WIDTH, HandFrame.HEIGHT));
		frame.setLocationRelativeTo(jogador.getTabuleiro());
		frame.setResizable(false);
		frame.setVisible(false);
		frame.setFocusable(false);
		
		
		frame.add(cardPanel.getScrollPanel());
		
		
	}
	public JFrame getFrame(){
		return this.frame;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		this.cardPanel.draw(g);
	}
	
}
