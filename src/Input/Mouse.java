package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{
	private int x, y;
	private boolean clicked = false, pressed = false;
	public Mouse(){
		this.x = 0;
		this.y = 0;
	}
	public int getX(){return x;}
	public int getY(){return y;}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {	this.clicked = true;}
	public boolean buttonClicked(){
		boolean c = this.clicked;
		this.clicked = false;
		return c;
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {this.pressed = true;}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		this.pressed = false;
		this.clicked = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		
	}

}
