package Game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import state.GameState;
import state.State;
import Deque.Carta;
import Deque.Jogador;
import Gráficos.MainFrame;
import Util.FpsVariables;
import Util.Importar;
import Util.Lista_de_Generics;

public class Game implements Runnable {
	//Files url's
	public static final File FILE = new File("./Cartas/All Descriptions URL's.txt");
	public static final File BACKGROUND_FILE = new File("./Background/All Background URL's.txt");
	
	
	private boolean gameLoop = false;
	private Thread tGame = null;
	
	MainFrame mFrame;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private int width, height;
	
	Importar importar;
	Jogador jogadorA;
	Jogador jogadorB;
	
	//States
	private State gameState;
	private State menuState;
	
	
	public Game() {
		width = MainFrame.WIDTH;
		height = MainFrame.HEIGHT;
	}
	
	
	public void init() {
		mFrame = new MainFrame();
		
		importar = new Importar();
		Lista_de_Generics<Carta> baralho = importar.importAllCards(Game.FILE);
		importar.importarBackground(BACKGROUND_FILE);
		
		jogadorA = new Jogador(baralho);
		jogadorB = new Jogador(baralho);
		
		gameState = new GameState();
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
				draw();
			}
			fps.FPS_printer();
			
		}

		stop();
	}

	private void update() {
		if(State.getState()!=null){
			State.getState().update();
		}
	}

	private void draw() {
		bs = mFrame.getCanvas().getBufferStrategy();
		if(bs == null){
			mFrame.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw
		
		if(State.getState()!=null){
			State.getState().draw(g);
		}
		
		//End Drawing
		bs.show();
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
