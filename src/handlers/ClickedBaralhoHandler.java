package handlers;

import tabuleiro.Jogador;
import listeners.CartaClickedListener;
import entity.Carta;
import entity.Entity;

public class ClickedBaralhoHandler implements CartaClickedListener {

	public ClickedBaralhoHandler(Jogador jogador) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CardClicked(Entity c) {
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
