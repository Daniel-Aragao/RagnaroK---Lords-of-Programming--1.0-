package entity;

import java.awt.image.BufferedImage;

import Util.Position;

public class CartaParameters {
	String nome, descricao;
	BufferedImage imagem;
	Tipo_Carta tipo;
	Position position;
	
	public CartaParameters (Tipo_Carta tipo){
		this.tipo = tipo;
	}
}
