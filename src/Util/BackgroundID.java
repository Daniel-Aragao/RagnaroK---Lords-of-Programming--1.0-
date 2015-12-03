package Util;

public enum BackgroundID {

	InicioBackground(0),
	JogoBackground(1),
	mao(2),
	RegrasBackground(3),
	pisoCemitério(4),
	pisoCriaturas(5),
	pisoED(6),
	pisoMagicas(7),
	pisoOO(8),
	verso(9),
	baralho(10),
	icone(11),
	selecionarCarta(12),
	escudo(13),
	espada(14),
	maoIco(15),
	hpJogador(16),
	bafome(17),
	foice(18),
	gameover(19);
	
	private final int index;
	
	BackgroundID(int index){
		this.index = index;
	}
	public int getindex(){return this.index;}
}
