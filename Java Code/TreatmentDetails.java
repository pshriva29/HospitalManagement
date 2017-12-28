
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class TreatmentDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lselectName, lId, lName, lAddress, lDOB, lPhnNo, lGender, lIllness, lAdmssnDate, lDischrgDate, lRoomNo;
	JButton b;
	JTextField tfselectName, tfId, tfName, tfAddress, tfDOB, tfPhnNo, tfGender, tfIllness, tfAdmssnDate, tfDischrgDate,
			tfRoomNo;

	private String user;
	int patientID;

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";

	/**
	 * Create the frame.
	 * 
	 * @param username
	 */
	public TreatmentDetails(String usr) {

		this.user = usr;

		setTitle("Your Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		tfId = new JTextField();
		tfName = new JTextField();
		tfAddress = new JTextField();
		tfDOB = new JTextField();
		tfPhnNo = new JTextField();
		tfGender = new JTextField();
		tfIllness = new JTextField();
		tfAdmssnDate = new JTextField();
		tfDischrgDate = new JTextField();
		tfRoomNo = new JTextField();

		tfId.setEditable(false);
		tfName.setEditable(false);
		tfAddress.setEditable(false);
		tfDOB.setEditable(false);
		tfPhnNo.setEditable(false);
		tfGender.setEditable(false);
		tfIllness.setEditable(false);
		tfAdmssnDate.setEditable(false);
		tfDischrgDate.setEditable(false);
		tfRoomNo.setEditable(false);

		lId = new JLabel("Patient Id:");
		lName = new JLabel("Patient Name:");
		lAddress = new JLabel("Patient Adress :");
		lDOB = new JLabel("Patient DOB:");
		lPhnNo = new JLabel("Patient ContactNo.:");
		lGender = new JLabel("Gender:");
		lIllness = new JLabel("Illness:");
		lAdmssnDate = new JLabel("Admission Date:");
		lDischrgDate = new JLabel("Discharge Date:");
		lRoomNo = new JLabel("Room:");

		lId.setBounds(20, 110, 200, 20);
		lName.setBounds(20, 140, 200, 20);
		lAddress.setBounds(20, 170, 200, 20);
		lDOB.setBounds(20, 200, 200, 20);
		lPhnNo.setBounds(20, 230, 200, 20);
		lGender.setBounds(20, 260, 200, 20);
		lIllness.setBounds(20, 290, 200, 20);
		lAdmssnDate.setBounds(20, 320, 200, 20);
		lDischrgDate.setBounds(20, 350, 200, 20);
		lRoomNo.setBounds(20, 380, 200, 20);

		tfId.setBounds(240, 110, 200, 20);
		tfName.setBounds(240, 140, 200, 20);
		tfAddress.setBounds(240, 170, 200, 20);
		tfDOB.setBounds(240, 200, 200, 20);
		tfPhnNo.setBounds(240, 230, 200, 20);
		tfGender.setBounds(240, 260, 200, 20);
		tfIllness.setBounds(240, 290, 200, 20);
		tfAdmssnDate.setBounds(240, 320, 200, 20);
		tfDischrgDate.setBounds(240, 350, 200, 20);
		tfRoomNo.setBounds(240, 380, 200, 20);

		contentPane.add(lName);
		contentPane.add(tfName);
		contentPane.add(lAddress);
		contentPane.add(tfAddress);
		contentPane.add(lDOB);
		contentPane.add(tfDOB);
		contentPane.add(lPhnNo);
		contentPane.add(tfPhnNo);
		contentPane.add(lGender);
		contentPane.add(tfGender);
		contentPane.add(lId);
		contentPane.add(lIllness);
		contentPane.add(lAdmssnDate);
		contentPane.add(lDischrgDate);
		contentPane.add(lRoomNo);
		contentPane.add(tfId);
		contentPane.add(tfIllness);
		contentPane.add(tfAdmssnDate);
		contentPane.add(tfDischrgDate);
		contentPane.add(tfRoomNo);

		try {

			Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

			PreparedStatement ps1 = conn.prepareStatement("select userID from Users where userName=?");
			ps1.setString(1, user);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				patientID = rs1.getInt(1);
			}

			PreparedStatement ps = conn.prepareStatement(
					"select patientId,name,address,DOB,phnNo,gender,illness,admissionDate,dischargeDate,roomNo from Patient where patientId=?");
			ps.setInt(1, patientID);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				tfName.setText(rs.getString(2));
				tfAddress.setText(rs.getString(3));
				tfDOB.setText(rs.getString(4));
				tfPhnNo.setText(rs.getString(1));
				tfGender.setText(rs.getString(6));
				tfId.setText(rs.getString(1));
				tfIllness.setText(rs.getString(7));
				tfAdmssnDate.setText(rs.getString(8));
				tfDischrgDate.setText(rs.getString(9));
				
				
				if (rs.getString(10).equals("0")) {
					tfRoomNo.setText("");
				
				}

				else {
					tfRoomNo.setText(rs.getString(10));
				}

			}
			//Close connection
			((java.sql.Connection) conn).close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
