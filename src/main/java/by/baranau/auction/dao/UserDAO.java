package by.baranau.auction.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.baranau.auction.data.User;
import by.baranau.auction.data.UserType;
import by.baranau.auction.pool.ConnectionPool;
import by.baranau.auction.pool.ProxyConnection;

public class UserDAO extends AbstractDAO <Integer, User> {
	
	private final static String SQL_SELECT_CLIENT_PASSWORD = 
			"SELECT C_PASSWORD FROM CLIENTS WHERE C_LOGIN=?";

	private final static String SQL_USER_EXISTS =
			"SELECT C_LOGIN FROM CLIENTS WHERE C_LOGIN=?";
	
	private final static String SQL_CREATE_USER =
			"INSERT INTO CLIENTS(CLIENT_ID, FIRST_NAME, LAST_NAME, C_LOGIN,"
			+ "C_PASSWORD, C_EMAIL, C_PHONE_NUMBER, USER_TYPE)"
			+ "VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
	
	private ProxyConnection connection;
	
	public UserDAO(ProxyConnection connection) {
		this.connection = connection;
	}

	@Override
	public List<User> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public User findEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(User entity) {
		PreparedStatement st = null;
		MessageDigest digest = null;
		String passwordHash = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] bpasswordHash = digest.digest(entity.getPassword().getBytes());
			passwordHash = new String(bpasswordHash);
			st = connection.prepareStatement(SQL_CREATE_USER);
			st.setString(1, entity.getFirstName());
			st.setString(2, entity.getLastName());
			st.setString(3, entity.getLogin());
			st.setString(4, passwordHash);
			st.setString(5, entity.getEmail());
			st.setString(6, entity.getPhoneNumber());
			st.setString(7, UserType.CLIENT.toString());
			st.execute();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return false;
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean checkLogin(String enteredLogin, String enteredPassword) {
		PreparedStatement st = null;
		
		String passwordHash = null;
		String enteredPasswordHash = null;
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] bEnteredPasswordHash = digest.digest(enteredPassword.getBytes());
			enteredPasswordHash = new String(bEnteredPasswordHash);
			st = connection.prepareStatement(SQL_SELECT_CLIENT_PASSWORD);
			
			st.setString(1, enteredLogin);
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			passwordHash = resultSet.getString(1);
		} catch (SQLException e) {
			//TODO
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (passwordHash == null) {
			return false;
		}
		return passwordHash.equals(enteredPasswordHash);
	}
	
	public boolean userExists(String login) {
		PreparedStatement st = null;
		String result = null;
		
		try {
			st = connection.prepareStatement(SQL_USER_EXISTS);
			st.setString(1, login);
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			result = resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return (result != null);
	}
	
	public void closeConnection() {
		ConnectionPool.getInstance().closeConnection(connection);
	}
}
