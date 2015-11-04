package state;

import java.awt.Graphics;

import tabuleiro.Tabuleiro;

public class GameState extends State{

	int Round;
	Tabuleiro tabuleiro; 
	Graphics g;
	
	public GameState(Graphics g) {
		this.g = g;
		tabuleiro = new Tabuleiro();
		Round = 0;


	
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
