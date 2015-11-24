package entity.cartas_de_topo;

import handlers.ClickedHandler;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_ED;

public class ED extends Carta{

	Lista_de_Generics<Carta_ED> lista;
	
	public ED(CartaParameters cp){
		super(cp);
		//this.addCartaClickedListener(TurnoState.CEMITERIO_HANDLER);
		lista = new Lista_de_Generics<Carta_ED>(5);
		//this.cartaClickedListener(new ClickedHandler());
		
	}
	
	
	public void registrar(Carta_ED c){
		lista.addFim(c);
	}
	
	public boolean usada(Carta_ED c){
		if(lista.contains(c)){
			return true;
		}
		return false;
	}

	@Override
	public Carta copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
