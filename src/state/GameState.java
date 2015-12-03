package state;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

import tabuleiro.Jogador;
import tabuleiro.Tabuleiro;
import Game.Game;

public class GameState extends State{

	Tabuleiro tabuleiro; 
	JFrame mFrame;
	static Game game;
	
	public GameState(Game game) {
		GameState.game = game;
		mFrame = game.getFrame().getFrame();
		
		tabuleiro = new Tabuleiro();
		mFrame.setContentPane(tabuleiro);
	}

	@Override
	public void update() {
		tabuleiro.update();
	}


	@Override
	public void repaintComponents() {
		tabuleiro.repaintComponents();
		for(Component i: mFrame.getComponents()){
			i.revalidate();
			i.repaint();
			
		}
	}

	public static void endGame(Jogador winner, Jogador loser) {
		setState(new GameOverState(game, winner, loser));
		
	}


}
