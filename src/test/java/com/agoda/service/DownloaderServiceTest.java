package com.agoda.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DownloaderServiceTest {
	
	DownloaderService downloaderService;
	
	@Test
	public void testDownloadDataFromUrls() {
		downloaderService = new DownloaderService();
		List<String> urlList = new ArrayList<String>();
		urlList.add("https://github.com/hannonhill/Webservices-Java-Sample-Project/archive/master.zip");
		urlList.add("sftp://sumit:sumit@localhost:22/data/Sumit_Sinhmar.pdf");
		urlList.add("http://www.mkyong.com/wp-content/uploads/2011/08/Spring3MVC-REST-HelloWorld-Example.zip");
		urlList.add("ftp://sumit:sumit@localhost/raz_lq40_gandhi_clr_ds.pdf");
		downloaderService.downloadDataFromUrls(urlList );
		assertTrue(downloaderService.isDownloadSuccessful());
	}

}
