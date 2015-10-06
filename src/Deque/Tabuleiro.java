package Deque;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;

import Util.Importar;
import Util.Lista_de_Generics;
//All Descriptions URL's.txt
@SuppressWarnings("serial")
public class Tabuleiro extends JPanel{
	private Lista_de_Generics<Carta> baralho;
	private Lista_de_Generics<Carta> cemitério;
	private Lista_de_Generics<Carta_Criatura> T_Personagens;
	private Carta_Especial ED;
	private Carta_Especial OO;
	private Lista_de_Generics<Carta_Especial>Registro_Especiais;
	private Importar importar = new Importar();
	
	private int energia;
	
	public int getEnergia() {
		return energia;
	}
	private void setEnergia(int energia) {
		// private para edição de setJogador
		this.energia = energia;
	}
	public Tabuleiro(){
		energia = 150;
		this.baralho = importar.importAllCards(new File(""));
		cemitério = new Lista_de_Generics<Carta>(30);
		T_Personagens = new Lista_de_Generics<Carta_Criatura>(5);
		
		setLayout(new GridLayout(2, 7));
		
		
		Registro_Especiais = new Lista_de_Generics<Carta_Especial>(7);
//		ED = new Carta_Especial("", null, Tipo_Carta.ENCAPSULAMENTO);
//		Registro_Especiais.addFim(ED);
	}
	
	public boolean repetida(Carta_Especial c){
		for(int i = 0; i < Registro_Especiais.getQtdElementos();i++){
			Carta_Especial C = Registro_Especiais.getElemento(i);
			if(c.getValor() == C.getValor()){
				return true;
			}
		}
		return false;
	}

	public Lista_de_Generics<Carta> correcao_de_baralho(Lista_de_Generics<Carta> Baralho){
		Lista_de_Generics<Carta> baralhoAux = new Lista_de_Generics<Carta>(30);
		for(int i = 0; i < Baralho.getQtdElementos(); i++){
			baralhoAux.add(i, Baralho.getElemento(i));
		}
		// adicionar no baralho aux as 9 cartas que completam o baralho de 30 cartas
		
		return baralhoAux;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
