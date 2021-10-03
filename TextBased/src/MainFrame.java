import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
public class MainFrame {

	public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		File file = new File ("seba2.wav");
		AudioInputStream audioStream =AudioSystem.getAudioInputStream(file);
		Clip clip  = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
		
		MyThread thread1 =new MyThread();
		MyThread2 thread2  =new MyThread2();
		MyThread3 thread3 = new MyThread3();
		thread1.start();
		thread1.join();
		thread2.start();
		thread2.join();
		thread3.start();
		
	}


}
