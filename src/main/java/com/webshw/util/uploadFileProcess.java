package com.webshw.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class uploadFileProcess {
	private static final Logger logger = LoggerFactory.getLogger(uploadFileProcess.class);
	
	/**
	 * @Method Name : uploadFile
	 * @작성일 : 2021. 3. 4.
	 * @작성자 : goott6
	 * 변경이력 :
	 * @Method 설명 : 업로드된 파일을 기반으로 실제 저장되는 파일의 썸네일을 생성하든, 파일아이콘을 생성하던
	 * @param uploadPath
	 * @param originalName
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws IOException {
		
		UUID uuid = UUID.randomUUID();
		
		String savedName = uuid.toString() + "_" + originalName;
		String savePath = calPath(uploadPath);
		System.out.println("히규힝 : " + savePath);
		File target = new File(uploadPath + savePath, savedName);
		FileCopyUtils.copy(fileData, target); // 실제 저장
		
		String ext = originalName.substring(originalName.lastIndexOf(".") + 1); // 확장자
		
		String uploadFileName = null;
		if (MediaConfirm.getMediaType(ext) != null) {
			uploadFileName = makeThumbnail(uploadPath, savePath, savedName); // 이미지 파일이므로 썸네일 생성
		} else {
			uploadFileName = makeIcon(uploadPath, savePath, savedName);
		}
		
		return uploadFileName;
	}
	
	private static String makeIcon(String uploadPath, String savePath, String savedName) {
		String iconName = uploadPath + savePath + File.separator + savedName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	/**
	 * @Method Name : calPath
	 * @작성일 : 2021. 3. 4.
	 * @작성자 : goott6
	 * 변경이력 :
	 * @Method 설명 : 현재 날짜를 연/월/일을 폴더로 만들기 위해 경로 처리
	 * @param uploadPath : 서버의 업로드 폴더 전체 경로
	 * @return : 하위 폴더(연/월/일) 경로 반환
	 * @throws IOException
	 */
	public static String calPath(String uploadPath) {
		// 현재 연/월/일을 폴더로 만들기 위해 처리
		
		Calendar cal = Calendar.getInstance();
		
		// 날짜별로 파일을 저장하기 위한 폴더 생성
		String yearPath = File.separator + (cal.get(Calendar.YEAR) + ""); // "\2021"
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // "\03" 
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // "\04"
		
		makeDir(uploadPath, yearPath, monthPath, datePath); // 폴더 생성
		
		logger.info(datePath);
		// ******************************************************
		
		return datePath;
	}

	/**
	 * @Method Name : makeThumbnail
	 * @작성일 : 2021. 3. 4.
	 * @작성자 : goott6
	 * 변경이력 :
	 * @Method 설명 : 업로드 된 경로의 이미지 파일을 썸네일을 만든다
	 * @param uploadPath : 서버의 파일 업로드 실제 경로
	 * @param path : 업로드 경로의 하위 연월일 경로
	 * @param fileName : 업로드된 파일 이름
	 * @return : 서버에 업로드 폴더 경로를 제외한 하위 경로 + 파일 이름
	 * @throws IOException - write할 때 발생
	 */
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws IOException {
		
		BufferedImage sourceImg =  ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100); // 높이 100px 리사이징
		
		String thumbnailName = uploadPath + path + File.separator + "thumb_" + fileName; // 썸네일 이미지의 경로와 이름
		File newThumbFile = new File(thumbnailName);
		
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(ext);
		
		ImageIO.write(destImg, ext.toLowerCase(), newThumbFile);
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// 기능: 넘겨 받은 경로에 실제로 폴더를 생성하는 메서드
	// 매개변수
	// String uploadPath: 서버의 실제 물리적 주소(resources/uploads)
	// String path: 연/월/일 정보
	// 반환값 타입: 없음
	// 만든 날짜: 2021-03-04
	// 만든이: winspow@gmail.com
	private static void makeDir(String uploadPath, String... paths) {
		// String... paths는 가변인자(변화가 가능한 인자) yearPath, monthPath, datePath 변수의 값을 paths의 배열의 값으로 받는다
		
//		for (String s : paths) {
//			System.out.println(s);
//		}
		
		if (new File(uploadPath + paths[paths.length - 1]).exists()) {
			// 해당 경로에 해당 폴더가 존재한다면 안만들어됨 exists: 존재하냐? 의미
			return;
		}
		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
			
		}
	}
	
	
	
}
