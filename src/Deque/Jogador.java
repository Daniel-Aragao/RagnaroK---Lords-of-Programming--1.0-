package Deque;

import java.io.File;

import Util.Lista_de_Generics;

public class Jogador {
	private String nome;
	private int energia;
	private Tabuleiro tabuleiro;
	
	public Jogador(Lista_de_Generics<Carta> baralho){
		tabuleiro = new Tabuleiro(baralho);
	}
	public String getNome(){return this.nome;}
	
	public int getEnergia() {return energia;}
	
	public void setEnergia(int energia) {this.energia = energia;}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	
}
