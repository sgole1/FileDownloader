package com.agoda.component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.agoda.util.FileUtil;

public class HttpFileDownloader implements FileDownloader {

	@Override
	public void downloadData(String sourceURL, String destination)
			throws IOException {
		URL url = new URL(sourceURL);
		File inputFile = new File(sourceURL);
		FileUtil.readFileChannel(url.openStream(),
				destination + inputFile.getName());
	}



//	public static void main(String args[]) {
//		try {
//			new HttpFileDownloader()
//					.downloadData(
//							"http://localhost:8080/RESTfulDemoApplication/file-management/pdf/demoPdfFile.pdf",
//							"D:/");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// System.out.println(type);
//		// System.out.println(FileUtil.identifyFileContentType("http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar"));
//		/*
//		 * try { System.out.println(getMimeType(
//		 * "http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar"
//		 * )); } catch (IOException e) { // TODO Auto-generated catch block
//		 * e.printStackTrace(); }
//		 */
//	}
}
