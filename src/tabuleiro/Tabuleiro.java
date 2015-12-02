package tabuleiro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import listeners.CommandListener;
import Gráficos.MainFrame;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.Entity;

//All Descriptions URL's.txt

@SuppressWarnings("serial")
public class Tabuleiro extends JPanel {
	public static final BufferedImage BACKGROUND = Importar.getBackground(BackgroundID.JogoBackground);
	

	private Jogador jogadorA;
	private Jogador jogadorB;
	
	private LogPanel logPanel;
	
	public Tabuleiro() {
		////////////////////IMPORTAR CARTAS//////////////////////////////
		Lista_de_Generics<Carta> baralho;
		baralho = Importar.importAllCards(Importar.FILE);
		/////////////////////////////////////////////////////////////////
		
		this.setPreferredSize(MainFrame.MainDimension);
		this.setMinimumSize(MainFrame.MainDimension);
		this.setMaximumSize(MainFrame.MainDimension);
		this.setLayout(null);
		
		
		CommandListener cl = commandCreator();
		Turno turno = new Turno();  
		logPanel = new LogPanel();
		logPanel.setBounds((int)Jogador.UP_LABEL.x + Jogador.ENERGIA_WIDTH + 10 
				,5, 250, 55);

		jogadorA = new Jogador(this, baralho, PlayerPosition.UP_REFERENCE);
		jogadorB = new Jogador(this, baralho, PlayerPosition.DOWN_REFERENCE);
		
		jogadorA.setCommandListener(cl);
		jogadorB.setCommandListener(cl);
		
		this.add(logPanel);
		
		this.setVisible(true);
	}
	
	public CommandListener commandCreator(){
		CommandListener aux = new CommandListener(){
			
			@Override
			public void passarVez(Jogador jogador) {
				if(jogador == jogadorA){
					jogadorA.setVez(false);
					jogadorB.setVez(true);
					jogador.setTurnoCounter(jogador.getTurnoCounter()+1);
				}else{
					jogadorA.setVez(true);
					jogadorB.setVez(false);
					jogador.setTurnoCounter(jogador.getTurnoCounter()+1);
					
					LogPanel.setFontColor(Color.RED);					
					LogPanel.appendText("------------------- Fim do turno "+
					(-1+jogador.getTurnoCounter())
					+" -------------------");
				}
				Turno.setPullTime(true);
				jogador.allowEndTurn(false);
			}


			@Override
			public void atacar(Jogador jogador, Entity alvo) {
				
				if(jogador == jogadorA){
					if(alvo.getNome().toLowerCase().equals("associação")){
						alvo = jogadorB;
					}
					LogPanel.appendText("Jogador A Ataca: "+ alvo.getNome());
					jogadorB.defesa(jogadorA.ataque(), alvo, jogadorB);
					
				}else{
					if(alvo.getNome().toLowerCase().equals("associação")){
						alvo = jogadorA;
					}
					LogPanel.appendText("Jogador B Ataca: "+ alvo.getNome());
					jogadorA.defesa(jogadorB.ataque(), alvo, jogadorA);
				}
				
					//JOptionPane.showMessageDialog(jogador, "Só após a 3ª rodada");
				
				Turno.setLetAtack(false);
				jogador.allowAtack(false);
			}


			@Override
			public void hooverInfo(Carta c) {
				jogadorA.getDescription().setInformacoes(c);
				jogadorB.getDescription().setInformacoes(c);
				
			}


			@Override
			public void assossiaçãoAtaque(Jogador jogador) {
				if(jogador == jogadorA){
					LogPanel.appendText("Jogador A Ataca: "+ jogadorB.getNome()+ " por Associação");
					jogadorB.defesa(jogadorA.ataque(), jogadorB, jogadorB);
					
				}else{
					
					LogPanel.appendText("Jogador B Ataca: "+ jogadorA.getNome()+ " por Associação");
					jogadorA.defesa(jogadorB.ataque(), jogadorA, jogadorA);
				}
				
			}


			@Override
			public void polimorfismo(Jogador jogador) {
				if(jogador == jogadorA){
					LogPanel.appendText(jogadorB.getNome()+ " sofreu Polimorfismo");
					jogadorB.getCampo().polimorfismo();
				}else{
					LogPanel.appendText(jogadorA.getNome()+ " sofreu Polimorfismo");
					jogadorA.getCampo().polimorfismo();
				}
				
			}


			@Override
			public void herança(Jogador jogador) {
				int[]A = jogadorA.getCampo().maioresAtributos();
				int[]B = jogadorA.getCampo().maioresAtributos();
				int[]C = new int[3];
				
				C[0] = A[0];
				if(A[0] < B[0]) C[0] = B[0];
				
				C[1] = A[1];
				if(A[1] < B[1]) C[1] = B[1];
				
				C[2] = A[2];
				if(A[2] < B[2]) C[2] = B[2];
				
//				avisar para o painel de seleção que a prox carta criatura 
//				receberá atributos de herança do vetor C
				
				
			}


			@Override
			public void encapsulamento() {
				// TODO Auto-generated method stub
				
			}
			
		};
		return aux;
	}
	
		
	@Override
	public void paintComponent(final Graphics g){
		super.paintComponent(g);
		 Graphics gr = g.create();  
		 
		 gr.drawImage(BACKGROUND,0,0,MainFrame.WIDTH*MainFrame.SCALE
				 ,MainFrame.HEIGHT*MainFrame.SCALE,null);
		 
		///////////////////////LINE MAKER///////////////////////
		g.setColor(new Color(218, 160, 26));
		
		//FIRST LINE
		//g.fillRect( 0, (int) Jogador.UP_REFERENCE.y-20, 1024, 4);
		
		//SECOND (BROKEN) LINE
		g.fillRect( 0, (int) Jogador.DOWN_REFERENCE.y-20, 353, 4);
		g.fillRect( (int) 700, (int) Jogador.DOWN_REFERENCE.y-20, 355, 4);
		
		//THIRD LINE
		//g.fillRect( 0, (int) Jogador.DOWN_REFERENCE.y+300, 1024, 4);
		
		g.setColor(Color.black);
		////////////////////////////////////////////////////////
		gr.dispose();
	}
	
	
	

	

	public void repaintComponents() {
		jogadorA.repaintComponents();
		jogadorB.repaintComponents();
		
	}

	

	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
