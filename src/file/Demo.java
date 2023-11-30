package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Demo {

	public static void main(String[] args) throws IOException {
//		File file = new File();
//		System.out.println(file.exists());
//		System.out.println(file.getName());
//		System.out.println(file.getPath());
		
//		try {
//			PrintWriter pw = new PrintWriter("D:\\FileDemo.txt");
//			pw.print(12);
//			pw.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			FileWriter fw = new FileWriter("D:\\FileDemo.txt");
//			fw.write("I'm gay");
//			fw.write("11");
//			fw.append("GAY");
//			fw.write("\n");
//			fw.write("Hello");
//			fw.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		

		File file = new File("D:\\Product.txt");
		try {
			FileWriter fw = new FileWriter("D:\\ProductOut.txt");
			BufferedWriter bfw = new BufferedWriter(fw);
			BufferedReader bfr = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);			
			String line = bfr.readLine();
//			line.split(" "); -> cắt chuỗi
			String[] rs = line.split("\\|");
			int a = Integer.parseInt(rs[0]);
			int b = Integer.parseInt(rs[5]);
			for (int i = 0; i < 100000; i++) {
					bfw.write((a + i) + "|" + rs[1] + "|" + rs[2] + "|" + rs[3] + "|" + rs[4] + "|" + (b + i) + "|" + rs[6] + "|" + rs[7] +"\n");
					System.out.println((new StringBuilder()).append(a + i).append("|").append(rs[1]).append("|").append(rs[2]).append("|").append(rs[3]).append("|").append(rs[4]).append("|").append(b + i).append("|").append(rs[6]).append("|").append(rs[7]));
			}
			bfw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
//		FileInputStream input = new FileInputStream("D:\\FileDemo.txt"); 
//		byte[] b = input.readAllBytes();
//		FileOutputStream output = new FileOutputStream("D:\\FileDemoOut.txt");
//		output.write(b);
		
		
		
//		File file = new File("D:\\FileDemo.txt");
//		String s = "Hello World";
//		byte[] inputs = s.getBytes();
//		FileOutputStream output = new FileOutputStream(file);
//		output.write(inputs);
		
		
		
		
		
		
		
		
		
		
	}

}
