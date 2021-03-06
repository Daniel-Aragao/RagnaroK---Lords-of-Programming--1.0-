package Gr�ficos.SideFrames.handPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.Border;

import Gr�ficos.SideFrames.HandFrame;
import entity.Carta;

public class ScrollingVCardPanel{
	JPanel panel;
	JScrollPane scrollPanel;
	
	
	public ScrollingVCardPanel(){
		panel = new JPanel();
		scrollPanel = new JScrollPane(panel);
		
		JScrollBar verticalScrollBar = scrollPanel.getVerticalScrollBar();
		verticalScrollBar.setBackground(new Color(62,28,100));
		
		scrollPanel.setBackground(new Color(0,0,0,0));
		
		scrollPanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		panel.setBackground(new Color(0,0,0,45));
		
		
		panel.setPreferredSize(new Dimension(HandFrame.WIDTH/2 -30,HandFrame.HEIGHT));
		
		panel.setLayout(new FlowLayout());
		
		//HandPanel.
		
		//panel.setPreferredSize();
		Border titled = BorderFactory.createTitledBorder("Informa��es: ");
		Border innerBorder = BorderFactory
				.createTitledBorder(titled, "Lista de Cartas:  ", 0, 0, null, Color.white);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		
		
	}
	
	public JScrollPane getScrollPanel(){ return this.scrollPanel;}
	
	public JPanel getPanel(){ return this.panel;}
	
	public void addCard(Carta c){
		panel.add(c);
		
		reCalcDimensions();		
	}
	public Carta removeCard(Carta c){
		panel.remove(c);
		
		reCalcDimensions();
		
		return c;		
	}
	public void reCalcDimensions(){

		int length = panel.getComponents().length;
		
		Dimension aux = panel.getPreferredSize();
		aux.height = ( ( (int) ( length + 1) / 2 ) * (Carta.DEFAULT_CARTA_HEIGHT)) + 60;
		panel.setPreferredSize(aux);
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		
	}
}
