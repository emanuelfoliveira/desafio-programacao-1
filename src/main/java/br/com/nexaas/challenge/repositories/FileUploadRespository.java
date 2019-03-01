package br.com.nexaas.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexaas.challenge.models.FileUpload;

/**
 * Respository File Upload Repository
 * 
 * @author emanuel.foliveira
 * @since 03/01/2019
 * @version 1.0
 */
@Repository
public interface FileUploadRespository extends JpaRepository<FileUpload, Long> {

}
