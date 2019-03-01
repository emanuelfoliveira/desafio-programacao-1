package br.com.nexaas.challenge.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.nexaas.challenge.models.FileUpload;
import br.com.nexaas.challenge.repositories.FileUploadRespository;

/**
 * Class Service to upload, parse, save and calculate gross income
 * 
 * @author emanuel.foliveira
 * @since 03/01/2019
 * @version 1.0
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {
	
	private static final String DELIMITER_TAB = "\\t";

	@Autowired
	private FileUploadRespository fileUploadRespository;

	/**
	 * Method to persist files info in H2 Database and calculate gross income
	 * 
	 * @param multiPartFile
	 * @return Double of gross income calculated
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Double store(MultipartFile multiPartFile) {
		try {
			clearFileUploadTable();
			
			Path pathFile = this.multipartToFile(multiPartFile).toPath();
			Stream<String> stream = Files.lines(pathFile).skip(1);
			
			parseAndPersistFileUpload(stream);
			 
		} catch (IllegalStateException | IOException exception) {
			exception.printStackTrace();
		}
		
		return calculateGrossIncome();
	}

	/**
	 * Parse and saving the lines of files.
	 * @param stream
	 */
	private void parseAndPersistFileUpload(Stream<String> stream) {
		stream.forEach(line -> fileUploadRespository.save(new FileUpload(line.split(DELIMITER_TAB))));
	}

	/**
	 * Using stream with lambda to calculate de gross income.
	 * @return Double gross income
	 */
	private Double calculateGrossIncome() {
		return fileUploadRespository.findAll()
										.stream()
										.map(grossIncome -> grossIncome.getItemPrice())
										.mapToDouble(Double::doubleValue)
										.sum();
	}	

	/**
	 * Drop table FileUpload before begin.
	 */
	private void clearFileUploadTable() {
		fileUploadRespository.deleteAll();
	}

	/**
	 * Convert MultipartFile to a File
	 * @param multipart
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convertedFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convertedFile);
		return convertedFile;
	}
}
