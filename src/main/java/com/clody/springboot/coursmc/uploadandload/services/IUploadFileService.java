package com.clody.springboot.coursmc.uploadandload.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	Resource loadFile(String fileName) throws MalformedURLException;
	String copyFile(MultipartFile file) throws IOException;
	boolean deleteFile(String fileName);
	Path getFilePath(String fileName);
}
