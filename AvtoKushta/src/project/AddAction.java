package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddAction  implements ActionListener {

	Frame frame = null;
	AddAction(Frame frame){
	this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String fname = frame.fnameTF.getText();
		String lname = frame.lnameTF.getText();
		String gender =frame.genderBox.getSelectedItem().toString();
		byte age ;
		float phone;
		try {
			age =  Byte.parseByte(frame.ageTF.getText());
		}
		catch(Exception e1) {
			frame.ageTF.setText("failna");
			return;
		}
		try {
			phone= Float.parseFloat(frame.phoneTF.getText());
		}
		catch(Exception e1) {
			frame.phoneTF.setText("failna");
			return;
		}
		frame.connection = DBconnect.getCon();
		try {
			frame.state = frame.connection.prepareStatement("Insert into person Values(?,?,?,?,?)");
			frame.state.setString(1, fname);
			frame.state.setString(2, lname);
			frame.state.setString(3, gender);
			frame.state.setByte(4, age);
			frame.state.setDouble(5, phone);
			frame.state.execute();
			frame.table.setModel(DBconnect.getAllData("person"));
			frame.rentPeopleTable.setModel(DBconnect.getAllData("person"));
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
			frame.clearFrom();
		}
	}

}
