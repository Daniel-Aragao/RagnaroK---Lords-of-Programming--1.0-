package state.inGameStates;

import handlers.AtackClickedHandler;
import handlers.ClickedHandler;
import handlers.ClickedBaralhoHandler;
import tabuleiro.Jogador;
import Gráficos.SideFrames.HandFrame;

public class AtaqueState extends TurnoState{

	public AtaqueState(Jogador jogador, HandFrame hand) {
		super(jogador);
		
		hand.getFrame().setVisible(true);
		
		TurnoState.CLICKED_HANDLER = new AtackClickedHandler(jogador);
		TurnoState.BARALHO_HANDLER = new ClickedBaralhoHandler(jogador);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	

}
