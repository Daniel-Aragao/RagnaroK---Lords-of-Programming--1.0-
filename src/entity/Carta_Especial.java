package entity;

import java.awt.image.BufferedImage;

public class Carta_Especial extends Carta {
	
	public Carta_Especial(CartaParameters cp) {
		super(cp);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Carta copy() {
		Carta_Especial aux = new Carta_Especial(cp);
		return aux;
	}
	
	


	
}
