package Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entity.Carta;
import listeners.CartaClickedListener;

public class Mouse extends MouseAdapter/*implements MouseListener, MouseMotionListener, MouseAdapter*/{
	private int x, y;
	private boolean clicked = false, pressed = false;
	private Carta carta;
	
	public Mouse(Carta c){
		this.x = 0;
		this.y = 0;
		System.out.println("Mouse Constructor");
		this.carta = c;
	}
	public Mouse(){
		this.x = 0;
		this.y = 0;
		System.out.println("Mouse Constructor");
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	public boolean update(){System.out.println("Update"); return clicked;}
	
	@Override
	public void mouseClicked(MouseEvent me) {	
		System.out.println(me.getX()+ ", "+ me.getY());
		
		if(carta!=null)
		carta.getCartaClickedListener().CardClicked(carta);
		
		this.clicked = true;
	}
	
	public boolean buttonClicked(){
		System.out.println("click");
		boolean c = this.clicked;
		this.clicked = false;
		return c;
	}
	
	@Override
	public void mouseEntered(MouseEvent me) {
		if(carta!=null)
		carta.getCartaClickedListener().CardHoover(carta, true);
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		if(carta!=null)
		carta.getCartaClickedListener().CardHoover(carta, false);
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		System.out.println(me.getX()+ ", "+ me.getY());
		this.pressed = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
//		cartaClickedListener.CardClicked(carta);
		this.pressed = false;
		this.clicked = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(this.x+" "+ this.y);
		this.x = e.getX();
		this.y = e.getY();
		
	}

}
