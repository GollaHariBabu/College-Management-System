package org.jsp.cms.entity;

import org.jsp.cms.util.UserRole;
import org.jsp.cms.util.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@Column(unique = true, nullable=false)
	private String username;

//	@Column( nullable=false)
	private String password;

//	@Column( nullable=false)
	private String name;

//	@Column(unique = true, nullable=false)
	private String email;

//	@Column(unique = true, nullable=false)
	private long phone;

	private int otp;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Enumerated(EnumType.STRING)
	private UserStatus status;
}
