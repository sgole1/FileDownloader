package com.agoda.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Map;

import com.agoda.util.CommonUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPFileDownloader implements FileDownloader {

	@Override
	public void downloadData(String sourceURL, String destinationFolder)
			throws IOException, JSchException, URISyntaxException,
			SftpException {
		Map<String, String> urlMap = CommonUtil.getParsedURL(sourceURL);
		Session session = null;
		try{
		 session = getConnectionSession(urlMap.get("host"),
				urlMap.get("port"), urlMap.get("user"), urlMap.get("password"));
		ChannelSftp channelSftp = getChannel(session);
		channelSftp.cd(urlMap.get("path"));
		readFile(channelSftp, destinationFolder, urlMap.get("fileName"));
		}finally{
			if(session != null)
			session.disconnect();
		}
	}

	private void readFile(ChannelSftp channelSftp, String destination,
			String sourceFileName) throws IOException, SftpException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(channelSftp.get(sourceFileName));
			byte[] buffer = new byte[1024];
			OutputStream os = new FileOutputStream(destination);
			bos = new BufferedOutputStream(os);
			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
		} finally {
			if(bis != null)
			bis.close();
			if(bos != null)
			bos.close();
		}

	}

//	public static void main(String args[]) {
//		
//		try {
//			new SFTPFileDownloader().downloadData(
//					"sftp://sumit:sumit@localhost:22/data/Sumit_Sinhmar.pdf",
//					"F:/");
//		} catch (IOException | JSchException | URISyntaxException
//				| SftpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private Session getConnectionSession(String host, String port, String user,
			String password) throws JSchException {
		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, Integer.parseInt(port));
		session.setPassword(password);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		return session;
	}

	private ChannelSftp getChannel(Session session) throws JSchException {

		Channel channel = session.openChannel("sftp");
		channel.connect();
		channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp channelSftp = (ChannelSftp) channel;
		return channelSftp;
	}
}