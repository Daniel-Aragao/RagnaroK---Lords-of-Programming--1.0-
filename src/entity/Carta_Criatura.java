package entity;

import java.awt.Graphics;

import entity.cartas_de_topo.Campo;
import state.inGameStates.AtaqueState;
import state.inGameStates.DefesaState;
import state.inGameStates.TurnoState;

@SuppressWarnings("serial")
public class Carta_Criatura extends Carta {
	// Pontos da Carta
		private int ataque;
		private int defesa;
		private int skill;
		private int valor_do_modo;
		private boolean tab_state; //true-on deck false-on tab
		
		////////// TAB/Deque State
		
	// Carta Mágica Associada
		private Carta_Magica magica ;
		
	// Estado da Carta quanto a ATQ-true ou DEF-false
		private TurnoState creatureState;
		
	public Carta_Criatura(int ataque, int defesa, int skill,CartaParameters cp) {
		super(cp);
		// TODO Auto-generated constructor stub
		this.ataque = ataque;
		this.defesa = defesa;
		this.skill = skill;
		this.setMagica(null);
		this.tab_state = false;
	}
	public TurnoState getState(){
		return creatureState;
	}
	public void ChangeState(){
//		if(creatureState != null ){
//			
//			if(creatureState instanceof AtaqueState){
//				creatureState = new DefesaState();
//			}else{
//				creatureState = new AtaqueState();
//			}
//		}
	}
	
	public void setState(TurnoState creatureState){
		this.creatureState = creatureState;
	}
	
	public boolean gettab_state(){ return tab_state;}
	public void changeTab_state(){ tab_state = !tab_state;}
	
	public int getAtaque() {
		int atq = 0;
		
		//atq = this.magica.getEfeito();
		
		return atq;
	}

	public int getDefesa() {
		return defesa;
	}

	public int getSkill() {
		return skill;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	public Carta_Magica getMagica() {
		return magica;
	}
	
	public void setMagica(Carta_Magica magica) {
		if(magica == null){
			this.magica = Campo.getNewCarta_PisoMagica();
		}else{
			this.magica = magica;			
		}
	}
	@Override
	public Carta copy() {
		Carta_Criatura aux = new Carta_Criatura(ataque, defesa, skill, cp);
		return aux;
	}
}
