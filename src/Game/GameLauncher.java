package Game;

import Util.BackgroundSoundID;
import Util.Importar;
import Util.MusicPlayer;

public class GameLauncher {

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));

	}

}
