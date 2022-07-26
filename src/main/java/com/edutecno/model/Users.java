package com.edutecno.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data //Get,Set,EqualsAndHasCode,ToString
public class Users implements Serializable {
	
	
	private static final long serialVersionUID =1L;
	
	private String email;
	private String password;
	private Role role;

}
