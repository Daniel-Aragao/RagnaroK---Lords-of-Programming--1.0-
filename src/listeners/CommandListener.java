package listeners;

import tabuleiro.Jogador;

public interface CommandListener {
	public void passarVez(Jogador jogador);
	public void atacar(Jogador jogador);
}
