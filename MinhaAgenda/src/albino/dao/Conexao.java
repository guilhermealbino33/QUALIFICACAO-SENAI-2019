package albino.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
public static Connection con;
	
	public static void conectar() {
		try {
			try {
				Class.forName("org.hsqldb.jdbcDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection("jdbc:hsqldb:file:magenda", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void desconectar() {
		try {
			if(!con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Boolean status() {
		try {
			if(!con.isClosed()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		Conexao.con = con;
	}

}
