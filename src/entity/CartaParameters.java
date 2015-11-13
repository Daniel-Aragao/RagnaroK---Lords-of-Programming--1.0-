package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Util.Position;

public class CartaParameters {
	public String nome, descricao;
	public BufferedImage imagem;
	public Tipo_Carta tipo;
	public Position position;
	
	public CartaParameters (Tipo_Carta tipo){
		this.tipo = tipo;
	}
}
