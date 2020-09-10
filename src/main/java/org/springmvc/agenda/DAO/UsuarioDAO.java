package org.springmvc.agenda.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springmvc.agenda.model.Usuario;
import org.springmvc.agenda.util.ConexaoAgendaFactory;

public class UsuarioDAO {
	public void create(Usuario usuario) {
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "INSERT INTO users (name, email, password) VALUES (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getName());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Usuario> readAll(){
		
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			
			while(rs.next()) {
				Usuario u= new Usuario();
				
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setSenha(rs.getString(4));
				
				usuarios.add(u);
			}
			
			return usuarios;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario read(int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()){
				usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setName(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));
			}
			
			return usuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Usuario usuario){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, usuario.getName());
			ps.setString(2, usuario.getEmail());
			ps.setInt(3, usuario.getId());
			ps.setString(4, usuario.getSenha());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		try(Connection conn = ConexaoAgendaFactory.getConexao()){
			
			String sql = "DELETE FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}