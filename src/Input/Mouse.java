package Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Gráficos.SideFrames.handPanels.HandCommandPanel;
import entity.Carta;
import entity.Entity;

public class Mouse extends MouseAdapter/*implements MouseListener, MouseMotionListener, MouseAdapter*/{
	private int x, y;
	private boolean clicked = false, pressed = false;
	
	
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
//		System.out.println(me.getX()+ ", "+ me.getY());
		
		
		if(me.getSource() instanceof Entity){
			Entity entidade = (Entity)me.getSource();
			if(entidade.getCartaClickedListener() != null){
				entidade.getCartaClickedListener().CardClicked(entidade);
			}
		}
		
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
		
		if(me.getSource() instanceof Carta){
			Carta aux = (Carta)me.getSource(); 
			if(aux.getCartaClickedListener() != null)
			aux.getCartaClickedListener().CardHoover(aux, true);
			
		}		
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		if(me.getSource() instanceof Carta){
			Carta aux = (Carta)me.getSource(); 
			if(aux.getCartaClickedListener() != null)
			aux.getCartaClickedListener().CardHoover(aux, false);
			
			
			
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
//		System.out.println(me.getX()+ ", "+ me.getY());
		this.setPressed(true);
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
//		carta.getCartaClickedListener().CardClicked(carta);
//		this.pressed = false;
//		this.clicked = false;
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

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

}
