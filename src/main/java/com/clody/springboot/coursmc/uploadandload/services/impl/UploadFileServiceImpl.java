package com.clody.springboot.coursmc.uploadandload.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clody.springboot.coursmc.uploadandload.services.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String UPLOAD_DIRECTORY = "uploads";

	@Override
	public Resource loadFile(String fileName) throws MalformedURLException {
		Path resourcePath = getFilePath(fileName);
		log.info(resourcePath.toString());
		Resource resource = new UrlResource(resourcePath.toUri());
		if (!resource.exists() || !resource.isReadable()) {
			resourcePath = Paths.get("src/main/resources/static/images/").resolve("nouser.png").toAbsolutePath();
			resource = new UrlResource(resourcePath.toUri());
			log.error("Error: Can not download the file: " + fileName);
		}
		return resource;
	}

	@Override
	public String copyFile(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
		Path filePath = getFilePath(fileName);
		log.info(filePath.toString());
		Files.copy(file.getInputStream(), filePath);
		return fileName;
	}

	@Override
	public boolean deleteFile(String fileName) {
		if (fileName != null && fileName.length() > 0) {
			Path fileNameAnteriorPath = Paths.get("uploads").resolve(fileName).toAbsolutePath();
			File pictureFileAnterior = fileNameAnteriorPath.toFile();
			if (pictureFileAnterior.exists() && pictureFileAnterior.canRead()) {
				pictureFileAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getFilePath(String fileName) {
		return Paths.get(UPLOAD_DIRECTORY).resolve(fileName).toAbsolutePath();
	}

}
