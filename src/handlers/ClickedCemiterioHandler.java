package handlers;

import tabuleiro.Jogador;
import entity.Carta;
import listeners.CartaClickedListener;

public class ClickedCemiterioHandler implements CartaClickedListener{

	public ClickedCemiterioHandler(Jogador jogador) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CardClicked(Carta c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CardHoover(Carta c, boolean b) {
		
		int acr = 10;
		if (b) {
			c.setSize(Carta.DEFAULT_CARTA_WIDTH+ acr,
					Carta.DEFAULT_CARTA_HEIGHT + acr);
			c.fantasy_CARTA_WIDTH += acr;
			c.fantasy_CARTA_HEIGHT += acr;
		} else {
			c.setSize(Carta.DEFAULT_CARTA_WIDTH,
					Carta.DEFAULT_CARTA_HEIGHT);
			c.fantasy_CARTA_WIDTH -= acr;
			c.fantasy_CARTA_HEIGHT -= acr;

		}
		
	}

	
	
}
