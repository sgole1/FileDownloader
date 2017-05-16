package com.agoda.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class CommonUtil {
	public static Integer[] getFlatArray(int[][] nestedData) {
		List<Integer> items = new ArrayList<>();
		IntStream dataStream = Arrays.stream(nestedData).flatMapToInt(
				row -> Arrays.stream(row));
		dataStream.forEach(x -> {
			items.add(x);
		});
		Integer[] data = items.toArray(new Integer[items.size()]);
		return data;
	}

	public static Map<String, String> getParsedURL(String url)
			throws URISyntaxException {
		Map<String, String> urlMap = new HashMap<String, String>();
		URI sftpURI = new URI(url);
		if (sftpURI.getUserInfo() != null) {
			String[] userInfo = sftpURI.getUserInfo().split(":");
			urlMap.put("user", userInfo.length > 0 ? userInfo[0] : null);
			urlMap.put("password", userInfo.length > 1 ? userInfo[1] : null);
		}

		urlMap.put("protocol", sftpURI.getScheme());

		urlMap.put("host", sftpURI.getHost());
		if (sftpURI.getPort() > 0) {
			urlMap.put("port", new Integer(sftpURI.getPort()).toString());
		}
		urlMap.put("completePath", sftpURI.getPath());
		String fileName = sftpURI.getPath().substring(
				sftpURI.getPath().lastIndexOf("/") + 1);
		String path = sftpURI.getPath().substring(0,
				sftpURI.getPath().lastIndexOf("/"));
		urlMap.put("path", path);
		urlMap.put("fileName", fileName);
		return urlMap;
	}

	public static String getCurrentDataAndTime() {
		LocalDateTime now = LocalDateTime.now();
		return String.join("_", new Integer(now.getDayOfMonth()).toString(),
				now.getMonth().toString(),
				new Integer(now.getYear()).toString(),
				new Integer(now.getHour()).toString(),
				new Integer(now.getMinute()).toString());

	}
}
