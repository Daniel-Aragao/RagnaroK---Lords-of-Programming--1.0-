package Gráficos.SideFrames.Panels;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import Util.Position;
import entity.Carta;

@SuppressWarnings("serial")
public class ScrollingCardPanel{
	JPanel panel;
	JScrollPane scrollPanel;
	
	public ScrollingCardPanel(){
		panel = new JPanel();
		scrollPanel = new JScrollPane(panel);
		
		panel.setLayout(new FlowLayout());
		
		Border innerBorder = BorderFactory
				.createTitledBorder("Lista de Cartas: ");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		
	}
	
	public JScrollPane getScrollPanel(){ return this.scrollPanel;}
	
	public void addCard(Carta c){
		panel.add(c);
		c.setPosition(new Position(c.getLocation().x,c.getLocation().y));
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
