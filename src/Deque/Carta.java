package Deque;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;

import entity.Entity;
import Util.Importar;

public class Carta extends Entity {
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

	// Tipo de Carta
	private Tipo_Carta tipo;

	// Localiza��o

	// M�todos
	// get imagem
	// get descri��o
	// get pontos
	// get tipo
	// get/set localiza��o
	// get/set estado

	// / Chamar o m�todo da classe Importar.importarCarta(Carta,File); para
	// inicializar o objeto
	public Carta(CartaParameters cp) {
		this.nome = cp.nome;
		this.descricao = cp.descricao;
		this.tipo = cp.tipo;
		this.imagem = cp.imagem;
		this.x = cp.x;
		this.y = cp.y;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo_Carta getTipo() {
		return tipo;
	}

	public int getValor() {
		return tipo.getValor();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
