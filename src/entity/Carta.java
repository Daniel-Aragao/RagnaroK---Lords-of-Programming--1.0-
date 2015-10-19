package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;

import Util.Importar;

public class Carta extends Entity {
	// 30 cartas = 10 personagem + 10 mágicas + 5 ED + 5 OO
	// soma dos atributos (ataque,defesa,skill) é 15

	public static final int DEFAULT_CARTA_WIDTH = 100,
							DEFAULT_CARTA_HEIGHT = 130;
	// Imagem da Carta
	protected BufferedImage imagem;
	protected String nome;
	
	//private int x,y;
	//protected int width, height;

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	// Descrição da Carta
	private String descricao;

	// Tipo de Carta
	private Tipo_Carta tipo;

	// Localização

	// Métodos
	// get imagem
	// get descrição
	// get pontos
	// get tipo
	// get/set localização
	// get/set estado

	// / Chamar o método da classe Importar.importarCarta(Carta,File); para
	// inicializar o objeto
	public Carta(CartaParameters cp) {
		super(DEFAULT_CARTA_WIDTH,DEFAULT_CARTA_HEIGHT);
		this.nome = cp.nome;
		this.descricao = cp.descricao;
		this.tipo = cp.tipo;
		this.imagem = cp.imagem;
		this.position.x = cp.x;
		this.position.y = cp.y;
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
		g.drawImage(imagem, (int)position.x, (int)position.y, width, height, null);

	}

}
