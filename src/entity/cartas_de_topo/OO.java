package entity.cartas_de_topo;

import state.inGameStates.TurnoState;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_ED;
import entity.Carta_OO;

public class OO extends Carta{
	Lista_de_Generics<Carta_OO> lista;
	
	
	
	public OO(CartaParameters cp){
		super(cp);
		//this.addCartaClickedListener(TurnoState.CEMITERIO_HANDLER);
		lista = new Lista_de_Generics<Carta_OO>(5);
		
	}
	public void registrar(Carta_OO c){
		lista.addFim(c);
	}
	
	public boolean usada(Carta_OO c){
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
