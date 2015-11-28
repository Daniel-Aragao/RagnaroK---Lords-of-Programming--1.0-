package handlers;

import listeners.CartaClickedListener;
import tabuleiro.Jogador;
import Gr�ficos.SideFrames.handPanels.DescriptionPanel;
import entity.Carta;
import entity.Carta_Criatura;
import entity.Carta_ED;
import entity.Carta_Magica;
import entity.Carta_OO;

public class ClickedHandler implements CartaClickedListener {
	
	private Jogador jogador;
	public ClickedHandler(Jogador jogador){
		this.jogador = jogador;
		
		
	}
	
	@Override
	public void CardClicked(Carta c) {

		

	}

	@Override
	public void CardHoover(Carta c, boolean b) {
		jogador.getClickedHandler().CardHoover(c, b);			

	}
}
