package Game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import Deque.Carta;
import Deque.Jogador;
import Gráficos.MainFrame;
import Util.Importar;
import Util.Lista_de_Generics;

public class Game implements Runnable {
	public static final File FILE = new File("./Cartas/All Descriptions URL's.txt");
	public static final int FPS = 60;
	private static final double TARGET_TIME = 1000 / FPS;

	private boolean gameLoop = false;
	private Thread tGame = null;
	
	MainFrame mFrame;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private int width, height;
	
	Importar importar;
	Jogador jogadorA;
	Jogador jogadorB;

	public Game() {
		importar = new Importar();
		Lista_de_Generics<Carta> baralho = importar.importAllCards(Game.FILE);
		jogadorA = new Jogador(baralho);
		jogadorB = new Jogador(baralho.copy());
		width = MainFrame.WIDTH;
		height = MainFrame.HEIGHT;
	}
	
	
	public void init() {
		mFrame = new MainFrame();
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		init();

		while (gameLoop) {
			long start = System.currentTimeMillis();
			
			update();
			draw();
			
			long elapsed = System.currentTimeMillis() - start;
			double wait = TARGET_TIME - elapsed;
					
			System.out.println((long)wait);
			try {
				tGame.sleep((long) wait); // pq ele deseja mudador de
												// threadGame para Thread?
				// threadGame.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		stop();
	}

	private void update() {
		// TODO Auto-generated method stub

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
