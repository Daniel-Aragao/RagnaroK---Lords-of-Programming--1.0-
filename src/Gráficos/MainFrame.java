package Gráficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Util.BackgroundID;
import Util.Importar;

public class MainFrame {
	///////////////////////////////////  DIMENSION  //////////////////////////////////////////
	public static final int WIDTH = 256;
	public static final int HEIGHT = 192;
	public static final int SCALE = 4;
	public static final Dimension MainDimension = 
			new Dimension(MainFrame.WIDTH*MainFrame.SCALE, MainFrame.WIDTH*MainFrame.SCALE); 
	//1024 x 768 (256,192,4)//800x600(800,600,1)
	////////////////////////////////////////////////////////////////////////////////////////////	
	
	private JFrame frame;
	
	private Dimension screenDimension;
	
	public MainFrame(){
		this.screenDimension = MainDimension;
		createFrame();
	}

	private void createFrame() {
		frame = new JFrame("Ragnarok - Lords of Programming(1.0)");
		frame.setName("Principal");
		frame.setPreferredSize(screenDimension);
		frame.setSize(screenDimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(frame.getRootPane());
		frame.setResizable(false);
		//frame.setUndecorated(true);
		frame.setFocusable(false);
		
		BufferedImage icon = Importar.getBackground(BackgroundID.icone);
		frame.setIconImage(icon);
		
		
		frame.setBackground(Color.black);
		frame.pack();
		frame.setVisible(true);
		
		//frame.add(new JLabel("000000000000000000000000000000000000000000000"));
	}
	
//	public Canvas getCanvas(){ return this.canvas;}
	
	public JFrame getFrame(){ return this.frame;}
	
	
}
