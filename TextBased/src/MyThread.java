
public class MyThread extends Thread {

	public void run() {
		
		HighScore.getConnection();
		HighScore.createTable();
		HighScore.readAndPost();
	}
}
