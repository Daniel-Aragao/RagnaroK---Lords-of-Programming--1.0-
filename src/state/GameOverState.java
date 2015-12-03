package state;

import java.awt.Color;
import java.awt.Component;
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
import Util.BackgroundSoundID;
import Util.ButtonCustomization;
import Util.Importar;
import Util.MusicPlayer;

public class GameOverState extends State{

	JFrame mFrame;
	static Game game;
	GameOverPanel gameOverPanel;
	ButtonsListener buttonsListener;

	public GameOverState(Game game, Jogador winner, Jogador loser){
		
		GameOverState.game = game;
		mFrame = game.getFrame().getFrame();
		
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));
				
		gameOverPanel = new GameOverPanel(winner,loser);
		gameOverPanel.setButtonsListener(new ButtonsListener(){

			@Override
			public void pressed(JButton button) {
				if(button.getText() == "Menu"){
					setState(new MenuState(game));
				}else if(button.getText()=="Créditos"){
					setState(new CreditosState(game));
				}
				
			}
			
		});
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
	
	private ButtonsListener buttonsListener;
	
	public GameOverPanel(Jogador winner, Jogador loser){
		
		creditos = new JButton("Créditos");
		menu = new JButton("Menu");
		if(loser.getEnergia() < 0){
			win = new JLabel(winner.getNome()+" Venceu com "+winner.getEnergia()+" de energia sobrando");
			los = new JLabel(loser.getNome()+" Perdeu com "+(loser.getEnergia()*-1)+" de energia faltando");
		}else{
			win = new JLabel(winner.getNome()+" Venceu!");
			los = new JLabel(loser.getNome()+" Perdeu pois estava sem cartas");
		}
		this.setLayout(null);
		
		creditos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonsListener.pressed((JButton) e.getSource());
				
			}
		});
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonsListener.pressed((JButton) e.getSource());
				
			}
		});
		win.setBounds(600,100 , 500, 20);
		win.setForeground(Color.white);
		los.setBounds(650, 150, 500, 20);
		los.setForeground(Color.white);
		
		creditos.setBounds(700, 200, 100, 50);
		menu.setBounds(750, 280, 100, 50);
		
		ButtonCustomization.buttonCustomization(creditos);
		ButtonCustomization.buttonCustomization(menu);
		
		this.add(creditos);
		this.add(menu);
		this.add(win);
		this.add(los);
		
	}	
	

	public void repaintComponents() {
		for(Component i: getComponents()){
			i.revalidate();
			i.repaint();			
		}
		if(!MusicPlayer.isAlive()){
			new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));;
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

	public ButtonsListener getButtonsListener() {
		return buttonsListener;
	}

	public void setButtonsListener(ButtonsListener buttonsListener) {
		this.buttonsListener = buttonsListener;
	}
	
	
}


