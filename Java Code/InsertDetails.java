
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InsertDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField patientName;
	private JTextField address;
	private JTextField gender;
	private JTextField DOB;
	private JTextField phnNo;
	private JTextField illness;
	private JTextField fee;
	private JTextField doctorId;
	private JTextField adate;
	private JTextField dDate;
	private JTextField roomNo;

	private JButton saveButton;

	protected java.lang.String Spassword;

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";

	/**
	 * Create the frame.
	 */
	public InsertDetails() {

		setTitle("Insert Patient Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel labelHeading = new JLabel("Enter new Patient Details(All date fields to be inserted in YYYY-MM-DD format)");

		labelHeading.setBounds(50, 50, 600, 14);

		contentPane.add(labelHeading);

		JLabel labelName = new JLabel("Name");

		labelName.setBounds(70, 104, 86, 14);

		contentPane.add(labelName);

		patientName = new JTextField();
		patientName.setBounds(210, 101, 200, 20);
		contentPane.add(patientName);
		patientName.setColumns(50);

		JLabel labelAddr = new JLabel("Address");
		// set bounds for label
		labelAddr.setBounds(70, 154, 86, 14);
		// add into contentPane
		contentPane.add(labelAddr);

		address = new JTextField();
		// set bounds for text fields
		address.setBounds(210, 151, 200, 20);
		// in contentPane add text field
		contentPane.add(address);
		// set column for text field
		address.setColumns(50);

		JLabel labelGender = new JLabel("Gender");
		// set bounds for label
		labelGender.setBounds(70, 204, 86, 14);
		// add into contentPane
		contentPane.add(labelGender);

		gender = new JTextField();
		// set bounds for text fields
		gender.setBounds(210, 201, 200, 20);
		// in contentPane add text field
		contentPane.add(gender);
		// set column for text field
		gender.setColumns(50);

		JLabel labelDOB = new JLabel("DOB");
		// set bounds for label
		labelDOB.setBounds(70, 254, 86, 14);
		// add into contentPane
		contentPane.add(labelDOB);

		DOB = new JTextField();
		// set bounds for text fields
		DOB.setBounds(210, 251, 200, 20);
		// in contentPane add text field
		contentPane.add(DOB);
		// set column for text field
		DOB.setColumns(50);

		JLabel labelphn = new JLabel("Contact No");
		// set bounds for label
		labelphn.setBounds(70, 304, 86, 14);
		// add into contentPane
		contentPane.add(labelphn);

		phnNo = new JTextField();
		// set bounds for text fields
		phnNo.setBounds(210, 301, 200, 20);
		// in contentPane add text field
		contentPane.add(phnNo);
		// set column for text field
		phnNo.setColumns(50);

		JLabel labelIllness = new JLabel("Illness");
		// set bounds for label
		labelIllness.setBounds(70, 354, 86, 14);
		// add into contentPane
		contentPane.add(labelIllness);

		illness = new JTextField();
		// set bounds for text fields
		illness.setBounds(210, 351, 200, 20);
		// in contentPane add text field
		contentPane.add(illness);
		// set column for text field
		illness.setColumns(50);

		JLabel labelfee = new JLabel("Illness Fee");
		// set bounds for label
		labelfee.setBounds(70, 404, 86, 14);
		// add into contentPane
		contentPane.add(labelfee);

		fee = new JTextField();
		// set bounds for text fields
		fee.setBounds(210, 401, 200, 20);
		// in contentPane add text field
		contentPane.add(fee);
		// set column for text field
		fee.setColumns(50);

		JLabel labelDId = new JLabel("Doctor ID");
		// set bounds for label
		labelDId.setBounds(70, 454, 86, 14);
		// add into contentPane
		contentPane.add(labelDId);

		doctorId = new JTextField();
		// set bounds for text fields
		doctorId.setBounds(210, 451, 200, 20);
		// in contentPane add text field
		contentPane.add(doctorId);
		// set column for text field
		doctorId.setColumns(50);

		JLabel labelADate = new JLabel("Admission Date");
		// set bounds for label
		labelADate.setBounds(70, 504, 100, 14);
		// add into contentPane
		contentPane.add(labelADate);

		adate = new JTextField();
		// set bounds for text fields
		adate.setBounds(210, 501, 200, 20);
		// in contentPane add text field
		contentPane.add(adate);
		// set column for text field
		adate.setColumns(50);

		JLabel labeldDate = new JLabel("Discharge Date");
		// set bounds for label
		labeldDate.setBounds(70, 554, 100, 14);
		// add into contentPane
		contentPane.add(labeldDate);

		dDate = new JTextField();
		// set bounds for text fields
		dDate.setBounds(210, 551, 200, 20);
		// in contentPane add text field
		contentPane.add(dDate);
		// set column for text field
		dDate.setColumns(50);

		// lable the room field
		JLabel labelroomNo = new JLabel("Room");
		// set bounds for label
		labelroomNo.setBounds(70, 604, 86, 14);
		// add into contentPane
		contentPane.add(labelroomNo);

		roomNo = new JTextField();
		// set bounds for text fields
		roomNo.setBounds(210, 601, 200, 20);
		// in contentPane add text field
		contentPane.add(roomNo);
		// set column for text field
		roomNo.setColumns(50);
		
		

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO Patient(name,address,gender,DOB,phnNo,illness,illnessFee,doctorID,admissionDate,dischargeDate,roomNo) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

					ps.setString(1, patientName.getText().trim());
					ps.setString(2, address.getText().trim());
					ps.setString(3, gender.getText().trim());
					ps.setString(4, DOB.getText().trim());
					ps.setString(5, phnNo.getText().trim());
					ps.setString(6, illness.getText().trim());
					ps.setInt(7, Integer.parseInt(fee.getText().trim()));
					ps.setInt(8, Integer.parseInt(doctorId.getText().trim()));

					if (adate.getText().equals("")) {
						ps.setNull(9, java.sql.Types.DATE);
					} else {
						ps.setString(9, adate.getText().trim());
					}

					if (adate.getText().equals("")) {
						ps.setNull(10, java.sql.Types.DATE);
					} else {
						ps.setString(10, dDate.getText().trim());
					}

					if (roomNo.getText().equals("")) {
						ps.setInt(11, 0);
					}

					else {

						ps.setInt(11, Integer.parseInt(roomNo.getText().trim()));
					}

					ps.execute();

					String SMessage = "Record added for " + patientName.getText().trim();

					// create dialog ox which is print message
					JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.PLAIN_MESSAGE);

					// close connection
					((java.sql.Connection) conn).close();

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Please enter correct data", "Message", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});

		// set bound for Save button
		saveButton.setBounds(170, 670, 89, 23);
		// add button into contentPane
		contentPane.add(saveButton);

	}

}
