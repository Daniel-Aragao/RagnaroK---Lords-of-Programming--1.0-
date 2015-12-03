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

import Util.ButtonCustomization;
import listeners.CommandListener;
import tabuleiro.Jogador;
import tabuleiro.LogPanel;
import tabuleiro.Turno;
import entity.Carta;
import entity.Carta_Criatura;
import entity.Carta_Especial;
import entity.Carta_Magica;
import entity.Entity;
import entity.cartas_de_topo.ED;
import entity.cartas_de_topo.OO;

public class HandCommandPanel {
	private Entity selected;
	private static Entity globalSelection;
	public static Carta selected_hand;
	private JPanel panel;
	 
	private JButton passarVez;
	private JButton atacar;
	private JButton usarCarta;
	private JButton ativarCarta;
	private JButton descartar;
	
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
		
		panel.setLayout(new GridLayout(3, 2, 5, 5));
		
		
		passarVez = new JButton("Fim de Turno");
		atacar = new JButton("Atacar");
		usarCarta = new JButton("Usar Carta");
		ativarCarta = new JButton("Ativar Carta ED/OO");
		descartar = new JButton("Descartar");
		
		ButtonCustomization.buttonCustomization(passarVez);
		ButtonCustomization.buttonCustomization(atacar);
		ButtonCustomization.buttonCustomization(usarCarta);
		ButtonCustomization.buttonCustomization(ativarCarta);
		ButtonCustomization.buttonCustomization(descartar);
		
		atacar.setEnabled(false);
		passarVez.setEnabled(false);
		usarCarta.setEnabled(false);
		ativarCarta.setEnabled(false);
		descartar.setEnabled(false);
		
		passarVez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = new String[2];
				options[0] = new String("Sim");
				options[1] = new String("Não");
				
				if (JOptionPane.showOptionDialog(passarVez,
						"Deseja terminar o turno?", "Fim de Turno",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, null) == 0) {
					
					jogador.getCommandListener().passarVez(jogador);
					
					denableButtons();
					Turno.setLetED_OO(false);
					setSelected(null);
					setHandCardClicked(null);
					setGlobalSelection(null);
				}
				
			}
		});
		
		atacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Entity selec = HandCommandPanel.getGlobalSelection();
				if(selec != null){
					if(selec.getSide() != jogador.getSide()){
						if(!(selec instanceof Jogador && ((Jogador)selec).isDefended())){
							jogador.getCommandListener().atacar(jogador,selec);
							atacar.setEnabled(false);
							usarCarta.setEnabled(false);
							Turno.setLetED_OO(true);
						}else{
							JOptionPane.showMessageDialog(atacar, "Este jogador está sendo defendido!");
						}
					}else{
						JOptionPane.showMessageDialog(atacar, "Impossível atacar esse alvo!");
					}
				}else{
					JOptionPane.showMessageDialog(atacar, "Selecione um Alvo!");
				}
				setSelected(null);				
			}
		});
		
		usarCarta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(selected_hand != null){
					if(selected != null || selected_hand instanceof Carta_Especial){
						if(selected_hand instanceof Carta_Magica){
							if(selected instanceof Carta_Criatura){
								
								jogador.getHand().getMainPanel().getCardPanel().removeCard(selected_hand);	
								((Carta_Criatura)selected).setMagica((Carta_Magica) selected_hand);
								usarCarta.setEnabled(false);
								descartar.setEnabled(false);
								
							}else{
								JOptionPane.showMessageDialog(usarCarta, "Alvo inválido!");
							}
						}
						
						if(selected_hand instanceof Carta_Especial){
							
							//add no ED ou no OO
							if(jogador.getCampo().addCarta(selected_hand)){
								jogador.getHand().getMainPanel().getCardPanel().removeCard(selected_hand);
								LogPanel.appendText(selected_hand.getNome()+" está em campo!");
							}
							
						}
						usarCarta.setEnabled(false);
						descartar.setEnabled(false);
						setHandCardClicked(null);
						
						
					}else{
						JOptionPane.showMessageDialog(usarCarta, "Selecione uma criatura");
					}
				}else{
					JOptionPane.showMessageDialog(usarCarta, "Selecione uma carta");
				}
				
			}
		}); 
		
//		perder ao acabar as cartas!!
		
//		perder...		
		
//		remover as cartas colcoadas automáticamente no jogador
		
		
		ativarCarta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(selected != null){
					//ativar ação no jogador.ED || jogador.OO
					LogPanel.appendText(selected.getNome() + " Ativado por "+jogador.getNome());
					
					if(selected instanceof ED){
						((ED)selected).ativarED(jogador.getBaralho(), jogador.getCemiterio());
						jogador.getCampo().RemoveCarta_Especial((ED)selected);
						
					}else if(selected instanceof OO){
						((OO)selected).ativarOO(jogador);
						jogador.getCampo().RemoveCarta_Especial((OO)selected);
					}
					setSelected(null);
					ativarCarta.setEnabled(false);
					
				}
				
			}
			
		});
		descartar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selected_hand != null){
					String[] options = new String[2];
					options[0] = new String("Sim");
					options[1] = new String("Não");
					
					if (JOptionPane.showOptionDialog(descartar,
							"Deseja enviar "+ selected_hand.getNome() +" para o cemitério?", "Descarte",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, null) == 0) {
						
						jogador.getHand().getMainPanel().getCardPanel().removeCard(selected_hand);
						jogador.getCemiterio().addCarta(selected_hand);
						
						setHandCardClicked(null);
						descartar.setEnabled(false);
						usarCarta.setEnabled(false);
					}
				}else{
					JOptionPane.showMessageDialog(atacar, "Selecione uma carta!");
				}
				
			}
		});
		
		panel.add(atacar);
		panel.add(usarCarta);
		panel.add(ativarCarta);
		panel.add(passarVez);
		panel.add(descartar);
	}
	public void attackButtonPressed(){
		
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

	public Entity getSelected() {
		return selected;
	}

	
	public void allowAtack(boolean letAtack) {
		this.atacar.setEnabled(letAtack);
	}
	

	public void allowEndTurn(boolean letEndTurn) {
		passarVez.setEnabled(letEndTurn);
		if(Turno.isLetAtack() && !this.atacar.isEnabled()){
			Turno.setLetED_OO(true);
		}
	}
	public void denableButtons(){
		atacar.setEnabled(false);
		passarVez.setEnabled(false);
		usarCarta.setEnabled(false);
		ativarCarta.setEnabled(false);
		descartar.setEnabled(false);
	}
	
	public void setSelected(Entity selected) {		
		if(selected_hand != null && selected == selected_hand ) return;
		if(selected instanceof Carta_Criatura 
				|| selected instanceof Carta_Especial 
				|| selected instanceof Jogador){
			
			if(selected.getNome().equals("ED"))return;
			if(selected.getNome().equals("OO"))return;
			
			if(!selected.getNome().toLowerCase().contains("piso")){
				if(selected != this.selected && this.selected!=null){
					this.selected.setBorder(null);
					if(this.selected instanceof Carta_Especial){
						ativarCarta.setEnabled(false);
					}
				}
				
				if(selected instanceof Carta_Especial  && Turno.isLetED_OO()){
					ativarCarta.setEnabled(true);
				}
				
				HandCommandPanel.setGlobalSelection(null);
				
				
				TitledBorder border = BorderFactory.createTitledBorder("SELECIONADO");
				border.setTitleColor(new Color(62,28,100));
				border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
				selected.setBorder(border);			
				this.selected = selected;
				
			}
		}
		if(selected == null && this.selected != null){
			this.selected.setBorder(null);
			this.selected = selected;
		}
	}

	
	public void setHandCardClicked(Carta c) {
		
		if(c != null){
			usarCarta.setEnabled(true);
			descartar.setEnabled(true);
			
			TitledBorder border = BorderFactory.createTitledBorder("SELECIONADO");
			border.setTitleColor(new Color(0,255,0));
			border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
			c.setBorder(border);
		}
		
		if(c != selected_hand && selected_hand != null){
			selected_hand.setBorder(null);
			
		}
		selected_hand = c;
		
		
	}
	public Carta getHandCardClicked(){
		return HandCommandPanel.selected_hand;
	}
	
	public static void setGlobalSelection(Entity e) {
		if(e instanceof Carta_Criatura || e instanceof Jogador){
			TitledBorder border = BorderFactory.createTitledBorder("SELECIONADO");
			border.setTitleColor(new Color(0,255,255));
			border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
			e.setBorder(border);			
			
		}		
		
		if(HandCommandPanel.globalSelection != null && HandCommandPanel.globalSelection != e){
			HandCommandPanel.globalSelection.setBorder(null);
		}		
		HandCommandPanel.globalSelection = e;
		
	}

	public static Entity getGlobalSelection() {
		return globalSelection;
	}


}
