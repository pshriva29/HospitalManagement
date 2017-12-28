
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class BillDetails extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField billNo;
	private JTextField patientName;
	private JTextField illness;
	private JTextField amount;
	private JTextField billDate;
	private JTextField billPaid;

	private String user;
	int patientID;

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";

	public BillDetails(String usr) {

		this.user = usr;

		setTitle("Your Bill Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		billNo = new JTextField();
		patientName = new JTextField();
		illness = new JTextField();
		amount = new JTextField();
		billDate = new JTextField();
		billPaid = new JTextField();

		billNo.setEditable(false);
		patientName.setEditable(false);
		illness.setEditable(false);
		amount.setEditable(false);
		billDate.setEditable(false);
		billPaid.setEditable(false);

		JLabel lBillNo = new JLabel("Bill No:");
		JLabel lName = new JLabel("Name :");
		JLabel lIllness = new JLabel("Illness :");
		JLabel lAmount = new JLabel("Amount :");
		JLabel lBillDate = new JLabel("Bill Date :");
		JLabel lBillPaid = new JLabel("Bill Paid :");

		lBillNo.setBounds(20, 110, 200, 20);
		lName.setBounds(20, 140, 200, 20);
		lIllness.setBounds(20, 170, 200, 20);
		lAmount.setBounds(20, 200, 200, 20);
		lBillDate.setBounds(20, 230, 200, 20);
		lBillPaid.setBounds(20, 260, 200, 20);

		billNo.setBounds(240, 110, 200, 20);
		patientName.setBounds(240, 140, 200, 20);
		illness.setBounds(240, 170, 200, 20);
		amount.setBounds(240, 200, 200, 20);
		billDate.setBounds(240, 230, 200, 20);
		billPaid.setBounds(240, 260, 200, 20);

		contentPane.add(lBillNo);
		contentPane.add(lName);
		contentPane.add(lIllness);
		contentPane.add(lAmount);
		contentPane.add(lBillDate);
		contentPane.add(lBillPaid);
		contentPane.add(billNo);
		contentPane.add(patientName);
		contentPane.add(illness);
		contentPane.add(amount);
		contentPane.add(billDate);
		contentPane.add(billPaid);
		
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

			PreparedStatement ps1 = conn.prepareStatement("select userID from Users where userName=?");
			ps1.setString(1, user);
		
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				patientID = rs1.getInt(1);
			}
			


			PreparedStatement ps = conn.prepareStatement(
					"select billNo,name,illness,amount,billDate,billPaid from Bill b,Patient p where b.patientID=p.patientID and b.patientID = ?");
			ps.setInt(1, patientID);
			
		
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				billNo.setText(rs.getString(1));
				patientName.setText(rs.getString(2));
				illness.setText(rs.getString(3));
				amount.setText(rs.getString(4));
				billDate.setText(rs.getString(5));
				billPaid.setText(rs.getString(6));
	

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			try {
				((java.sql.Connection) conn).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
