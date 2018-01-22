package by.baranau.auction.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.baranau.auction.exception.ConnectionPoolInterruptedException;
import by.baranau.auction.pool.ConnectionPool;
import by.baranau.auction.pool.ProxyConnection;

public class LoginLogic {
	private final static String SQL_SELECT_CLIENT_LOGIN = 
			"SELECT C_LOGIN, C_PASSWORD FROM CLIENT_PASSWORDS WHEERE C_LOGIN=?";
	
	private ConnectionPool connectionPool;
	
	public LoginLogic(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}
	
	public boolean checkLogin(String enterLogin, String enterPassword) {
		ProxyConnection cn = null;
		PreparedStatement st = null;
		
		String login = null;
		String password = null;
		
		try {
			cn = connectionPool.getConnection();
			st = cn.prepareStatement(SQL_SELECT_CLIENT_LOGIN);
			st.setString(1, enterLogin);
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			login = resultSet.getString(1);
			password = resultSet.getString(2);
		} catch (SQLException e) {
			//TODO
		} catch (ConnectionPoolInterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionPool.closeConnection(cn);
		}
		if (login == null || password == null) {
			return false;
		}
		return login.equals(enterLogin) && password.equals(enterPassword);
	}
}
