package handlers;

import entity.Carta;
import listeners.CartaClickedListener;
import tabuleiro.Jogador;

public class ClickedHandHandler implements CartaClickedListener{
	
	private Jogador jogador;
	public ClickedHandHandler(Jogador jogador) {
		this.jogador = jogador;
	}
	
	@Override
	public void CardClicked(Carta c) {
		jogador.getHand().getMainPanel().getCommandPanel().setHandCardClicked(c);
		
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
