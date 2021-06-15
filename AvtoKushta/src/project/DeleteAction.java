package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteAction implements ActionListener {
	Frame frame = null;
	DeleteAction(Frame frame){
		this.frame = frame;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.connection = DBconnect.getCon();
		String sql = "Delete from person where id=?";
		try {
			frame.state= frame.connection.prepareStatement(sql);
			frame.state.setInt(1, frame.id);
			frame.state.execute();
			frame.table.setModel(DBconnect.getAllData("person"));
			frame.rentPeopleTable.setModel(DBconnect.getAllData("person"));
			frame.id=-1;
	//		DBconnect.fillCombo(searchCombo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
