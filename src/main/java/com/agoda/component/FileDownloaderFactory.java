/**
 * 
 */
package com.agoda.component;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Sumit
 *
 */
public class FileDownloaderFactory {

	public FileDownloader getFileDownloaderInstance(String scheme)
			throws Exception {
		FileDownloader fileDownloader = null;
		switch (scheme) {
		case "http":
			fileDownloader = new HttpFileDownloader();
			break;
		case "https":
			fileDownloader = new HttpsFileDownloader();
			break;
		case "ftp":
			fileDownloader = new FTPFileDownloader();
			break;
		case "sftp":
			fileDownloader = new SFTPFileDownloader();
			break;
		default:
			System.out.println("Scheme is not supported yet");
			return null;
		}

		return fileDownloader;

	}

}
