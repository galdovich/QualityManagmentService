package com.galdovich.qaulity.dao.pool;

import com.galdovich.qaulity.util.DBParameter;
import com.galdovich.qaulity.util.DBResourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The class represents connection poll.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private Queue<ProxyConnection> givenAwayConnections;
    private BlockingQueue<ProxyConnection> freeConnection;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    /**
     * Gets ConnectionPool instance.
     *
     * @return the instance
     */
    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
            Class.forName(driverName);
            freeConnection = new LinkedBlockingDeque<>(poolSize);
            givenAwayConnections = new ArrayDeque<>();
            for (int i = 0; i < poolSize; i++) {
                freeConnection.add(new ProxyConnection(DriverManager.getConnection(url, user, password)));
            }
        } catch (SQLException exc) {
            logger.log(Level.ERROR, "Connection creating ERROR", exc);
        } catch (ClassNotFoundException exc) {
            logger.log(Level.ERROR, "ERROR while registering Driver", exc);
        } catch (NumberFormatException exc) {
            logger.log(Level.ERROR, "Error while parsing pool size", exc);
            poolSize = 8;
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnection.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException exc) {
            logger.log(Level.FATAL, "Error while taking connection from BlockingQueue", exc);
        }
        return connection;
    }

    /**
     * Deregister all drivers by for each
     */
    private void deregisterDrivers() {
        while (DriverManager.getDrivers().hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            } catch (SQLException exc) {
                logger.log(Level.ERROR, "Cannot deregister driver", exc);
            }
        }
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class && givenAwayConnections.remove(connection)) {
            freeConnection.add((ProxyConnection) connection);
        } else {
            logger.log(Level.ERROR, "Error while releasing UNKNOWN connection");
        }
    }

    /**
     * Destroy pool.
     * Waits until all connections return back to the pool.
     */
    public void destroyPool() {
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnection.take().reallyClose();
            }
        } catch (SQLException exc) {
            logger.log(Level.ERROR, "Error while closing connection", exc);
        } catch (InterruptedException exc) {
            logger.log(Level.ERROR, "Error while taking connection from BlockingQueue", exc);
        } finally {
            deregisterDrivers();
        }
    }

    /**
     * Release resultSet and statement
     *
     * @param resultSet resultSet to be closed
     * @param statement statement to be closed
     */
    public void releaseResources(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException exc) {
                logger.log(Level.WARN, "Can't close resultSet", exc);
            }
        }
        releaseResources(statement);
    }

    /**
     * @param statement statement to be closed
     */
    public void releaseResources(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException exc) {
                logger.log(Level.WARN, "Can't close statement", exc);
            }
        }
    }
}