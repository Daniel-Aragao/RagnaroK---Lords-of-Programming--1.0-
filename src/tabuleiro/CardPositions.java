package tabuleiro;

import Util.Position;

public class CardPositions {
	private Jogador jogador;
	
	public Position 
	ED, 
	POS1, 
	POS2, 
	POS3, 
	POS4, 
	POS5, 
	BAR, 
	OO, 
	MPOS1, 
	MPOS2, 
	MPOS3, 
	MPOS4, 
	MPOS5, 
	CEM;
	
	
	public CardPositions(Jogador jogador){
		this.jogador = jogador;
		
		createPositions();
	}


	private void createPositions() {
		ED = new Position(0,jogador.getPosition().y);
		POS1 = new Position(150,jogador.getPosition().y); 
		POS2 = new Position(300,jogador.getPosition().y); 
		POS3 = new Position(450,jogador.getPosition().y); 
		POS4 = new Position(600,jogador.getPosition().y); 
		POS5 = new Position(750,jogador.getPosition().y); 
		BAR = new Position(900,jogador.getPosition().y); 
		
		OO = new Position(0,jogador.getPosition().y + 150); 
		MPOS1 = new Position(150,jogador.getPosition().y + 150); 
		MPOS2 = new Position(300,jogador.getPosition().y + 150); 
		MPOS3 = new Position(450,jogador.getPosition().y + 150); 
		MPOS4 = new Position(600,jogador.getPosition().y + 150); 
		MPOS5 = new Position(750,jogador.getPosition().y + 150); 
		CEM = new Position(900,jogador.getPosition().y + 150);
		
	}
	
}
