package by.baranau.auction.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.baranau.auction.pool.ConnectionPool;
import by.baranau.auction.pool.ProxyConnection;

public class LoginLogic {
	private final static String SQL_SELECT_CLIENT_LOGIN = 
			"SELECT P_EMAIL, P_PASSWORD FROM PASSWORDS WHEERE P_EMAIL=?";
	
	private ConnectionPool connectionPool;
	
	public LoginLogic(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}
	
	public boolean checkLogin(String enterLogin, String enterPassword) {
		ProxyConnection cn = null;
		PreparedStatement st = null;
		
		String email = null;
		String password = null;
		
		try {
			cn = connectionPool.getConnection();
			st = cn.prepareStatement(SQL_SELECT_CLIENT_LOGIN);
			st.setString(1, enterLogin);
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			email = resultSet.getString(1);
			password = resultSet.getString(2);
		} catch (SQLException e) {
			//TODO
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionPool.closeConnection(cn);
		}
		if (email == null || password == null) {
			return false;
		}
		return email.equals(enterLogin) && password.equals(enterPassword);
	}
}
