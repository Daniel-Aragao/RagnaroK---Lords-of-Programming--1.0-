package Deque;

import java.awt.Graphics;

import Util.Importar;
import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;
import entity.Carta_Criatura;
import entity.Carta_Especial;
import entity.Carta_Magica;
import entity.Entity;
import entity.TopoBaralho;

//All Descriptions URL's.txt

@SuppressWarnings("serial")
public class Tabuleiro extends Entity {
	public static final int DEFAULT_TABULEIRO_WIDTH = 1024,
							DEFAULT_TABULEIRO_HEIGHT = 380;
	public static final Position UP_TAB = new Position(),
								DOWN_TAB = new Position();
	
	private Lista_de_Generics<Carta> baralho;
	private Lista_de_Generics<Carta> cemiterio;
	private Lista_de_Generics<Carta_Criatura> field;
	
	private Carta_Especial ED;
	private Carta_Especial OO;
	
	private TopoBaralho topoBaralho; 
	private Lista_de_Generics<Carta_Especial> Registro_Especiais;
	private Importar importar = new Importar();
	private Carta BlankSpace;

	public Tabuleiro(Lista_de_Generics<Carta> baralho) {
		super(Tabuleiro.DEFAULT_TABULEIRO_WIDTH, Tabuleiro.DEFAULT_TABULEIRO_HEIGHT);
		
//		importar.importAllCards(Game.FILE);;
		this.baralho = baralho;
//		exibirBaralhoNoConsole();
		this.baralho = autoCompletar_baralho();
		
		topoBaralho = new TopoBaralho(baralho);

		cemiterio = new Lista_de_Generics<Carta>(30);

		field = new Lista_de_Generics<Carta_Criatura>(5);

		Registro_Especiais = new Lista_de_Generics<Carta_Especial>(7);
		
		
		// ED = new Carta_Especial("", null, Tipo_Carta.ENCAPSULAMENTO);
		// Registro_Especiais.addFim(ED);

		//setLayout(new GridLayout(2, 7));
	}

	public boolean repetida(Carta_Especial c) {
		for (int i = 0; i < Registro_Especiais.getQtdElementos(); i++) {
			Carta_Especial C = Registro_Especiais.getElemento(i);
			if (c.getValor() == C.getValor()) {
				return true;
			}
		}
		return false;
	}
	public Lista_de_Generics<Carta> autoCompletar_baralho() {
		Lista_de_Generics<Carta> baralhoAux = new Lista_de_Generics<>(30);
		int qtd = baralho.getQtdElementos();
		for (int i = 0; i < qtd; i++) {
			baralhoAux.addFim(baralho.getElemento(i));
		}
			

		Lista_de_Generics<Carta_Magica> magicas = new Lista_de_Generics<Carta_Magica>(10);
		Lista_de_Generics<Carta_Especial> eds = new Lista_de_Generics<Carta_Especial>(5);
		Lista_de_Generics<Carta_Especial> oos = new Lista_de_Generics<Carta_Especial>(5);
		
		qtd = baralhoAux.getQtdElementos();
		for (int i = 0; i < qtd; i++) {
			if (baralhoAux.getElemento(i) != null) {
				
				switch (baralhoAux.getElemento(i).getTipo()) {
				case MAGICA:
					magicas.addFim((Carta_Magica) baralhoAux.remover(i));
					i--;
					break;
				case ED:
					eds.addFim((Carta_Especial) baralhoAux.remover(i));
					i--;
					break;
				case OO:
					oos.addFim((Carta_Especial) baralhoAux.remover(i));
					i--;
					break;
				default:
				}
			}
		}
		magicas.embaralhar();
		magicas.fill();
		for (int i = 0; i < magicas.length(); i++) {
			System.out.println(baralhoAux.getQtdElementos());
			baralhoAux.addFim(magicas.removerInicio());
		}
		System.out.println();
		eds.embaralhar();
		eds.fill();
		for (int i = 0; i < eds.length(); i++) {
			System.out.println(baralhoAux.getQtdElementos());
			baralhoAux.addFim(eds.removerInicio());
		}
		System.out.println();
		oos.embaralhar();
		oos.fill();
		for (int i = 0; i < oos.length(); i++) {
			System.out.println(baralhoAux.getQtdElementos());
			baralhoAux.addFim(oos.removerInicio());
		}
		System.out.println();
		System.out.println(baralhoAux.getQtdElementos());
		baralhoAux.embaralhar();
		return baralhoAux;
	}

	public void exibirBaralhoNoConsole(/*Lista_de_Generics<Carta> a*/) {
		Lista_de_Generics<Carta> a = baralho;
		for (int i = 0; i < a.getQtdElementos(); i++) {
			System.out.println("i:" + i);
			if(a.getElemento(i) != null){
				System.out.println("Carta: " + a.getElemento(i).getNome());
			}else{
				System.out.println("Carta: " + a.getElemento(i));
			}
		}
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(Graphics g){
		drawCemiterio(g);
		drawField(g);
		drawED(g);
		drawOO(g);
		
	}
	public void drawCemiterio(Graphics g){
		if(!cemiterio.isEmpty()){
			for(int i = 0; i < cemiterio.length(); i++){
				cemiterio.getElemento(i).draw(g);
			}
		}
	}
	public void drawField(Graphics g){
		if(!field.isEmpty()){
			for(int i = field.length()-1; i > 0; i--){
				Carta_Criatura aux = field.getElemento(i);
				if(aux != null){
					aux.draw(g);
				}
			}
		}
	}
	public void drawED(Graphics g){
		if(ED != null){
			ED.draw(g);
		}
	}
	public void drawOO(Graphics g){
		if(OO != null){
			OO.draw(g);
		}
	}
	
	
	
}
