package entity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import listeners.CartaClickedListener;
import Util.Position;

@SuppressWarnings("serial")
public abstract class Entity  extends JPanel {
	
	protected Position position;

	protected int width, height;

	protected BufferedImage imagem;
	
	protected String nome;
	
	protected char side;
	
	protected CartaClickedListener cartaClickedListener;
	
	public char getSide() {
		return side;
	}

	public void setSide(char side) {
		this.side = side;
	}

	public Entity(int width, int height){
		this.width = width;
		this.height = height;
		
		
		this.setPreferredSize(new Dimension(width, height));
//		this.setLocation(100, 100);
		setImagem(imagem);
	}
	
	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
		
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();
	
		gr.drawImage(this.imagem, 0, 0, null);
		
		
		gr.dispose();
		
	}
	
	public void addCartaClickedListener(CartaClickedListener listener){
		this.cartaClickedListener = listener;
	}
	
	public CartaClickedListener getCartaClickedListener(){
		return this.cartaClickedListener;
	}

	public String getNome() {
		return nome;
	}
}
