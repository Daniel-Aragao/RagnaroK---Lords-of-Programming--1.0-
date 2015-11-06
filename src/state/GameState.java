package state;

import java.awt.BorderLayout;
import java.awt.Graphics;

import tabuleiro.Tabuleiro;
import Game.Game;
import Input.Mouse;

public class GameState extends State{

	int Round;
	Tabuleiro tabuleiro; 
	Graphics g;
	
	public GameState(Game game, Graphics g) {
		tabuleiro = new Tabuleiro();
		Round = 0;
		game.getmFrame().getFrame().add(tabuleiro, BorderLayout.CENTER);

	
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
