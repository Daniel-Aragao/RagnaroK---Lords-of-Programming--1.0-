package entity.cartas_de_topo;

import state.inGameStates.TurnoState;
import tabuleiro.Jogador;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Especial;
import entity.Carta_Magica;

public class Baralho extends Carta{
	
	public final static int TAMANHO_DO_BARALHO = 30;

	private Lista_de_Generics<Carta> lista;
	private Jogador jogador;
	
	public Baralho(Lista_de_Generics<Carta> lista, CartaParameters cp, Jogador jogador) {
		super(cp);
		this.jogador = jogador;
		this.side = jogador.getSide();
		
		this.width += 10;		
		
		this.addCartaClickedListener(TurnoState.BARALHO_HANDLER);
		setBaralho(lista);
	}

	public void setBaralho(Lista_de_Generics<Carta> baralho) {
		lista = baralho;	
		lista = autoCompletar_baralho();
		//topo = lista.getElemento(0);
	}

	public Lista_de_Generics<Carta> autoCompletar_baralho() {
		
		Lista_de_Generics<Carta> baralhoAux = new Lista_de_Generics<>(Baralho.TAMANHO_DO_BARALHO);
		int qtd = lista.getQtdElementos();
		for (int i = 0; i < qtd; i++) {
			Carta j = lista.getElemento(i).copy();
			baralhoAux.addFim(j);
			j.setSide(jogador.getSide());
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
		magicas.fill();
		for (int i = 0; i < magicas.length(); i++) {
			Carta_Magica magica= magicas.removerInicio();
			magica.setSide(this.side);
			baralhoAux.addFim(magica);
		}
		eds.fill();
		for (int i = 0; i < eds.length(); i++) {
			Carta_Especial ed = eds.removerInicio();
			ed.setSide(this.side);
			baralhoAux.addFim(ed);
		}
		oos.fill();
		for (int i = 0; i < oos.length(); i++) {
			Carta_Especial oo = oos.removerInicio();
			oo.setSide(this.side);
			baralhoAux.addFim(oo);
		}
		return baralhoAux;
	}

		
	public void embaralhar() {
		lista.embaralhar();
		
	}
	public void exibirBaralhoNoConsole(/*Lista_de_Generics<Carta> a*/) {
		Lista_de_Generics<Carta> a = lista;
		for (int i = 0; i < a.getQtdElementos(); i++) {
			System.out.println("i:" + i);
			if(a.getElemento(i) != null){
				System.out.println("Carta: " + a.getElemento(i).getNome());
			}else{
				System.out.println("Carta: " + a.getElemento(i));
			}
		}
	}

	public Carta remover(Carta c) {
		return lista.remover(c);		
	}

	
	@Override
	public Carta copy() {
		Baralho b = new Baralho(lista, cp, jogador);
		b.lista = this.lista;
		
		return b;
	}

	public Carta getElemento(int i) {
		// TODO Auto-generated method stub
		return lista.getElemento(i);
	}
}
