package state;

import java.awt.Graphics;

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

	public abstract void draw(Graphics g);
	
	public abstract void repaintComponents();
	
}
