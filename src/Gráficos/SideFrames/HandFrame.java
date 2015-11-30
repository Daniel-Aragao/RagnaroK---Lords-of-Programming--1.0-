package Gráficos.SideFrames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Util.BackgroundID;
import Util.Importar;
import tabuleiro.Jogador;
import Gráficos.MainFrame;
import Gráficos.SideFrames.handPanels.HandPanel;
import Gráficos.SideFrames.handPanels.ScrollingVCardPanel;

public class HandFrame extends JFrame{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int SCALE = 1;
	
	
	Jogador jogador;
	
	public static final Dimension DEFAULT_HAND_DIMENSION = 
			new Dimension(HandFrame.WIDTH*HandFrame.SCALE, HandFrame.HEIGHT*HandFrame.SCALE); 
	
	private HandPanel handPanel;
	
	public HandFrame(Jogador jogador){
		this.jogador = jogador;
		handPanel = new HandPanel(jogador);
		createFrame();
		
	}

	

	private void createFrame() {
		this.setTitle("Mão "+jogador.getNome());
		this.setPreferredSize(HandFrame.DEFAULT_HAND_DIMENSION);
		this.setLocationRelativeTo(jogador);
		this.setResizable(false);
		this.setVisible(false);
		this.setFocusable(false);
		
		BufferedImage icon = Importar.getBackground(BackgroundID.icone);
		this.setIconImage(icon);
		
		
		//this.add(handPanel);
		this.setContentPane(handPanel);
		
		this.pack();
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
	}
	public JFrame getFrame(){
		return this;
	}
	public HandPanel getMainPanel(){
		return this.handPanel;
	}
	
	public void update(){
		
	}
	
	
	
	
}
