package com.p2.Cursos.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.p2.Cursos.cursos.model.entities.Aluno;
import com.p2.Cursos.cursos.model.repository.AlunoRepository;
import com.p2.Cursos.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private AlunoRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Aluno aluno = repo.findByLogin(username);
		if(aluno == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(aluno.getId(),aluno.getLogin(), aluno.getSenha(), aluno.getPerfis());
	}

}