package handlers;

import listeners.CartaClickedListener;
import tabuleiro.Jogador;
import entity.Carta;
import entity.Entity;

public class ClickedSelectHandler implements CartaClickedListener{
	private Jogador jogador;
	public ClickedSelectHandler(Jogador jogador){
		this.jogador = jogador;
		
		
	}
	
	@Override
	public void CardClicked(Entity c) {
		jogador.getBaralho().SelectionCardClicked((Carta) c);		
	}

	@Override
	public void CardHoover(Carta c, boolean b) {
		int acr = 10;
		if(b){
			c.fantasy_CARTA_WIDTH += acr;
			c.fantasy_CARTA_HEIGHT += acr;
						
			jogador.getCommandListener().hooverInfo(c);
			
		}else{
			c.fantasy_CARTA_WIDTH -= acr;
			c.fantasy_CARTA_HEIGHT -= acr;
			
		}
		
	}

}
