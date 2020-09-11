package org.springmvc.agenda.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springmvc.agenda.model.Compromisso;
import org.springmvc.agenda.model.Contato;
import org.springmvc.agenda.util.ConexaoAgendaFactory;

public class CompromissoDAO {
	
	public void create(Compromisso compromisso, int userid) {
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "INSERT INTO compromissos (local, data, contatoid, descricao, userid) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, compromisso.getLocal());
			ps.setString(2, compromisso.getData());
			ps.setInt(3, compromisso.getContatoID());
			ps.setString(4, compromisso.getDescricao());
			ps.setInt(5, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public List<Compromisso> readAll(int userid){
		
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "SELECT * FROM compromissos WHERE userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			List<Compromisso> compromisso = new ArrayList<Compromisso>();
			
			while(rs.next()) {
				//Pega dados do Contato
				ContatoDAO contatoDAO = new ContatoDAO();
				Contato contato = contatoDAO.read(Integer.parseInt(rs.getString(4)));
				//Fim dados contato
				
				Compromisso c= new Compromisso();
				c.setId(rs.getInt(1));
				c.setLocal(rs.getString(2));
				c.setData(rs.getString(3));
				c.setContatoID(Integer.parseInt(rs.getString(4)));
				c.setDescricao(rs.getString(5));
				c.setContatoNome(contato.getNome());
				compromisso.add(c);
			}
			
			return compromisso;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public Compromisso read(int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			String sql = "SELECT * FROM compromissos WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Compromisso c= new Compromisso();
			
			if(rs.next()){
				//Pega dados do Contato
				ContatoDAO contatoDAO = new ContatoDAO();
				Contato contato = contatoDAO.read(Integer.parseInt(rs.getString(4)));
				//Fim dados contato
				
				c.setId(rs.getInt(1));
				c.setLocal(rs.getString(2));
				c.setData(rs.getString(3));
				c.setContatoID(Integer.parseInt(rs.getString(4)));
				c.setDescricao(rs.getString(5));
				c.setContatoNome(contato.getNome());
			}
			
			return c;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Compromisso compromisso, int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "UPDATE compromissos SET local = ?, data = ?, contatoid = ?, descricao = ? WHERE id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, compromisso.getLocal());
			ps.setString(2, compromisso.getData());
			ps.setInt(3, compromisso.getContatoID());
			ps.setString(4, compromisso.getDescricao());
			ps.setInt(5, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "DELETE FROM compromissos WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
