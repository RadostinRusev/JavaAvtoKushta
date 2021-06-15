package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	static String  DBPath;
	static String  driverPath;	
		static Properties properties = new Properties();
		static InputStream inputStream = new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		}; 
		public static void config() {
			try {
		        inputStream = new FileInputStream("src/config/config.properties"); 
		        properties.load(inputStream);
		         DBPath = properties.getProperty("db_path");
		       driverPath = properties.getProperty("db_driver");
		  
		    } catch (FileNotFoundException e) { //
		        try {
		            File file = new File("src/config/config.properties");
		            file.getParentFile().mkdirs();
		            file.createNewFile();
		            FileOutputStream fileOutputStream = new FileOutputStream(file);
		            properties.setProperty("db_path", "jdbc:sqlserver://localhost\\SQLEXPRESS01;databaseName=AvtoKushta;integratedSecurity=true");
		            properties.setProperty("db_driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            properties.store(fileOutputStream, "properties");

		        } catch (IOException exception) {
		            exception.printStackTrace();
		        }
		        e.printStackTrace(); //TODO create config or show error|create default config
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		}
	    public static String GetDBPath() {
			return DBPath;
	    	
	    }
	    public static String GetdriverPath() {
			return driverPath;
	    	
	    }
}
