/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp.users.repository;

import com.erp.users.models.ERole;
import com.erp.users.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
