package listeners;

import tabuleiro.Jogador;
import entity.Carta;
import entity.Entity;

public interface CommandListener {
	public void passarVez(Jogador jogador);
	public void atacar(Jogador jogador, Entity alvo);
	public void endGame(Jogador winner);
	public void hooverInfo(Carta c);
	public void assossia��oAtaque(Jogador jogador);
	public void polimorfismo(Jogador jogador);
	public void heran�a(Jogador jogador);
	public void encapsulamento(Jogador jogador);
}
