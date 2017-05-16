package com.agoda.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.agoda.util.FileUtil;
import com.agoda.util.PropertyUtil;

public class FTPFileDownloaderTest  {

	private static FTPFileDownloader fTPFileDownloader;
	private static File destinationDirectoryLocation;
	private static File destinationFileLocation;

	@BeforeClass
	public static void setUp() throws Exception {
		fTPFileDownloader = new FTPFileDownloader();
		PropertyUtil pUtil = new PropertyUtil();
		Properties properties = pUtil.getProperties("test.properties");
		String destination = properties.getProperty("file.destinationFolder");
		destinationDirectoryLocation = new File(destination);
		FileUtil.makeDirs(destination);
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		fTPFileDownloader = null;
		destinationDirectoryLocation = null;
		destinationFileLocation.delete();
		
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testFtpFileDownload() {
	
		try {
			fTPFileDownloader.downloadData(
					"ftp://sumit:sumit@localhost/raz_lq40_gandhi_clr_ds.pdf",
					String.join("\\", destinationDirectoryLocation.getAbsolutePath(),
							"raz_lq40_gandhi_clr_ds.pdf"));
			destinationFileLocation = new File(String.join("/",
					destinationDirectoryLocation.getAbsolutePath(),
					"raz_lq40_gandhi_clr_ds.pdf"));
			
			Assert.assertNotNull(destinationFileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testException() throws  IOException{
		thrown.expect(java.io.IOException.class);
		fTPFileDownloader.downloadData(
				"ftp://sumit:sumit@localhost/raz_lq40_gandhi_clr_ds.pdf",
				String.join("\\", "/a/b/",
						"raz_lq40_gandhi_clr_ds.pdf"));
	}

}
