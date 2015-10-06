package Deque;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;

import Util.Importar;

public class Carta {
	// 30 cartas = 10 personagem + 10 m�gicas + 5 ED + 5 OO
	// soma dos atributos (ataque,defesa,skill) � 15

	
	// Imagem da Carta
	private BufferedImage imagem;
	private String nome;

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	// Descri��o da Carta
	private String descricao;
	// Localiza��o
	
	// M�todos
	// get imagem
	// get descri��o
	// get pontos
	// get tipo
	// get/set localiza��o
	// get/set estado

	/// Chamar o m�todo da classe Importar.importarCarta(Carta,File); para inicializar o objeto
	public Carta(String nome, String descricao, BufferedImage imagem) {
		this.nome = nome;
		this.descricao = descricao;
		
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {return nome;}

	private void setNome(String nome) {this.nome = nome;}

	

}
