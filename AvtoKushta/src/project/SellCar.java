package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SellCar implements ActionListener {
	Frame frame = null;
	SellCar(Frame frame){
		this.frame = frame;
		}
	@Override
	public void actionPerformed(ActionEvent e) {				
		int personid = frame.id;
		int carid = frame.id2;
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   String time = dtf.format(now);
		String clientname  = frame.firstName + " " + frame.secondName;
		float price = frame.priceFromTable;
		
		float actualprice=0;
		try {
			actualprice =  Float.parseFloat(frame.sellPriceTF.getText());
		}
		catch(Exception e1) {
			frame.sellPriceTF.setText("failna");
			return;
		}
		float changeprice = price-actualprice;
		frame.connection = DBconnect.getCon();
		try {
			frame.state = frame.connection.prepareStatement("insert into Sales values(?,?,?,?,?,?,?)");
			frame.state.setInt(1, personid);
			frame.state.setInt(2, carid);
			frame.state.setString(3, time);
			frame.state.setString(4, clientname);
			frame.state.setFloat(5, price);
			frame.state.setFloat(6, changeprice);
			frame.state.setFloat(7, actualprice);
			

			frame.state.execute();
			frame.rentedCarsTable.setModel(DBconnect.getRentData(frame.firstName));
//	table.setModel(DBconnect.getAllData("koli"));
	//	DBconnect.fillCombo(searchCombo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				frame.state.close();
				frame.connection.close();
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
			frame.clearCarFrom();
		}
	}

}
