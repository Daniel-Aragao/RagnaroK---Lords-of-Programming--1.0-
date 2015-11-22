package Game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.JLabel;

import entity.Carta;
import state.GameState;
import state.MenuState;
import state.State;
import tabuleiro.Jogador;
import Gr�ficos.MainFrame;
import Input.Mouse;
import Util.FpsVariables;
import Util.Importar;
import Util.Lista_de_Generics;

public class Game implements Runnable {
	public static final Mouse CARD_MOUSE = new Mouse();
	
	private boolean gameLoop = false;
	private Thread tGame = null;
	
	private MainFrame mFrame;
	
	public MainFrame getFrame() {
		return mFrame;
	}

	private BufferStrategy bs;
	private Graphics g;
	
	private int width, height;
	
	
	
	//States
	private State gameState;
	private State menuState;
	
	
	public Game() {
		width = MainFrame.WIDTH;
		height = MainFrame.HEIGHT;
	}
	
	
	public void init() {
		mFrame = new MainFrame();
//		mFrame.getFrame().add(new JLabel("22222222222222222222222222222222222222222222222"), BorderLayout.NORTH);
		gameState = new GameState(this);
		menuState = new MenuState();
		State.setState(gameState);
	}

	@Override
	public void run() {
		init();
		FpsVariables fps = new FpsVariables(60);


		while (gameLoop) {
			fps.calculateFPS_Limitation();

			if (fps.FPS_Limitation()) {
				update();
				//AQUI
				//draw();
				repaint();
			}
			fps.title_printer(mFrame.getFrame());
			
			
		}

		stop();
	}

	private void repaint() {
		if(State.getState()!=null){
			State.getState().repaintComponents();
		}
	}


	private void update() {
		
		if(State.getState()!=null){
			State.getState().update();
		}
	}

	private void draw() {
		bs = mFrame.getFrame().getBufferStrategy();
		if(bs == null){
			mFrame.getFrame().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		//g.clearRect(0, 0, width, height);
		//Draw
		
		if(State.getState()!=null){
			State.getState().draw(g);
		}
		
		//End Drawing
//		bs.show();
		g.dispose();
		
	}
	
	public void start() {
		if (gameLoop)
			return;
		gameLoop = true;
		tGame = new Thread(this,"tGame");
		tGame.start();
	}

	public void stop() {
		if (!gameLoop)
			return;
		gameLoop = false;
		try {
			tGame.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
