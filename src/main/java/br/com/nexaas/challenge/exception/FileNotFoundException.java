package br.com.nexaas.challenge.exception;

/**
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1055959428045716923L;

	public FileNotFoundException(String file) {
		super("File not found - " + file);
	}
}
