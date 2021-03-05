package com.webshw.springProj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	// 스프링 책 488페이지 예제
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
		System.out.println(path);
		
		try {
//			uploadFile(request, file.getOriginalFilename(), file.getBytes());
			String uploadFile = uploadFileProcess.uploadFile(path, file.getOriginalFilename(), file.getBytes());
			return new ResponseEntity<String>(uploadFile, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ResponseBody // byte[]의 데이터(파일 데이터)가 web에 그대로 전송되도록 명시
	@RequestMapping(value="/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws IOException {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		MediaType mType = MediaConfirm.getMediaType(ext); // 이미지 파일인지 아닌지를 검사
		
		HttpHeaders header = new HttpHeaders(); // 헤더 객체 생성 (데이터 패킷의 header에 MIME type 정보를 붙임)
		
		String path = request.getSession().getServletContext().getRealPath("resources/uploads");
		
		fileName = fileName.replace('/', File.separatorChar);
		logger.info(path + fileName);
		
		in = new FileInputStream(path + fileName); // 서버의 하드디스크에서 업로드된 파일을 읽어오는 스트림 객체
		
		if (mType != null) {
			header.setContentType(mType); // header에 MIME type 정보를 붙임
		} else {
			fileName = fileName.substring(fileName.indexOf("_") + 1); // UUID_ 다음 originalFileName을 얻어옴
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 다운로드 가능한 파일임
			header.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			// ''는 자바에서는 문자열이라 인식히지 못해 \로 씀, 다운로드 될 수 있도록 일종의 링크 제공
		}
		
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), header, HttpStatus.CREATED); // 파일을 읽은 뒤 데이터를 그대로 전송
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest request) {
		logger.info("삭제할 파일 : " + fileName);
		
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		String path = request.getSession().getServletContext().getRealPath("resources/uploads");
		
		MediaType mType = MediaConfirm.getMediaType(ext);
		
		System.out.println("!!!!!!!!!!!!!!!!!!" + ext);
		
		String tmp = fileName.replace("thumb_", "");
		String originalFile = path + tmp; // 삭제해야할 오리지널 이미지 파일
		String thumbFile = path + fileName; // 삭제해야할 썸네일 이미지 파일
		
		// ************* 현재 OS가 windows이므로 아래의 코드를 해줘야 함 (windows는 File.seperatro : \, linux : /) *************
		
		originalFile = originalFile.replace("/", File.separator);
		thumbFile = thumbFile.replace("/", File.separator);
		
		logger.info("삭제할 파일(Original) : " + originalFile + ", thumb : " + thumbFile);
		if (mType != null) {
			// 이미지 파일이면
			// 삭제할 파일과 경로
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			new File(originalFile).delete();
			new File(thumbFile).delete();
			
		}
		
		// DB테이블에도 삭제
		
		return new ResponseEntity<String>("delete success!", HttpStatus.OK);
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
