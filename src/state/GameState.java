package state;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

import Util.BackgroundSoundID;
import Util.Importar;
import Util.MusicPlayer;
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
		
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.game));
		
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
		if(!MusicPlayer.isAlive()){
			new MusicPlayer().start(Importar.getSound(BackgroundSoundID.game));
		}
	}

	public static void endGame(Jogador winner, Jogador loser) {
		setState(new GameOverState(game, winner, loser));		
	}


}
