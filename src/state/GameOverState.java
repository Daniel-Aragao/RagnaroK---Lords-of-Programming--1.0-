package state;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tabuleiro.Jogador;
import Game.Game;
import Gráficos.MainFrame;
import Util.BackgroundID;
import Util.Importar;

public class GameOverState extends State{

	JFrame mFrame;
	static Game game;
	GameOverPanel gameOverPanel;

	public GameOverState(Game game, Jogador winner, Jogador loser){
		
		GameOverState.game = game;
		mFrame = game.getFrame().getFrame();
		
		gameOverPanel = new GameOverPanel(winner,loser);
		mFrame.setContentPane(gameOverPanel);		
		
	}	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repaintComponents() {
		gameOverPanel.repaintComponents();
		for(Component i: mFrame.getComponents()){
			i.revalidate();
			i.repaint();
			
		}
		
	}

}
@SuppressWarnings("serial")
class GameOverPanel extends JPanel{
	public static final BufferedImage BACKGROUND = Importar.getBackground(BackgroundID.gameover);
	private JButton creditos;
	private JButton menu;
	private JLabel win;
	private JLabel los;
	private Jogador winner;
	private Jogador loser;
	
	
	public GameOverPanel(Jogador winner, Jogador loser){
		this.winner = winner;
		this.loser  = loser ;
		
		creditos = new JButton("Créditos");
		menu = new JButton("Menu");
		win = new JLabel(winner.getNome()+" Venceu com "+winner.getEnergia()+" de energia sobrando");
		los = new JLabel(loser.getNome()+" Perdeu com "+(loser.getEnergia()*-1)+" de energia faltando");
		
		this.setLayout(null);
		
		creditos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		win.setBounds(600,100 , 500, 20);
		win.setForeground(Color.white);
		los.setBounds(650, 150, 500, 20);
		los.setForeground(Color.white);
		
		creditos.setBounds(700, 200, 100, 50);
		menu.setBounds(750, 280, 100, 50);
		
		buttonCustomization(creditos);
		buttonCustomization(menu);
		
		this.add(creditos);
		this.add(menu);
		this.add(win);
		this.add(los);
		
	}	
	
	public void buttonCustomization(JButton button){
		button.setForeground(Color.white);
		button.setBackground(new Color(62,28,100));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setFocusPainted(false);
	}
	

	public void repaintComponents() {
		for(Component i: getComponents()){
			i.revalidate();
			i.repaint();			
		}
		
	}
	
	@Override
	public void paintComponent(final Graphics g){
		super.paintComponent(g);
		 Graphics gr = g.create();  
		 
		 gr.drawImage(GameOverPanel.BACKGROUND,0,0,MainFrame.WIDTH*MainFrame.SCALE
				 ,MainFrame.HEIGHT*MainFrame.SCALE,null);
		 
		
		gr.dispose();
	}
	
	
}


