package entity.cartas_de_topo;

import java.awt.Dimension;
import java.awt.Graphics;

import state.inGameStates.TurnoState;
import listeners.CartaClickedListener;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Criatura;
import entity.Carta_Especial;
import entity.Carta_Magica;

public class Baralho extends Carta{
	
	public final static int TAMANHO_DO_BARALHO = 30;

	private Lista_de_Generics<Carta> lista;
	
	public Baralho(Lista_de_Generics<Carta> lista, CartaParameters cp) {
		super(cp);
		
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
			baralhoAux.addFim(lista.getElemento(i).copy());
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
			baralhoAux.addFim(magicas.removerInicio());
		}
		eds.fill();
		for (int i = 0; i < eds.length(); i++) {
			baralhoAux.addFim(eds.removerInicio());
		}
		oos.fill();
		for (int i = 0; i < oos.length(); i++) {
			baralhoAux.addFim(oos.removerInicio());
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
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
		


	@Override
	public Carta copy() {
		Baralho b = new Baralho(lista, cp);
		b.lista = this.lista;
		
		return b;
	}

	public Carta getElemento(int i) {
		// TODO Auto-generated method stub
		return lista.getElemento(i);
	}
}
