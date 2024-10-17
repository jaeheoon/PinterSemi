package com.member.ncp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {
	public String uploadFile(String bucketName, String string, MultipartFile userProfileImg);
	public void deleteFile(String bucketName, String string, String userProfile);
}
