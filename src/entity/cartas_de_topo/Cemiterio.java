package entity.cartas_de_topo;

import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Criatura;

public class Cemiterio{
	private Lista_de_Generics<Carta> lista;
	private Carta topo;
	

	public Cemiterio() {
		lista = new Lista_de_Generics<Carta>(30);
	}



	public void addCarta(Carta c) {
		if(topo!=null){
			lista.addFim(topo);
		}
		topo = c;
		
	}
	

}
