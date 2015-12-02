package Gráficos.SideFrames.SelectPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tabuleiro.Jogador;
import tabuleiro.Turno;
import Gráficos.SideFrames.SelectFrame;
import Util.BackgroundID;
import Util.Importar;
import entity.Carta;
import entity.Carta_Criatura;
import entity.cartas_de_topo.Baralho;
import entity.cartas_de_topo.Campo;

public class SelectCommandPanel {
	
	private JPanel panel;
	
	private JButton ATQ;
	private JButton DEF;
	private JButton toMao;
	private JButton toBaralho;
	
	
	public static Carta selected;
	
	private Jogador jogador;
	
	public SelectCommandPanel(Jogador jogador){
		panel = new JPanel();
		
		this.jogador = jogador;
		
		ATQ = new JButton();   
		DEF = new JButton();   
		toMao = new JButton();   
		toBaralho = new JButton("Baralho");
		
		Image img = Importar.getBackground(BackgroundID.espada);
		ATQ.setIcon(new ImageIcon(img));
		img = Importar.getBackground(BackgroundID.escudo);
		DEF.setIcon(new ImageIcon(img));
		img = Importar.getBackground(BackgroundID.maoIco);
		toMao.setIcon(new ImageIcon(img));
		
		buttonCustomization(ATQ);
		buttonCustomization(DEF);
		buttonCustomization(toMao);
		buttonCustomization(toBaralho);
		
		Border titled = BorderFactory.createTitledBorder("");
		Border innerBorder = BorderFactory
				.createTitledBorder(titled, "Comandos: ", 0, 0, null, Color.white);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		panel.setBackground(new Color(0,0,0,100));
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,30,SelectFrame.HEIGHT/12 -10));
		
		ATQ.setEnabled(false);
		DEF.setEnabled(false);
		toMao.setEnabled(false);
		toBaralho.setEnabled(false);	
		
		setButtonsProperties();
				
		panel.add(toMao);
		panel.add(toBaralho);
		panel.add(ATQ);
		panel.add(DEF);
		
	}
	
	public void setButtonsProperties(){
		ATQ.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected == null) return;
				((Carta_Criatura)selected).setAtackMode(true);
				
				Campo campo = jogador.getCampo();
				campo.addCarta(selected);
				
				afterPull();
				
			}
		});
		
		DEF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected == null) return;
				((Carta_Criatura)selected).setAtackMode(false);
		
				Campo campo = jogador.getCampo();
				campo.addCarta(selected);
				
				afterPull();
				
			}
		});
		
		toBaralho.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected == null) return;
				Baralho baralho = jogador.getBaralho();
				
				baralho.addFim(selected);
				
				afterPull();
				
			}
		});
		
		toMao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected == null) return;
				jogador.addCartaMao(selected);
				
				afterPull();
				
			}
		});

		
	}
	public void afterPull(){
		removeFromCardPanel();
		denableOptions();
		setSelected(null);
		
		Turno.setLetAtack(true);
		jogador.allowAtack(true);
		
	}
	public void removeFromCardPanel(){
		jogador.getBaralho().getSelectPanel().getScrollPanel().removeCard(selected);
	}
	
	public void enableOptions(){
		denableOptions();
		if(selected instanceof Carta_Criatura){
				if(!jogador.getCampo().isFull()){
					ATQ.setEnabled(true);
					DEF.setEnabled(true);
				}
		}else{
				toMao.setEnabled(true);
		}
		
		toBaralho.setEnabled(true);
		
	}
	public void denableOptions(){
		ATQ.setEnabled(false);
		DEF.setEnabled(false);
		toMao.setEnabled(false);
		toBaralho.setEnabled(false);	
	}
	
	public void buttonCustomization(JButton button){
		button.setForeground(Color.white);
		button.setBackground(new Color(62,28,100));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setFocusPainted(false);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getATQ() {
		return ATQ;
	}

	public void setATQ(JButton aTQ) {
		ATQ = aTQ;
	}

	public JButton getDEF() {
		return DEF;
	}

	public void setDEF(JButton dEF) {
		DEF = dEF;
	}

	public JButton getToMao() {
		return toMao;
	}

	public void setToMao(JButton toMao) {
		this.toMao = toMao;
	}

	public JButton getToBaralho() {
		return toBaralho;
	}

	public void setToBaralho(JButton toBaralho) {
		this.toBaralho = toBaralho;
	}

	
	public void setSelected(Carta c) {
		if(c != null && selected != c){
			
			if(selected != null){
				selected.setBorder(null);
			}
			
			selected = c;
			
			enableOptions();
			
			
			TitledBorder border = BorderFactory.createTitledBorder("SELECIONADO");
			border.setTitleColor(new Color(62,0,0));
			border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
			selected.setBorder(border);	
			
			
		}else{
			if(selected != null){
				selected.setBorder(null);
			}
			selected = null;
		}
	}   
	
}
