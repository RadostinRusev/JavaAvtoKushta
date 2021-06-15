package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteRentedCar implements ActionListener {
	Frame frame = null;
	DeleteRentedCar(Frame frame){
		this.frame = frame;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.connection = DBconnect.getCon();
		String sql = "Delete from Sales where id=?";
		try {
			frame.state= frame.connection.prepareStatement(sql);
			frame.state.setInt(1, frame.delrentedcar);
			frame.state.execute();
			frame.rentedCarsTable.setModel(DBconnect.getRentData("firstname"));
			frame.delrentedcar=-1;
	//		DBconnect.fillCombo(searchCombo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
