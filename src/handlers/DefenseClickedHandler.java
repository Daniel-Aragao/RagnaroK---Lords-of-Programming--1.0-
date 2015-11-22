package handlers;

import listeners.CartaClickedListener;
import tabuleiro.Jogador;
import Gráficos.SideFrames.handPanels.DescriptionPanel;
import entity.Carta;
import entity.Carta_Criatura;
import entity.Carta_ED;
import entity.Carta_Magica;
import entity.Carta_OO;

public class DefenseClickedHandler extends ClickedHandler implements CartaClickedListener {
	
	Jogador jogador;
	public DefenseClickedHandler(Jogador jogador){
		super(jogador);
		this.jogador = jogador;
		
		
	}
	
	@Override
	public void CardClicked(Carta c) {

		if (c instanceof Carta_Criatura){
			System.out.println("Carta criatura clicada");
			c.stateAction();
		}
		if (c instanceof Carta_Magica){
			System.out.println("Carta Magica clicada");
		}
		if (c instanceof Carta_ED){
			System.out.println("Carta ED clicada");
		}
		if (c instanceof Carta_OO){
			System.out.println("Carta OO clicada");
		}

	}

	@Override
	public void CardHoover(Carta c, boolean b) {
		DescriptionPanel description = jogador.getHand().getMainPanel().getEastPanel().getDescriptionPanel();
		int acr = 10;
		if (b) {
			c.setSize(Carta.DEFAULT_CARTA_WIDTH-acr, Carta.DEFAULT_CARTA_HEIGHT-acr);
			c.fantasy_CARTA_WIDTH -= acr;
			c.fantasy_CARTA_HEIGHT -= acr;
			
			
			description.setInformacoes(c);
			
			
		} else {
			
			c.setSize(Carta.DEFAULT_CARTA_WIDTH,
					Carta.DEFAULT_CARTA_HEIGHT);
			c.fantasy_CARTA_WIDTH += acr;
			c.fantasy_CARTA_HEIGHT += acr;
			//description.setInformacoes(null);
			c.repaint();
		}
		
		
		

	}

}
