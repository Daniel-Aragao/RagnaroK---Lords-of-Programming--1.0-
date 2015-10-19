package entity;

public enum Tipo_Carta {
	
	CRIATURA (1),
	MAGICA (2),
	ED (3),
	OO (4);
	
	
	private final int valor;
	
	Tipo_Carta(int valor){
		this.valor = valor;
	}
	public int getValor(){return this.valor;}
}
