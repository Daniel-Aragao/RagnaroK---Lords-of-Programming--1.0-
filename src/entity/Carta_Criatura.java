package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Carta_Criatura extends Carta {
	// Pontos da Carta
		private int ataque;
		private int defesa;
		private int skill;
		private int valor_do_modo;
		private boolean modo; //true-ataque false-defesa
		private boolean tab_state; //true-on deck false-on tab
		
	// Carta Mágica Associada
		private Carta_Magica magica ;
		
	// Estado da Carta quanto a ATQ-true ou DEF-false
		private boolean estado;
		
	public Carta_Criatura(int ataque, int defesa, int skill,CartaParameters cp) {
		super(cp);
		// TODO Auto-generated constructor stub
		this.ataque = ataque;
		this.defesa = defesa;
		this.skill = skill;
		this.magica = null;
		this.modo = true;
		this.tab_state = true;
	}
	public boolean getModo(){return modo;}
	private void ChangeModo(){ modo = !modo;}
	public boolean gettab_state(){ return tab_state;}
	public void changeTab_state(){ tab_state = !tab_state;}
	
	public int getAtaque() {
		return ataque;
	}

	public int getDefesa() {
		return defesa;
	}

	public int getSkill() {
		return skill;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(imagem, (int)position.x, (int)position.y, width, height, null);
		if(magica != null){
			magica.draw(g);
		}
	}
}
