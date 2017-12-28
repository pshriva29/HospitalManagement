
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.sql.*;

public class BillUpdate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel l, l1, l2, l3, l4, l5, l6;
	JButton b, update;
	JTextField tf1, tf2, tf3, tf4, tf5;

	String status = "";

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";

	public BillUpdate() {

		setTitle("Update Bill Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		l = new JLabel("Enter Patient ID:");
		b = new JButton("Submit");
		tf1 = new JTextField();

		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();

		l.setBounds(20, 20, 150, 20);
		tf1.setBounds(180, 20, 50, 20);
		b.setBounds(120, 100, 100, 30);

		contentPane.add(tf1);

		add(l);
		add(b);

		tf2.setEditable(false);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();

			}

			public void showData() {

				JFrame f1 = new JFrame();

				f1.setSize(500, 500);
				f1.setLayout(null);
				f1.setTitle("Patient Data");

				l1 = new JLabel("Bill No:");
				l2 = new JLabel("Amount :");
				l3 = new JLabel("Bill Date :");
				l4 = new JLabel("Bill Paid :");

				l1.setBounds(20, 110, 200, 20);
				l2.setBounds(20, 140, 200, 20);
				l3.setBounds(20, 170, 200, 20);
				l4.setBounds(20, 200, 200, 20);

				tf2.setBounds(240, 110, 200, 20);
				tf3.setBounds(240, 140, 200, 20);
				tf4.setBounds(240, 170, 200, 20);
				tf5.setBounds(240, 200, 200, 20);

				f1.add(l1);
				f1.add(tf2);
				f1.add(l2);
				f1.add(tf3);
				f1.add(l3);
				f1.add(tf4);
				f1.add(l4);
				f1.add(tf5);

				int id = Integer.parseInt(tf1.getText());

				try {

					Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

					PreparedStatement ps1 = conn
							.prepareStatement("select billNo,amount,billDate,billPaid from Bill where patientId=?");
					ps1.setInt(1, id);

					ResultSet rs1 = ps1.executeQuery();
					
					if (!rs1.isBeforeFirst()) {
						JOptionPane.showMessageDialog(null, "There are no bill records for Patient", "Message",
								JOptionPane.PLAIN_MESSAGE);
					}
					
					else
					{
						
					

					while (rs1.next()) {

						status = rs1.getString(4);

						tf2.setText(rs1.getString(1));
						tf3.setText(rs1.getString(2));
						tf4.setText(rs1.getString(3));
						tf5.setText(rs1.getString(4));

					}
		

				update = new JButton("Update");

				if (status.equalsIgnoreCase("Yes"))

				{

					update.setEnabled(false);
				}

				update.setBounds(240, 240, 200, 30);

				update.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						double updatedAmt = Double.parseDouble(tf3.getText());
						String updateddate = tf4.getText();
						String updatedStatus = tf5.getText();

						Connection conn;
						try {
							conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

							PreparedStatement ps = conn.prepareStatement(
									"UPDATE Bill SET amount=?,billDate=?,billPaid=? where patientId=?");
							ps.setDouble(1, updatedAmt);
							ps.setString(2, updateddate);
							ps.setString(3, updatedStatus);
							ps.setInt(4, id);

							int rowsUpdated = ps.executeUpdate();
							if (rowsUpdated > 0) {
								JOptionPane.showMessageDialog(null, "Bill details are updated", "Update",
										JOptionPane.PLAIN_MESSAGE);
							}

							if (updatedStatus.equalsIgnoreCase("Yes")) {

								PreparedStatement ps2 = conn.prepareStatement(
										"INSERT INTO PatientHistory(patientId,name,illness,doctorID,admissionDate,dischargeDate) SELECT patientId,name,illness,doctorID,admissionDate,dischargeDate FROM Patient where patientId=?");

								ps2.setInt(1, id);

								ps2.execute();

								PreparedStatement ps3 = conn.prepareStatement("DELETE FROM Bill where patientId=?");

								ps3.setInt(1, id);

								ps3.execute();

								PreparedStatement ps4 = conn.prepareStatement("DELETE FROM Patient where patientId=?");

								ps4.setInt(1, id);

								ps4.execute();

							}
							//Close connection
							((java.sql.Connection) conn).close();

						} 
						
						catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				
				
				
				

				f1.add(update);

				f1.setVisible(true);

			
				
				//Close connection
				((java.sql.Connection) conn).close();
				
					}
			}
			
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		});

	
	}
	}
