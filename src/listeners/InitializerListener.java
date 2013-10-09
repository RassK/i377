package listeners;


import java.io.File;
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
        	System.out.println("Exists!!");
            setup.createSchema();
            setup.insertTestData();
        }
        
//        setup.createSchema();
//        setup.insertTestData();
    }

	/**
     * @see InitializerListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
