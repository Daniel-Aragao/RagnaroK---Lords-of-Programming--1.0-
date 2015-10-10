package Deque;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Util.Importar;
import Util.Lista_de_Generics;

import Game.Game;

//All Descriptions URL's.txt

@SuppressWarnings("serial")
public class Tabuleiro extends JPanel {
	
	
	private Lista_de_Generics<Carta> baralho;
	private Lista_de_Generics<Carta> cemitério;
	private Lista_de_Generics<Carta_Criatura> T_Personagens;
	private Carta_Especial ED;
	private Carta_Especial OO;
	private Lista_de_Generics<Carta_Especial> Registro_Especiais;
	private Importar importar = new Importar();
	private Carta BlankSpace;

	public Tabuleiro(Lista_de_Generics<Carta> baralho) {
//		importar.importAllCards(Game.FILE);;
		this.baralho = baralho;
//		exibirBaralhoNoConsole();
		this.baralho = autoCompletar_baralho();

		cemitério = new Lista_de_Generics<Carta>(30);

		T_Personagens = new Lista_de_Generics<Carta_Criatura>(5);

		Registro_Especiais = new Lista_de_Generics<Carta_Especial>(7);
		// ED = new Carta_Especial("", null, Tipo_Carta.ENCAPSULAMENTO);
		// Registro_Especiais.addFim(ED);

		setLayout(new GridLayout(2, 7));
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
			baralhoAux.addFim(baralho.removerFim());
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
}
