package com.erp.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "am_refresh_token")
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long refresh_id;

	private String user_id;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private Instant expiry_date;

	@OneToOne
	@Transient  // Ignoring this field during JSON serialization
	private User user;

}
