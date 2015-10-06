package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Deque.Tabuleiro;
import Input.Mouse;
import Util.Importar;
import Util.JImagePanel;
import Util.Lista_de_Generics;

public class Screen_G extends JPanel implements Runnable {
	public static final int FPS = 60;
	private static final double TARGET_TIME = 1000 / FPS;
	// dimensions

	private boolean stop = false;
	private Thread tGame = null;
	
	private Mouse mouse;
	private JPanel tab1;
	private JPanel tab2;
	private JPanel center;
	
	private JLabel energia1;
	private JLabel energia2;
	private JLabel infoCarta;
	
	private Tabuleiro t1;
	private Tabuleiro t2;
	
	
	int width = Main_Frame.WIDTH, height = Main_Frame.HEIGHT,
			scale = Main_Frame.SCALE;
	Dimension tabuleiros;
	
	public Screen_G() {
		
		
		mouse = new Mouse();
		montarTabuleiro();
		// pack();
	}
	
	public void montarTabuleiro(){
		//Criar classe jogador(jpanel) para receber tabuleiro(jpanel) 
		//e remover a criação do tabuleiro daqui
		t1 = new Tabuleiro();
		t2 = new Tabuleiro();
		tabuleiros = new Dimension((width * scale) * 3 / 8,
				(height * scale) * 3 / 8);
		
		
		//tab1.setBackground(Color.yellow);
		JImagePanel images[] = new JImagePanel[14];
		try {
			for(int i = 0;i<images.length;i++){
				images[i] = new JImagePanel ( ImageIO.read(new File("./Cartas/Criaturas/Poring.jpg")) );
				t1.add(images[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for(int i = 0;i<images.length;i++){
				images[i] = new JImagePanel ( ImageIO.read(new File("./Cartas/Criaturas/Eclipse.png")) );
				t2.add(images[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		//this.setLayout(new GridLayout(4, 1));
		this.setLayout(new GridLayout(2, 1));
		this.setSize(Main_Frame.WIDTH*3,Main_Frame.HEIGHT*3);
		
//		energia1 = new JLabel(("Energia = "+ t1.getEnergia()));
//		energia2 = new JLabel(("Energia = "+ t2.getEnergia()));
		//add(energia1);
		
		add(t1);
		add(t2);
		//add(energia2);
		
	}

	public void start() {
		if (tGame == null) {
			tGame = new Thread(this, "Jogo");
			tGame.start();
		}
	}

	public void stop() {
		stop = true;
		tGame = null;
	}

	@Override
	public void run() {
		repaint();
		while (Thread.currentThread() == tGame && !stop) {
			long start = System.nanoTime();
			
			
			MouseActions();
			
			
			
			
			long elapsed = System.nanoTime() - start;
			double wait = TARGET_TIME - elapsed / 1000000; // conversão nano -> milis

			try {
				tGame.sleep((long) wait); // pq ele deseja mudador de
												// threadGame para Thread?
				// threadGame.notifyAll();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	public void MouseActions(){
//		System.out.print(mouse.getX()+" ");
//		System.out.println(mouse.getY());
		if(mouse.buttonClicked()){
			System.out.println(mouse.getX()+" "+mouse.getY());
		}
	}
	
	
	
}
