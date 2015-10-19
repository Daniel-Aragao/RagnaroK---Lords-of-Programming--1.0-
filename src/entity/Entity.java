package entity;

import java.awt.Graphics;

import Util.Position;

public abstract class Entity {
	
	protected Position position;
	protected int width, height;

	public Entity(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
}
