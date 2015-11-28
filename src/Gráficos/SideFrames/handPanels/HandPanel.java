package Gráficos.SideFrames.handPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.CommandListener;
import tabuleiro.Jogador;
import Gráficos.SideFrames.HandFrame;
import Util.BackgroundID;
import Util.Importar;

public class HandPanel extends JPanel{

	private BufferedImage imagem;
	

	private ScrollingCardPanel cardPanel;
	private HandEastPanel eastPanel;
	private HandCommandPanel commandPanel; 
	
	private CommandListener commandListener;
	
	
	public HandPanel(Jogador jogador){
		imagem = Importar.getBackground(BackgroundID.mao);
		
		
		setLayout(new BorderLayout());
		
		cardPanel = new ScrollingCardPanel();
		eastPanel = new HandEastPanel(jogador);
		
		
		Dimension cardPanelDimension = this.getPreferredSize();
		cardPanelDimension.width = HandFrame.WIDTH/2;		
		
		cardPanel.getScrollPanel().setPreferredSize(cardPanelDimension);
		//cardPanel.getPanel().setPreferredSize(cardPanelDimension);
		cardPanelDimension.width = HandFrame.WIDTH/2 -3;
		eastPanel.getPanel().setPreferredSize(cardPanelDimension);		
		
		
		add(cardPanel.getScrollPanel(),BorderLayout.WEST);
		add(eastPanel.getPanel(),BorderLayout.EAST);
		
		commandPanel = eastPanel.getCommandsPanel();
		
		
	}
	
	
	public ScrollingCardPanel getCardPanel() {
		return cardPanel;
	}


	public void setCardPanel(ScrollingCardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}


	public HandEastPanel getEastPanel() {
		return eastPanel;
	}
	
	public DescriptionPanel getDescriptionPanel(){
		return eastPanel.getDescriptionPanel();
	}

	public void setEastPanel(HandEastPanel eastPanel) {
		this.eastPanel = eastPanel;
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();
		
		
		
		gr.clearRect(0, 0, HandFrame.WIDTH, HandFrame.HEIGHT);
		
		gr.drawImage(this.imagem, 0, 0,HandFrame.WIDTH,HandFrame.HEIGHT, null);
		
		
		gr.dispose();
		
	}
	
	
	
}
