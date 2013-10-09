package db;

import java.io.File;
import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.SQLExec;

public class SetupDb extends AbstractDao {

	public void createSchema(){
		executeSqlFromFile(getClassPathFile("/sql/database.sql"));
	}
	
	public void insertTestData(){
		executeSqlFromFile(getClassPathFile("/sql/data.sql"));
	}
	
	private String getClassPathFile(String filename){
		return getClass().getClassLoader().getResource(filename).getFile();
	}
	
	private void executeSqlFromFile(String sqlFilePath){
		Project project = new Project();
		project.init();
		
		SQLExec e = new SQLExec();
		e.setProject(project);
		e.setTaskType("sql");
		e.setTaskName("sql");
		e.setSrc(new File(sqlFilePath));
		e.setDriver("org.hsqldb.jdbcDriver");
		e.setUserid("sa");
		e.setPassword("");
		e.setUrl(DB_URL);
		e.execute();
	}
}
