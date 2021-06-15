package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PersonEditAction implements ActionListener {

	Frame frame = null;
	PersonEditAction(Frame frame){
		this.frame = frame;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		String fname = frame.fnameTF.getText();
		String lname = frame.lnameTF.getText();
		String gender = frame.genderBox.getSelectedItem().toString();
		byte age ;
		try {
			age =  Byte.parseByte(frame.ageTF.getText());
		}
		catch(Exception e1) {
			frame.ageTF.setText("failna");
			return;
		}
		double phone= Double.parseDouble(frame.phoneTF.getText());
		frame.connection = DBconnect.getCon();
		String sql = "Update person Set fname =?,lname=?,gender=?,age=?,phone=? Where id=?;";
		try {
			frame.state= frame.connection.prepareStatement(sql);
			frame.state.setString(1, fname);
			frame.state.setString(2, lname);
			frame.state.setString(3, gender);
			frame.state.setByte(4, age);
			frame.state.setDouble(5, phone);
			frame.state.setInt(6, frame.id);
			frame.state.execute();
			frame.table.setModel(DBconnect.getAllData("person"));
			frame.rentPeopleTable.setModel(DBconnect.getAllData("person"));
			frame.id=-1;
	//		DBconnect.fillCombo(searchCombo);
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
			
		}
		
	}

}
