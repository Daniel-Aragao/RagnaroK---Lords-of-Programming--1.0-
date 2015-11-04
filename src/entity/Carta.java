package entity;

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
	
	
	
	
	// Imagem da Carta
	
	protected String nome;
	
	//Listeners
	protected CartaClickedListener cartaClickedListener;
	
	//private int x,y;
	//protected int width, height;

	

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
		this.position = cp.position;
		this.addMouseListener(new Mouse(this));
				
	}


	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
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

	@Override
	public abstract void update();


	public void addCartaClickedListener(CartaClickedListener listener){
		this.cartaClickedListener = listener;
	}
	
	public CartaClickedListener getCartaClickedListener(){
		return this.cartaClickedListener;
	}
	
	
}
