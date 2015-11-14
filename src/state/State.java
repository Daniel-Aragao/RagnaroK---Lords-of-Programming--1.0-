package state;

import java.awt.Graphics;

import tabuleiro.UpdaterEntity;

public abstract class State implements UpdaterEntity{
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
