package entity;

public enum Tipo_Carta {
	
	CRIATURA (1),
	MAGICA (2),
	ED (3),
	OO (4),
	BARALHO(5),
	CEMITERIO(6),
	EDGROUND(7),
	OOGROUND(8),
	CAMPOCRIATURA(9),
	CAMPOMAGICA(10);
	
	
	private final int valor;
	
	Tipo_Carta(int valor){
		this.valor = valor;
	}
	public int getValor(){return this.valor;}
}
