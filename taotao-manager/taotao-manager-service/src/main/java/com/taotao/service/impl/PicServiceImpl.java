package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PicService;

@Service
public class PicServiceImpl implements PicService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public Map uploadPic(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			// 生成新文件名
			String oldName = uploadFile.getOriginalFilename();
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			// 图片上传
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH,
					imagePath, newName, uploadFile.getInputStream());
			// 返回结果
			if (result) {
				resultMap.put("error", 0);
				resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
				return resultMap;
			} else {
				resultMap.put("error", 1);
				resultMap.put("message", "上传失败！");
				return resultMap;
			}

		} catch (Exception e) {
			resultMap.put("error", 1);
			resultMap.put("message", "发生异常，上传失败！");
			return resultMap;
		}
	}

}
