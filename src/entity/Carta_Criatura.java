package entity;

import java.awt.Graphics;

import state.inGameStates.CreatureAtaqueState;
import state.inGameStates.CreatureDefesaState;
import state.inGameStates.CreatureState;

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
		private CreatureState creatureState;
		
	public Carta_Criatura(int ataque, int defesa, int skill,CartaParameters cp) {
		super(cp);
		// TODO Auto-generated constructor stub
		this.ataque = ataque;
		this.defesa = defesa;
		this.skill = skill;
		this.magica = null;
		this.tab_state = false;
	}
	public CreatureState getState(){
		return creatureState;
	}
	public void ChangeState(){
		if(creatureState != null ){
			
			if(creatureState instanceof CreatureAtaqueState){
				creatureState = new CreatureDefesaState();
			}else{
				creatureState = new CreatureAtaqueState();
			}
		}
	}
	
	public void setState(CreatureState creatureState){
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
}
