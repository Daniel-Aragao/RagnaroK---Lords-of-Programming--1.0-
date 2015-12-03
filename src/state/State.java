package state;


public abstract class State {
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	
	//abstract states
	public abstract void update();
	
	public abstract void repaintComponents();
	
}
