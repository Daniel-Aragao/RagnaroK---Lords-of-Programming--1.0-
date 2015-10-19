package state;

import java.awt.Graphics;

import entity.Carta;
import Deque.Jogador;
import Game.Game;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;

public class GameState extends State {

	Importar importar;
	Jogador jogadorA;
	Jogador jogadorB;
	int Round;
	Lista_de_Generics<Carta> baralho;

	public GameState() {
		importar = new Importar();
		baralho = importar.importAllCards(Game.FILE);
		importar.importarBackground(Game.BACKGROUND_FILE);

		Round = 0;
		
		criarJogadores();
		jogadorA.setVez(true);
		jogadorB.setVez(false);
	}

	private void criarJogadores() {
		//exibir JPanel
		jogadorA = new Jogador(baralho);
		//exibir JPanel
		jogadorB = new Jogador(baralho);

	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		// g.drawImage(image, x, y, imageObserver);
		g.drawImage(Importar.getBackground(BackgroundID.JogoBackground), 0, 0,
				null);
		jogadorA.draw(g);
		jogadorB.draw(g);
	}

}
