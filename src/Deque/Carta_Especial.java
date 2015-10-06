package Deque;

import java.awt.image.BufferedImage;

public class Carta_Especial extends Carta {
	
//	ENCAPSULAMENTO (1),
//	HERAN�A (2),
//	POLIMORFISMO (3),
//	ASSOCIA��O (4),
//	LISTA (5),
//	FILA (6),
//	PILHA (7);
	
	private Tipo_Carta tipo;
	
	public Carta_Especial(String nome, String descricao, BufferedImage imagem, Tipo_Carta tipo) {
		super(nome, descricao, imagem);
		this.setTipo(tipo);
		
	}
	
	public Tipo_Carta getTipo() {return tipo;}
	public int getValor(){return tipo.getValor();}
	
	private void setTipo(Tipo_Carta tipo) {this.tipo = tipo;}
	
//	public void Action(Carta_Monstro c){
//		switch(tipo){
//		case ENCAPSULAMENTO:
//			escapsulamento(c);
//			break;
//		case HERAN�A:
//			break;
//		case POLIMORFISMO:
//			break;
//		case ASSOCIA��O:
//			break;
//		case LISTA:
//			break;
//		case FILA:
//			break;
//		case PILHA:
//			break;
//		}
//	}
//	
//	
//
//	private void escapsulamento(Carta_Monstro c) {
//		
//		
//	}

	
}
