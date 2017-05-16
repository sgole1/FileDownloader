package com.agoda.component;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.agoda.util.FileUtil;
import com.agoda.util.PropertyUtil;

public class HttpsFileDownloaderTest {

	private static HttpsFileDownloader httpsFileDownloader;
	private static File destinationDirectoryLocation;
	private static File destinationFileLocation;

	@BeforeClass
	public static void setUp() throws Exception {
		httpsFileDownloader = new HttpsFileDownloader();
		PropertyUtil pUtil = new PropertyUtil();
		Properties properties = pUtil.getProperties("test.properties");
		String destination = properties.getProperty("file.destinationFolder");
		destinationDirectoryLocation = new File(destination);
		FileUtil.makeDirs(destination);
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		httpsFileDownloader = null;
		destinationDirectoryLocation = null;
		destinationFileLocation.delete();
		
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testHttpsFileDownload() {
	
		try {
			httpsFileDownloader.downloadData(
					"https://github.com/hannonhill/Webservices-Java-Sample-Project/archive/master.zip",
					String.join("/", destinationDirectoryLocation.getAbsolutePath(),
							"master.zip"));
			destinationFileLocation = new File(String.join("/",
					destinationDirectoryLocation.getAbsolutePath(),
					"master.zip"));
			
			Assert.assertNotNull(destinationFileLocation);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	//@Test
	public void testException() throws  Exception{
		thrown.expect(Exception.class);
		httpsFileDownloader.downloadData(
				"https://github.com/hannonhill/Webservices-Java-Sample-Project/archive/master.zip",
				String.join("\\", "/a/b/",
						"master.zip"));
	}

}
