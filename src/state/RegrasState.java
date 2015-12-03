package state;

import Util.BackgroundSoundID;
import Util.Importar;
import Util.MusicPlayer;
import Game.Game;

public class RegrasState extends State{

	public RegrasState(Game game) {
		
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repaintComponents() {
		// TODO Auto-generated method stub
		
	}

}
