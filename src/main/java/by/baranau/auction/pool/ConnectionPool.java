package by.baranau.auction.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.baranau.auction.exception.DatabaseLogicalException;

public class ConnectionPool {
    
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    
	private static ConnectionPool instance;
	private static Lock lock = new ReentrantLock();
	private static AtomicBoolean instanceCreated = new AtomicBoolean();
	
	private BlockingQueue<ProxyConnection> connectionQueue;
	private final int POOL_SIZE;
	
	private ConnectionPool() {
		if (instance != null) {
			ResourceBundle resourceBoundle = ResourceBundle.getBundle("resources.messages");
			String instanceAlreadyCreatedMessage = 
					resourceBoundle.getString("message.singleton.instance");
			throw new RuntimeException(instanceAlreadyCreatedMessage);
		}
		
		ResourceBundle resourceBoundle = ResourceBundle.getBundle("resources.param");
		String sPoolSize = resourceBoundle.getString("database.poolsize");
		String databaseUrl = resourceBoundle.getString("database.url");
		String databaseUser = resourceBoundle.getString("database.user");
		String databasePassword = resourceBoundle.getString("database.password");
		
		POOL_SIZE = Integer.parseInt(sPoolSize);
		
		connectionQueue = new ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			for (int i = 0; i < POOL_SIZE; i++) {
				Connection connection = DriverManager.getConnection(
						databaseUrl, 
						databaseUser,
						databasePassword);
				connectionQueue.offer(new ProxyConnection(connection));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Create connection pool error", e);
		}	
	}
	
	public ProxyConnection getConnection() {
		ProxyConnection connection = null;
		try {
			connection = connectionQueue.take();
			LOGGER.log(Level.INFO, "Connection has been gotten from pool");
		} catch (InterruptedException e) {
		    LOGGER.log(Level.ERROR, "Connection pool error while getting connection");
		}
	    return connection;
	}
	
	public void closeConnection(ProxyConnection connection) {
		connectionQueue.offer(connection);
		LOGGER.log(Level.INFO, "Connection has been returned to the pool");
	}
	
	public void destroy() {
		try {
			for (int i = 0; i < POOL_SIZE; i++) {
				ProxyConnection connection = connectionQueue.take();
				connection.closeConnection();
			}
			DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
		    LOGGER.log(Level.ERROR, "Error occurred while deregistering driver", e);
		} catch (InterruptedException e) {
		    LOGGER.log(Level.ERROR, "Release connections interrupted", e);
		} catch (DatabaseLogicalException e) {
		    LOGGER.log(Level.ERROR, "Error occurred while releasing connections", e);
        }
	}
	
	public static ConnectionPool getInstance() {
		if (!instanceCreated.get()){
			lock.lock();
			try {
				if (instance == null) {
					instance = new ConnectionPool();
					instanceCreated.set(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		if (instance != null) {
			throw new CloneNotSupportedException();
		}
		return super.clone();
	}
}
