package Gráficos.SideFrames.handPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import listeners.CommandListener;
import tabuleiro.Jogador;
import Gráficos.SideFrames.HandFrame;

public class HandEastPanel {

	private JPanel panel;
	private DescriptionPanel descriptionPanel;
	

	private HandCommandPanel commandsPanel;
	private CommandListener commandListener;
	private Jogador jogador;

	public HandEastPanel(Jogador jogador){
		panel = new JPanel();
		
		this.jogador = jogador;
				
		panel.setBackground(new Color(0,0,0,0));
		panel.setLayout(new BorderLayout());
		
		descriptionPanel = new DescriptionPanel();
		commandsPanel = new HandCommandPanel(jogador);
		
		Dimension discrptDim = panel.getPreferredSize();
		discrptDim.height = HandFrame.HEIGHT/3;
		
		descriptionPanel.getScrollPanel().setPreferredSize(discrptDim);
		commandsPanel.getPanel().setPreferredSize(discrptDim);
		
		panel.add(descriptionPanel.getScrollPanel(), BorderLayout.CENTER);
		
		discrptDim.height = HandFrame.HEIGHT/3;
		panel.add(commandsPanel.getPanel(),BorderLayout.SOUTH);
		
		
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public DescriptionPanel getDescriptionPanel() {
		return descriptionPanel;
	}
	
	public HandCommandPanel getCommandsPanel() {
		return commandsPanel;
	}
}
