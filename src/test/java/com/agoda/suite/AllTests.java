package com.agoda.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.agoda.component.FTPFileDownloaderTest;
import com.agoda.component.HttpFileDownloaderTest;
import com.agoda.component.HttpsFileDownloaderTest;
import com.agoda.service.DownloaderService;
import com.agoda.service.DownloaderServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ FTPFileDownloaderTest.class, HttpFileDownloaderTest.class,
		FTPFileDownloaderTest.class, HttpsFileDownloaderTest.class, DownloaderServiceTest.class })
public class AllTests {

}
