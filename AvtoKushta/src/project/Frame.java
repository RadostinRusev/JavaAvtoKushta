package project;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;




public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected  Connection connection = null;
	protected PreparedStatement state = null;
	protected ResultSet result = null;
	int delrentedcar= -1;
	JTable table = new JTable();
	JTable carTable = new JTable();
	JTable rentPeopleTable= new JTable();
	JTable rentCarTable=new JTable();
	JTable rentedCarsTable=new JTable();
	JScrollPane rentedCarsScroller = new JScrollPane(rentedCarsTable);
	JScrollPane rentScroller1 = new JScrollPane(rentPeopleTable);
	JScrollPane rentScroller2 = new JScrollPane(rentCarTable);
	JScrollPane scroller = new JScrollPane(table);
	JScrollPane carScroller = new JScrollPane(carTable);
	JButton rentedCarsDelBtn = new JButton("del");
JTabbedPane tab = new JTabbedPane();
	

	int id=-1;
	int id2=-1;
	String firstName;
	String secondName;
	float priceFromTable;
	JPanel personP= new JPanel();
	JPanel carP= new JPanel();
	JPanel rentalP= new JPanel();
	JPanel sprP= new JPanel();
	
	JPanel persontopPanel= new JPanel();
	JPanel personmidPanel= new JPanel();
	JPanel personbotPanel= new JPanel();
	
	JPanel cartopPanel= new JPanel();
	JPanel carmidPanel= new JPanel();
	JPanel carbotPanel= new JPanel();
	
	JButton personaddBtn= new JButton("Add");
	JButton persondelBtn= new JButton("Del");
	JButton personeditBtn= new JButton("Edit");
	
	JButton caraddBtn= new JButton("Add");
	JButton cardelBtn= new JButton("Del");
	JButton careditBtn= new JButton("Edit");
	
	JLabel fname= new JLabel("FirstName");
	JLabel lname= new JLabel("LastName");
	JLabel gender= new JLabel("Gender");
	JLabel age= new JLabel("Age");
	JLabel phone= new JLabel("phone");
	JLabel sellPrice= new JLabel("sellPrice");
	JLabel searchLabel= new JLabel("search by name");
	JLabel searchCarLabel= new JLabel("search by marka");
	JLabel SearchPersonRentLabel= new JLabel("search by name");
	JLabel SearchCarRentLabel= new JLabel("search by marka");
	JLabel searchPersonSpravkaLabel= new JLabel("search by name");
	JLabel searchCarSpravkaLabel= new JLabel("search by marka");
	//JLabel searchLabel= new JLabel("search by name");
	//JLabel searchLabel= new JLabel("search by name");
	
	JLabel marka= new JLabel("marka");
	JLabel durjava= new JLabel("durjava");
	JLabel model= new JLabel("model");
	JLabel producsionYear= new JLabel("producsionYear");
	JLabel price= new JLabel("price");
	JLabel description= new JLabel("description");
	JTextField markaTF= new JTextField();
	JTextField durjavaTF= new JTextField();
	JTextField producsionYearTF= new JTextField();
	JTextField priceTF= new JTextField();
	JTextField modelTF= new JTextField();
	JTextField descriptionTF= new JTextField();
	
	JTextField fnameTF= new JTextField();
	JTextField lnameTF= new JTextField();
	JTextField genderTF= new JTextField();
	JTextField ageTF= new JTextField();
	JTextField phoneTF= new JTextField();
	JTextField sellPriceTF= new JTextField(85);
	JButton searchCarBtn= new JButton("search");
	JButton personCarBtn= new JButton("search");
	JButton rentedCarBtn= new JButton("search");
	JButton personCarBtnInRent= new JButton("search person");
	JButton CarBtnInRent= new JButton("search car");
	
	JButton sellBtn = new JButton("sell");
	
	JTextField searchbar= new JTextField(60);
	JTextField personSearchBar = new JTextField(60);
	JTextField personSearchBarInRent = new JTextField(75);
	JTextField CarSearchBarInRent = new JTextField(60);
	
	JTextField rentSearchBar = new JTextField(80);
	JTextField rentSearchBar2 = new JTextField(80);
	String[] item = {"male","female"};
	JComboBox<String> genderBox= new JComboBox<String>(item);
	MyModel mymodel;
	public Frame() {
		super("FullScreen");
		this.setSize(1000,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tab.add(personP,"client");
		tab.add(carP,"car");
		tab.add(rentalP,"rent");
		tab.add(sprP,"spravka za klient");
		
		AddPersonPanel();
		addCarPanel();
		addRentPanel();
		addSpravkaPanel();
		this.add(tab);
		this.setVisible(true);
	}
	
	public void AddPersonPanel() {
		personP.setLayout(new GridLayout(3,1));
		persontopPanel.setLayout(new GridLayout(5,2));
		//person top panel
		persontopPanel.add(fname);
		persontopPanel.add(fnameTF);
		persontopPanel.add(lname);
		persontopPanel.add(lnameTF);
		persontopPanel.add(gender);
		persontopPanel.add(genderBox);
		persontopPanel.add(age);
		persontopPanel.add(ageTF);
		persontopPanel.add(phone);
		persontopPanel.add(phoneTF);
	    personP.add(persontopPanel);
		//person tab mid
	    personmidPanel.add(personaddBtn);
	    personmidPanel.add(personeditBtn);
	    personmidPanel.add(persondelBtn);
	    personmidPanel.add(searchLabel);
	    personmidPanel.add(searchbar);
	    personmidPanel.add(personSearchBar); 
	    personmidPanel.add(personCarBtn);
	    personCarBtn.addActionListener(new personSearch());
	    personP.add(personmidPanel);
	    //person tab bot
	    personbotPanel.add(scroller);
		scroller.setPreferredSize(new Dimension(450,160));
		table.setModel(DBconnect.getAllData("person"));
		table.addMouseListener(new TableListener(this));
	    personP.add(personbotPanel);
	    
	    
	    
	    personaddBtn.addActionListener(new AddAction(this));
	    persondelBtn.addActionListener(new DeleteAction(this));
	   personeditBtn.addActionListener(new PersonEditAction(this));
	}
	
	public void addCarPanel() {
		carP.setLayout(new GridLayout(3,1));
		cartopPanel.setLayout(new GridLayout(6,2));
		//person top panel
		cartopPanel.add(marka);
		cartopPanel.add(markaTF);
		cartopPanel.add(durjava);
		cartopPanel.add(durjavaTF);
		cartopPanel.add(model);
		cartopPanel.add(modelTF);
		cartopPanel.add(producsionYear);
		cartopPanel.add(producsionYearTF);
		cartopPanel.add(price);
		cartopPanel.add(priceTF);
		cartopPanel.add(description);
		cartopPanel.add(descriptionTF);
	    carP.add(cartopPanel);
		//person tab mid
	    carmidPanel.add(caraddBtn);
	    carmidPanel.add(careditBtn);
	    carmidPanel.add(cardelBtn);
	    carmidPanel.add(searchCarLabel);
	    carmidPanel.add(searchbar);
	    carmidPanel.add(searchCarBtn);
	    carP.add(carmidPanel);
	
		//person tab bot
	    carbotPanel.add(carScroller);
	  		carScroller.setPreferredSize(new Dimension(450,160));
	  		carTable.setModel(DBconnect.getAllData("koli"));
	  		carTable.addMouseListener(new CarTableListener(this));
	  	    carP.add(carbotPanel);
	    
	   searchCarBtn.addActionListener(new carSearch());
	   caraddBtn.addActionListener(new AddActionCar(this));
	   cardelBtn.addActionListener(new DeleteActionCar(this));
	   careditBtn.addActionListener(new CarEditAction(this));
	}
	public void addRentPanel() {
		rentalP.add(rentScroller1);
		rentScroller1.setPreferredSize(new Dimension(980,250));
		rentPeopleTable.setModel(DBconnect.getAllData("person"));
		rentPeopleTable.addMouseListener(new rentPeopleTableListener());
		rentalP.add(rentScroller2);
		rentScroller2.setPreferredSize(new Dimension(980,250));
		rentCarTable.setModel(DBconnect.getAllData("koli"));
		rentCarTable.addMouseListener(new rentCarTableListener());
		rentalP.add(sellPrice);
		rentalP.add(sellPriceTF);
		sellBtn.addActionListener(new SellCar(this));
		rentalP.add(sellBtn);
		rentalP.add(SearchPersonRentLabel); 
		rentalP.add(personSearchBarInRent); 
		rentalP.add(personCarBtnInRent);
		personCarBtnInRent.addActionListener(new personSearchInRent());
		rentalP.add(SearchCarRentLabel); 
		rentalP.add(CarSearchBarInRent); 
		rentalP.add(CarBtnInRent);
		CarBtnInRent.addActionListener(new CarSearchInRent());
	}
	public void addSpravkaPanel() {
		rentedCarsScroller.setPreferredSize(new Dimension(900,600));
		rentedCarsTable.setModel(DBconnect.getRentData(firstName));
		sprP.add(rentedCarsScroller);
		sprP.add(searchPersonSpravkaLabel);
		sprP.add(rentSearchBar);
		sprP.add(rentedCarBtn);
		sprP.add(rentedCarsDelBtn);
		sprP.add(searchCarSpravkaLabel);
		sprP.add(rentSearchBar2);
		rentedCarsTable.addMouseListener(new rentedCarTableListener());
		rentedCarsDelBtn.addActionListener(new DeleteRentedCar(this));
		rentedCarBtn.addActionListener(new rentSearch());
	}
	
	/*
	 * class AddAction implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { String fname =
	 * fnameTF.getText(); String lname = lnameTF.getText(); String gender =
	 * genderBox.getSelectedItem().toString(); byte age ; float phone; try { age =
	 * Byte.parseByte(ageTF.getText()); } catch(Exception e1) {
	 * ageTF.setText("failna"); return; } try { phone=
	 * Float.parseFloat(phoneTF.getText()); } catch(Exception e1) {
	 * phoneTF.setText("failna"); return; } connection = DBconnect.getCon(); try {
	 * state = connection.prepareStatement("Insert into person Values(?,?,?,?,?)");
	 * state.setString(1, fname); state.setString(2, lname); state.setString(3,
	 * gender); state.setByte(4, age); state.setDouble(5, phone); state.execute();
	 * table.setModel(DBconnect.getAllData("person")); //
	 * DBconnect.fillCombo(searchCombo); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }finally { try {
	 * state.close(); connection.close(); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } clearFrom(); } } }
	 */
	/*
	 * class AddActioncar implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { String marka =
	 * markaTF.getText(); String durjava = durjavaTF.getText(); String model =
	 * modelTF.getText(); double productionYear ; try { productionYear =
	 * Double.parseDouble(producsionYearTF.getText()); } catch(Exception e1) {
	 * producsionYearTF.setText("failna"); return; } float price=
	 * Float.parseFloat(priceTF.getText()); String description=
	 * descriptionTF.getText(); connection = DBconnect.getCon(); try { state =
	 * connection.prepareStatement("Insert into koli values(?,?,?,?,?,?)");
	 * state.setString(1, marka); state.setString(2, durjava); state.setString(3,
	 * model); state.setDouble(4, productionYear); state.setFloat(5, price);
	 * state.setString(6, description); state.execute();
	 * table.setModel(DBconnect.getAllData("koli")); //
	 * DBconnect.fillCombo(searchCombo); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }finally { try {
	 * state.close(); connection.close(); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } clearCarFrom(); } } }
	 */ /*
		 * class personEditAction implements ActionListener{
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { String fname =
		 * fnameTF.getText(); String lname = lnameTF.getText(); String gender =
		 * genderBox.getSelectedItem().toString(); byte age ; try { age =
		 * Byte.parseByte(ageTF.getText()); } catch(Exception e1) {
		 * ageTF.setText("failna"); return; } double phone=
		 * Double.parseDouble(phoneTF.getText()); connection = DBconnect.getCon();
		 * String sql =
		 * "Update person Set fname =?,lname=?,gender=?,age=?,phone=? Where id=?;"; try
		 * { state= connection.prepareStatement(sql); state.setString(1, fname);
		 * state.setString(2, lname); state.setString(3, gender); state.setByte(4, age);
		 * state.setDouble(5, phone); state.setInt(6, id); state.execute();
		 * carTable.setModel(DBconnect.getAllData("person")); id=-1; //
		 * DBconnect.fillCombo(searchCombo); } catch (SQLException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }finally { try {
		 * state.close(); connection.close(); } catch (SQLException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */
	/*
	 * class carEditAction implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { String marka =
	 * markaTF.getText(); String durjava = durjavaTF.getText(); String model =
	 * modelTF.getText(); double productionYear ; try { productionYear =
	 * Double.parseDouble(producsionYearTF.getText()); } catch(Exception e1) {
	 * producsionYearTF.setText("failna"); return; } float price=
	 * Float.parseFloat(priceTF.getText()); String description=
	 * descriptionTF.getText(); connection = DBconnect.getCon(); String sql =
	 * "Update koli Set marka = ?,durjava=?,model=?,productionYear=?,price=?,description=? Where id=?;"
	 * ; try { state= connection.prepareStatement(sql); state.setString(1, marka);
	 * state.setString(2, durjava); state.setString(3, model); state.setDouble(4,
	 * productionYear); state.setFloat(5, price); state.setString(6, description);
	 * state.setInt(7, id); state.execute();
	 * carTable.setModel(DBconnect.getAllData("koli")); id=-1; //
	 * DBconnect.fillCombo(searchCombo); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }finally { try {
	 * state.close(); connection.close(); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * }
	 * 
	 * } }
	 */
	
		public void clearFrom() {
			fnameTF.setText("");
			lnameTF.setText("");
			ageTF.setText("");
			phoneTF.setText("");
		}
		public void clearCarFrom() {
			markaTF.setText("");
			durjavaTF.setText("");
			modelTF.setText("");
			producsionYearTF.setText("");
			priceTF.setText("");
			descriptionTF.setText("");
		}

	/*
	 * class sellCar implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { int personid = id; int
	 * carid = id2; DateTimeFormatter dtf =
	 * DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); LocalDateTime now =
	 * LocalDateTime.now(); String time = dtf.format(now); String clientname =
	 * firstName + " " + secondName; float price = priceFromTable;
	 * 
	 * float actualprice=0; try { actualprice =
	 * Float.parseFloat(sellPriceTF.getText()); } catch(Exception e1) {
	 * sellPriceTF.setText("failna"); return; } float changeprice =
	 * price-actualprice; connection = DBconnect.getCon(); try { state =
	 * connection.prepareStatement("insert into Sales values(?,?,?,?,?,?,?)");
	 * state.setInt(1, personid); state.setInt(2, carid); state.setString(3, time);
	 * state.setString(4, clientname); state.setFloat(5, price); state.setFloat(6,
	 * changeprice); state.setFloat(7, actualprice);
	 * 
	 * 
	 * state.execute(); rentedCarsTable.setModel(DBconnect.getRentData(firstName));
	 * // table.setModel(DBconnect.getAllData("koli")); //
	 * DBconnect.fillCombo(searchCombo); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }finally { try {
	 * state.close(); connection.close(); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } clearCarFrom(); } } }
	 */
		class carSearch implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e){
				connection = DBconnect.getCon();
				String rar="%"+carsearch()+"%";
				try {
					state = connection.prepareStatement("SELECT * FROM koli WHERE marka LIKE ?");
					state.setString(1, rar);
					result= state.executeQuery();
					try {
						mymodel= new MyModel(result);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				carTable.setModel(mymodel);
				}catch(SQLException e1) {
					
				}
				
				
			}

			private String carsearch() {
				
				return searchbar.getText();
			}
			
		}
		
		class CarSearchInRent implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e){
				connection = DBconnect.getCon();
				String rar="%"+carsearch()+"%";
				try {
					state = connection.prepareStatement("SELECT * FROM koli WHERE marka LIKE ?");
					state.setString(1, rar);
					result= state.executeQuery();
					try {
						mymodel= new MyModel(result);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					rentCarTable.setModel(mymodel);
				}catch(SQLException e1) {
					
				}
				
				
			}

			private String carsearch() {
				
				return CarSearchBarInRent.getText();
			}
			
		}
		class personSearch implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e){
				connection = DBconnect.getCon();
				String rar="%"+personsearch()+"%";
				try {
					state = connection.prepareStatement("SELECT * FROM person WHERE fname LIKE ?");
					state.setString(1, rar);
					result= state.executeQuery();
					try {
						mymodel= new MyModel(result);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				table.setModel(mymodel);
				}catch(SQLException e1) {
					
				}
				
				
			}
		}
			
			class personSearchInRent implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e){
					connection = DBconnect.getCon();
					String rar="%"+personSearchInRent()+"%";
					try {
						state = connection.prepareStatement("SELECT * FROM person WHERE fname LIKE ?");
						state.setString(1, rar);
						result= state.executeQuery();
						try {
							mymodel= new MyModel(result);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						rentPeopleTable.setModel(mymodel);
					}catch(SQLException e1) {
						
					}
					
					
				}
			}

			protected String personsearch() {
				
				return personSearchBar.getText();
			}
private String personSearchInRent() {
				
				return personSearchBarInRent.getText();
			}
	
		class rentSearch implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e){
				connection = DBconnect.getCon();
				String rar="%"+rentsearch()+"%";
				String rar2="%"+rentsearch2()+"%";
				try {
					state = connection.prepareStatement("Select s.clientname,marka,durjava,model,s.price,SellPrice from koli k inner join Sales s on k.id=s.koliid WHERE s.clientname LIKE ?  AND k.marka LIKE ? ");
					state.setString(1, rar);
					state.setString(2, rar2);
					result= state.executeQuery();
					try {
						mymodel= new MyModel(result);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				rentedCarsTable.setModel(mymodel);
				}catch(SQLException e1) {
					
				}
				
				
			}

			private String rentsearch() {
				
				return rentSearchBar.getText();
			}
			
private String rentsearch2() {
				
				return rentSearchBar2.getText();
			}
			
		}

	/*
	 * class TableListener implements MouseListener{
	 * 
	 * @Override public void mouseClicked(MouseEvent e) { if (e.getClickCount() == 2
	 * && !e.isConsumed()) { String sql ="Select * from person where id=?;";
	 * connection = DBconnect.getCon(); String fname = null; String lname = null;
	 * String gender = null; double phone = 0; byte age = 0; try { state=
	 * connection.prepareStatement(sql); state.setInt(1, id); result=
	 * state.executeQuery(); while (result.next()) { fname =
	 * result.getString("fname"); lname= result.getString("lname"); gender =
	 * result.getString("gender"); age = result.getByte("age"); phone =
	 * result.getDouble("phone");
	 * 
	 * } System.out.println(id); fnameTF.setText(fname); lnameTF.setText(lname);
	 * genderBox.setToolTipText(gender); phoneTF.setText(String.valueOf(phone));
	 * ageTF.setText(String.valueOf(age)); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }finally { try {
	 * state.close(); connection.close();
	 * table.setModel(DBconnect.getAllData("person")); } catch (SQLException e1) {
	 * // TODO Auto-generated catch block e1.printStackTrace(); } }
	 * 
	 * e.consume(); //handle double click event. } else { int row
	 * =table.getSelectedRow(); id = Integer.parseInt(table.getValueAt(row,
	 * 0).toString()); }
	 * 
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	/*
	 * class carTableListener implements MouseListener{
	 * 
	 * @Override public void mouseClicked(MouseEvent e) { if (e.getClickCount() == 2
	 * && !e.isConsumed()) { String sql ="Select * from koli where id=? ";
	 * connection = DBconnect.getCon(); String marka = null; String durjava = null;
	 * String model = null; double productionYear = 0; float price = 0; String
	 * descriptrion = null; try { state= connection.prepareStatement(sql); result=
	 * state.executeQuery(); while (result.next()) { marka =
	 * result.getString("marka"); durjava= result.getString("durjava"); model =
	 * result.getString("model"); productionYear =
	 * result.getDouble("productionYear"); price = result.getFloat("price");
	 * descriptrion = result.getString("description"); } System.out.println(id);
	 * markaTF.setText(marka); durjavaTF.setText(durjava); modelTF.setText(model);
	 * producsionYearTF.setText(String.valueOf(productionYear));
	 * priceTF.setText(String.valueOf(price)); descriptionTF.setText(descriptrion);
	 * } catch (SQLException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); }
	 * 
	 * e.consume(); //handle double click event. } else { int row
	 * =carTable.getSelectedRow(); id = Integer.parseInt(carTable.getValueAt(row,
	 * 0).toString()); }
	 * 
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
		class rentCarTableListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				
					int row =rentCarTable.getSelectedRow();
					 id2 = Integer.parseInt(rentCarTable.getValueAt(row, 0).toString());
					 priceFromTable=Float.parseFloat(rentCarTable.getValueAt(row, 5).toString());
					 System.out.println(priceFromTable);
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
		class rentedCarTableListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				int row =rentedCarsTable.getSelectedRow();
				delrentedcar = Integer.parseInt(rentedCarsTable.getValueAt(row, 0).toString());
								 System.out.println(delrentedcar);
				
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
		class rentPeopleTableListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				
					int row =rentPeopleTable.getSelectedRow();
					 id = Integer.parseInt(rentPeopleTable.getValueAt(row, 0).toString());
					 firstName=(String)rentPeopleTable.getValueAt(row, 1);
					 secondName=(String)rentPeopleTable.getValueAt(row, 2);
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
		
		
	/*
	 * class DeleteAction implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { connection =
	 * DBconnect.getCon(); String sql = "Delete from person where id=?"; try {
	 * state= connection.prepareStatement(sql); state.setInt(1, id);
	 * state.execute(); table.setModel(DBconnect.getAllData("person")); id=-1; //
	 * DBconnect.fillCombo(searchCombo); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }
	 */
	/*
	 * class DeleteActioncar implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { connection =
	 * DBconnect.getCon(); String sql = "Delete from koli where id=?"; try { state=
	 * connection.prepareStatement(sql); state.setInt(1, id); state.execute();
	 * carTable.setModel(DBconnect.getAllData("koli")); id=-1; //
	 * DBconnect.fillCombo(searchCombo); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }
	 */
	/*
	 * class deleteRentedCar implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { connection =
	 * DBconnect.getCon(); String sql = "Delete from Sales where id=?"; try { state=
	 * connection.prepareStatement(sql); state.setInt(1, delrentedcar);
	 * state.execute();
	 * rentedCarsTable.setModel(DBconnect.getRentData("firstname"));
	 * delrentedcar=-1; // DBconnect.fillCombo(searchCombo); } catch (SQLException
	 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }
	 */
		}
		
