package Gráficos.SideFrames;

import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import tabuleiro.Jogador;
import Util.BackgroundID;
import Util.Importar;
import Gráficos.SideFrames.SelectPanel.SelectPanel;

@SuppressWarnings("serial")
public class SelectFrame extends JFrame{
	private static final int SCALE = 1;
	public static final int WIDTH = 600 * SelectFrame.SCALE;
	public static final int HEIGHT = 200 * SelectFrame.SCALE;
	
	public static final Dimension DEFAULT_SELECT_DIMENSION = 
			new Dimension(SelectFrame.WIDTH, SelectFrame.HEIGHT); 
	
	SelectPanel selectPanel;
	
	public SelectFrame(){
		selectPanel = new SelectPanel();
		createFrame();
		
		
	}
	
	
	private void createFrame() {
		this.setTitle("Opções do Baralho");
		this.setPreferredSize(SelectFrame.DEFAULT_SELECT_DIMENSION);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(false);
		this.setFocusable(false);
		
		BufferedImage icon = Importar.getBackground(BackgroundID.icone);
		this.setIconImage(icon);
		
		
		this.setContentPane(selectPanel);
		
		this.pack();
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
	}
		
		
	


	public SelectPanel getMainPanel() {
		
		return selectPanel;
	}

}
