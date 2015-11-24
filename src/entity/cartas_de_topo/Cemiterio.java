package entity.cartas_de_topo;

import handlers.ClickedHandler;
import tabuleiro.Jogador;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;

public class Cemiterio extends Carta{
	private Lista_de_Generics<Carta> lista;
	private Carta topo;
	
	private Jogador jogador;

	public Cemiterio(CartaParameters cp, Jogador jogador) {
		super(cp);
		lista = new Lista_de_Generics<Carta>(30);
		
		this.width += 10;
		
		this.jogador = jogador;
		
		this.addCartaClickedListener(new ClickedHandler(this.jogador));
		
	}



	public void addCarta(Carta c) {
		if(topo!=null){
			lista.addFim(topo);
		}
		topo = c;
		
	}



	@Override
	public Carta copy() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
