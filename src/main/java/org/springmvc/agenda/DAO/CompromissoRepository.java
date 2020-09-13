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
public class CompromissoRepository {
	@PersistenceContext
	private EntityManager manager;
	
	public void create(Compromissos compromisso) {
		compromisso.getContato();
		manager.persist(compromisso);
	}
	
	public List<Compromissos> readAll(int userid){
		TypedQuery<Compromissos> query = manager.createQuery("SELECT c FROM Compromissos c WHERE user_id = " + userid, Compromissos.class);
		return query.getResultList();
	}
	
	public Compromissos read(int id){
		return manager.find(Compromissos.class, id);
	}
	
	public void update(Compromissos compromisso, int id){
		compromisso.setId(id);
		manager.merge(compromisso);
	}
	
	public void delete(int id){
		Compromissos compromisso = read(id);
		manager.remove(compromisso);
	}

}
