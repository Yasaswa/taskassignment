/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@EnableScheduling
public class ErpDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpDevApplication.class, args);
	}

}
