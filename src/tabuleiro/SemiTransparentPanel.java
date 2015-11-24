package tabuleiro;

import java.awt.Color;

import javax.swing.JPanel;

import entity.Carta;
import Gráficos.MainFrame;
import Util.Position;

@SuppressWarnings("serial")
public class SemiTransparentPanel extends JPanel{

	public SemiTransparentPanel(Position position) {
		setBounds((int)position.x, (int)position.y,
				(Carta.DEFAULT_CARTA_WIDTH*7+Jogador.ESPACAMENTO*6), 
				(Carta.DEFAULT_CARTA_HEIGHT*2 + Jogador.ESPACAMENTO - 10 ));
		setBackground(new Color(0,0,50,100));
		//setOpaque(true);
		setVisible(true);
	}

	
	
}
