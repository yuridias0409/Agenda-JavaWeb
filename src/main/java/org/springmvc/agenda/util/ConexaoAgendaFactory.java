package org.springmvc.agenda.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoAgendaFactory {
	public static Connection getConexao() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/agendadb", "SA", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
