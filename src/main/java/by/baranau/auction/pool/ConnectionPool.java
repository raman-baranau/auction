package by.baranau.auction.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.baranau.auction.exception.DatabaseLogicalException;

public class ConnectionPool {

	private static ConnectionPool instance;
	
	private BlockingQueue<ProxyConnection> connectionQueue;
	
	private ConnectionPool() { //singleton
		connectionQueue = new  ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			for (int i = 0; i < POOL_SIZE; i++) {
				ProxyConnection connection = (ProxyConnection) DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/auction", 
						"root",
						"admin");//TODO
				connectionQueue.offer(connection);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public ProxyConnection getConnection() throws DatabaseLogicalException{
		ProxyConnection connection = null;
		try {
			connection = connectionQueue.take();
		} catch (InterruptedException e) {
			throw new DatabaseLogicalException(e);
		}
	    return connection;
	}
	
	public void closeConnection(ProxyConnection connection) {
		connectionQueue.offer(connection);
	}
	
	public void destroy() {
		//
	}
	
	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
}
