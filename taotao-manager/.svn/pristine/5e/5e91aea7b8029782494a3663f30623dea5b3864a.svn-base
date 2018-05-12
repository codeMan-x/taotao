package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	@Test
	public void testFtpClient() throws SocketException, IOException {

		FTPClient ftpClient = new FTPClient();// 创建一个ftpClent对象
		ftpClient.connect("192.168.94.101", 21); // 创建ftp链接
		ftpClient.login("root", "123321"); // 登录ftp 用户名+密码
		ftpClient.changeWorkingDirectory("/home/ftpuser/www");// 设置上传路径
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);// 修改上传文件格式
		FileInputStream fileInputStream = new FileInputStream(new File("E:\\图片\\2.jpg"));// 读取本地文件
		ftpClient.storeFile("abc.jpg", fileInputStream);// 上传文件;服务端文件名;上传文件的流
		ftpClient.logout(); // 关闭链接
	}
}
