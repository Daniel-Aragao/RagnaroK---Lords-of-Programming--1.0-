package tabuleiro;


public class Turno{
	private static boolean letAtack;
	private static boolean letED_OO;
	
	private static boolean pullTime;
	
	
	public Turno(){
		setPullTime(true);
		letAtack = false;
		letED_OO = false;
	}	

	public static boolean isLetAtack() {
		return letAtack;
	}

	public static void setLetAtack(boolean letAtack) {
		Turno.letAtack = letAtack;
	}

	public static boolean isLetED_OO() {
		return letED_OO;
	}

	public static void setLetED_OO(boolean letED_OO) {
		Turno.letED_OO = letED_OO;
	}
	
	public static boolean isPullTime() {
		return pullTime;
	}
	public static void setPullTime(boolean pullTime) {
		Turno.pullTime = pullTime;
	}

	
}
