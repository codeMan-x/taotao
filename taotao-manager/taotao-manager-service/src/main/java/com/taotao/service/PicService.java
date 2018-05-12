package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PicService {
	Map uploadPic(MultipartFile uploadFile) ;
}
