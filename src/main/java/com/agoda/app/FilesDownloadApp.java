package com.agoda.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.agoda.service.DownloaderService;

public class FilesDownloadApp {

	final static Logger logger = Logger.getLogger(FilesDownloadApp.class);

	public static void main(String args[]) {
		String urlsString = getUrlsStringAsInput();

		String[] urls = urlsString.split(",");
		List<String> urlList = Arrays.asList(urls);
		DownloaderService downloaderService = new DownloaderService();
		if (urlList != null && urlList.size() > 0) {
			downloaderService.downloadDataFromUrls(urlList);
			if (downloaderService.isDownloadSuccessful()) {
				System.out.println("All files are downloaded successfully");
			} else {
				System.out.println("File download failed inbetween");
			}
		}

	}

	private static String getUrlsStringAsInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("Enter the URLs seperated by ,");
		String urlsString = null;
		try {
			urlsString = reader.readLine();
		} catch (IOException e) {
			logger.error("Error : ", e);
		}
		return urlsString;
	}

}
