package com.erp.users.service;

import com.erp.security.auth.TokenRefreshException;
import com.erp.users.models.RefreshToken;
import com.erp.users.repository.RefreshTokenRepository;
import com.erp.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
	@Value("${dits.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public Optional<RefreshToken> findByToken(String token) {
		Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(token);
		if (optionalRefreshToken.isPresent()){
			RefreshToken refreshTokenDetails =  optionalRefreshToken.get();
			refreshTokenDetails.setUser(userRepository.findByUserCode(refreshTokenDetails.getUser_id()));
		}
		return optionalRefreshToken;
	}

	public RefreshToken createRefreshToken(String userCode) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findByUserCode(userCode));
		refreshToken.setUser_id(userCode);
		refreshToken.setExpiry_date(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiry_date().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Transactional
	public int deleteByUserId(String userCode, String refreshToken) {
//		return refreshTokenRepository.deleteByUser(userRepository.findByUserCode(userCode));
		return refreshTokenRepository.deleteByUser(userCode, refreshToken);
	}
}