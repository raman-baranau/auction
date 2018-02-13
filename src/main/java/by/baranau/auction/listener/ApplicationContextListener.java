package by.baranau.auction.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.baranau.auction.pool.ConnectionPool;

public class ApplicationContextListener implements ServletContextListener {
    
    private static final Logger LOGGER = LogManager.getLogger(ApplicationContextListener.class);
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        ConnectionPool.getInstance().destroy();
        LOGGER.log(Level.INFO, "Application context destroyed, connection pool destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        LOGGER.log(Level.INFO, "Application context initialized");
    }
}
