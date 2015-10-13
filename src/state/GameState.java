package state;

import java.awt.Graphics;

import Util.BackgroundID;
import Util.Importar;

public class GameState extends State{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) {
		//g.drawImage(image, x, y, imageObserver);
		g.drawImage(Importar.getBackground(BackgroundID.JogoBackground), 0, 0, null);
	}

}
