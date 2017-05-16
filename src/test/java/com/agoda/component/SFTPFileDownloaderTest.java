package com.agoda.component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.agoda.util.FileUtil;
import com.agoda.util.PropertyUtil;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class SFTPFileDownloaderTest {
	
	private static SFTPFileDownloader sftpFileDownloader;
	private static File destinationDirectoryLocation;
	private static File destinationFileLocation;

	@BeforeClass
	public static void setUp() throws Exception {
		sftpFileDownloader = new SFTPFileDownloader();
		PropertyUtil pUtil = new PropertyUtil();
		Properties properties = pUtil.getProperties("test.properties");
		String destination = properties.getProperty("file.destinationFolder");
		destinationDirectoryLocation = new File(destination);
		FileUtil.makeDirs(destination);
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		sftpFileDownloader = null;
		destinationDirectoryLocation = null;
		destinationFileLocation.delete();
		
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testSFTPFileDownload() {
	
		try {
			sftpFileDownloader.downloadData(
					"sftp://sumit:sumit@localhost:22/data/Sumit_Sinhmar.pdf",
					String.join("\\", destinationDirectoryLocation.getAbsolutePath(),
							"Sumit_Sinhmar.pdf"));
			destinationFileLocation = new File(String.join("/",
					destinationDirectoryLocation.getAbsolutePath(),
					"Sumit_Sinhmar.pdf"));
			
			Assert.assertNotNull(destinationFileLocation);
		} catch (IOException | JSchException |URISyntaxException |
				SftpException sftpe) {
			sftpe.printStackTrace();
		}


	}
	
	@Test
	public void testException() throws  IOException, JSchException, URISyntaxException,
	SftpException{
		thrown.expect(JSchException.class);
		sftpFileDownloader.downloadData(
				"sftp://sumit:sumit1@localhost:22/data/Sumit_Sinhmar.pdf",
				String.join("\\", "/a/b/",
						"Sumit_Sinhmar.pdf"));
	}

}
