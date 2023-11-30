package homework;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		File files = new File("D:\\Hub\\Input\\");
		File[] listFile = files.listFiles();
		
		for(File file : listFile) {
			MyThread mt = new MyThread(file);
			CreateThread ct = new CreateThread(mt);
			
			ct.start();
		}
		
//		MyThread mt = new MyThread();
//		CreateThread ct = new CreateThread(mt); 
//
//		ct.start();
//	
	
	
	}

}
