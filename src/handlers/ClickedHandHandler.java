package handlers;

import entity.Carta;
import entity.Entity;
import listeners.CartaClickedListener;
import tabuleiro.Jogador;

public class ClickedHandHandler implements CartaClickedListener{
	
	private Jogador jogador;
	public ClickedHandHandler(Jogador jogador) {
		this.jogador = jogador;
	}
	
	@Override
	public void CardClicked(Entity c) {
		jogador.getHand().getMainPanel().getCommandPanel().setHandCardClicked((Carta) c);
		
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
