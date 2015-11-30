package Gráficos.SideFrames.SelectPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import tabuleiro.Jogador;
import Gráficos.SideFrames.HandFrame;
import Gráficos.SideFrames.SelectFrame;
import Util.BackgroundID;
import Util.Importar;

@SuppressWarnings("serial")
public class SelectPanel extends JPanel{
	
	private BufferedImage imagem;
	
	private ScrollingHCardPanel scrollPanel;	
	private SelectCommandPanel commandPanel;
	
	private Jogador jogador;
	
	public SelectPanel(Jogador jogador){
		imagem = Importar.getBackground(BackgroundID.selecionarCarta);
		
		this.jogador = jogador;
		
		scrollPanel = new ScrollingHCardPanel(jogador);
		commandPanel = new SelectCommandPanel(jogador);
		
		setLayout(new BorderLayout());
	
		Dimension selectPanelDimension = this.getPreferredSize();
		selectPanelDimension.width = SelectFrame.WIDTH*2/3;		
		
		scrollPanel.getScrollPanel().setPreferredSize(selectPanelDimension);
		
		add(scrollPanel.getScrollPanel(),BorderLayout.WEST);
		add(commandPanel.getPanel(),BorderLayout.CENTER);
	
	}	
	
	public ScrollingHCardPanel getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(ScrollingHCardPanel scrollPanel) {
		this.scrollPanel = scrollPanel;
	}

	public SelectCommandPanel getCommandPanel() {
		return commandPanel;
	}

	public void setCommandPanel(SelectCommandPanel commandPanel) {
		this.commandPanel = commandPanel;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();
		
		
		
		gr.clearRect(0, 0, HandFrame.WIDTH, HandFrame.HEIGHT);
		
		gr.drawImage(this.imagem, 0, 0,SelectFrame.WIDTH,SelectFrame.HEIGHT, null);
		
		
		gr.dispose();
		
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}
}
