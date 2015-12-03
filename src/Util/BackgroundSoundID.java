package Util;

public enum BackgroundSoundID {
	
//	creditos(0),
//	menu(1),
//	gameOver(2),
//	game(3);
	
	game(0),
	menu(1);
	
	private final int index;
	

	BackgroundSoundID(int index){
		this.index = index;
	}
	public int getindex(){return this.index;}
}
