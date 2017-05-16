/**
 * 
 */
package com.agoda.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sumit
 *
 */
public class FileUtil {
	public static void readFileChannel(InputStream inputStream,
			String destination) throws IOException {
		ReadableByteChannel rbc = Channels.newChannel(inputStream);
		FileOutputStream fos = new FileOutputStream(destination);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		rbc.close();

	}

	public static String getFileName(String file) {
		File f = new File(file);
		return f.getName();
	}

	public static void makeDirs(String url) {
		File file = new File(url);
		file.mkdirs();
	}

	public static void deleteAllFilesAndSourceDirectory(String sourceDirectory) {
		File f = new File(sourceDirectory);
		if (f.isDirectory()) {
			File[] containedFiles = f.listFiles();
			for (int i = 0; i < containedFiles.length; i++) {
				containedFiles[i].delete();
			}
			f.delete();
		}

	}

}
