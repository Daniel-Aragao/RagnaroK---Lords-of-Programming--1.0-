package handlers;

import listeners.CartaClickedListener;
import tabuleiro.Jogador;
import entity.Carta;
import entity.cartas_de_topo.Baralho;

public class ClickedHandler implements CartaClickedListener {
	
	private Jogador jogador;
	public ClickedHandler(Jogador jogador){
		this.jogador = jogador;
		
		
	}
	
	@Override
	public void CardClicked(Carta c) {
		if(c instanceof Baralho){
			((Baralho)c).itWasClicked();
		}
		

	}

	@Override
	public void CardHoover(Carta c, boolean b) {
		jogador.getClickedHandler().CardHoover(c, b);
	}
}
