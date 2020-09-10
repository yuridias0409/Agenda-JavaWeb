package org.springmvc.agenda.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springmvc.agenda.model.Contato;
import org.springmvc.agenda.util.ConexaoAgendaFactory;

public class ContatoDAO{
	public void create(Contato contato) {
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "INSERT INTO contatos (name, email) VALUES (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public List<Contato> readAll(){
		
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "SELECT * FROM contatos";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			List<Contato> contatos = new ArrayList<Contato>();
			
			while(rs.next()) {
				Contato c= new Contato();
				
				c.setId(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setEmail(rs.getString(3));
				
				contatos.add(c);
			}
			
			return contatos;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public Contato read(int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			String sql = "SELECT * FROM contatos WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Contato contato = null;
			
			if(rs.next()){
				contato = new Contato();
				contato.setId(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setEmail(rs.getString(3));
			}
			
			return contato;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Contato contato){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "UPDATE contatos SET name = ?, email = ? WHERE id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setInt(3, contato.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "DELETE FROM contatos WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
