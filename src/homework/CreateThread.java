package homework;

public class CreateThread extends Thread{
	
	MyThread myThread;
	
		
	public CreateThread(MyThread myThread) {
		this.myThread = myThread;
	}



	public void run() {
		try {
			myThread.readFile();
			myThread.generateFilesByStore();
			myThread.zipFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
