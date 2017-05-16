package com.agoda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	public Properties getProperties(String propertyFile) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(propertyFile).getFile());
		System.out.println(file.getAbsolutePath());
		InputStream inStream = null;
		Properties prop = new Properties();
		try {
			inStream = new FileInputStream(file);
			prop.load(inStream);
			return prop;
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
