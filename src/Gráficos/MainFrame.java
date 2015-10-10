package Gráficos;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int SCALE = 1;
	//1024 x 768 (256,192,4)//800x600(800,600,1)
	
	private JFrame frame;
	private Canvas canvas;
	
	private Dimension screenDimension;
	private int width, height;	
	
	public MainFrame(){
		this.screenDimension = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		this.width = screenDimension.width;
		this.height = screenDimension.height;
		createFrame();
	}

	private void createFrame() {
		frame = new JFrame("Ragnarok - Lords of Programming(1.0)");
		frame.setSize(screenDimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(screenDimension);
		canvas.setMaximumSize(screenDimension);
		canvas.setMinimumSize(screenDimension);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public Canvas getCanvas(){ return canvas;}
	
	
}
