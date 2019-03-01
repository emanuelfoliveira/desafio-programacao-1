package br.com.nexaas.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.nexaas.challenge.models.FileUpload;
import br.com.nexaas.challenge.repositories.FileUploadRespository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileUploadRepositoyTest {

    @Autowired
    FileUploadRespository repo;

    @Test
    public void saveTest() throws Exception {
    	repo.save(new FileUpload());
    }
    @Test
    public void findAllTest() throws Exception {
    	repo.findAll().isEmpty();
    }
}
