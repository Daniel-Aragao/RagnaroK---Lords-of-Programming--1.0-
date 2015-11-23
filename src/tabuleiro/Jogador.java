package tabuleiro;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import listeners.CommandListener;
import listeners.SelectedListener;
import state.inGameStates.AtaqueState;
import state.inGameStates.DefesaState;
import state.inGameStates.TurnoState;
import Gráficos.SideFrames.HandFrame;
import Gráficos.SideFrames.handPanels.HandPanel;
import Gráficos.SideFrames.handPanels.ScrollingCardPanel;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Criatura;
import entity.Carta_Especial;
import entity.Carta_Magica;
import entity.Entity;
import entity.Tipo_Carta;
import entity.cartas_de_topo.Baralho;
import entity.cartas_de_topo.Campo;
import entity.cartas_de_topo.Cemiterio;

@SuppressWarnings("serial")
public class Jogador extends Entity implements SelectedListener{
	public static final Position UP_LABEL = new Position(100, 40);
	public static final Position DOWN_LABEL = new Position(100, 720);
	public static final Position[] VETLABEL = {Jogador.UP_LABEL,Jogador.DOWN_LABEL};	
	public static final Position UP_REFERENCE = new Position(0,68+17);
	public static final Position DOWN_REFERENCE = new Position(0,400+17);
	public static final int ESPACAMENTO = 46;
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	public static final int ENERGIA_INICIAL = 150;

	private String vezString;
	private int energia;
	private boolean vez;
	PlayerPosition playerPosition;
	SemiTransparentPanel semiTransparentPanel;
	private TurnoState turnoState;
	private AtaqueState ataqueState;
	private DefesaState defesaState;
	
	private Position interfacePosition;
	private JLabel jogadorInfo;
	private JButton handButton;
	private HandFrame hand;
	
	public HandFrame getHand() {
		return hand;
	}

	private Position position;
	
	///////////////CARTAS////////////////
	
	private Baralho baralho;
	private Cemiterio cemiterio;
	private Campo campo;
	private Carta_Especial ED;
	private Carta_Especial OO;
	
	//private TopoBaralho topoBaralho; 
	//private Lista_de_Generics<Carta_Especial> Registro_Especiais;
	
	/////////////////////////////////////

	// tamanho do tabuleiro = 1024 x 380

	private Tabuleiro tabuleiro;
	private CommandListener commandListener;
	
	
	public Jogador(Tabuleiro tabuleiro, Lista_de_Generics<Carta> baralho, PlayerPosition playerPosition) {
		super(Jogador.WIDTH,Jogador.HEIGHT);
		this.setTabuleiro(tabuleiro);
		
		//panel.setBackground(new Color(213, 134, 145, 123)); transluscent color
		
		
		this.energia = Jogador.ENERGIA_INICIAL;
		
		this.playerPosition = playerPosition;

		//POP UP 
		popUpPlayerInfoCaller();
		
		if(playerPosition == PlayerPosition.UP_REFERENCE){
			position = Jogador.UP_REFERENCE;
			this.interfacePosition = Jogador.UP_LABEL;
			this.setSide('1');
			vez = true;
		}else{
			position = Jogador.DOWN_REFERENCE;
			this.interfacePosition = Jogador.DOWN_LABEL;
			this.setSide('2');
			vez = false;
		}
		semiTransparentPanel = new SemiTransparentPanel(this.position);
		
		hand = new HandFrame(this);
		
		
		this.setVez(vez);
		
		createButton();
		
		
		///////////////CARTAS////////////////
		/////////////////////////////////////
		cartasCreator(baralho);		
		
		//if(playerPosition == PlayerPosition.UP_REFERENCE){
			Carta_Criatura aux2 = (Carta_Criatura) this.baralho.getElemento(5);
			aux2.setMagica((Carta_Magica) this.baralho.getElemento(10));
			campo.addCriaturaNoCampo(aux2,this.position,tabuleiro);
			
			aux2 = (Carta_Criatura) this.baralho.getElemento(5);
			aux2.setMagica((Carta_Magica) this.baralho.getElemento(11));
			campo.addCriaturaNoCampo(aux2,this.position,tabuleiro);
			
			aux2 = (Carta_Criatura) this.baralho.getElemento(5);
			campo.addCriaturaNoCampo(aux2,this.position,tabuleiro);
			
			aux2 = (Carta_Criatura) this.baralho.getElemento(5);
			//campo.addCriaturaNoCampo(aux2,this.position,tabuleiro);
			
			aux2 = (Carta_Criatura) this.baralho.getElemento(5);
			campo.addCriaturaNoCampo(aux2,this.position,tabuleiro);
			
			Carta aux = this.baralho.getElemento(11);
			addCartaMao(aux);
			aux = this.baralho.getElemento(12);
			addCartaMao(aux);
			aux = this.baralho.getElemento(13);
			addCartaMao(aux);
			aux = this.baralho.getElemento(14);
			addCartaMao(aux);
			aux = this.baralho.getElemento(15);
			addCartaMao(aux);
			
		//}
		
		this.baralho.embaralhar();
	}
	
	

	


	private void createButton() {
		handButton = new JButton("Mão");
		
		handButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hand.getFrame().setVisible(getVez());
			}
		});
		
		handButton.setBounds(0, (int)this.interfacePosition.y, 60, 25);
		
		tabuleiro.add(handButton);
		
	}



	private void cartasCreator(Lista_de_Generics<Carta> baralho) {
		//baralho
		CartaParameters cp = new CartaParameters(Tipo_Carta.BARALHO);
		cp.imagem = Importar.getBackground(BackgroundID.baralho);
		cp.descricao = "Topo do Baralho";
		cp.nome = "Baralho";
		this.baralho = new Baralho(baralho,cp, this);
		
		//cemiterio
		cemiterio = new Cemiterio();
		
		//campo
		campo = new Campo(this.baralho,cemiterio, this);
		
	}



	public void setJogadorInfo(String name, int energia, boolean vez){
		if(jogadorInfo == null){
			if(vez){
				this.jogadorInfo = new JLabel("Nome: "+this.nome+" Energia: "+energia+" Vez: Jogando!");
			}else{
				this.jogadorInfo = new JLabel("Nome: "+this.nome+" Energia: "+energia+" Vez: Aguardando");
			}
			
			jogadorInfo.setBounds((int)this.interfacePosition.x, (int)this.interfacePosition.y,500,20);
			
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
	
	public void setVez(boolean vez){
		this.vez = vez;
		//tabuleiro.trocaVez(this);
		this.setJogadorInfo(nome, energia, vez);
		if(vez){
			turnoState = ataqueState = new AtaqueState(this, hand);
			tabuleiro.remove(semiTransparentPanel);
		}else{
			turnoState = defesaState = new DefesaState(this, hand);
			tabuleiro.add(semiTransparentPanel);
		}
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
	
	public void popUpPlayerInfoCaller(){
		do{
			nome = JOptionPane.showInputDialog("Nome Player "+(this.playerPosition.getValor())+": ");
			if(nome == null || nome.equals("") || nome.startsWith(" ")){
				if(nome == null || nome.equals("")){
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


	public int ataque(){
		int resultado = 0;
		
		return resultado;
	}
	public void defesa(int ataque, Entity alvo, Jogador jogadorAlvo){
		
		if(alvo instanceof Carta_Criatura){
			defender(ataque,(Carta_Criatura) alvo, jogadorAlvo);
		}else if(alvo instanceof Jogador){
			defender(ataque, (Jogador)alvo);
		}else{
			System.out.println("Impossível Atacar "+alvo.getNome());
		}
		
	}
	private void defender(int ataque, Carta_Criatura alvo, Jogador jogadorAlvo){
		int resultado = ataque - alvo.getDefesa();
		if(resultado <= 0){
			System.out.println("Defendido");
		}else{
			System.out.println("Morto");
			defender(resultado, jogadorAlvo);
		}
	}
	private void defender(int ataque, Jogador alvo){
		//vida - dano 
		alvo.setEnergia(alvo.getEnergia() - ataque);
		if(alvo.getEnergia() < 0){
			System.out.println("Fim de jogo. "+  alvo.getNome().toUpperCase()+" perdeu!!");
		}
	}

	public void repaintComponents() {
		for(Component i : hand.getComponents()){
			//i.validate();
			i.revalidate();
			i.repaint();
		}
		
	}


	public void addCartaMao(Carta c){
		
		if(c instanceof Carta_Criatura) return;
		
		ScrollingCardPanel hand = this.hand.getMainPanel().getCardPanel();
		hand.addCard(c);
	}



	public CommandListener getCommandListener() {
		return commandListener;
	}






	public void setCommandListener(CommandListener commandListener) {
		this.commandListener = commandListener;
	}






	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}

}
