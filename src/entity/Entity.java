package entity;

import java.awt.Graphics;

public abstract class Entity {
	
	protected float x, y;
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
}
