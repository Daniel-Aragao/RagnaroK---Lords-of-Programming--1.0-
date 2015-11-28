package listeners;

import tabuleiro.Jogador;
import entity.Carta;
import entity.Entity;

public interface CommandListener {
	public void passarVez(Jogador jogador);
	public void atacar(Jogador jogador, Entity alvo);
	public void hooverInfo(Carta c);
}
