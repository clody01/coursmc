package com.clody.springboot.coursmc.uploadandload.services.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clody.springboot.coursmc.uploadandload.services.IUploadFileService;
import com.clody.springboot.coursmc.uploadandload.services.exceptions.FileException;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String UPLOAD_DIRECTORY = "uploads";

	@Value("${img.prefix.client.profile}")
	private String prefix;

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
		deleteFile(fileName);
		Path filePath = getFilePath(fileName);
		log.info(filePath.toString());
		Files.copy(file.getInputStream(), filePath);
		return fileName;
	}

	@Override
	public String copyFilePlus(BufferedImage image, String fileName, String extension) throws IOException {
		deleteFile(fileName);
		Path filePath = getFilePath(fileName);
		log.info(filePath.toString());
		Files.copy(getInputStream(image, extension), filePath);
		return fileName;
	}

	@Override
	public void deleteFile(String fileName) {
		if (fileName != null && fileName.length() > 0) {
			Path fileNameAnteriorPath = getFilePath(fileName);
			File pictureFileAnterior = fileNameAnteriorPath.toFile();
			if (pictureFileAnterior.exists() && pictureFileAnterior.canRead()) {
				pictureFileAnterior.delete();
			}
		}

	}

	@Override
	public Path getFilePath(String fileName) {
		return Paths.get(UPLOAD_DIRECTORY).resolve(fileName).toAbsolutePath();
	}

	@Override
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String fileExtension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(fileExtension) && !"jpg".equals(fileExtension)) {
			throw new FileException("Only PNG and JFG images are permited");
		}

		try {
			BufferedImage image = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(fileExtension)) {
				image = pngToJpg(image);
			}
			return image;
		} catch (IOException e) {
			throw new FileException("Exte Error!");
		}
	}

	
	public BufferedImage pngToJpg(BufferedImage image) {
		BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage image, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (Exception e) {
			throw new FileException("Archive Error!");
		}
	}
	@Override
	public BufferedImage cropSquare(BufferedImage imageSource) {
		int min = (imageSource.getHeight() <= imageSource.getWidth()) ? imageSource.getHeight() : imageSource.getWidth();
		return Scalr.crop(imageSource, (imageSource.getWidth() / 2) - (min / 2),(imageSource.getHeight() / 2) - (min / 2), min, min);
	}
	@Override
	public BufferedImage resize(BufferedImage imageSource, Integer size) {		
		return Scalr.resize(imageSource,Scalr.Method.ULTRA_QUALITY, size);
	}

}
