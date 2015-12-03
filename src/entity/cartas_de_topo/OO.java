package entity.cartas_de_topo;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import tabuleiro.Jogador;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_OO;

@SuppressWarnings("serial")
public class OO extends Carta_OO{
	private Lista_de_Generics<Carta_OO> registro;
	private Carta_OO carta;

		//Padr�o OO
		private BufferedImage imagemPadr�o;
		private String nomePadr�o;
		private String descri��oPadr�o;
	
	
	public OO(CartaParameters cp){
		super(cp);
		registro = new Lista_de_Generics<Carta_OO>(5);
		imagemPadr�o = imagem;
		
		
	}
	public void registrar(Carta_OO c){
		registro.addFim(c);
	}
	
	public boolean usada(Carta_OO c){
		for(int i = 0 ; i < registro.getQtdElementos(); i++){
			Carta_OO aux = registro.getElemento(i);
			if(aux.getNome().equals(c.getNome())){
				return true;
			}
		}
		return false;
	}
	@Override
	public Carta copy() {
		return null;
	}
	public boolean addCard(Carta c) {
		if(usada((Carta_OO) c)) {
			JOptionPane.showMessageDialog(c, "Carta j� utilizada, descarte-a");
			return false;
		}
		if(carta != null){
			JOptionPane.showMessageDialog(c, "J� existe uma carta OO posicionada");
			return false;
		}
		
		setCarta((Carta_OO) c);
		
		registrar((Carta_OO) c);
		imagem = c.getImagem();
		nome = carta.getNome();
		descricao = carta.getDescricao();
		
		
		
		return true;		
	}
	public Carta_OO remove(Carta c){
		carta.addCartaClickedListener(null);
		
		Carta_OO aux = carta;
		setCarta(null);
		imagem = imagemPadr�o;
		nome = nomePadr�o;
		descricao = descri��oPadr�o;
		
		return aux;
	}
	
	public Carta_OO getCarta() {
		return carta;
	}
	private void setCarta(Carta_OO carta) {
		this.carta = carta;
	}
	public void ativarOO(Jogador jogador) {
		String nomeLower = carta.getNome().toLowerCase();
		
		if (nomeLower.equals("associa��o")){
			String[] options = new String[2];
			options[0] = new String("Atacar");
			options[1] = new String("Defender");
		
			if (JOptionPane.showOptionDialog(this,
				"Deseja Atacar ou Defender-se?", "Associa��o",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, null) == 0) {
				
				jogador.getCommandListener().assossia��oAtaque(jogador);
				
			}else{				
				
				jogador.setAssossia��o(jogador.getCampo().getDefesaAssocia��o());
			}
			
		}else if(nomeLower.equals("polimorfismo")){
			jogador.getCommandListener().polimorfismo(jogador);
			
		}else if(nomeLower.equals("heran�a")){
			jogador.getCommandListener().heran�a(jogador);
			
		}else if(nomeLower.equals("encapsulamento")){
			jogador.getCommandListener().encapsulamento(jogador);

		}
		
	}
	
	
}
