package Gráficos.SideFrames.SelectPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.plaf.ScrollBarUI;

import tabuleiro.Jogador;
import Gráficos.SideFrames.SelectFrame;
import entity.Carta;

public class ScrollingHCardPanel {
	private JPanel panel;
	
	private JScrollPane scrollPanel;
	
	private Jogador jogador;
	
	public ScrollingHCardPanel(Jogador jogador){
		panel = new JPanel();
		scrollPanel = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JScrollBar horizontalScrollBar = scrollPanel.getHorizontalScrollBar();
		horizontalScrollBar.setBackground(new Color(62,28,100));				
				
		scrollPanel.setBackground(new Color(0,0,0,0));
		
		scrollPanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		panel.setBackground(new Color(0,0,0,45));
		
		panel.setPreferredSize(new Dimension(SelectFrame.WIDTH*2/3 -30, SelectFrame.HEIGHT -32));
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT,10,-3);
		panel.setLayout(layout);
		
		
		//scrollPanel.
		
		//panel.setPreferredSize();
		Border titled = BorderFactory.createTitledBorder("Informações: ");
		Border innerBorder = BorderFactory
				.createTitledBorder(titled, "Lista de Cartas:  ", 0, 0, null, Color.white);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		this.jogador = jogador;
		
	}
	
	public void scrollBarCreation(){
		
	}

	public void addCard(Carta c){
		panel.add(c);
		
		
		reCalcDimensions();
		
		c.addCartaClickedListener(jogador.CLICKED_SELECT_HANDLER);
		
	}
	public Carta removeCard(Carta c){
		panel.remove(c);

		//c.addCartaClickedListener(null); não discomentar
		
		reCalcDimensions();
		
		return c;		
	}
	public void reCalcDimensions(){
		int length = panel.getComponents().length + 1;
		
		Dimension aux = panel.getPreferredSize();
		if(length > 3){
			aux.width = ( ( (int) length * (Carta.DEFAULT_CARTA_WIDTH)) + ((int) length*10) - 30);
		}else{
			aux.width = SelectFrame.WIDTH*2/3 -30;
		}
		panel.setPreferredSize(aux);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}
	
}
