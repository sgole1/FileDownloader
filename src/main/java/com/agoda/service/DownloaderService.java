package com.agoda.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.agoda.component.FileDownloader;
import com.agoda.component.FileDownloaderFactory;
import com.agoda.util.CommonUtil;
import com.agoda.util.FileUtil;
import com.agoda.util.PropertyUtil;

public class DownloaderService {

	final static Logger logger = Logger.getLogger(DownloaderService.class);

	private boolean isDownloadSuccessful;

	public boolean isDownloadSuccessful() {
		return isDownloadSuccessful;
	}

	private static Properties propertyFileReference;

	public void downloadDataFromUrls(List<String> urlList) {
		if (propertyFileReference == null) {
			propertyFileReference = new PropertyUtil()
					.getProperties("config.properties");
		}
		String destinationFolder = getDestinationFolder();
		FileDownloaderFactory fileDownloaderFactory = new FileDownloaderFactory();
		try {
			urlList.forEach(url -> {
				try {
					Map<String, String> urlMap = CommonUtil.getParsedURL(url);

					FileDownloader fileDownloader = fileDownloaderFactory
							.getFileDownloaderInstance(urlMap.get("protocol"));
					fileDownloader.downloadData(
							url,
							String.join(("/"), destinationFolder,
									urlMap.get("fileName")));
				} catch (Exception e) {
					logger.error("Error while downloading the data", e);
					FileUtil.deleteAllFilesAndSourceDirectory(destinationFolder);
					isDownloadSuccessful = false;
					throw new RuntimeException(e);
				}
			});

		} catch (RuntimeException re) {
			logger.error("RuntimeException :", re);
			return;
		}
		isDownloadSuccessful = true;

	}

	private static String getDestinationFolder() {

		String currentDateTime = CommonUtil.getCurrentDataAndTime();
		String destinationFilePath = String.join("/",
				propertyFileReference.getProperty("file.destinationFolder"),
				currentDateTime);

		FileUtil.makeDirs(destinationFilePath);
		return destinationFilePath;
	}
}
