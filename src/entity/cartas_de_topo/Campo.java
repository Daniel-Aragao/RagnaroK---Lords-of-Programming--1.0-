package entity.cartas_de_topo;

import handlers.PisoClickedHandler;

import java.awt.Rectangle;

import state.inGameStates.TurnoState;
import tabuleiro.Jogador;
import tabuleiro.Tabuleiro;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Criatura;
import entity.Carta_Magica;
import entity.Tipo_Carta;

public class Campo{
	public static final PisoClickedHandler pisoHandler = new PisoClickedHandler();

	private Lista_de_Generics<Carta_Criatura> lista;
	private int length;
	private Baralho baralho;
	private Cemiterio cemiterio;
	private Jogador jogador;
	
	
	public Campo(Baralho baralho, Cemiterio cemiterio, Jogador jogador) {
		lista = new Lista_de_Generics<Carta_Criatura>(5);
		this.baralho = baralho;
		this.cemiterio = cemiterio;
		this.jogador = jogador;
		length = 0;
		PisoCreator();
		Putbaralho(); 
	}
	
	private void Putbaralho() {
		int x = (Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*6;
		
		int y = (int) this.jogador.getPosition().y;
		
		this.baralho.setBounds(x, y, Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
		
		this.jogador.getTabuleiro().add(this.baralho);
		
	}

	private void PisoCreator() {
		while(!lista.isFull()){
			Carta_Criatura aux = getNewCarta_PisoCriatura();
			
			lista.addFim(aux);
			
		
			
			aux.setBounds((Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*(lista.getIndex(aux)+1), 
					(int) this.jogador.getPosition().y, 
					Carta.DEFAULT_CARTA_WIDTH, 
					Carta.DEFAULT_CARTA_HEIGHT);
			
			
			addMagicaNoCampo(aux,aux.getMagica(),this.jogador.getPosition(),this.jogador.getTabuleiro());
			
			this.jogador.getTabuleiro().add(aux);
		}		
	}

	public static Carta_Criatura getNewCarta_PisoCriatura(){
				
		CartaParameters cp = new CartaParameters(Tipo_Carta.CAMPOCRIATURA);		
		cp.descricao = "Piso Criaturas";
		cp.imagem = Importar.getBackground(BackgroundID.pisoCriaturas);
		cp.nome = "Piso";
		
		Carta_Criatura criatura = new Carta_Criatura(0,0,0,cp);
		criatura.addCartaClickedListener(pisoHandler);
		
		return criatura;
	}
	
	public static Carta_Magica getNewCarta_PisoMagica(){
		
		CartaParameters cp = new CartaParameters(Tipo_Carta.CAMPOMAGICA);		
		cp.descricao = "Piso Magica";
		cp.imagem = Importar.getBackground(BackgroundID.pisoMagicas);
		cp.nome = "Piso";
		
		Carta_Magica magica = new Carta_Magica(cp);
		magica.addCartaClickedListener(pisoHandler);
		
		return magica;
	}
	
	public void addPiso(Carta c){
		
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
	
	public void addCriaturaNoCampo(Carta_Criatura c,Position addPosition, Tabuleiro tabuleiro){
		
		if(!isFull()){
			
			Carta_Criatura aux;
			aux = (Carta_Criatura)this.baralho.remover(c);
			int safeposition = safePosition();
			removeCriaturaDoCampo(lista.getElemento(safeposition),tabuleiro);
			
			
			lista.add(safeposition,aux);
		
			
			aux.setBounds((Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*(lista.getIndex(aux)+1), 
					(int) addPosition.y, 
					Carta.DEFAULT_CARTA_WIDTH, 
					Carta.DEFAULT_CARTA_HEIGHT);
			
			aux.addCartaClickedListener(TurnoState.CLICKED_HANDLER);
			addMagicaNoCampo(aux,aux.getMagica(),addPosition,tabuleiro);
			
			tabuleiro.add(aux);
			length++;
		}
	}
		
	public void addMagicaNoCampo(Carta_Criatura cc, Carta_Magica c,Position addPosition, Tabuleiro tabuleiro){
		Carta_Magica aux;
		if(c.getTipo() == Tipo_Carta.CAMPOMAGICA){
			aux = c;
		}else{
			aux  = (Carta_Magica)this.baralho.remover(c);
			
			
		}		
		
		int x = (Carta.DEFAULT_CARTA_WIDTH+Jogador.ESPACAMENTO)*(lista.getIndex(cc)+1);
		int y = (int) (addPosition.y + Jogador.ESPACAMENTO-10 + Carta.DEFAULT_CARTA_HEIGHT);
		
		if(tabuleiro.getComponentAt(x, y) != null){
			tabuleiro.remove(tabuleiro.getComponentAt(x, y));
		}
		
		aux.setBounds(x, y, Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
		
		if(c.getTipo() != Tipo_Carta.CAMPOMAGICA){
			aux.addCartaClickedListener(TurnoState.CLICKED_HANDLER);
		}
		
		tabuleiro.add(aux);
			
	}
	
	public void removeCriaturaDoCampo(Carta_Criatura c,Tabuleiro tabuleiro){
		c.addCartaClickedListener(null);
		
		if(c.getTipo() != Tipo_Carta.CAMPOCRIATURA){
			this.cemiterio.addCarta(c);
			lista.remover(c);
			length--;
		}
		
		removeMagicaDoCampo(c.getMagica(),tabuleiro);
		c.setMagica(null);
		
		//Rectangle aux = c.getBounds();
		tabuleiro.remove(c);
		lista.remover(c);
		//addPisoCriatura(aux);
	}
	
	
	
	private void removeMagicaDoCampo(Carta_Magica c, Tabuleiro tabuleiro) {
		c.addCartaClickedListener(null);
		
		tabuleiro.remove(c);
		if(c.getTipo()!= Tipo_Carta.CAMPOMAGICA){
			this.cemiterio.addCarta(c);	
		}
	}
}
