package listeners;


import java.io.File;
import java.sql.*;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import db.AbstractDao;
import db.SetupDb;

/**
 * Application Lifecycle Listener implementation class InitializerListener
 *
 */
public class InitializerListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitializerListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see InitializerListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        SetupDb setup = new SetupDb();
        if(!new File(System.getProperty("user.home") + AbstractDao.Db_Location + ".script").exists()){
        	System.out.println("Cannot find database, creating new");
            setup.createSchema();
            setup.insertTestData();
        }
        
//      setup.createSchema();
//      setup.insertTestData();
    }

	/**
     * @see InitializerListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                System.out.println(String.format("Error deregistering driver %s: %s", driver, e));
            }

        }
    }
}
