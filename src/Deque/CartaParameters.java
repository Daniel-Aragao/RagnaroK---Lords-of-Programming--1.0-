package Deque;

import java.awt.image.BufferedImage;

public class CartaParameters {
	String nome, descricao;
	BufferedImage imagem;
	Tipo_Carta tipo;
	float x,y;
	
	public CartaParameters (Tipo_Carta tipo){
		this.tipo = tipo;
	}
}
