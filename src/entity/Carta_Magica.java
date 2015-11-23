package entity;

import java.awt.Graphics;

public class Carta_Magica extends Carta {
	
	public Carta_Magica(CartaParameters cp) {
		super(cp);
	}

	

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Carta copy() {
		Carta_Magica aux = new Carta_Magica(cp);
		return aux;
	}
	
	
}
