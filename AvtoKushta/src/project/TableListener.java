package project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class TableListener implements MouseListener {
	Frame frame = null;
	TableListener(Frame frame){
		this.frame = frame;
		}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && !e.isConsumed()) {
			String sql ="Select * from person where id=?;";
			frame.connection = DBconnect.getCon();
			String fname = null;
			String lname = null;
			String gender = null;
			double phone = 0;
			byte age = 0;
			try {
				frame.state= frame.connection.prepareStatement(sql);
				frame.state.setInt(1, frame.id);
				frame.result= frame.state.executeQuery();
				 while (frame.result.next()) {
					 fname  = frame.result.getString("fname");
					 lname= frame.result.getString("lname");
					 gender = frame.result.getString("gender");
					 age = frame.result.getByte("age");
					 phone = frame.result.getDouble("phone");
			
			        }				
				System.out.println(frame.id);
				frame.fnameTF.setText(fname);
				frame.lnameTF.setText(lname);
				frame.genderBox.setToolTipText(gender);
				frame.phoneTF.setText(String.valueOf(phone));
				frame.ageTF.setText(String.valueOf(age));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {						
					try {
						frame.state.close();
						frame.connection.close();
						frame.table.setModel(DBconnect.getAllData("person"));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}						
			}
			
		     e.consume();
		     //handle double click event.
		}
		else {
			int row =frame.table.getSelectedRow();
			frame.id = Integer.parseInt(frame.table.getValueAt(row, 0).toString());
		}
	
			
		}
		

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
