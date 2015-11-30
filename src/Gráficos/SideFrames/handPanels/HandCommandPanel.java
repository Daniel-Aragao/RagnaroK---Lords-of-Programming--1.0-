package Gráficos.SideFrames.handPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import entity.Carta;
import entity.Carta_Criatura;
import entity.Carta_Especial;
import entity.Carta_Magica;
import entity.Entity;

public class HandCommandPanel {
	private static Entity selected;
	public static Carta selected_hand;
	private JPanel panel;
	 
	private JButton passarVez;
	private JButton atacar;
	private JButton usarCarta;
	private static JButton ativarCarta;
	
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
		
		panel.setLayout(new GridLayout(3, 1, 5, 5));
		
		
		passarVez = new JButton("Fim de Turno");
		atacar = new JButton("Atacar");
		usarCarta = new JButton("Usar Carta");
		ativarCarta = new JButton("Ativar Carta ED/OO");
		
		buttonCustomization(passarVez);
		buttonCustomization(atacar);
		buttonCustomization(usarCarta);
		buttonCustomization(ativarCarta);
		
		atacar.setEnabled(false);
		passarVez.setEnabled(false);
		usarCarta.setEnabled(false);
		ativarCarta.setEnabled(false);
		
		passarVez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(jogador, "Tem certeza que deseja passar a vez?")==0){
					
					jogador.getCommandListener().passarVez(jogador);
					
					denableButtons();
					
					setSelected(null);
				}
				
			}
		});
		
		atacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Entity selec = selected;
				if(selec != null){
					if(selec.getSide() != jogador.getSide()){
						jogador.getCommandListener().atacar(jogador,selec);
						atacar.setEnabled(false);
						usarCarta.setEnabled(false);
					}else{
						JOptionPane.showMessageDialog(jogador, "Impossível atacar esse alvo");
					}
				}else{
					JOptionPane.showMessageDialog(jogador, "Selecione um Alvo");
				}
				setSelected(null);				
			}
		});
		
		usarCarta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(selected_hand != null){
					if(selected != null || selected_hand instanceof Carta_Especial){
						// remover carta da mão
						// adicionar carta no monstro selected
					}else{
						JOptionPane.showMessageDialog(jogador, "Selecione uma criatura");
					}
				}else{
					JOptionPane.showMessageDialog(jogador, "Selecione uma carta");
				}
				
			}
		});
		ativarCarta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(selected_hand != null){
					if(selected_hand instanceof Carta_Especial){
						//ativar ação no jogador.ED || jogador.OO
						
						setHandCardClicked(null);
					}
				}
				
			}
			
		});
		
		panel.add(atacar);
		panel.add(usarCarta);
		panel.add(ativarCarta);
		panel.add(passarVez);
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

	
	public void allowAtack(boolean letAtack) {
		this.atacar.setEnabled(letAtack);
		
	}
	
	public void buttonCustomization(JButton button){
		button.setForeground(Color.white);
		button.setBackground(new Color(62,28,100));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setFocusPainted(false);
	}

	public void allowEndTurn(boolean letEndTurn) {
		passarVez.setEnabled(letEndTurn);
		
	}
	public void denableButtons(){
		atacar.setEnabled(false);
		passarVez.setEnabled(false);
		usarCarta.setEnabled(false);
		ativarCarta.setEnabled(false);
	}
	
	public static void setSelected(Entity selected) {
		if(selected instanceof Carta_Criatura 
				|| selected instanceof Carta_Especial
				|| selected instanceof Jogador){
			
			if(selected instanceof Jogador){
				if(((Jogador)selected).isDefended()){
					return;
				}
			}
			if(!selected.getNome().toLowerCase().contains("piso")){
				if(selected != HandCommandPanel.selected && HandCommandPanel.selected!=null){
					HandCommandPanel.selected.setBorder(null);
					if(HandCommandPanel.selected instanceof Carta_Especial){
						ativarCarta.setEnabled(false);
					}
				}
				
				if(selected instanceof Carta_Especial){
					ativarCarta.setEnabled(true);
				}
				
				TitledBorder border = BorderFactory.createTitledBorder("SELECIONADO");
				border.setTitleColor(new Color(62,28,100));
				border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
				selected.setBorder(border);			
				HandCommandPanel.selected = selected;
				
			}
		}
		if(selected == null && HandCommandPanel.selected != null){
			HandCommandPanel.selected.setBorder(null);
			HandCommandPanel.selected = selected;
		}
	}

	
	public void setHandCardClicked(Carta c) {
		if(c != null){
			if(c != selected_hand){
				
				usarCarta.setEnabled(true);
				selected_hand = c;
				
			}
		}else if(selected_hand != null){
			selected_hand.setBorder(null);
			selected_hand = null;
		}
		
	}
	botão ativar enabled sem selecionar a carta, seleção não permite trocar de selecionado, carta selecionada sem
	ter a troca de borda ainda, corrigir urgente!!
}
