package Deque;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;

import Util.Importar;

public class Carta {
	// 30 cartas = 10 personagem + 10 mágicas + 5 ED + 5 OO
	// soma dos atributos (ataque,defesa,skill) é 15

	
	// Imagem da Carta
	private BufferedImage imagem;
	private String nome;

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	// Descrição da Carta
	private String descricao;
	// Localização
	
	// Métodos
	// get imagem
	// get descrição
	// get pontos
	// get tipo
	// get/set localização
	// get/set estado

	/// Chamar o método da classe Importar.importarCarta(Carta,File); para inicializar o objeto
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
