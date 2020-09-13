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
import org.springmvc.agenda.model.Compromissos;
import org.springmvc.agenda.model.Contatos;
import org.springmvc.agenda.util.ConexaoAgendaFactory;

@Repository
public class ContatoRepository{
	@PersistenceContext
	private EntityManager manager;
	
	public void create(Contatos contato) {
		manager.persist(contato);
	}
	
	public void delete(int id) {
		Contatos contato = read(id);
		manager.remove(contato);
	}
	
	public List<Contatos> readAll(int userid){
		TypedQuery<Contatos> query = manager.createQuery("SELECT c FROM Contatos c WHERE user_id =" + userid, Contatos.class);
		return query.getResultList();
	}
	
	public List<Contatos> readForName(String nome, int userid){
		TypedQuery<Contatos> query = manager.createQuery("SELECT c FROM Contatos c WHERE NAME LIKE '%"+ nome +"%' and user_id = " + userid, Contatos.class);
		return query.getResultList();
	}
	
	public Contatos read(int id){
		return manager.find(Contatos.class, id);
	}
	
	public void update(Contatos contato){
		manager.merge(contato);
	}
}
