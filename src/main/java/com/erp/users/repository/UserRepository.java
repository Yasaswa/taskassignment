/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp.users.repository;

import com.erp.Company.Companies.Model.CCompanyModel;
import com.erp.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
//	@Query("FROM User u WHERE u.username = :username and u.company_id = :company_id")
//	Boolean findByUsername(@Param("username") String email, @Param("company_id") int company_id);

	Boolean existsByUsername(String username);

	@Query("FROM User u WHERE u.user_code = ?1")
	User findByUserCode(String userCode);
 
	@Query("FROM User u WHERE u.user_code = ?1 and u.company_id =?2")
	User findUserByCodeAndPass(String user_code, int company_id);

	@Query(value = "SELECT cmv.company_id, cmv.company_name FROM cmv_company cmv WHERE cmv.is_delete = 0 AND cmv.is_active = 1", nativeQuery = true)
	List<Map<String, String>> getcompanydeatils();

}
