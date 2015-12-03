package Game;

import Util.BackgroundSoundID;
import Util.Importar;
import Util.MusicPlayer;

public class GameLauncher {

	public static void main(String[] args) {
		//início 11/09/2015
		
		Game game = new Game();
		game.start();
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));
		
		//fim 03/12/2015
	}

}
