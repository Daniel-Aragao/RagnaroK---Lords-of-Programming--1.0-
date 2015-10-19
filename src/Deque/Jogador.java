package Deque;

import java.awt.Color;
import java.awt.Graphics;

import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;
import entity.Entity;

public class Jogador extends Entity {
	public static final Position UP_LABEL = new Position(100, 20);
	public static final Position DOWN_LABEL = new Position(100, 740);
	public static final int ESPACAMENTO = 80;

	private String nome;
	private String energiaString;
	private String vezString;
	private int energia;
	private boolean vez;
	private Tabuleiro tabuleiro;

	// tamanho do tabuleiro = 1024 x 380

	public Jogador(Lista_de_Generics<Carta> baralho) {
		super(Tabuleiro.DEFAULT_TABULEIRO_WIDTH,
				Tabuleiro.DEFAULT_TABULEIRO_HEIGHT);
		tabuleiro = new Tabuleiro(baralho);
		nome = "Nome: Daniel";
		energiaString = "Energia: "+energia;
		vezString = "Jogando!";
		this.position = DOWN_LABEL;

	}

	public void setVez(boolean vez) {
		this.vez = vez;
		if(vez) {
			this.vezString = "Jogando!";
			position = DOWN_LABEL;
			
		}else{
			this.vezString = "Aguardando.";
			position = UP_LABEL;
		}
	}

	public boolean getVez() {return this.vez;}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = "Nome: " + nome;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
		energiaString = "Energia: " + energia;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		tabuleiro.draw(g);
		
		Position energiaStringPosition = new Position(position.x + nome.length()+ Jogador.ESPACAMENTO,position.y);
		
		Position vezStringPosition = new Position(energiaStringPosition.x + energiaString.length() + Jogador.ESPACAMENTO,position.y);
		
		// draw "Nome: energia Jogando/Aguardando (a vez)
		g.setColor(Color.pink);
		g.drawString(nome, (int) position.x, (int) position.y);
		g.drawString(energiaString, (int) energiaStringPosition.x, (int) position.y);
		g.drawString(vezString, (int)vezStringPosition.x , (int) position.y);
		


	}

}
