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
	public void create(Contato contato, int userid) {
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "INSERT INTO contatos (name, email, endereco, telefone, userid) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, contato.getNome().toUpperCase());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getEndereco());
			ps.setString(4, contato.getTelefone());
			ps.setInt(5, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public List<Contato> readAll(int userid){
		
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "SELECT * FROM contatos WHERE userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			List<Contato> contatos = new ArrayList<Contato>();
			
			while(rs.next()) {
				Contato c= new Contato();
				c.setId(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setEmail(rs.getString(3));
				c.setEndereco(rs.getString(4));
				c.setTelefone(rs.getString(5));
				contatos.add(c);
			}
			
			return contatos;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Contato> readForName(String nome, int userid){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			String n = nome.toUpperCase();
			String sql = "SELECT * FROM CONTATOS WHERE NAME LIKE '"+n+"%' and userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			List<Contato> contatos = new ArrayList<Contato>();
			
			while(rs.next()) {
				Contato c= new Contato();
				c.setId(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setEmail(rs.getString(3));
				c.setEndereco(rs.getString(4));
				c.setTelefone(rs.getString(5));
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
				contato.setEndereco(rs.getString(4));
				contato.setTelefone(rs.getString(5));
			}
			
			return contato;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Contato contato, int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "UPDATE contatos SET name = ?, email = ?, endereco = ?, telefone = ? WHERE id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, contato.getNome().toUpperCase());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getEndereco());
			ps.setString(4, contato.getTelefone());
			ps.setInt(5, id);
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
