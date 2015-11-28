package state;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

import tabuleiro.Tabuleiro;
import Game.Game;

public class GameState extends State{

	int Round;
	Tabuleiro tabuleiro; 
	Graphics g;
	JFrame mFrame;
	Game game;
	
	public GameState(Game game) {
		this.game = game;
		Round = 0;
		mFrame = game.getFrame().getFrame();
//		mFrame.add(new JLabel("11111111111111111111111111111111111111111"), BorderLayout.SOUTH);
		
		//mFrame.addMouseListener(new Mouse());
		
		tabuleiro = new Tabuleiro();
		mFrame.setContentPane(tabuleiro);

	
		System.out.println("reach game state constructor last line");
	}

	

	@Override
	public void update() {
		//tabuleiro.update();
	}



	@Override
	public void draw(Graphics g) {
		tabuleiro.draw(g);
		
	}



	@Override
	public void repaintComponents() {
		tabuleiro.repaintComponents();
		for(Component i: mFrame.getComponents()){
			i.revalidate();
			i.repaint();
			
		}
	}


}
