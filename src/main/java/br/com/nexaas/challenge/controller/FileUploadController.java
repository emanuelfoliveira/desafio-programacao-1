package br.com.nexaas.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.nexaas.challenge.exception.FileNotFoundException;
import br.com.nexaas.challenge.service.FileUploadService;

/**
 * File upload Controller handler
 * 
 * @author emanuel.foliveira
 * @since 03/01/2019
 * @version 1.0
 */
@Controller
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * Method handle to get gross income and redirect page with upload attributes.
	 * @param file
	 * @param redirectAttributes
	 * @return String with page to open
	 */
	@PostMapping(value="/uploadForm")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		Double grossIncome = fileUploadService.store(file);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
		redirectAttributes.addFlashAttribute("grossIncome", "Gross Income: " + grossIncome);

		return "redirect:/uploadStatus";
	}
	
	/**
	 * Open page Upload Status
	 * @param redirectAttributes
	 * @return String with page to open
	 */
	@GetMapping(value="/uploadStatus")
	public String status( RedirectAttributes redirectAttributes) {
		return "uploadStatus";
	}
	
	/**
	 * Open page upload form
	 * @param redirectAttributes
	 * @return String with page to open
	 */
	@PostMapping(value="/redirectUploadForm")
	public String redirectUploadForm( RedirectAttributes redirectAttributes) {
		return "uploadForm";
	}
	
	/**
	 * Handle a exception
	 * @param FileNotFoundException
	 * @return Responsi Entity 
	 */
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
