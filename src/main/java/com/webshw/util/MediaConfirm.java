package com.webshw.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaConfirm {
	private static Map<String, MediaType> mediaMap;
	
	static { // static 멤버의 초기화 블록
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("jpg", MediaType.IMAGE_JPEG);
		mediaMap.put("gif", MediaType.IMAGE_GIF);
		mediaMap.put("png", MediaType.IMAGE_PNG);
		mediaMap.put("jpeg", MediaType.IMAGE_JPEG);
	}
	
	public static MediaType getMediaType(String ext) {
//		mediaMap.keySet() // 받아온 키가 있는지 검사하는 메서드
		return mediaMap.get(ext.toLowerCase());
	}
	
}
