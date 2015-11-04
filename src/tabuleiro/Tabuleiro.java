package tabuleiro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Game.Game;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;

//All Descriptions URL's.txt

@SuppressWarnings("serial")
public class Tabuleiro extends JPanel implements UpdaterEntity{
	
	public static final Position UP_TAB = new Position(0,52),
								DOWN_TAB = new Position(0,388);
	
	
	//private Importar importar = new Importar();
	private Jogador jogadorA;
	private Jogador jogadorB;
	
	private BufferedImage background;
	
	private Lista_de_Generics<Carta> baralho;
	
	public Tabuleiro() {
		baralho = Importar.importAllCards(Game.FILE);
		Importar.importarBackground(Game.BACKGROUND_FILE);
		background = Importar.getBackground(BackgroundID.JogoBackground);
		

		jogadorA = new Jogador(this, baralho, PlayerPosition.UP_REFERENCE);
		jogadorB = new Jogador(this, baralho, PlayerPosition.DOWN_REFERENCE);
		
		
		
	}
	
	public void draw(Graphics g){
		this.getComponentGraphics(g);
		g.drawImage(background, 0, 0, null);
		jogadorA.draw(g);
		jogadorB.draw(g);
		
		///////////////////////LINE MAKER///////////////////////
		g.setColor(Color.pink);
		
		//FIRST LINE
		g.fillRect( (int) Jogador.UP_REFERENCE.x, (int) Jogador.UP_REFERENCE.y-20, 1024, 4);
		//SECOND (BROKEN) LINE
		g.fillRect( (int) Jogador.DOWN_REFERENCE.x, (int) Jogador.DOWN_REFERENCE.y-20, 353, 4);
		g.fillRect( (int) 700, (int) Jogador.DOWN_REFERENCE.y-20, 355, 4);
		//THIRD LINE
		g.fillRect( (int) Jogador.DOWN_REFERENCE.x, (int) Jogador.DOWN_REFERENCE.y+300, 1024, 4);
		
		g.setColor(Color.black);
		////////////////////////////////////////////////////////
	}

//	public boolean repetida(Carta_Especial c) {
//		for (int i = 0; i < Registro_Especiais.getQtdElementos(); i++) {
//			Carta_Especial C = Registro_Especiais.getElemento(i);
//			if (c.getValor() == C.getValor()) {
//				return true;
//			}
//		}
//		return false;
//	}
	

	
	

	public void update() {
		
		
	}
	

//	public int calcAtaque() {
//		int atq = 0;
//		
//		for(int i = 0 ; i < field.getQtdElementos(); i ++){
//			atq += field.getElemento(i).getAtaque();
//		}
//		
//		return atq ;
//	}
	
	
	
}
