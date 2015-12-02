package entity;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import listeners.MagicSetListener;
import entity.cartas_de_topo.Campo;

@SuppressWarnings("serial")
public class Carta_Criatura extends Carta {
	// Pontos da Carta
	private int ataque;
	private int defesa;
	private int skill;
	private boolean atackMode;
	private boolean herançaModeOn;
	private BufferedImage defImage;
	private BufferedImage atqImage;

	// Carta Mágica Associada
	private Carta_Magica magica;
	private MagicSetListener magicSetListener;

	
	public Carta_Criatura(int ataque, int defesa, int skill,BufferedImage defImage, CartaParameters cp) {
		super(cp);
		// TODO Auto-generated constructor stub
		this.ataque = ataque;
		this.defesa = defesa;
		this.skill = skill;
		atqImage = this.imagem;
		this.defImage = defImage; 
		this.magica = Campo.getNewCarta_PisoMagica();
		setAtackMode(true);
		setHerançaModeOn(false);
	}

	// public int getActionResult(boolean vez){
	// if(vez)return getAtaque();
	// return getDefesa();
	// }
	
	public int getAtaqueValue(){
		return this.ataque;
	}

	public int getAtaque(boolean atacando) {
		int atq = this.ataque;

		if (this.magica != null) {
			String lowerNome = this.magica.getNome().toLowerCase();
			if(atacando){
				if (lowerNome.equals("if")) {
					
					String[] options = new String[2];
					options[0] = new String("ATQ");
					options[1] = new String("DEF");
					
					if (JOptionPane.showOptionDialog(this,
							"Utilizar ATQ ou DEF neste ataque?", "IF",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, null) != 0) {
						
						atq = this.defesa;
					}
					
				}else if(lowerNome.equals("switch")){
					
					String[] options = new String[2];
					options[0] = new String("Ativar!");
					options[1] = new String("Não!");
					
					if (JOptionPane.showOptionDialog(this,
							"Ativar Switch?", "SWITCH",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, null) != 0) {
						
						magicSetListener.swtichAtivado(this);
					}			
					
					
				}else if(lowerNome.equals("for")){
					
					String[] options = new String[2];
					options[0] = new String("Ativar!");
					options[1] = new String("Não!");
					
					if (JOptionPane.showOptionDialog(this,
							"Ativar For?", "FOR",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, null) != 0) {
						
						magicSetListener.forAtivado(this);
					}	
					
				}
			}	
			
			if(lowerNome.equals("while")){
				atq = atq * this.skill;
				
			}
		}

		return atq;
	}

	public int getDefesa(boolean defendendo) {
		int def = this.defesa;
		
		if(this.magica != null){
			String lowerNome = this.magica.getNome().toLowerCase();
			if(!lowerNome.contains("piso")){
				if(defendendo){
					
					if(lowerNome.equals("if")){
					
						String[] options = new String[2];
						options[0] = new String("ATQ");
						options[1] = new String("DEF");
					
						if (JOptionPane.showOptionDialog(this,
							"Utilizar ATQ ou DEF nesta defesa?", "IF",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null) == 0) {
							
							def = this.ataque;
						}
					
					}else if(lowerNome.equals("switch")){
						
						String[] options = new String[2];
						options[0] = new String("Ativar!");
						options[1] = new String("Não!");
						
						if (JOptionPane.showOptionDialog(this,
								"Ativar Switch?", "SWITCH",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null) != 0) {
							
							magicSetListener.swtichAtivado(this);
						}	
						
					}else if(lowerNome.equals("for")){
						
						String[] options = new String[2];
						options[0] = new String("Ativar!");
						options[1] = new String("Não!");
						
						if (JOptionPane.showOptionDialog(this,
								"Ativar For?", "FOR",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null) != 0) {
							
							magicSetListener.forAtivado(this);
						}	
						
					}
				}
				
				if(lowerNome.equals("while")){
					def = this.defesa*this.skill;
					
				} 
				
			}
		}
		return def;
	}

	public int getSkill() {
		return skill;
	}

	public Carta_Magica getMagica() {
		return magica;
	}

	public void setMagica(Carta_Magica magica) {
		
		if(magicSetListener != null){
			this.magicSetListener.magicaSetada(this, magica);
		}

		this.magica = magica;
		
	}

	

	@Override
	public Carta copy() {
		Carta_Criatura aux = new Carta_Criatura(ataque, defesa, skill,defImage, cp);
		return aux;
	}

	public boolean isAtackMode() {
		return atackMode;
	}

	public void setAtackMode(boolean atackMode) {
		this.atackMode = atackMode;
		if(atackMode){
			this.imagem = this.atqImage;
		}else{
			this.imagem = this.defImage;
		}
	}

	public MagicSetListener getMagicSetListener() {
		return magicSetListener;
	}

	public void setMagicSetListener(MagicSetListener magicSetListener) {
		this.magicSetListener = magicSetListener;
	}

	public boolean isHerançaModeOn() {
		return herançaModeOn;
	}

	public void setHerançaModeOn(boolean herançaModeOn) {
		this.herançaModeOn = herançaModeOn;
	}
	
	
}
