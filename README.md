# FileDownloader
A standalone application to download data through different protocols like Http, Https, FTP & SFTP.
The different protocols support is added through the Factory pattern (Easy to extend for additional protocols).  Core Java (Java 8) is used for the development. Here all the files are downloaded in current Data + Current time folder. If download fails for any protocol, all files are rolledback on this folder.
