package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PicService;

/**
 * 上传图片处理
 * 
 * @author xushy
 *
 */
@Controller
public class PicController {
	@Autowired
	private PicService picService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile) {

		Map resultMap = picService.uploadPic(uploadFile);
		String json = JsonUtils.objectToJson(resultMap);
		return json;

	}
}
