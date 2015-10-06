package Deque;

import java.awt.image.BufferedImage;

public class Carta_Criatura extends Carta {
	// Pontos da Carta
		private int ataque;
		private int defesa;
		private int skill;
		
	// Carta Mágica Associada
		private Carta_Magica magica ;
		
	// Estado da Carta quanto a ATQ-true ou DEF-false
		private boolean estado;
		
	public Carta_Criatura(String nome,String descricao, int ataque, int defesa, int skill,
			BufferedImage imagem) {
		super(nome, descricao, imagem);
		// TODO Auto-generated constructor stub
		this.ataque = ataque;
		this.defesa = defesa;
		this.skill = skill;
		this.magica = null;
	}
	
	public int getAtaque() {
		return ataque;
	}

	public int getDefesa() {
		return defesa;
	}

	public int getSkill() {
		return skill;
	}
}
