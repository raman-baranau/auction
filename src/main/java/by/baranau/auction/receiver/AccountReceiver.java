package by.baranau.auction.receiver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.baranau.auction.dao.UserDAO;
import by.baranau.auction.data.User;
import by.baranau.auction.pool.ConnectionPool;

public class AccountReceiver {
	
    private static final Logger LOGGER = LogManager.getLogger(AccountReceiver.class);
    
	public User findUserByCredentials(String enteredLogin, String enteredPassword) {
		UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
		User user = userDao.findUserByCredentials(enteredLogin, enteredPassword);
		userDao.closeConnection();
		return user;
	}
	
	public boolean userExists(String login) {
		UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
		boolean result = userDao.userExists(login);
		userDao.closeConnection();
		return result;
	}
	
	public void registerUser(User user) {
		UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
		userDao.create(user);
		userDao.closeConnection();
	}
	
	public User findUserById(int id) {
	    UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
        User user = userDao.findEntityById(id);
        userDao.closeConnection();
        return user;
	}
	
	public int findLotCount(int userId) {
	    UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
	    int count = userDao.findLotCount(userId);
	    userDao.closeConnection();
	    return count;
	}
	
	public boolean updateUserPassword(User user, String oldPassword, String newPassword) {
	    UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
	    MessageDigest digest = null;
	    boolean result = false;
	    
	    try {
	        digest = MessageDigest.getInstance("SHA-256");
	        byte[] bpasswordHash = digest.digest(oldPassword.getBytes());
	        String sPasswordHash = new String(bpasswordHash);
	        if (sPasswordHash.equals(user.getPassword())) {
	            byte[] bNewPasswordHash = digest.digest(newPassword.getBytes());
	            user.setPassword(new String(bNewPasswordHash));
	            userDao.update(user);
	            result = true;
	        }
	    } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.ERROR, e);
        } finally {
            userDao.closeConnection();
	    }
	    return result;
	}
	
	public void updateUserContacts(User user, String email, String phoneNumber) {
	    UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
	    user.setEmail(email);
	    user.setPhoneNumber(phoneNumber);
	    userDao.update(user);
	    userDao.closeConnection();
	}
}
