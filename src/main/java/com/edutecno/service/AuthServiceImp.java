package com.edutecno.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edutecno.mapper.UsuarioMapper;
import com.edutecno.model.Users;

@Service
public class AuthServiceImp implements UserDetailsService {
	
	//inyeccion de dependencias para la interfaz UsuarioMapper y disponibilizar cuando se requiera
	@Autowired
	private UsuarioMapper usuarioMapper;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Users user = usuarioMapper.findByEmail(email);
		
		if (user == null) {//si el usuario es null
			throw new UsernameNotFoundException(email);//se ejecuta una excepcion
			
		}
		//almacenar todos GrantedAuthority del usuario
		List<GrantedAuthority> authorities = new ArrayList<>();
		//agregando el rol convertido en SimpleGrantedAuthority
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		
		//se retorna un nuevo usuario con email,pass y las autoridades creadas
		return new User(user.getEmail(), user.getPassword(), authorities);
	}

}
