package state;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import tabuleiro.Tabuleiro;
import Game.Game;
import Input.Mouse;

public class GameState extends State{

	int Round;
	Tabuleiro tabuleiro; 
	Graphics g;
	JFrame mFrame;
	
	public GameState(Game game, Graphics g) {
		tabuleiro = new Tabuleiro();
		Round = 0;
		mFrame = game.getFrame().getFrame();
		
		mFrame.addMouseListener(new Mouse());
		mFrame.add(tabuleiro, BorderLayout.CENTER);

	
	}

	

	@Override
	public void update() {
		tabuleiro.update();
	}



	@Override
	public void draw(Graphics g) {
		tabuleiro.draw(g);
		
	}


}
