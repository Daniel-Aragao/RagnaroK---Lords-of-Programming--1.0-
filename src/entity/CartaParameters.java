package entity;

import java.awt.image.BufferedImage;

import Game.Game;
import Input.Mouse;
import Util.Position;

public class CartaParameters {
	private Mouse mouse ;
	public String nome, descricao;
	public BufferedImage imagem;
	private Tipo_Carta tipo;
	
	public CartaParameters (Tipo_Carta tipo){
		this.tipo = tipo;
		mouse = Game.CARD_MOUSE;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public Tipo_Carta getTipo() {
		return tipo;
	}


}
