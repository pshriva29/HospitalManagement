

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PatientOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userName;
	private JButton loginButton, viewDetailsButton, viewBillButton,registerButton;
	private JPasswordField userPassword;
	protected java.lang.String Spassword;

	String username = "";
	char[] password;

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";

	/**
	 * Create the frame.
	 */
	public PatientOptions() {
		setTitle("Patient Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// lable the text field
		JLabel labelUser = new JLabel("User Name");
		// set bounds for label
		labelUser.setBounds(70, 54, 86, 14);
		// add into contentPane
		contentPane.add(labelUser);

		userName = new JTextField();
		// set bounds for text fields
		userName.setBounds(188, 51, 99, 20);
		// in contentPane add text field
		contentPane.add(userName);
		// set column for text field
		userName.setColumns(10);

		// lable the text field
		JLabel labelPassword = new JLabel("Password");
		// set bounds for label
		labelPassword.setBounds(70, 109, 86, 14);
		// add into contentPane
		contentPane.add(labelPassword);

		// create text field for password
		userPassword = new JPasswordField();
		// set bound for password field
		userPassword.setBounds(188, 106, 99, 20);
		// add text field on contentPane
		contentPane.add(userPassword);

		userPassword.setColumns(10);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				try {

					// get values using getText() method
					username = userName.getText().trim();
					password = userPassword.getPassword();

					if (username.equals("") || password.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter both fields", "Login Error",
								JOptionPane.ERROR_MESSAGE);
					}

					else {

						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append("Select count(*) from Users where userName ='");
						stringBuilder.append(username);
						stringBuilder.append("' and password= SHA('");
						stringBuilder.append(password);
						stringBuilder.append("')");
						String sql = stringBuilder.toString();

						Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
						Statement statement = conn.createStatement();
						ResultSet result = statement.executeQuery(sql);

						if (!result.isBeforeFirst()) {
							JOptionPane.showMessageDialog(null, "Please enter correct Username", "Login Error",
									JOptionPane.PLAIN_MESSAGE);
						}

						else {

							while (result.next()) {

								int resultPass = result.getInt(1);

								if (resultPass == 1) {

									JFrame f1 = new JFrame();

									f1.setSize(500, 500);
									f1.setLayout(null);
									f1.setTitle("Patient Data");

									viewDetailsButton = new JButton("Click to view your Details");

									viewDetailsButton.addActionListener(new ActionListener() {

										public void actionPerformed(ActionEvent e) {

											TreatmentDetails pDetails = new TreatmentDetails(username);

											pDetails.setVisible(true);
											pDetails.setSize(600, 700);

										}
									});

									viewBillButton = new JButton("Click to view Bill Details");

									viewBillButton.addActionListener(new ActionListener() {

										public void actionPerformed(ActionEvent e) {

											BillDetails bDetails = new BillDetails(username);
											bDetails.setVisible(true);
											bDetails.setSize(600, 700);

										}
									});

									viewDetailsButton.setBounds(100, 100, 250, 23);
									viewBillButton.setBounds(100, 150, 250, 23);

									f1.add(viewDetailsButton);
									f1.add(viewBillButton);

									f1.setVisible(true);

								}

								else {
									JOptionPane.showMessageDialog(null, "Please enter correct Username and Password",
											"Login Error", JOptionPane.PLAIN_MESSAGE);

								}

							}

						}
						//Close connection
						((java.sql.Connection) conn).close();

					}

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// set bound for Login button
		loginButton.setBounds(100, 150, 89, 23);
		// add button into contentPane
		contentPane.add(loginButton);
		
		//Adding Register button
		
				registerButton = new JButton("Click here for new Registration");
				
				registerButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						
						Registration bRegister = new Registration();
						bRegister.setVisible(true);
						bRegister.setSize(400, 400);
						
						
					}
					
		           });
				
				// set bound for register button
				registerButton.setBounds(70, 200, 220, 23);
						// add button into contentPane
						contentPane.add(registerButton);
				
				
				
		
		
		
		
		
	}

}
