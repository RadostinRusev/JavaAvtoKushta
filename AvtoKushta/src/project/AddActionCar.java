package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddActionCar implements ActionListener {

	Frame frame = null;
	AddActionCar(Frame frame){
		this.frame = frame;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		String marka = frame.markaTF.getText();
		String durjava = frame.durjavaTF.getText();
		String model = frame.modelTF.getText();
		double productionYear ;
		try {
			productionYear =  Double.parseDouble(frame.producsionYearTF.getText());
		}
		catch(Exception e1) {
			frame.producsionYearTF.setText("failna");
			return;
		}
		float price= Float.parseFloat(frame.priceTF.getText());
		String description= frame.descriptionTF.getText();
		frame.connection = DBconnect.getCon();
		try {
			frame.state = frame.connection.prepareStatement("Insert into koli values(?,?,?,?,?,?)");
			frame.state.setString(1, marka);
			frame.state.setString(2, durjava);
			frame.state.setString(3, model);
			frame.state.setDouble(4, productionYear);
			frame.state.setFloat(5, price);
			frame.state.setString(6, description);
			frame.state.execute();
			frame.carTable.setModel(DBconnect.getAllData("koli"));
			frame.rentCarTable.setModel(DBconnect.getAllData("koli"));
			
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
