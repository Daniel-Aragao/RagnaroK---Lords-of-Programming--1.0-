package tabuleiro;

import state.GameState;
import state.MenuState;
import state.State;
import Gráficos.MainFrame;
import listeners.CommandListener;

public class Turno implements Runnable{
	private boolean letPull;
	private boolean letAtack;
	private boolean letED_OO;
	private int turno, loopCounter;
	private boolean turning;
	private Jogador jogador;
	private CommandListener commandListener;
	
	private Thread turnoThread;
	
	public Turno(){
		
	}
	public void init() {
		letPull = false;
		letAtack = false;
		letED_OO = false;
		turno = loopCounter = 0;
		
	}
		
	public void start() {
		if (turning)
			return;
		turning = true;
		
		turnoThread = new Thread(this,"Turno Thread");
		turnoThread.start();		
	}
	
	@Override
	public void run() {
		init();
		
		while(turning){
			// pull time
			try {
				turnoThread.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// atack, if is possible
			if(turno > 3){
				if(jogador.canAtack()){
					letAtack = true;
					jogador.allowAtack(letAtack);					
					
					try {
						turnoThread.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}				
				
				
				letAtack = false;
				jogador.allowAtack(letAtack);
			}
			
			//allow ED / OO			
			try {
				turnoThread.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//end of turn
			loopCounter++;
			if(loopCounter % 2 != 0 ){
				turno++;
			}
			
			commandListener.passarVez(jogador);
		}
		stop();
	}

	public void stop() {
		if (!turning)
			return;
		turning = false;
		try {
			turnoThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void externalNotify(){
		turnoThread.notify();
	}
	
	public boolean isLetPull() {
		return letPull;
	}

	public void setLetPull(boolean letPull) {
		this.letPull = letPull;
	}

	public boolean isLetAtack() {
		return letAtack;
	}

	public void setLetAtack(boolean letAtack) {
		this.letAtack = letAtack;
	}

	public boolean isLetED_OO() {
		return letED_OO;
	}

	public void setLetED_OO(boolean letED_OO) {
		this.letED_OO = letED_OO;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getLoopCounter() {
		return loopCounter;
	}

	public void setLoopCounter(int loopCounter) {
		this.loopCounter = loopCounter;
	}

	public boolean isTurning() {
		return turning;
	}

	public void setTurning(boolean turning) {
		this.turning = turning;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		// matar turno loop, melhor não
		this.jogador = jogador;
	}

	

	public void setCommandListener(CommandListener cl) {
		this.commandListener = cl;
		
	}

	
}
