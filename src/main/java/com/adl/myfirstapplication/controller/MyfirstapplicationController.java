package com.adl.myfirstapplication.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MyfirstapplicationController {
	
	String UPLOAD_DIR = "D://upload//";
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
		String fileExtension = getFileExtension(file);
		String filename = getRandomString();
 
		File targetFile = getTargetFile(fileExtension, filename);
 
		byte[] bytes = file.getBytes();
		System.out.print(bytes);
		file.transferTo(targetFile);
		String UploadedDirectory = targetFile.getAbsolutePath();
		System.out.print(UploadedDirectory);
		return filename;
	}
	
	private String getRandomString() {
		return new Random().nextInt(999999) + "_" + System.currentTimeMillis();
	}
	
	private File getTargetFile(String fileExtn, String fileName) {
		File targetFile = new File(UPLOAD_DIR + fileName + fileExtn);
		return targetFile;
	}
	
	private String getFileExtension(MultipartFile inFile) {
		String fileExtention = inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));
		return fileExtention;
	}
}
