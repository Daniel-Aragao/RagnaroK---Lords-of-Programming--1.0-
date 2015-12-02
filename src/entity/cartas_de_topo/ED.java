package entity.cartas_de_topo;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import handlers.ClickedHandler;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_ED;
import entity.Carta_OO;

public class ED extends Carta_ED{

	private Lista_de_Generics<Carta_ED> registro;
	private Carta_ED carta;
	
	//Padrão ED
	private BufferedImage imagemPadrão;
	private String nomePadrão;
	private String descriçãoPadrão;
	
	
	public ED(CartaParameters cp){
		super(cp);
		registro = new Lista_de_Generics<Carta_ED>(5);
		
		imagemPadrão = imagem;
		nomePadrão = nome;
		descriçãoPadrão = descricao;
		
	}
	
	
	public void registrar(Carta_ED c){
		registro.addFim(c);
	}
	
	public boolean usada(Carta_ED c){
		if(registro.contains(c)){
			return true;
		}
		return false;
	}


	public boolean addCard(Carta c) {
		if(usada((Carta_ED) c)) {
			JOptionPane.showMessageDialog(c, "Carta já utilizada, descarte-a");
			return false;
		}
		if(carta != null){
			JOptionPane.showMessageDialog(c, "Já existe uma carta ED posicionada");
			return false;
		}
		
		setCarta((Carta_ED) c);
		
		registrar((Carta_ED) c);
		imagem = c.getImagem();
		nome = carta.getNome();
		descricao = carta.getDescricao();
		
		
		
		return true;		
	}
	public Carta_ED remove(Carta c){
		c.addCartaClickedListener(null);
		
		setCarta(null);
		imagem = imagemPadrão;
		nome = nomePadrão;
		descricao = descriçãoPadrão;
		
		return (Carta_ED) c;
	}
	
	@Override
	public Carta copy() {
		// TODO Auto-generated method stub
		return null;
	}


	public Carta_ED getCarta() {
		return carta;
	}


	public void setCarta(Carta_ED carta) {
		this.carta = carta;
	}


	public void ativarED(Baralho baralho, Cemiterio cemiterio) {
		String nomeLower = carta.getNome().toLowerCase();
		Lista_de_Generics<Carta> listaC = cemiterio.getLista();
		Lista_de_Generics<Carta> listaB = baralho.getBaralho();
		
		if(nomeLower.equals("lista")){
			for(int i = 0; i < 3; i++){
				if(!listaC.isEmpty()){
					listaB.addInicio(listaC.remover(listaC.getElementoRandom()));
				}
			}
			
		}else if(nomeLower.equals("fila")){
			for(int i = 0; i < 3; i++){
				if(!listaC.isEmpty()){
					listaB.addFim(listaC.remover(listaC.getElemento(listaC.getQtdElementos())));
				}
			}
			
		}else if(nomeLower.equals("pilha")){
			for(int i = 0; i < 3; i++){
				if(!listaC.isEmpty()){
					listaB.addInicio(listaC.remover(listaC.getElemento(0)));
				}
			}
		}
		
	}
}
