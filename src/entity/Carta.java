package entity;

import java.awt.Graphics;

import tabuleiro.Jogador;
import Input.Mouse;
import Util.Position;
import listeners.CartaClickedListener;

@SuppressWarnings("serial")
public abstract class Carta extends Entity {
	// 30 cartas = 10 personagem + 10 mágicas + 5 ED + 5 OO
	// soma dos atributos (ataque,defesa,skill) é 15

	public static final int DEFAULT_CARTA_WIDTH = 100,
							DEFAULT_CARTA_HEIGHT = 130;
	
	public int fantasy_CARTA_WIDTH = DEFAULT_CARTA_WIDTH,fantasy_CARTA_HEIGHT = DEFAULT_CARTA_HEIGHT;
	
	
	//Listeners
	protected CartaClickedListener cartaClickedListener;
	//
	
	private String descricao;

	private Tipo_Carta tipo;

	protected CartaParameters cp;
	
	public Carta(CartaParameters cp) {
		super(DEFAULT_CARTA_WIDTH, DEFAULT_CARTA_HEIGHT);
		this.cp = cp;
		this.nome = cp.nome;
		this.descricao = cp.descricao;
		this.tipo = cp.getTipo();
		this.imagem = cp.imagem;
		this.addMouseListener(cp.getMouse());
				
	}


	public String getDescricao() {
		return descricao;
	}

	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo_Carta getTipo() {
		return tipo;
	}

	public int getValor() {
		return tipo.getValor();
	}
	

	public void draw(){
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();
	
		gr.drawImage(this.imagem, 0, 0,this.fantasy_CARTA_WIDTH,this.fantasy_CARTA_HEIGHT, null);
		
		
		gr.dispose();
		
	}
	public void addCartaClickedListener(CartaClickedListener listener){
		this.cartaClickedListener = listener;
	}
	
	public CartaClickedListener getCartaClickedListener(){
		return this.cartaClickedListener;
	}


	public void stateAction() {
		// TODO Auto-generated method stub
		
	}


	public abstract Carta copy();
	
	
}
