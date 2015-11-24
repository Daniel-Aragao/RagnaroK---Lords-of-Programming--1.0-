package entity;

import listeners.MagicaAdicionadaListener;
import listeners.SelectedListener;
import state.inGameStates.TurnoState;
import entity.cartas_de_topo.Campo;

@SuppressWarnings("serial")
public class Carta_Criatura extends Carta{
	// Pontos da Carta
		private int ataque;
		private int defesa;
		private int skill;
		private boolean atackMode;
		
	// Carta Mágica Associada
		private Carta_Magica magica ;
		
		private MagicaAdicionadaListener magicaAddedListener;
		
	



	public Carta_Criatura(int ataque, int defesa, int skill,CartaParameters cp) {
		super(cp);
		// TODO Auto-generated constructor stub
		this.ataque = ataque;
		this.defesa = defesa;
		this.skill = skill;
		this.setMagica(null);
		this.setModo_de_ataque(true);
	}
	
	
	
	public int getAtaque() {
		int atq = this.ataque;
		
		//atq = this.magica.getEfeito();
		
		return atq;
	}

	public int getDefesa() {
		return defesa;
	}

	public int getSkill() {
		return skill;
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
		
		if(this.getMagicaAddedListener() != null){
			magicaAddedListener.magicaSetada(this, magica);
		}
	}
	public MagicaAdicionadaListener getMagicaAddedListener() {
		return magicaAddedListener;
	}
	
	public void setMagicaAddedListener(MagicaAdicionadaListener magicaAddedListener) {
		this.magicaAddedListener = magicaAddedListener;
	}
	
	@Override
	public Carta copy() {
		Carta_Criatura aux = new Carta_Criatura(ataque, defesa, skill, cp);
		return aux;
	}
	
	public boolean isModo_de_ataque() {
		return atackMode;
	}
	public void setModo_de_ataque(boolean modo_de_ataque) {
		this.atackMode = modo_de_ataque;
	}
}
