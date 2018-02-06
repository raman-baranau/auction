package by.baranau.auction.receiver;

import by.baranau.auction.dao.UserDAO;
import by.baranau.auction.data.User;
import by.baranau.auction.pool.ConnectionPool;

public class AccountReceiver {
	
	public boolean checkLogin(String enteredLogin, String enteredPassword) {
		UserDAO userDao = new UserDAO(ConnectionPool.getInstance().getConnection());
		//TODO logic must be here
		boolean result = userDao.checkLogin(enteredLogin, enteredPassword);
		userDao.closeConnection();
		return result;
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
}
