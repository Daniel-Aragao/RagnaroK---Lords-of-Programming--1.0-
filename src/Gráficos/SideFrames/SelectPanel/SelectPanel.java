package Gráficos.SideFrames.SelectPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Util.BackgroundID;
import Util.Importar;
import Gráficos.SideFrames.HandFrame;
import Gráficos.SideFrames.SelectFrame;

@SuppressWarnings("serial")
public class SelectPanel extends JPanel{
	
	private BufferedImage imagem;
	
	
	public SelectPanel(){
		imagem = Importar.getBackground(BackgroundID.selecionarCarta);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();
		
		
		
		gr.clearRect(0, 0, HandFrame.WIDTH, HandFrame.HEIGHT);
		
		gr.drawImage(this.imagem, 0, 0,SelectFrame.WIDTH,SelectFrame.HEIGHT, null);
		
		
		gr.dispose();
		
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}
}
