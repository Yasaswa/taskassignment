package com.erp.users.repository;

import com.erp.users.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);

	@Modifying
	@Query("DELETE FROM RefreshToken r WHERE r.user_id = ?1 and r.token = ?2")
	int deleteByUser(String userCode, String refreshToken);
}