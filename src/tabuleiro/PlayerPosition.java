package tabuleiro;

public enum PlayerPosition {
	UP_REFERENCE(0),
	DOWN_REFERENCE(1);
	
	private final int valor;
	
	PlayerPosition(int valor){
		this.valor = valor;
	}
	public int getValor(){return this.valor;}
}
