package Util;

public class FpsVariables {
	public int fps;
	public double targetTime;
	public double delta;
	public long currentTime;
	public long lastTime;
	public long timer;
	public int frames;

	public FpsVariables(int fps) {
		this.fps = fps;
		targetTime = 1000000000 / fps;
		delta = 0;
		lastTime = System.nanoTime();
		timer = 0;
		frames = 0;
	}

	public void calculateFPS_Limitation() {
		currentTime = System.nanoTime();
		delta += (currentTime - lastTime) / targetTime;
		timer += currentTime - lastTime;
		lastTime = currentTime;
	}

	public boolean FPS_Limitation() {
		if (delta >= 1) {
			frames++;
			delta--;
			return true;
		}
		return false;
	}

	/**
	 * Colocar este método diretamente no código se necessário 
	 * exibir o fps em outro local
	 */
	public void FPS_printer(){
		if(timer>=1000000000){
			System.out.println("FPS:"+frames);
			frames = 0;
			timer = 0;
		}
	}
	
}
