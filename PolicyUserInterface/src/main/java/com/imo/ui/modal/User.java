package com.imo.ui.modal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

//	@Id
//	@Column(name = "user_name")
	@NotNull(message = "User Name is required")
	private String userName;
	
//	@Column(name = "user_pass")
	@NotNull(message = "Password is required")
	private String userPassword;
	
}
