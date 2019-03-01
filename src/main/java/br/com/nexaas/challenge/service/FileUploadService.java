package br.com.nexaas.challenge.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface File Upload Service
 * 
 * @author emanuel.foliveira
 * @since 03/01/2019
 * @version 1.0
 */
public interface FileUploadService {

	Double store(MultipartFile file);
}