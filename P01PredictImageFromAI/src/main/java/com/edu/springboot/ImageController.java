package com.edu.springboot;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageController {
	
	@GetMapping("/image-to-base64.do")
	public Map<String, String> convertImageToBase64(
			@RequestParam("imageName") String imageName) {
		String encodedString = null;
		Map<String, String> map = new HashMap<>();
		
		try {
			//이미지의 물리적 경로 얻어오기
			String uploadDir = ResourceUtils
					.getFile("classpath:static/").toPath().toString();
			System.out.println("물리적경로:"+uploadDir);
			String imagePath = uploadDir + File.separator + imageName;
			//이미지 파일 불러오기
			File file = new File(imagePath);
			//파일을 바이트 배열로 변환
			byte[] fileContent = Files.readAllBytes(file.toPath());
			encodedString = Base64.getEncoder()
								.encodeToString(fileContent);
			System.out.println(encodedString);
			//Flask 서버 URL 설정 (이미지 이름을 URL 파라미터로 전달)
			String flaskUrl = "http://127.0.0.1:5000/getDecodeImage.fk"
					+ "?imageName="+imageName;
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.postForObject(flaskUrl,
					encodedString, String.class);
			//응답 출력 및 JSON출력을 위한 Map생성
			System.out.println("Flask 응답: " + response);
			response = response.replace("\\", "").replace("\n", "");
			map.put("result", "success");
			map.put("imageName", imageName);
			map.put("Flask", response);
		}
		catch (Exception e) {
			e.printStackTrace();
			encodedString = "Error: " + e.getMessage();
			map.put("result", "error");
		}
		
		return map;
	}
}
