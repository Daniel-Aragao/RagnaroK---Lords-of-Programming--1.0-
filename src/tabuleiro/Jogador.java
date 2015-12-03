package tabuleiro;

import handlers.ClickedHandHandler;
import handlers.ClickedHandler;
import handlers.ClickedSelectHandler;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import listeners.CommandListener;
import Gráficos.SideFrames.HandFrame;
import Gráficos.SideFrames.SelectFrame;
import Gráficos.SideFrames.SelectPanel.SelectPanel;
import Gráficos.SideFrames.handPanels.DescriptionPanel;
import Gráficos.SideFrames.handPanels.HandCommandPanel;
import Gráficos.SideFrames.handPanels.HandPanel;
import Gráficos.SideFrames.handPanels.ScrollingVCardPanel;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;
import Util.Position;
import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Criatura;
import entity.Entity;
import entity.Tipo_Carta;
import entity.cartas_de_topo.Baralho;
import entity.cartas_de_topo.Campo;
import entity.cartas_de_topo.Cemiterio;
import entity.cartas_de_topo.ED;
import entity.cartas_de_topo.OO;

@SuppressWarnings("serial")
public class Jogador extends Entity{
	public static final Position UP_LABEL = new Position(100, 16);
	public static final Position DOWN_LABEL = new Position(100, 710);
	public static final Position[] VETLABEL = {Jogador.UP_LABEL,Jogador.DOWN_LABEL};	
	public static final Position UP_REFERENCE = new Position(25,68);
	public static final Position DOWN_REFERENCE = new Position(25,400);
	public static final int ESPACAMENTO = 46;
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	public static final int ENERGIA_WIDTH = 350,
							ENERGIA_HEIGHT = 25;
	
	public static final int ENERGIA_INICIAL = 150;
	
	public ClickedSelectHandler CLICKED_SELECT_HANDLER = new ClickedSelectHandler(this);
	public ClickedHandHandler CLICKED_HAND_HANDLER = new ClickedHandHandler(this);

	private int energia;
	private int associaçãoDEF;
	private int associaçãoATQ;
	private boolean vez;
	private PlayerPosition playerPosition;
	
	private int turnoCounter;
	
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
	private ED ed;
	private OO oo;
		
	/////////////////////////////////////
	

	///////////////PANELS////////////////
	
	private Tabuleiro tabuleiro;
	private CommandListener commandListener;
	private DescriptionPanel description ;
	private HandPanel handPanel;
	private HandCommandPanel handCommandPanel;
	private SelectPanel selectPanel;
	
	/////////////////////////////////////
	
	public Jogador(Tabuleiro tabuleiro, Lista_de_Generics<Carta> baralho, PlayerPosition playerPosition) {
		super(Jogador.WIDTH,Jogador.HEIGHT);
		this.setTabuleiro(tabuleiro);
		
		//panel.setBackground(new Color(213, 134, 145, 123)); transluscent color
		
		this.turnoCounter = 1;
		
		this.energia = Jogador.ENERGIA_INICIAL;
		
		this.associaçãoDEF = 0;
		this.associaçãoATQ = 0;
		
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
		//semiTransparentPanel = new SemiTransparentPanel(this.position);
		
		hand = new HandFrame(this);
		handPanel = hand.getMainPanel();
		handCommandPanel = handPanel.getCommandPanel();
		
		this.imagem = Importar.getBackground(BackgroundID.hpJogador);
		
		createButton();
		
		cartasCreator(baralho);
		
		this.setVez(vez);
		
		this.description = getHand().getMainPanel().getEastPanel().getDescriptionPanel();
		
		this.addCartaClickedListener(ownClickedHandler());		
			
		this.setSelectPanel(this.baralho.getSelectPanel());
		
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		
		CartaParameters cp = new CartaParameters(Tipo_Carta.CRIATURA);
		BufferedImage img = Importar.getBackground(BackgroundID.pisoCemitério);
		cp.imagem = img;
		img = Importar.getBackground(BackgroundID.pisoOO);
		Carta_Criatura imaginaria = new Carta_Criatura(200,200,200,img,cp);
		campo.addCarta(imaginaria);
		this.turnoCounter = 5;
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		
		
		putJogador();
		this.baralho.embaralhar();
		this.baralho.embaralhar();
		//this.baralho.embaralhar();
	}
	
	private void putJogador(){
		
		//this.setBackground(new Color(255,0,0));
		
		this.setBounds((int)interfacePosition.x, (int)interfacePosition.y, ENERGIA_WIDTH, ENERGIA_HEIGHT);
		
		//JPanel defaultVida = new JPanel();
		//TitledBorder border = BorderFactory.createTitledBorder("");
		//border.setTitleColor(Color.white);
		//defaultVida.setBorder(border);
		//defaultVida.setBounds((int)interfacePosition.x, (int)interfacePosition.y, ENERGIA_WIDTH, 25);
		//defaultVida.setBackground(new Color(0,0,0,0));
		//tabuleiro.add(defaultVida);
		
		this.addMouseListener(Game.Game.CARD_MOUSE);
		
		tabuleiro.add(this);
	}


	private void createButton() {
		handButton = new JButton("Mão");
		
		handButton.setForeground(Color.white);
		handButton.setBackground(new Color(62,28,100));
		handButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		handButton.setFocusPainted(false);
		
		handButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hand.getFrame().setVisible(getVez());
			}
		});
		
		handButton.setBounds((int)this.position.x, (int)this.interfacePosition.y, 60, 25);
		
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
		cp = new CartaParameters(Tipo_Carta.CEMITERIO);
		cp.imagem = Importar.getBackground(BackgroundID.pisoCemitério);
		cp.descricao = "Cemitério";
		cp.nome = "Cemitério";
		cemiterio = new Cemiterio(cp,this);
		
		//ed
		cp = new CartaParameters(Tipo_Carta.ED);
		cp.imagem = Importar.getBackground(BackgroundID.pisoED);
		cp.descricao = "Estrutura de dados";
		cp.nome = "ED";
		ed = new ED(cp);
		
		//oo
		cp = new CartaParameters(Tipo_Carta.OO);
		cp.imagem = Importar.getBackground(BackgroundID.pisoOO);
		cp.descricao = "Orientação a Objetos";
		cp.nome = "OO";
		oo = new OO(cp);
		
		//campo
		campo = new Campo(this.baralho, this.cemiterio, this.oo, this.ed, this);
		
	}



	public void setJogadorInfo(String name, int energia, boolean vez){
		if(jogadorInfo == null){
			if(vez){
				this.jogadorInfo = new JLabel("Nome: "+this.nome+" Energia: "+energia+" Vez: Jogando!");
			}else{
				this.jogadorInfo = new JLabel("Nome: "+this.nome+" Energia: "+energia+" Vez: Aguardando.");
			}
			jogadorInfo.setForeground(Color.white);
			
			jogadorInfo.setBounds((int)this.interfacePosition.x, (int)this.interfacePosition.y,ENERGIA_WIDTH,20);
			
			tabuleiro.add(jogadorInfo);
		}else{
			if(vez){
				this.jogadorInfo.setText("Nome: "+this.nome+" Energia: "+energia+" Vez: Jogando!");
			}else{
				this.jogadorInfo.setText("Nome: "+this.nome+" Energia: "+energia+" Vez: Aguardando.");
			}
		}
		this.jogadorInfo.setText(jogadorInfo.getText() + " Turno: " + getTurnoCounter());
	}
	public void atualizarInfoEnergia(){
		if(vez){
			this.jogadorInfo.setText("Nome: "+this.nome+" Energia: "+energia+" Vez: Jogando!");
		}else{
			this.jogadorInfo.setText("Nome: "+this.nome+" Energia: "+energia+" Vez: Aguardando.");
		}
		int newWidth = (energia*ENERGIA_WIDTH)/ENERGIA_INICIAL;
		this.setSize(newWidth, 25);
	}

	public JLabel getJogadorInfo(){
		return this.jogadorInfo;
	}
	
	public void setVez(boolean vez){
		this.vez = vez;
		this.setJogadorInfo(nome, energia, vez);
		if(vez){
			
		}else{
			hand.setVisible(false);
			baralho.getSelectFrame().setVisible(vez);
		}
		this.baralho.getSelectPanel().getCommandPanel().denableOptions();
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
		
		if(this.associaçãoATQ == 0){
			resultado = campo.getAtaque();
		}else{
			resultado = this.associaçãoATQ;
			this.associaçãoATQ = 0;
		}
		
		return resultado;
	}
	public void defesa(int ataque, Entity alvo, Jogador jogadorAlvo){
		
		if(alvo instanceof Carta_Criatura){
			defender(ataque,(Carta_Criatura) alvo, jogadorAlvo);
		}else if(alvo instanceof Jogador){
			defender(ataque, (Jogador)alvo);
		}else{
			LogPanel.appendText("Impossível Atacar "+alvo.getNome());
		}
		atualizarInfoEnergia();		
	}
	private void defender(int ataque, Carta_Criatura alvo, Jogador jogadorAlvo){
		// ataque - defesa
		int defesaAlvo = alvo.getDefesa(true);
		int resultado = ataque - defesaAlvo;
		
		LogPanel.appendText("Ataque: "+ataque);
		LogPanel.appendText("Defesa: "+defesaAlvo);
		
		if(resultado <= 0){
			LogPanel.appendText("Defendido");
		}else{
			LogPanel.appendText("Morto");
			campo.removeCriaturaDoCampo(alvo);
			defender(resultado, jogadorAlvo);
		}
		
	}
	private void defender(int ataque, Jogador alvo){
		//vida + associação - dano 
		
		alvo.setEnergia(alvo.getEnergia() +alvo.getAssossiação() - ataque);
		
		LogPanel.appendText("Dano : " + (ataque - alvo.getAssossiação()));
		LogPanel.appendText("Energia: " + alvo.getEnergia());
		
		if(alvo.getAssossiação() != 0){
			LogPanel.appendText("Defesa por Associação: "+alvo.getAssossiação());
			alvo.setAssossiação(0);
		}
		if(alvo.getEnergia() <= 0){
			LogPanel.appendText("Fim de jogo. "+  alvo.getNome().toUpperCase()+" perdeu!!");
			commandListener.endGame(alvo);
		}
	}

	public void repaintComponents() {
		baralho.repaintComponents();
		for(Component i : hand.getComponents()){
			i.revalidate();
			i.repaint();
		}
		
	}


	public void addCartaMao(Carta c){
		
		if(c instanceof Carta_Criatura || c == null) return;
		
		ScrollingVCardPanel hand = this.hand.getMainPanel().getCardPanel();
		c.addCartaClickedListener(CLICKED_HAND_HANDLER);
		hand.addCard(c);
	}



	public CommandListener getCommandListener() {
		return commandListener;
	}

	public void setCommandListener(CommandListener commandListener) {
		this.commandListener = commandListener;
	}


	public DescriptionPanel getDescription(){
		return this.description;
	}



	private ClickedHandler ownClickedHandler() {
		ClickedHandler ch = new ClickedHandler(this){
			int acr = 10;
			@Override
			public void CardClicked(Entity c) {
				HandCommandPanel.setGlobalSelection(c);
				
				if(getVez()){
					getHandCommandPanel().setSelected(c);
				}		
				
			}

			@Override
			public void CardHoover(Carta c, boolean b) {
				
				if(vez){
					if(b){
						crescer(c);
						commandListener.hooverInfo(c);
					}else{
						desCrescer(c);						
					}
				}else{
					if(b){
						diminuir(c);
						commandListener.hooverInfo(c);
					}else{
						desDiminuir(c);						
					}
				}
			}
			public void diminuir(Carta c){
				c.setSize(Carta.DEFAULT_CARTA_WIDTH-acr, Carta.DEFAULT_CARTA_HEIGHT-acr);
				c.fantasy_CARTA_WIDTH -= acr;
				c.fantasy_CARTA_HEIGHT -= acr;
				
			}
			public void desDiminuir(Carta c){
				c.setSize(Carta.DEFAULT_CARTA_WIDTH,
						Carta.DEFAULT_CARTA_HEIGHT);
				c.fantasy_CARTA_WIDTH += acr;
				c.fantasy_CARTA_HEIGHT += acr;
				//description.setInformacoes(null);
				c.repaint();
			}
			
			public void crescer(Carta c){
				c.setSize(Carta.DEFAULT_CARTA_WIDTH + acr,
						Carta.DEFAULT_CARTA_HEIGHT + acr);
				c.fantasy_CARTA_WIDTH += acr;
				c.fantasy_CARTA_HEIGHT += acr;
			}
			public void desCrescer(Carta c){
				c.setSize(Carta.DEFAULT_CARTA_WIDTH, Carta.DEFAULT_CARTA_HEIGHT);
				c.fantasy_CARTA_WIDTH -= acr;
				c.fantasy_CARTA_HEIGHT -= acr;
			}
			
		};
		
		return ch;
	}


	public boolean isDefended() {
		return campo.isDefended();
	}
	public void allowEndTurn(boolean letEndTurn){
		this.handPanel.getCommandPanel().allowEndTurn(letEndTurn);
	}
	public void allowAtack(boolean letAtack) {
		if(getTurnoCounter() > 3 && canAtack()){
			this.handPanel.getCommandPanel().allowAtack(letAtack);
		}
		allowEndTurn(true);
		this.hand.setVisible(true);
		this.baralho.getSelectFrame().setVisible(false);
	}

	public boolean canAtack() {
		return campo.canAtack();
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public int getTurnoCounter() {
		return turnoCounter;
	}

	public void setTurnoCounter(int turnoCounter) {
		this.turnoCounter = turnoCounter;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		 Graphics gr = g.create();  
		 
		 gr.drawImage(this.imagem,0,0,Jogador.ENERGIA_WIDTH
				 ,ENERGIA_HEIGHT,null);
		 
		
		gr.dispose();
		
		
		
	}

	public HandCommandPanel getHandCommandPanel() {
		return handCommandPanel;
	}

	public void setHandCommandPanel(HandCommandPanel handCommandPanel) {
		this.handCommandPanel = handCommandPanel;
	}
	public Cemiterio getCemiterio() {
		return cemiterio;
	}

	public int getAssossiação() {
		return associaçãoDEF;
	}

	public void setAssossiação(int assossiação) {
		this.associaçãoDEF = assossiação;
	}

	public SelectPanel getSelectPanel() {
		return selectPanel;
	}
	public SelectFrame getSelectFrame(){
		return this.baralho.getSelectFrame();
	}

	public void setSelectPanel(SelectPanel selectPanel) {
		this.selectPanel = selectPanel;
	}
}
