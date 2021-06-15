package project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class CarTableListener implements MouseListener {
	Frame frame = null;
	CarTableListener(Frame frame){
		this.frame = frame;
		}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && !e.isConsumed()) {
			String sql ="Select * from koli where id=? ";
			frame.connection = DBconnect.getCon();
			String marka = null;
			String durjava = null;
			String model = null;
			double productionYear = 0;
			float price = 0;
			String descriptrion = null;
			try {
				frame.state= frame.connection.prepareStatement(sql);
				frame.state.setInt(1, frame.id);
				frame.result= frame.state.executeQuery();
				 while (frame.result.next()) {
					 marka  = frame.result.getString("marka");
					 durjava= frame.result.getString("durjava");
					 model = frame.result.getString("model");
				productionYear = frame.result.getDouble("productionYear");
				price = frame.result.getFloat("price");
				descriptrion = frame.result.getString("description");
			        }				
				System.out.println(frame.id);
				frame.markaTF.setText(marka);
				frame.durjavaTF.setText(durjava);
				frame.modelTF.setText(model);
				frame.producsionYearTF.setText(String.valueOf(productionYear));
				frame.priceTF.setText(String.valueOf(price));
				frame.descriptionTF.setText(descriptrion);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		     e.consume();
		     //handle double click event.
		}
		else {
			int row =frame.carTable.getSelectedRow();
			frame.id = Integer.parseInt(frame.carTable.getValueAt(row, 0).toString());
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