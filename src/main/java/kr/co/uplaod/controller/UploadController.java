package kr.co.uplaod.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {

	@RequestMapping("/")
	public String home() {
		return "redirect:upload";
	}
	
	@RequestMapping("upload")
	public String upload(MultipartHttpServletRequest request) throws Exception {
		List<MultipartFile> files=request.getFiles("file"); // name="file" 인걸 받아온다.
		
		String uploadpath=request.getServletContext().getRealPath("uploads"); // 이건 선생님한테 배운 경로를 적어도됨 난 그거 몰라서 이거 적음
		Path path=Paths.get(uploadpath);
		if(Files.notExists(path)) {			// 저장경로가 없다면. 저장경로를 만든다 라는 if문
			Files.createDirectories(path);
		}
		
		for(MultipartFile file:files) {		// multiple에 있는 파일들을 하나씩 저장
			if(!file.isEmpty()) {
				String orifname1=file.getOriginalFilename();		// 원본이름	ex) exname.png
				String fname1=System.currentTimeMillis()%100000+"_"+orifname1;  // 중복없애는작업    ex) 5124_exname.png
				
				Path save=Paths.get(uploadpath,fname1);		// 경로 추가   		ex) 위의 uploadpath경로+5124_exname.png
				Files.copy(file.getInputStream(), save,StandardCopyOption.REPLACE_EXISTING);
			}
		}
		
		
		return "upload";
	}
}
