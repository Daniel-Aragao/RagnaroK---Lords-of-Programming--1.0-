package entity.cartas_de_topo;

import handlers.ClickedHandler;

import javax.swing.JOptionPane;

import listeners.MagicSetListener;
import tabuleiro.Jogador;
import tabuleiro.Tabuleiro;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Criatura;
import entity.Carta_ED;
import entity.Carta_Especial;
import entity.Carta_Magica;
import entity.Tipo_Carta;


public class Campo{

	private Lista_de_Generics<Carta_Criatura> lista;
	private int length;
	
	private Baralho baralho;
	private Cemiterio cemiterio;
	private Jogador jogador;
	private OO oo;
	private ED ed;	
	
	private ClickedHandler campoClickedHandler;
	
	public Campo(Baralho baralho, Cemiterio cemiterio, OO oo, ED ed,Jogador jogador) {
		lista = new Lista_de_Generics<Carta_Criatura>(5);
		
		this.baralho = baralho;
		this.cemiterio = cemiterio;
		this.jogador = jogador;
		this.ed = ed;
		this.oo = oo;
		
		
		campoClickedHandler = new ClickedHandler(jogador);
		ed.addCartaClickedListener(campoClickedHandler);
		oo.addCartaClickedListener(campoClickedHandler);
		
		length = 0;
		PisoCreator();
		PutBaralho(); 
		PutCemiterio();
		PutOO();
		PutED();
	}
	
	private void PutED() {
		int x = (int)jogador.getPosition().x;;
		
		int y = (int) this.jogador.getPosition().y;
		
		this.ed.setBounds(x, y, Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
		
		this.jogador.getTabuleiro().add(this.ed);
		
	}

	private void PutOO() {
		int x = (int)jogador.getPosition().x;
		
		int y = (int) this.jogador.getPosition().y + Carta.DEFAULT_CARTA_HEIGHT + Jogador.ESPACAMENTO - 10;
		
		this.oo.setBounds(x, y, Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
		
		this.jogador.getTabuleiro().add(this.oo);
		
	}

	private void PutBaralho() {
		int x = (int) ((Carta.DEFAULT_CARTA_WIDTH + Jogador.ESPACAMENTO)*6 + jogador.getPosition().x);
		
		int y = (int) this.jogador.getPosition().y;
		
		this.baralho.setBounds(x, y, Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
		
		this.jogador.getTabuleiro().add(this.baralho);
		
	}
	
	private void PutCemiterio(){
		int x = (int) ((Carta.DEFAULT_CARTA_WIDTH + Jogador.ESPACAMENTO)*6 + jogador.getPosition().x);
		
		int y = (int) this.jogador.getPosition().y + Carta.DEFAULT_CARTA_HEIGHT + Jogador.ESPACAMENTO - 10;
		
		this.cemiterio.setBounds(x, y, Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
		
		this.jogador.getTabuleiro().add(this.cemiterio);		
		
	}

	private void PisoCreator() {
		while(!lista.isFull()){
			Carta_Criatura aux = getNewCarta_PisoCriatura();
			
			lista.addFim(aux);
			
			aux.setBounds((int) ((Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*(lista.getIndex(aux)+1)
					+ this.jogador.getPosition().x), 
					(int) this.jogador.getPosition().y, 
					Carta.DEFAULT_CARTA_WIDTH, 
					Carta.DEFAULT_CARTA_HEIGHT);
			
			
			addMagicaNoCampo(aux,aux.getMagica());
			
			this.jogador.getTabuleiro().add(aux);
		}		
	}

	public static Carta_Criatura getNewCarta_PisoCriatura(){
				
		CartaParameters cp = new CartaParameters(Tipo_Carta.CAMPOCRIATURA);		
		cp.descricao = "Piso Criaturas";
		cp.imagem = Importar.getBackground(BackgroundID.pisoCriaturas);
		cp.nome = "Piso";
		
		Carta_Criatura criatura = new Carta_Criatura(0,0,0,cp.imagem,cp);
		criatura.addCartaClickedListener(null);
		
		return criatura;
	}
	
	public static Carta_Magica getNewCarta_PisoMagica(){
		
		CartaParameters cp = new CartaParameters(Tipo_Carta.CAMPOMAGICA);		
		cp.descricao = "Piso Magica";
		cp.imagem = Importar.getBackground(BackgroundID.pisoMagicas);
		cp.nome = "Piso";
		
		Carta_Magica magica = new Carta_Magica(cp);
		magica.addCartaClickedListener(null);
		
		return magica;
	}
	
	public boolean addCarta(Carta c){
		if(c instanceof Carta_Criatura){
			addCriaturaNoCampo((Carta_Criatura) c);
		}else if(c instanceof Carta_Especial){
			if(c instanceof Carta_ED){
				return ed.addCard(c);
			}else{
				return oo.addCard(c);
			}
		}
		return true;
	}
	public Carta_Especial RemoveCarta_Especial(Carta_Especial ce){
		Carta_Especial aux = null;
		if(ce instanceof Carta_ED){ // 
			aux = ed.remove(ce);
		}else{
			aux = oo.remove(ce);
		}
		cemiterio.addCarta(aux);
		return aux;
	}
	
	public boolean isFull(){
		if(length >= 5){
			return true;
		}
		return false;
	}
	
	public int safePosition(){
		if(!isFull()){
			for(int i = 0; i < 5; i++){
				if(lista.getElemento(i).getTipo() == Tipo_Carta.CAMPOCRIATURA){
					return i;
				}
			}
		}
		return -1;
	}
	
	public void addCriaturaNoCampo(Carta_Criatura c){
		Tabuleiro tabuleiro = jogador.getTabuleiro();
		Position addPosition = jogador.getPosition();
		
		if(!isFull()){
			
			Carta_Criatura aux = c;
			int safeposition = safePosition();
			removeCriaturaDoCampo(lista.getElemento(safeposition));
			
			
			lista.add(safeposition,aux);
		
			
			aux.setBounds((int) ((Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*(lista.getIndex(aux)+1)
					+ this.jogador.getPosition().x), 
					(int) addPosition.y, 
					Carta.DEFAULT_CARTA_WIDTH, 
					Carta.DEFAULT_CARTA_HEIGHT);
			
			
			aux.addCartaClickedListener(this.campoClickedHandler);
			addMagicaNoCampo(aux,aux.getMagica());
			
			//////////////////////////////////////////////////////////////////
			/////////////////////NEW MAGIC SET LISTENER/////////////////////
			
			aux.setMagicSetListener(new MagicSetListener(){

				@Override
				public void magicaSetada(Carta_Criatura cc, Carta_Magica cm) {
					addMagicaNoCampo(cc, cm);					
				}

				@Override
				public Carta_Criatura switchAtivado(Carta_Criatura cc) {
					Carta_Criatura newCard = null;
					
					for(int i = 0; i < baralho.getQtdElementos(); i++){
						Carta aux = baralho.getElemento(i);
						if(aux instanceof Carta_Criatura){
							newCard = (Carta_Criatura) aux;
							break;
						}
					}
					
					if(newCard != null){
						if(newCard.getSkill()%2 == 0){
							newCard.setAtackMode(cc.isAtackMode());
						}else{
							newCard.setAtackMode(!cc.isAtackMode());
						}
						removeCriaturaDoCampo(cc);
						baralho.remover(newCard);
						addCriaturaNoCampo(newCard);
						return newCard;
					}else{
						JOptionPane.showMessageDialog(cc, "Não existe mais criaturas no deque");
						cc.setMagica(getNewCarta_PisoMagica());
						return null;
					}
				}

				@Override
				public Carta_Criatura forAtivado(Carta_Criatura cc) {
					Carta_Criatura newCard = null;
					
					Lista_de_Generics<Carta_Criatura> criaturas = 
							new Lista_de_Generics<Carta_Criatura>(10);
							
					for(int i = 0; i < baralho.getQtdElementos(); i++){
						Carta aux = baralho.getElemento(i);
						if(aux instanceof Carta_Criatura){
							criaturas.addFim((Carta_Criatura) aux);
						}
					}
					
					if(!criaturas.isEmpty()){
						newCard = criaturas.getElementoRandom();
					}
					
					if(newCard != null){
						if(newCard.getSkill()%2 == 0){
							newCard.setAtackMode(cc.isAtackMode());
						}else{
							newCard.setAtackMode(!cc.isAtackMode());
						}
						removeCriaturaDoCampo(cc);
						baralho.remover(newCard);
						addCriaturaNoCampo(newCard);
						return newCard;
					}else{
						JOptionPane.showMessageDialog(cc, "Não existe mais criaturas no deque");
						cc.setMagica(getNewCarta_PisoMagica());
						return null;
					}
				}
				
			});
			//////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////
			
			tabuleiro.add(aux);
			length++;
		}
	}
		
	public void addMagicaNoCampo(Carta_Criatura cc, Carta_Magica c){
		Tabuleiro tabuleiro = jogador.getTabuleiro();
		Position addPosition = jogador.getPosition();
		Carta_Magica aux;
		
		aux = c;
			
		
		int x = (int) ((Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*(lista.getIndex(cc)+1) + this.jogador.getPosition().x);
		int y = (int) (addPosition.y + Jogador.ESPACAMENTO-10 + Carta.DEFAULT_CARTA_HEIGHT);
		
		if(cc.getMagica() != null){
			removeMagicaDoCampo(cc.getMagica());
		}
		
		aux.setBounds(x, y, Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
		
		if(c.getTipo() != Tipo_Carta.CAMPOMAGICA){
			aux.addCartaClickedListener(this.campoClickedHandler);
		}
		
		tabuleiro.add(aux);
			
	}
	
	public void removeCriaturaDoCampo(Carta_Criatura c){
		c.addCartaClickedListener(null);
		Carta_Criatura novoPiso = getNewCarta_PisoCriatura();
		int posExcluido = 0;
		
		if(c.getTipo() != Tipo_Carta.CAMPOCRIATURA){
			this.cemiterio.addCarta(c);
			novoPiso.setBounds(c.getBounds());
			posExcluido = lista.getIndex(c);
			length--;
		}
		
		removeMagicaDoCampo(c.getMagica());
		c.setMagicSetListener(null); 										// antes ou depois de setar nova magica?
		c.setMagica(Campo.getNewCarta_PisoMagica());
		
		jogador.getTabuleiro().remove(c);
		lista.remover(c);
		
		if(c.getTipo() != Tipo_Carta.CAMPOCRIATURA){
			addCriaturaPisoNoCampo(novoPiso, posExcluido);
		}
		
	}
	
	
	
	private void addCriaturaPisoNoCampo(Carta_Criatura aux, int posExcluido) {
		lista.add(posExcluido,aux);
		
		
		aux.setBounds((int) ((Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*(lista.getIndex(aux)+1)
				+ this.jogador.getPosition().x), 
				(int) jogador.getPosition().y, 
				Carta.DEFAULT_CARTA_WIDTH, 
				Carta.DEFAULT_CARTA_HEIGHT);
		
		jogador.getTabuleiro().add(aux);
		
		addMagicaNoCampo(aux,aux.getMagica());
		
	}

	private void removeMagicaDoCampo(Carta_Magica c) {
		c.addCartaClickedListener(null);
		
		jogador.getTabuleiro().remove(c);
		if(c.getTipo()!= Tipo_Carta.CAMPOMAGICA){
			this.cemiterio.addCarta(c);	
		}
	}

	public int getAtaque() {
		int resultado = 0;

		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
			if(aux.getTipo() != Tipo_Carta.CAMPOCRIATURA){
				if(aux.isAtackMode()){
					resultado += aux.getAtaque(true);
				}
			}
		}
		
		return resultado;
	}
	
	public int[] maioresAtributos(){
		int maiorA = 0, maiorD = 0, maiorS = 0;

		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
			if(aux.getTipo() != Tipo_Carta.CAMPOCRIATURA){
				int ataque = aux.getAtaque(false);
				int defesa = aux.getDefesa(false);
				int skill = aux.getSkill();
				
				if(ataque > maiorA){
					maiorA = ataque;
				}
				if(defesa > maiorD){
					maiorD = defesa;
				}
				if(skill > maiorS){
					maiorS = skill;
				}
			}
		}
		int [] vetor = {maiorA, maiorD, maiorS};
		return vetor;
	}
	public int maiorDefesa(){
		int maior = 0;

		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
			if(aux.getTipo() != Tipo_Carta.CAMPOCRIATURA){
				int defesa = aux.getDefesa(false);
				if(defesa > maior)
				maior = defesa;
			}
		}
		
		return maior;
	}
	public int maiorSkill(){
		int maior = 0;

		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
			if(aux.getTipo() != Tipo_Carta.CAMPOCRIATURA){
				int skill = aux.getSkill();
				if(skill > maior)
				maior = skill;
			}
		}
		
		return maior;
	}

	public boolean isDefended() {
		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
			if(aux.getTipo() != Tipo_Carta.CAMPOCRIATURA){
				if(!aux.isAtackMode()){
					return true;
				}
			}
		}
		return false;
	}

	public boolean canAtack() {
		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
			if(aux.getTipo() != Tipo_Carta.CAMPOCRIATURA){
				if(aux.isAtackMode()){
					return true;
				}
			}
		}
		return false;
	}

	public boolean isEmpty() {
		return lista.isEmpty();
	}
	
	public int getAtaqueAssociação(){
		int resultado = 0;

		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
				resultado += aux.getAtaque(true);
		}
		
		return resultado;
	}
	public int getDefesaAssociação(){
		int resultado = 0;

		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
				resultado += aux.getDefesa(true);
			
		}
		
		return resultado;
	}
	public void polimorfismo(){
		for(int i = 0; i < lista.getQtdElementos(); i ++){
			Carta_Criatura aux = lista.getElemento(i);
				aux.setAtackMode(!aux.isAtackMode());			
		}
	}
}
