package com.webshw.springProj;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webshw.util.MediaConfirm;
import com.webshw.util.uploadFileProcess;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static String uploadPath = "D:\\Academy\\Spring\\springupload";
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@RequestMapping(value="/test")
	public String ajaxTest() {
		System.out.println("에이젝스 호출");
		
		return "testJson";
	}
	
	@RequestMapping(value="/uploadForm", method=RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value="/uploadForm", method=RequestMethod.POST)
//	public String uploadForm(@RequestParam("uploadfile") MultipartFile file, Model model) - 같은 거
	public String uploadForm(HttpServletRequest request, MultipartFile uploadfile, Model model) throws IOException {
		System.out.println("업로드 파일 이름 : " + uploadfile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + uploadfile.getSize());
		System.out.println("업로드 파일의 타입 : " + uploadfile.getContentType()); // 파일의 MIME type
		System.out.println(Arrays.toString(uploadfile.getBytes())); // 컨텐츠의 내용을 2진수의 배열로 보여줌
		
		String uploadFileName = uploadFile(request, uploadfile.getOriginalFilename(), uploadfile.getBytes()); // DB로 전송
		
		System.out.println(request.getRealPath("resources/uploads"));;
		
		// 서비스단 호출
		
		model.addAttribute("saveFileName", uploadFileName);
		
		return "uploadResult";
	}

	@RequestMapping(value="/uploadAjax", method=RequestMethod.GET)
	public void uploadAjax() {
		
	}
	
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> uploadAjax(HttpServletRequest request, MultipartFile file) {
		
		System.out.println("업로드 파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 사이즈 : " + file.getSize());
		System.out.println("업로드 파일의 타입 : " + file.getContentType());
		System.out.println("파일 separator : " + File.separator);
		
		String path = request.getSession().getServletContext().getRealPath("resources/uploads");
		
		try {
//			uploadFile(request, file.getOriginalFilename(), file.getBytes());
			uploadFileProcess.uploadFile(path, file.getOriginalFilename(), file.getBytes());
			return new ResponseEntity<String>(file.getOriginalFilename(), HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		MediaType mType = MediaConfirm.getMediaType(ext);
		
		HttpHeaders header = new HttpHeaders();
		
		if (mType != null) {
			header.setContentType(mType);
		} else {
			fileName = fileName.substring(fileName.indexOf("_") + 1); // UUID_ 다음 originalFileName을 얻어옴
		}
		
		return entity;
	}
	
	private String uploadFile(HttpServletRequest request, String originalFilename, byte[] bytes) throws IOException {
		// UUID: Universal Unique ID
		
		UUID uuid = UUID.randomUUID();
		
		String saveName = uuid.toString() + "_" + originalFilename;
		System.out.println("완성된 파일 이름 : " + saveName);
		
		String path = request.getSession().getServletContext().getRealPath("resources/uploads");
		System.out.println(path);
		
		File target = new File(request.getRealPath("resources/uploads"), saveName);
		FileCopyUtils.copy(bytes, target);
		
		return saveName;
	}
	
	
}
