package state.inGameStates;

import tabuleiro.Jogador;
import Gráficos.SideFrames.HandFrame;

public class DefesaState extends TurnoState{

	public DefesaState(Jogador jogador, HandFrame hand) {
		super(jogador);
		
		hand.getFrame().setVisible(false);
		
		
//		TurnoState.BARALHO_HANDLER = null;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	

}
