package br.com.nexaas.challenge.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity h2 of FileUpload
 * @author emanuel.foliveira
 * @since 03/01/2019
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class FileUpload {
	
	public FileUpload(String[] line) {
		this.setPurchaserName(line[0]);
		this.setItemDescription(line[1]);
		this.setItemPrice(new Double(line[2]));
		this.setPurchaseCount(new Integer(line[3]));
		this.setMerchantAdress(line[4]);
		this.setMerchantName(line[5]);
		this.setLocalDate(LocalDate.now());
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String purchaserName;
	private String itemDescription;
	private Double itemPrice;
	private Integer purchaseCount;
	private String merchantAdress;
	private String merchantName;
	private LocalDate localDate;
}
