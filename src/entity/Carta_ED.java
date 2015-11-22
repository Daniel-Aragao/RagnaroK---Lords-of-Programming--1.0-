package entity;

import java.awt.image.BufferedImage;

public class Carta_ED extends Carta_Especial {

	public Carta_ED(CartaParameters cp) {
		super(cp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Carta copy() {
		Carta_ED aux = new Carta_ED(cp);
		return aux;
	}
}
