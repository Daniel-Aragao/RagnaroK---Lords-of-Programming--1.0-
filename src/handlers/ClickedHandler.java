package handlers;

import Gráficos.SideFrames.handPanels.HandCommandPanel;
import listeners.CartaClickedListener;
import tabuleiro.Jogador;
import entity.Carta;
import entity.Entity;
import entity.cartas_de_topo.Baralho;

public class ClickedHandler implements CartaClickedListener {
	
	private Jogador jogador;
	public ClickedHandler(Jogador jogador){
		this.jogador = jogador;
		
		
	}
	
	@Override
	public void CardClicked(Entity c) {
		//System.out.println(c.toString());
		if(c instanceof Baralho){
			((Baralho)c).itWasClicked();
		}else{
			HandCommandPanel.setGlobalSelection(c);
			
			if(jogador.getVez()){
				jogador.getHandCommandPanel().setSelected(c);
			}			
		}
		

	}

	@Override
	public void CardHoover(Carta c, boolean b) {
		jogador.getCartaClickedListener().CardHoover(c, b);
	}
}
