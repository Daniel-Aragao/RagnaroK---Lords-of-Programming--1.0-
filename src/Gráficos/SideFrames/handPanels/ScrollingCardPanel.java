package Gráficos.SideFrames.handPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.Border;

import Gráficos.SideFrames.HandFrame;
import entity.Carta;

@SuppressWarnings("serial")
public class ScrollingCardPanel{
	JPanel panel;
	JScrollPane scrollPanel;
	
	
	public ScrollingCardPanel(){
		panel = new JPanel();
		scrollPanel = new JScrollPane(panel);
		
		scrollPanel.setBackground(new Color(0,0,0,0));
		
		scrollPanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		panel.setBackground(new Color(0,0,0,45));
		
		
		panel.setPreferredSize(new Dimension(HandFrame.WIDTH/2 -30,HandFrame.HEIGHT));
		
		panel.setLayout(new FlowLayout());
		
		//HandPanel.
		
		//panel.setPreferredSize();
		Border titled = BorderFactory.createTitledBorder("Informações: ");
		Border innerBorder = BorderFactory
				.createTitledBorder(titled, "Lista de Cartas:  ", 0, 0, null, Color.white);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		
		
	}
	
	public JScrollPane getScrollPanel(){ return this.scrollPanel;}
	
	public JPanel getPanel(){ return this.panel;}
	
	public void addCard(Carta c){
		panel.add(c);
		int length = panel.getComponents().length;
		
			Dimension aux = panel.getPreferredSize();
			aux.height = ( ( (int) ( length + 1) / 2 ) * (Carta.DEFAULT_CARTA_HEIGHT)) + 60;
			panel.setPreferredSize(aux);
		
	}
	public Carta removeCard(Carta c){
		panel.remove(c);		
		return c;		
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		
	}
}
