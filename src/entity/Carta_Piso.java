package entity;

public class Carta_Piso extends Carta {

	public Carta_Piso(CartaParameters cp){
		super(cp);
		
	}

	

	@Override
	public Carta copy() {
		Carta_Piso aux = new Carta_Piso(cp);
		
		return aux;
	}
	
	
}
