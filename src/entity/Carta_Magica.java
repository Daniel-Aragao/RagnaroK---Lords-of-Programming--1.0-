package entity;


@SuppressWarnings("serial")
public class Carta_Magica extends Carta {
	
	public Carta_Magica(CartaParameters cp) {
		super(cp);
	}

	
	
	

	@Override
	public Carta copy() {
		Carta_Magica aux = new Carta_Magica(cp);
		return aux;
	}
	
	
}
