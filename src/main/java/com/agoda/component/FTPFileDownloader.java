package com.agoda.component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.agoda.util.FileUtil;

public class FTPFileDownloader implements FileDownloader {

	@Override
	public void downloadData(String path, String destination) throws IOException{
		URL url = new URL (path);
		URLConnection urlc = url.openConnection();
		InputStream is = urlc.getInputStream();
		FileUtil.readFileChannel(is,destination);
	}
}
