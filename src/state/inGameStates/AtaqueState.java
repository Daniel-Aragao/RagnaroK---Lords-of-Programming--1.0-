package state.inGameStates;

import tabuleiro.Jogador;
import Gráficos.SideFrames.HandFrame;

public class AtaqueState extends TurnoState{

	public AtaqueState(Jogador jogador, HandFrame hand) {
		super(jogador);
		
		hand.getFrame().setVisible(true);
		
		
//		TurnoState.BARALHO_HANDLER = new ClickedBaralhoHandler(jogador);
//		TurnoState.CEMITERIO_HANDLER = new ClickedCemiterioHandler(jogador);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	

}
