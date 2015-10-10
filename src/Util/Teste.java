package Util;

import Deque.Tabuleiro;

public class Teste {

	public static void main(String[] args) {
		Lista_de_Generics<Integer> lista = new Lista_de_Generics<Integer>(5);
		lista.addInicio(3);
		lista.addFim(lista.getElemento(0));
		lista.setElemento(1, lista.getElemento(1)+1);
		Lista_de_Generics<Integer> lista2 = lista.copy();
		lista2.add(0, 2);
		System.out.println(lista.toString());
		System.out.println(lista2.toString());
	}

}
