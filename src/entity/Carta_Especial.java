package entity;


@SuppressWarnings("serial")
public class Carta_Especial extends Carta {
	
	public Carta_Especial(CartaParameters cp) {
		super(cp);
	}

	

	@Override
	public Carta copy() {
		Carta_Especial aux = new Carta_Especial(cp);
		return aux;
	}
	
	


	
}
