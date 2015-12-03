package Game;

import state.MenuState;
import state.State;
import Gráficos.MainFrame;
import Input.Mouse;
import Util.FpsVariables;

public class Game implements Runnable {
	public static final Mouse CARD_MOUSE = new Mouse();
	
	private boolean gameLoop = false;
	private Thread tGame = null;
	
	private MainFrame mFrame;
	
	public MainFrame getFrame() {
		return mFrame;
	}

		
	//States
//	private State gameState;
	private State menuState;
	
	
	public Game() {
		
	}
	
	
	public void init() {
		mFrame = new MainFrame();
		//gameState = new GameState(this); comentar isso aqui
		menuState = new MenuState(this);
		State.setState(menuState);
	}

	@Override
	public void run() {
		init();
		FpsVariables fps = new FpsVariables(60);


		while (gameLoop) {
			fps.calculateFPS_Limitation();

			if (fps.FPS_Limitation()) {
				update();
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
