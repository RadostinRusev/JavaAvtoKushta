package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class DBconnect {

	
	public static String url =Config.GetDBPath();
	public static Connection connection = null;
	public static MyModel model = null;
	public static PreparedStatement state = null;
	public static ResultSet result = null;
	public static Connection getCon() {
		try {
			Class.forName(Config.GetdriverPath());
			try {
				connection= DriverManager.getConnection(url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return connection;
		}
	public static MyModel getAllData(String tablename){
		connection= getCon();
		try {
			state= connection.prepareStatement("Select * from " + tablename);
			result= state.executeQuery();
			model= new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	public static MyModel getRentData(String tablename){
		connection= getCon();
		try {
			state= connection.prepareStatement("Select s.id, s.clientname,marka,durjava,model,s.price,SellPrice from koli k inner join Sales s on k.id=s.koliid;");
			result= state.executeQuery();
			model= new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	 static void fillCombo(JComboBox<String> combo) {
		 
		 connection = getCon();
		 String sql= "select id,fname from person";
		 try {
			state= connection.prepareStatement(sql);
			result = state.executeQuery();
			combo.removeAllItems();// combo.setModel(aModel); ComboBoxModel
			while(result.next()) {
				String item = result.getObject(1).toString() + " " + result.getObject(2).toString();
				combo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	 }
}
