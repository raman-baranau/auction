package by.baranau.auction.receiver;

import by.baranau.auction.dao.UserDAO;
import by.baranau.auction.data.User;
import by.baranau.auction.pool.ConnectionPool;

public class AccountReceiver {
	
	public User findUserByCredentials(String enteredLogin, String enteredPassword) {
		UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
		//TODO logic must be here
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
}
