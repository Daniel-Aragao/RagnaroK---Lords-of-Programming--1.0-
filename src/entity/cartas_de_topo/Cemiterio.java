package entity.cartas_de_topo;

import java.awt.Graphics;

import handlers.ClickedHandler;
import tabuleiro.Jogador;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;

@SuppressWarnings("serial")
public class Cemiterio extends Carta{
	private Lista_de_Generics<Carta> lista;
	
	
	private Jogador jogador;

	public Cemiterio(CartaParameters cp, Jogador jogador) {
		super(cp);
		lista = new Lista_de_Generics<Carta>(30);
		
		this.width += 10;
		
		this.jogador = jogador;
		
		this.addCartaClickedListener(new ClickedHandler(this.jogador));
		
	}



	public void addCarta(Carta c) {
		if(c!=null){
			lista.addFim(c);
			this.revalidate();
			this.repaint();
		}
		
	}
	public Carta remove(Carta c){
		if(c != null && !lista.isEmpty()){
			return lista.remover(c);
		}
		return null;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();
	
		if(!lista.isEmpty()){
			gr.drawImage(this.imagem, 0, 0,this.fantasy_CARTA_WIDTH,this.fantasy_CARTA_HEIGHT, null);
			Carta a = lista.getElemento(lista.getQtdElementos()-1);
			gr.drawImage(a.getImagem(), 0, 0,a.fantasy_CARTA_WIDTH-10,a.fantasy_CARTA_HEIGHT, null);
		}else{
			gr.drawImage(this.imagem, 0, 0,this.fantasy_CARTA_WIDTH,this.fantasy_CARTA_HEIGHT, null);
			
		}
		gr.dispose();
		
	}


	@Override
	public Carta copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Lista_de_Generics<Carta> getLista() {
		return lista;
	}
}
