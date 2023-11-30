package work;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.processing.Generated;

public class Work {

	public static void main(String[] args) throws IOException {

		while (true) {

			File files = new File("D:\\Hub\\Input\\");
			File[] listFile = files.listFiles();

			for (File file : listFile) {
				try {
					BufferedReader bfr = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
					String line = bfr.readLine();
					String[] rs = line.split("\\|");
					int id = Integer.parseInt(rs[0]);
					String brand = rs[1];
					String model = rs[2];
					String year = rs[3];
					String size = rs[4];
					int serialNumber = Integer.parseInt(rs[5]);
					String color = rs[6];
					String discount = rs[7];
					String[] stores = rs[8].split("e");
					int storesNumber = Integer.parseInt(stores[1]);
					String[] storesEnd = rs[9].split("e");
					int storesEndNumber = Integer.parseInt(storesEnd[1]);
					int storeSeries = storesEndNumber - storesNumber + 1;

					generateFilesByStore(id, brand, model, year, size, serialNumber, color, discount, storesEnd,
							storeSeries);
					zipFile(brand);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void zipFile(String brand) {
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

	private static void generateFilesByStore(int id, String brand, String model, String year, String size,
			int serialNumber, String color, String discount, String[] stores, int storeSeri) {
		for (int i = 0; i < storeSeri; i++) {
			String fileName = "ProductOut" + "_" + brand + (i + 1) + ".txt";
			try {
				FileWriter fw = new FileWriter("D:\\Hub\\Output\\" + fileName);
				BufferedWriter bfw = new BufferedWriter(fw);
				for (int j = 0; j < 1000000; j++) {
					bfw.write((id + j) + "|" + brand + "|" + model + "|" + year + "|" + size + "|" + (serialNumber + j)
							+ "|" + color + "|" + discount + "|" + "store" + (i + 1) + "\n");
				}
				bfw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
