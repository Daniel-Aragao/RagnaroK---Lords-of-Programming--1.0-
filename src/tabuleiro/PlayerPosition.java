package tabuleiro;

public enum PlayerPosition {
	UP_REFERENCE(1),
	DOWN_REFERENCE(2);
	
	private final int valor;
	
	PlayerPosition(int valor){
		this.valor = valor;
	}
	public int getValor(){return this.valor;}
}
