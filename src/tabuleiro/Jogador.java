package tabuleiro;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;
import entity.Carta_Criatura;
import entity.Carta_Especial;
import entity.Carta_Magica;

@SuppressWarnings("serial")
public class Jogador implements UpdaterEntity{
	public static final Position UP_LABEL = new Position(100, 40);
	public static final Position DOWN_LABEL = new Position(100, 720);
	public static final Position UP_REFERENCE = new Position(0,68+17);
	public static final Position DOWN_REFERENCE = new Position(0,400+17);
	public static final int ESPACAMENTO = 80;

	private String nome;
	private String energiaString;
	private String vezString;
	private int energia;
	private boolean vez;
	PlayerPosition playerPosition;
	
	private JLabel jogadorInfo;
	
	private Position position;
	
	///////////////CARTAS////////////////
	
	private Lista_de_Generics<Carta> baralho;
	private Lista_de_Generics<Carta> cemiterio;
	private Lista_de_Generics<Carta_Criatura> field;
	
	private Carta_Especial ED;
	private Carta_Especial OO;
	
	//private TopoBaralho topoBaralho; 
	//private Lista_de_Generics<Carta_Especial> Registro_Especiais;
	
	/////////////////////////////////////

	// tamanho do tabuleiro = 1024 x 380

	private Tabuleiro tabuleiro;
	
	public Jogador(Tabuleiro tabuleiro, Lista_de_Generics<Carta> baralho, PlayerPosition playerPosition) {
		this.setTabuleiro(tabuleiro);
		
		//panel.setBackground(new Color(213, 134, 145, 123)); transluscent color
		
		this.playerPosition = playerPosition;
		popUpPlayerInfoCaller();
		
		///////////////LABEL/////////////////
		/////////////////////////////////////
		this.setJogadorInfo(nome, energia, vez);
		
		
		///////////////CARTAS////////////////
		/////////////////////////////////////
		
		this.baralho = baralho;
		this.baralho = autoCompletar_baralho();
		
		cemiterio = new Lista_de_Generics<Carta>(30);

		field = new Lista_de_Generics<Carta_Criatura>(5);

		//topoBaralho = new TopoBaralho(baralho);
		//Registro_Especiais = new Lista_de_Generics<Carta_Especial>(7);
		
		field.addFim((Carta_Criatura) baralho.getElemento(0));	
		
		/////////////////////////////////////
		/////////////////////////////////////

	}
	
	public void popUpPlayerInfoCaller(){
		do{
			nome = JOptionPane.showInputDialog("Nome Player "+(this.playerPosition.getValor())+": ");
			System.out.println(nome);
			if(nome == null || nome.equals("") || nome.startsWith(" ")){
				if(nome == null){
					nome = "Player "+this.playerPosition.getValor();
				}else{
					JOptionPane.showMessageDialog(tabuleiro, "Nome inválido");
				}	
			}
		}while(nome == null || nome.equals("") || nome.startsWith(" "));
		
		if(this.playerPosition == PlayerPosition.UP_REFERENCE){
			this.vez = false;
			this.setPosition(Jogador.UP_REFERENCE);
		}else{
			this.vez = true;
			this.setPosition(Jogador.DOWN_REFERENCE);
		}
	}
	
	public void setJogadorInfo(String name, int energia, boolean vez){
		if(jogadorInfo == null){
			if(vez){
				this.jogadorInfo = new JLabel("Nome: "+this.nome+" Energia: "+energia+" Vez: Jogando!");
			}else{
				this.jogadorInfo = new JLabel("Nome: "+this.nome+" Energia: "+energia+" Vez: Aguardando");
			}
			Position[]aux = new Position[2];
			aux[0]=UP_LABEL;
			aux[1]=DOWN_LABEL;
			jogadorInfo.setBounds((int)aux[this.playerPosition.getValor()-1].x, (int)aux[this.playerPosition.getValor()-1].y,100,20);
			
			tabuleiro.add(jogadorInfo);
		}else{
			if(vez){
				this.jogadorInfo.setText("Nome: "+this.nome+" Energia: "+energia+" Vez: Jogando!");
			}else{
				this.jogadorInfo.setText("Nome: "+this.nome+" Energia: "+energia+" Vez: Aguardando");
			}
		}
	}

	public JLabel getJogadorInfo(){
		return this.jogadorInfo;
	}
	public void setJogadorVezLabel(boolean vez){
		this.setJogadorInfo(nome, energia, vez);
	}

	public void setVez(boolean vez) {
		this.vez = vez;
		if(vez) {
			this.vezString = "Jogando!";
			
		}else{
			this.vezString = "Aguardando.";
		}
		setJogadorVezLabel(vez);
	}

	public boolean getVez() {return this.vez;}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = "Nome: " + nome;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
		energiaString = "Energia: " + energia;
	}


//	public int calcAtaque(){
//		int atq = 0;
//		
//		atq = this.tabuleiro.calcAtaque();
//		
//		
//		return atq;
//	}
	
	
	public void update() {
		//this.tabuleiro.update();
	}
	
	public void draw(Graphics g){
		
		//drawField(g);
	}
	public void drawCemiterio(Graphics g){
		if(!cemiterio.isEmpty()){
			cemiterio.getElemento(cemiterio.getQtdElementos()).draw(g);
			cemiterio.getElemento(cemiterio.getQtdElementos()).draw(g);
		}
	}
	public void drawField(Graphics g){
		
		if(!field.isEmpty()){
			for(int i = field.length()-1; i > 0; i--){
				Carta_Criatura aux = field.getElemento(i);
				if(aux != null){
					aux.draw(g);
				}
			}
		}
	}
	
	public Lista_de_Generics<Carta> autoCompletar_baralho() {
		
		Lista_de_Generics<Carta> baralhoAux = new Lista_de_Generics<>(30);
		int qtd = baralho.getQtdElementos();
		for (int i = 0; i < qtd; i++) {
			baralhoAux.addFim(baralho.getElemento(i));
		}
			

		Lista_de_Generics<Carta_Magica> magicas = new Lista_de_Generics<Carta_Magica>(10);
		Lista_de_Generics<Carta_Especial> eds = new Lista_de_Generics<Carta_Especial>(5);
		Lista_de_Generics<Carta_Especial> oos = new Lista_de_Generics<Carta_Especial>(5);
		
		qtd = baralhoAux.getQtdElementos();
		for (int i = 0; i < qtd; i++) {
			if (baralhoAux.getElemento(i) != null) {
				
				switch (baralhoAux.getElemento(i).getTipo()) {
				case MAGICA:
					magicas.addFim((Carta_Magica) baralhoAux.remover(i));
					i--;
					break;
				case ED:
					eds.addFim((Carta_Especial) baralhoAux.remover(i));
					i--;
					break;
				case OO:
					oos.addFim((Carta_Especial) baralhoAux.remover(i));
					i--;
					break;
				default:
				}
			}
		}
		
		magicas.embaralhar();
		magicas.fill();
		for (int i = 0; i < magicas.length(); i++) {
			System.out.println(baralhoAux.getQtdElementos());
			baralhoAux.addFim(magicas.removerInicio());
		}
		System.out.println();
		eds.embaralhar();
		eds.fill();
		for (int i = 0; i < eds.length(); i++) {
			System.out.println(baralhoAux.getQtdElementos());
			baralhoAux.addFim(eds.removerInicio());
		}
		System.out.println();
		oos.embaralhar();
		oos.fill();
		for (int i = 0; i < oos.length(); i++) {
			System.out.println(baralhoAux.getQtdElementos());
			baralhoAux.addFim(oos.removerInicio());
		}
		System.out.println();
		System.out.println(baralhoAux.getQtdElementos());
		baralhoAux.embaralhar();
		return baralhoAux;
	}

	public void exibirBaralhoNoConsole(/*Lista_de_Generics<Carta> a*/) {
		Lista_de_Generics<Carta> a = baralho;
		for (int i = 0; i < a.getQtdElementos(); i++) {
			System.out.println("i:" + i);
			if(a.getElemento(i) != null){
				System.out.println("Carta: " + a.getElemento(i).getNome());
			}else{
				System.out.println("Carta: " + a.getElemento(i));
			}
		}
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}
