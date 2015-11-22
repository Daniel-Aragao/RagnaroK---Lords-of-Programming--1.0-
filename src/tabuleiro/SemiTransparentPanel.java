package tabuleiro;

import java.awt.Color;

import javax.swing.JPanel;

import entity.Carta;
import Gráficos.MainFrame;
import Util.Position;

@SuppressWarnings("serial")
public class SemiTransparentPanel extends JPanel{

	public SemiTransparentPanel(Position position) {
		setBounds((int)position.x, (int)position.y,MainFrame.WIDTH*MainFrame.SCALE, 
				(Carta.DEFAULT_CARTA_HEIGHT*2 + Jogador.ESPACAMENTO ));
		setBackground(new Color(0,0,0,65));
		setOpaque(true);
		setVisible(true);
	}

	
	
}
