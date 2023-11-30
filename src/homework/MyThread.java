package homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MyThread {

	private String line;
	private int id = 1;
	private String[] rs;
	private String brand;
	private String model;
	private String year;
	private String size;
	private int serialNumber = 1;
	private String color;
	private String discount;
	private String[] stores;
	private int storesNumber;
	private String[] storesEnd;
	private int storesEndNumber;
	private int storeSeries = storesEndNumber - storesNumber + 1;
	private File file;

	public MyThread() {
		super();
	}

	public MyThread(File file) {
		super();
		this.file = file;
	}

	public MyThread(String line, int id, String[] rs, String brand, String model, String year, String size,
			int serialNumber, String color, String discount, String[] stores, int storesNumber, String[] storesEnd,
			int storesEndNumber, int storeSeries) {
		super();
		this.line = line;
		this.id = id;
		this.rs = rs;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.size = size;
		this.serialNumber = serialNumber;
		this.color = color;
		this.discount = discount;
		this.stores = stores;
		this.storesNumber = storesNumber;
		this.storesEnd = storesEnd;
		this.storesEndNumber = storesEndNumber;
		this.storeSeries = storeSeries;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getRs() {
		return rs;
	}

	public void setRs(String[] rs) {
		this.rs = rs;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String[] getStores() {
		return stores;
	}

	public void setStores(String[] stores) {
		this.stores = stores;
	}

	public int getStoresNumber() {
		return storesNumber;
	}

	public void setStoresNumber(int storesNumber) {
		this.storesNumber = storesNumber;
	}

	public String[] getStoresEnd() {
		return storesEnd;
	}

	public void setStoresEnd(String[] storesEnd) {
		this.storesEnd = storesEnd;
	}

	public int getStoresEndNumber() {
		return storesEndNumber;
	}

	public void setStoresEndNumber(int storesEndNumber) {
		this.storesEndNumber = storesEndNumber;
	}

	public int getStoreSeries() {
		return storeSeries;
	}

	public void setStoreSeries(int storeSeries) {
		this.storeSeries = storeSeries;
	}

	public synchronized void zipFile(String brand) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy_HHmmss");
		String timestamp = dateFormat.format(new java.util.Date());
		String sourceFolderPath = "D:\\Hub\\Output";
		String outputFolderPath = "D:\\Hub\\Archive\\";
		File sourceFolder = new File(sourceFolderPath);
		File[] files = sourceFolder.listFiles();

		int filesPerZip = 5;
		int zipCount = 1;
		int fileCount = 0;

		try {
			FileOutputStream fos = null;
			ZipOutputStream zos = null;

			for (File file : files) {
				if (fileCount % filesPerZip == 0) {
					String zipFilePath = outputFolderPath + timestamp + "_" + brand + zipCount + ".zip";
					fos = new FileOutputStream(zipFilePath);
					zos = new ZipOutputStream(fos);
					zipCount++;
				}
				
				byte[] buffer = new byte[1024];
				FileInputStream fis = new FileInputStream(file);
				zos.putNextEntry(new ZipEntry(file.getName()));

				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();
				fis.close();
				fileCount++;

				if (fileCount % filesPerZip == 0) {
					zos.close();
					fos.close();
				}
				file.delete();
			}

			if (zos != null) {
				zos.close();
				fos.close();
			}

			System.out.println("Files zipped successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void zipFile() {
		this.zipFile(brand);
	}

	public synchronized void generateFilesByStore(int id, String brand, String model, String year, String size, int serialNumber,
			String color, String discount, String[] stores, int storeSeri) {
		storeSeries = storesEndNumber - storesNumber + 1;
		for (int i = 0; i < storeSeri; i++) {
			String fileName = "ProductOut" + "_" + brand + (i + 1) + ".txt";
			try {
				FileWriter fw = new FileWriter("D:\\Hub\\Output\\" + fileName);
				BufferedWriter bfw = new BufferedWriter(fw);
				for (int j = 0; j < 10000; j++) {
					bfw.write((id + j) + "|" + brand + "|" + model + "|" + year + "|" + size + "|" + (serialNumber + j)
							+ "|" + color + "|" + discount + "|" + "store" + (i + 1) + "\n");
				}
				bfw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void generateFilesByStore() {
		this.generateFilesByStore(id, brand, model, year, size, serialNumber, color, discount, stores, storeSeries);
	}

	public synchronized void readFile(File file) {
		try {
			BufferedReader bfr = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
			line = bfr.readLine();
			rs = line.split("\\|");
			id = Integer.parseInt(rs[0]);
			brand = rs[1];
			model = rs[2];
			year = rs[3];
			size = rs[4];
			serialNumber = Integer.parseInt(rs[5]);
			color = rs[6];
			discount = rs[7];
			stores = rs[8].split("e");
			storesNumber = Integer.parseInt(stores[1]);
			storesEnd = rs[9].split("e");
			storesEndNumber = Integer.parseInt(storesEnd[1]);
			storeSeries = storesEndNumber - storesNumber + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void readFile() {
		this.readFile(file);
	}
	
//	public void runOneThread() {
//		File files = new File("D:\\Hub\\Input\\");
//		File[] listFile = files.listFiles();
//	
//		for (File file : listFile) {
//			readFile(file);
//			generateFilesByStore();
//			zipFile();
//		}
//	}

}