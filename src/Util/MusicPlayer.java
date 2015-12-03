package Util;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer implements Runnable{
	private static AudioInputStream sound;
	private static Thread musica;
	static AudioInputStream din;

	public MusicPlayer(){
	}
	private static void music(AudioInputStream audioInputStream)
	{
	  try
	  {
	    // Get AudioInputStream from given file.	
	    AudioInputStream in = audioInputStream;
	    if (in != null)
	    {
	        AudioFormat baseFormat = in.getFormat();
	        AudioFormat  decodedFormat = new AudioFormat(
	                AudioFormat.Encoding.PCM_SIGNED,
	                baseFormat.getSampleRate(),
	                16,
	                baseFormat.getChannels(),
	                baseFormat.getChannels() * 2,
	                baseFormat.getSampleRate(),
	                false);
	         // Get AudioInputStream that will be decoded by underlying VorbisSPI
	        din = AudioSystem.getAudioInputStream(decodedFormat, in);
	        // Play now !
	        rawplay(decodedFormat/*, din*/);
	        in.close();		
	    }
	  }
	  catch (Exception e)
	  {
	    e.printStackTrace();
	  }
	}

	private static void rawplay(AudioFormat targetFormat 
	                                   /*, AudioInputStream din*/) throws IOException, LineUnavailableException
	{
	   byte[] data = new byte[4096];
	  SourceDataLine line = getLine(targetFormat);		
	  if (line != null)
	  {
	     // Start
	    line.start();
	     int nBytesRead = 0, nBytesWritten = 0;
	     while (nBytesRead != -1 && !stoped)
	    {
	        nBytesRead = din.read(data, 0, data.length);
	         if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);
	    }
	     // Stop
	    line.drain();
	    line.stop();
	    line.close();
//	    din.close();
	    
	  }		
	}

	private static SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
	{
	  SourceDataLine res = null;
	  DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	  res = (SourceDataLine) AudioSystem.getLine(info);
	  res.open(audioFormat);
	  return res;
	}
	@Override
	public void run() {
		MusicPlayer.music(sound);		
	}
	
	public void start(AudioInputStream sound) {
		//if(sound == MusicPlayer.sound) return;
		MusicPlayer.sound = sound;
		if(musica != null){
			stop();
		}
		stoped = false;
		musica = new Thread(this,"Music");
		musica.setDaemon(true);
		musica.start();
	}
	public static boolean stoped;
	public static void stop() {
		try {
			stoped=true;
			din.close();			
			musica.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean isAlive(){
		return musica.isAlive();
	}
}
