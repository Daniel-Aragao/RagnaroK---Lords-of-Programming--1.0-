package state.inGameStates;

import handlers.ClickedHandler;
import handlers.ClickedBaralhoHandler;
import tabuleiro.Jogador;

public abstract class TurnoState {
	public static ClickedHandler CLICKED_HANDLER;
	public static ClickedBaralhoHandler BARALHO_HANDLER;
	
	private TurnoState currentState;

	private Jogador jogador;
	
	public TurnoState(Jogador jogador){
		this.setJogador(jogador);
	}
	
	public TurnoState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(TurnoState currentState) {
		this.currentState = currentState;
	}
	
	public abstract void action();

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
}
