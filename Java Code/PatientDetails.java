
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;

public class PatientDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lselectName, lId, lName, lAddress, lDOB, lPhnNo, lGender, lIllness, lAdmssnDate, lDischrgDate, lRoomNo;
	JButton b;
	JTextField tfselectName, tfId, tfName, tfAddress, tfDOB, tfPhnNo, tfGender, tfIllness, tfAdmssnDate, tfDischrgDate,
			tfRoomNo;

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";

	/**
	 * Create the frame.
	 * 
	 * @param username
	 */
	public PatientDetails() {

		setTitle("Patient Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lselectName = new JLabel("Enter Patient Name:");
		b = new JButton("Submit");
		tfselectName = new JTextField();

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

		lselectName.setBounds(70, 54, 200, 14);
		tfselectName.setBounds(210, 51, 99, 20);
		b.setBounds(150, 60, 86, 14);

		contentPane.add(tfselectName);

		add(lselectName);
		add(b);

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

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();

			}

			public void showData() {

				JFrame f1 = new JFrame();
				
				f1.setSize(500, 500);
				f1.setLayout(null);
				f1.setTitle("Patient Data");

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

				f1.add(lName);
				f1.add(tfName);
				f1.add(lAddress);
				f1.add(tfAddress);
				f1.add(lDOB);
				f1.add(tfDOB);
				f1.add(lPhnNo);
				f1.add(tfPhnNo);
				f1.add(lGender);
				f1.add(tfGender);
				f1.add(lId);
				f1.add(lIllness);
				f1.add(lAdmssnDate);
				f1.add(lDischrgDate);
				f1.add(lRoomNo);
				f1.add(tfId);
				f1.add(tfIllness);
				f1.add(tfAdmssnDate);
				f1.add(tfDischrgDate);
				f1.add(tfRoomNo);

				String name = tfselectName.getText().trim();

				try {

					Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

					PreparedStatement ps = conn.prepareStatement(
							"select patientId,name,address,DOB,phnNo,gender,illness,admissionDate,dischargeDate,roomNo from Patient where name=?");
					ps.setString(1, name);
					ResultSet rs = ps.executeQuery();
					
					if (!rs.isBeforeFirst()) {
						JOptionPane.showMessageDialog(null, "Please enter correct Patient name", "Message",
								JOptionPane.PLAIN_MESSAGE);
					}

					
					else
					{
						while (rs.next()) {

							tfId.setText(rs.getString(1));
							tfName.setText(rs.getString(2));
							tfAddress.setText(rs.getString(3));
							tfDOB.setText(rs.getString(4));
							tfPhnNo.setText(rs.getString(5));
							tfGender.setText(rs.getString(6));
						    tfIllness.setText(rs.getString(7));
							tfAdmssnDate.setText(rs.getString(8));
							tfDischrgDate.setText(rs.getString(9));
							
							
							
							if (rs.getString(10).equals("0")) {
								tfRoomNo.setText("");
							
							}

							else {
								tfRoomNo.setText(rs.getString(10));
							}

							
							f1.setVisible(true);

						}
						
					}
					
					
					
					//Close connection
					((java.sql.Connection) conn).close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

}
