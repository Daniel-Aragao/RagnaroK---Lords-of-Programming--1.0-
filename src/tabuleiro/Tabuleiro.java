package tabuleiro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Game;
import Gr�ficos.MainFrame;
import Input.Mouse;
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
		////////////////////IMPORTAR/////////////////////////////////////
		baralho = Importar.importAllCards(Game.FILE);
		Importar.importarBackground(Game.BACKGROUND_FILE);
		background = Importar.getBackground(BackgroundID.JogoBackground);
		/////////////////////////////////////////////////////////////////
		
		this.setPreferredSize(MainFrame.MainDimension);
		this.setMinimumSize(MainFrame.MainDimension);
		this.setMaximumSize(MainFrame.MainDimension);
		this.setLayout(null);

		jogadorA = new Jogador(this, baralho, PlayerPosition.UP_REFERENCE);
		jogadorB = new Jogador(this, baralho, PlayerPosition.DOWN_REFERENCE);
//		this.add(jogadorA.getJogadorInfo(), BorderLayout.NORTH);
//		this.add(new JLabel("33333333333333333333333333333333333333333333333"), BorderLayout.NORTH);
//		this.addMouseListener(new Mouse());
		
		
		this.setVisible(true);
	}
	@Override
	public void paintComponent(final Graphics g){
		super.paintComponent(g);
		 Graphics gr = g.create();  
		 gr.drawImage(background,0,0,null);
		 
//		g.drawImage(background, 0, 0, null);
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
		gr.dispose();
//		g.dispose();
	}
	
	public void draw(Graphics g){
//		this.getComponentGraphics(g);
		
		//g.drawImage(background, 0, 0, null);
		jogadorA.draw(g);
		jogadorB.draw(g);
		
		
		
	}
	public void repaint(){
		for(Component i: this.getComponents()){
			//System.out.println(i.toString());			
			i.validate();
			i.repaint();
		}
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
		jogadorA.update();
		jogadorB.update();
		
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
