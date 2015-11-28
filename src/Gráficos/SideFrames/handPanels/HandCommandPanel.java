package Gráficos.SideFrames.handPanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import listeners.CommandListener;
import tabuleiro.Jogador;
import Gráficos.SideFrames.HandFrame;
import entity.Carta_Criatura;
import entity.Entity;

public class HandCommandPanel {
	private static Entity selected;
	private JPanel panel;
	 
	private JButton passarVez;
	private JButton atacar;
	
	private CommandListener commandListener;
	private Jogador jogador;
	
	public HandCommandPanel(Jogador jogador){
		panel = new JPanel();
		
		this.jogador = jogador;
		
		Border titled = BorderFactory.createTitledBorder("");
		Border innerBorder = BorderFactory
				.createTitledBorder(titled, "Comandos: ", 0, 0, null, Color.white);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		panel.setBackground(new Color(0,0,0,100));
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,HandFrame.HEIGHT/12));
		
		
		passarVez = new JButton("Passar a Vez");
		atacar = new JButton("Atacar");
		
		passarVez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(jogador, "Tem certeza que deseja passar a vez?")==0){
					selected = null;
					jogador.getCommandListener().passarVez(jogador);
				}
				
			}
		});
		
		atacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Entity selec = selected;
				if(selec != null && selec.getSide() != jogador.getSide())
				jogador.getCommandListener().atacar(jogador,selec);
				
			}
		});
		
		panel.add(passarVez);
		panel.add(atacar);
		
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public CommandListener getCommandListener() {
		return commandListener;
	}

	public void setCommandListener(CommandListener commandListener) {
		this.commandListener = commandListener;
	}

	public static Entity getSelected() {
		return selected;
	}

	public static void setSelected(Entity selected) {
		if(selected instanceof Carta_Criatura || selected instanceof Jogador){
			if(!selected.getNome().toLowerCase().contains("piso")){
				if(selected != HandCommandPanel.selected && HandCommandPanel.selected!=null){
					HandCommandPanel.selected.setBorder(null);
				}
				
				TitledBorder border = BorderFactory.createTitledBorder("Selecionado");
				//border.setTitleColor(new Color(218, 160, 26));
				border.setTitleColor(new Color(218,0,0));
				selected.setBorder(border);			
				
				HandCommandPanel.selected = selected;
			}
		}
	}
	
	
	
}
