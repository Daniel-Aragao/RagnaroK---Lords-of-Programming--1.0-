package entity;

import java.awt.image.BufferedImage;

public class Carta_OO extends Carta_Especial{

	public Carta_OO(CartaParameters cp) {
		super(cp);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Carta copy() {
		Carta_OO aux = new Carta_OO(cp);
		return aux;
	}
}

