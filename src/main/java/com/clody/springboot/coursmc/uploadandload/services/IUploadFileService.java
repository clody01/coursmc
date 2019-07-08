package com.clody.springboot.coursmc.uploadandload.services;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	Resource loadFile(String fileName) throws MalformedURLException;
	String copyFile(MultipartFile file) throws IOException;
	void deleteFile(String fileName);
	Path getFilePath(String fileName);
	BufferedImage getJpgImageFromFile(MultipartFile uploadedFile);
	String copyFilePlus(BufferedImage image, String fileName, String extension) throws IOException;
	BufferedImage cropSquare(BufferedImage jpgImage);
	BufferedImage resize(BufferedImage jpgImage, Integer size);
}
