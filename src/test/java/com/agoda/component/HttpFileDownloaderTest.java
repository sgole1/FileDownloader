package com.agoda.component;

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

public class HttpFileDownloaderTest {
	
	private static HttpFileDownloader httpFileDownloader;
	private static File destinationDirectoryLocation;
	private static File destinationFileLocation;

	@BeforeClass
	public static void setUp() throws Exception {
		httpFileDownloader = new HttpFileDownloader();
		PropertyUtil pUtil = new PropertyUtil();
		Properties properties = pUtil.getProperties("test.properties");
		String destination = properties.getProperty("file.destinationFolder");
		destinationDirectoryLocation = new File(destination);
		FileUtil.makeDirs(destination);
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		httpFileDownloader = null;
		destinationDirectoryLocation = null;
		destinationFileLocation.delete();
		
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testHttpFileDownload() {
	
		try {
			httpFileDownloader.downloadData(
					"http://www.mkyong.com/wp-content/uploads/2011/08/Spring3MVC-REST-HelloWorld-Example.zip",
					String.join("\\", destinationDirectoryLocation.getAbsolutePath(),
							"Spring3MVC-REST-HelloWorld-Example.zip"));
			destinationFileLocation = new File(String.join("/",
					destinationDirectoryLocation.getAbsolutePath(),
					"Spring3MVC-REST-HelloWorld-Example.zip"));
			
			Assert.assertNotNull(destinationFileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testException() throws  IOException{
		thrown.expect(java.io.IOException.class);
		httpFileDownloader.downloadData(
				"http://www.mkyong.com/wp-content/uploads/2011/08/Spring3MVC-REST-HelloWorld-Example.zip",
				String.join("\\", "/a/b/",
						"Spring3MVC-REST-HelloWorld-Example.zip"));
	}

}
