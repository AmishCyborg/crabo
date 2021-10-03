
public class MyThread3 extends Thread{

	public void run() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameFrame.actualGame();
		
	}
}
	
