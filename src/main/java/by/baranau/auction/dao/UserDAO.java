package by.baranau.auction.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.baranau.auction.data.User;
import by.baranau.auction.data.UserType;
import by.baranau.auction.pool.ConnectionPool;
import by.baranau.auction.pool.ProxyConnection;

public class UserDAO extends AbstractDAO <Integer, User> {
	
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
    
	private final static String SQL_SELECT_CLIENT_PASSWORD = 
			"SELECT CLIENT_ID, FIRST_NAME, LAST_NAME, C_LOGIN,"
			+ "C_PASSWORD, C_EMAIL, C_PHONE_NUMBER, USER_TYPE"
			+ " FROM CLIENTS WHERE C_LOGIN=?";

	private final static String SQL_USER_EXISTS =
			"SELECT C_LOGIN FROM CLIENTS WHERE C_LOGIN=?";
	
	private final static String SQL_CREATE_USER =
			"INSERT INTO CLIENTS(CLIENT_ID, FIRST_NAME, LAST_NAME, C_LOGIN,"
			+ "C_PASSWORD, C_EMAIL, C_PHONE_NUMBER, USER_TYPE)"
			+ "VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
	
	private final static String SQL_FIND_USER_BY_ID =
	        "SELECT CLIENT_ID, FIRST_NAME, LAST_NAME, C_LOGIN, "
	        + "C_PASSWORD, C_EMAIL, C_PHONE_NUMBER, USER_TYPE "
	        + "FROM CLIENTS "
	        + "WHERE CLIENT_ID=?";
	
	private final static String SQL_FIND_LOT_COUNT =
	        "SELECT COUNT(AUCTION_ID) "
	        + "FROM AUCTIONS "
	        + "WHERE OWNER_ID=?";
	
	private final static String SQL_UPDATE_USER =
	        "UPDATE CLIENTS "
	        + "SET C_PASSWORD=?, C_EMAIL=?, C_PHONE_NUMBER=? "
	        + "WHERE CLIENT_ID=?";
	
	private final static String FIELD_CLIENT_ID =
	        "CLIENT_ID";
	private final static String FIELD_FIRST_NAME =
            "FIRST_NAME";
	private final static String FIELD_LAST_NAME =
	        "LAST_NAME";
	private final static String FIELD_LOGIN =
	        "C_LOGIN";
	private final static String FIELD_PASSWORD =
	        "C_PASSWORD";
	private final static String FIELD_EMAIL =
	        "C_EMAIL";
	private final static String FIELD_PHONE_NUMBER =
	        "C_PHONE_NUMBER";
	private final static String FIELD_USER_TYPE =
	        "USER_TYPE";
	        
	
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
		PreparedStatement statement = null;
		User user = null;
		
		try {
		    statement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
		    statement.setInt(1, id);
		    ResultSet result = statement.executeQuery();
		    result.next();
		    user = new User();
		    user.setId(result.getInt(FIELD_CLIENT_ID));
		    user.setFirstName(result.getString(FIELD_FIRST_NAME));
		    user.setLastName(result.getString(FIELD_LAST_NAME));
		    user.setLogin(result.getString(FIELD_LOGIN));
		    user.setPassword(result.getString(FIELD_PASSWORD));
		    user.setEmail(result.getString(FIELD_EMAIL));
		    user.setPhoneNumber(result.getString(FIELD_PHONE_NUMBER));
		    user.setUserType(UserType.valueOf(result.getString(FIELD_USER_TYPE)));
		} catch (SQLException e) {
		    LOGGER.log(Level.ERROR, "Error occured while executing query", e);
        } finally {
		    try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error occured while closing statement", e);
            }
		}
		return user;
	}

	@Override
	public boolean delete(Integer id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean delete(User entity) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean create(User entity) {
		PreparedStatement statement = null;
		MessageDigest digest = null;
		String passwordHash = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] bpasswordHash = digest.digest(entity.getPassword().getBytes());
			passwordHash = new String(bpasswordHash);
			statement = connection.prepareStatement(SQL_CREATE_USER);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setString(3, entity.getLogin());
			statement.setString(4, passwordHash);
			statement.setString(5, entity.getEmail());
			statement.setString(6, entity.getPhoneNumber());
			statement.setString(7, UserType.CLIENT.toString());
			statement.executeUpdate();
		} catch (NoSuchAlgorithmException e) {
		    LOGGER.log(Level.ERROR, "Error occured while hashing password", e);
		} catch (SQLException e) {
		    LOGGER.log(Level.ERROR, "Error occured while executing query", e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
			    LOGGER.log(Level.ERROR, "Error occured while closing statement", e);
			}
		}
				
		return false;
	}

	@Override
	public void update(User entity) {
		PreparedStatement statement = null;
		
		try {
		    statement = connection.prepareStatement(SQL_UPDATE_USER);
		    statement.setString(1, new String(entity.getPassword()));
		    statement.setString(2, entity.getEmail());
		    statement.setString(3, entity.getPhoneNumber());
		    statement.setInt(4, entity.getId());
		    statement.executeUpdate();
		} catch (SQLException e) {
		    LOGGER.log(Level.ERROR, "Error occured while executing query", e);
        } finally {
		    try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error occured while closing statement", e);
            }
		}
	}
	
	public User findUserByCredentials(String enteredLogin, String enteredPassword) {
		PreparedStatement statement = null;
		
		String passwordHash = null;
		String enteredPasswordHash = null;
		MessageDigest digest = null;
		
		int id = 0;
		String firstName = null;
		String lastName = null;
		String login = null;
		String email = null;
		String phoneNumber = null;
		UserType userType = UserType.GUEST;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] bEnteredPasswordHash = digest.digest(enteredPassword.getBytes());
			enteredPasswordHash = new String(bEnteredPasswordHash);
			statement = connection.prepareStatement(SQL_SELECT_CLIENT_PASSWORD);
			
			statement.setString(1, enteredLogin);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			id = resultSet.getInt(FIELD_CLIENT_ID);
			firstName = resultSet.getString(FIELD_FIRST_NAME);
			lastName = resultSet.getString(FIELD_LAST_NAME);
			login = enteredLogin;
			email = resultSet.getString(FIELD_EMAIL);
			phoneNumber = resultSet.getString(FIELD_PHONE_NUMBER);
			userType = UserType.valueOf(resultSet.getString(FIELD_USER_TYPE));
			passwordHash = resultSet.getString(FIELD_PASSWORD);
		} catch (SQLException e) {
		    LOGGER.log(Level.ERROR, "Error occured while executing query", e);
		} catch (NoSuchAlgorithmException e) {
		    LOGGER.log(Level.ERROR, "Error occured while hashing password", e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
			    LOGGER.log(Level.ERROR, "Error occured while closing statement", e);
			}
		}
		
		if (passwordHash == null) {
			return null;
		}
		if ( !passwordHash.equals(enteredPasswordHash) ) {
		    return null;
		}
		
		User user = new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLogin(login);
		user.setPassword(enteredPasswordHash);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setUserType(userType);
		
		return user;
	}
	
	public boolean userExists(String login) {
		PreparedStatement statement = null;
		String result = null;
		
		try {
			statement = connection.prepareStatement(SQL_USER_EXISTS);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = resultSet.getString(1);
		} catch (SQLException e) {
		    LOGGER.log(Level.ERROR, "Error occured while executing query", e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
			    LOGGER.log(Level.ERROR, "Error occured while closing statement", e);
			}
		}
		
		return (result != null);
	}
	
	public int findLotCount(int id) {
	    int count = 0;
	    PreparedStatement statement = null;
	    
	    try {
	        statement = connection.prepareStatement(SQL_FIND_LOT_COUNT);
	        statement.setInt(1, id);
	        ResultSet result = statement.executeQuery();
	        result.next();
	        count = result.getInt(1);
	    } catch (SQLException e) {
	        LOGGER.log(Level.ERROR, "Error occured while executing query", e);
        } finally {
	        try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error occured while closing statement", e);
            }
	    }
	    return count;
	}
	
	public void closeConnection() {
		ConnectionPool.getInstance().closeConnection(connection);
	}
}
