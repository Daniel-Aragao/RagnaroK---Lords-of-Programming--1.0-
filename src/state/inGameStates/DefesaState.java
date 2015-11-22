package state.inGameStates;

import handlers.DefenseClickedHandler;
import Gráficos.SideFrames.HandFrame;
import tabuleiro.Jogador;

public class DefesaState extends TurnoState{

	public DefesaState(Jogador jogador, HandFrame hand) {
		super(jogador);
		
		hand.getFrame().setVisible(false);
		
		TurnoState.CLICKED_HANDLER = new DefenseClickedHandler(jogador);
		TurnoState.BARALHO_HANDLER = null;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	

}
