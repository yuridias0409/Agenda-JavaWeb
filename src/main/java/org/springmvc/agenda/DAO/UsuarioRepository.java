package org.springmvc.agenda.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springmvc.agenda.model.Contatos;
import org.springmvc.agenda.model.Users;
import org.springmvc.agenda.util.ConexaoAgendaFactory;

@Repository
public class UsuarioRepository {
	@PersistenceContext
	private EntityManager manager;
	
	public void create(Users usuario) {
		manager.persist(usuario);
	}
	
	public List<Users> readAll(){
		TypedQuery<Users> query = manager.createQuery("SELECT u FROM Users u", Users.class);
		return query.getResultList();
	}
	
	public Users read(int id){
		return manager.find(Users.class, id);
	}
			
	public void update(Users usuario){
		manager.merge(usuario);
	}
	
	public void delete(int id){
		Users users = read(id);
		manager.remove(users);
	}
	
	public boolean verificaCadastrado(String email){
		TypedQuery<Users> query = manager.createQuery("SELECT u FROM Users u WHERE email like '"+ email +"'", Users.class);
		List<Users> users = query.getResultList(); 
		if(users.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean verificaLogin(String email, String senha) {
		TypedQuery<Users> query = manager.createQuery("SELECT u FROM Users u WHERE email like '"+ email +"' and password = "+senha, Users.class);
		List<Users> users = query.getResultList(); 
		if(users.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public int retornaID(String email) {
		TypedQuery<Users> query = manager.createQuery("SELECT u FROM Users u WHERE email like '"+ email +"'", Users.class);
		List<Users> users = query.getResultList(); 
		if(users.isEmpty()) {
			return 0;
		} else {
			return users.get(0).getId();
		}
	}
	
	
}
