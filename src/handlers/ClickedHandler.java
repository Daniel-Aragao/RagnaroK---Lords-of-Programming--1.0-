package handlers;

import listeners.CartaClickedListener;
import tabuleiro.Jogador;
import Gráficos.SideFrames.handPanels.DescriptionPanel;
import entity.Carta;
import entity.Carta_Criatura;
import entity.Carta_ED;
import entity.Carta_Magica;
import entity.Carta_OO;

public abstract class ClickedHandler implements CartaClickedListener {
	
	Jogador jogador;
	public ClickedHandler(Jogador jogador){
		this.jogador = jogador;
		
		
	}
	
	@Override
	public abstract void CardClicked(Carta c);
	@Override
	public abstract void CardHoover(Carta c, boolean b);

}
