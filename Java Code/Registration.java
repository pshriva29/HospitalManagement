

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userName,userPassword,userID;
	private JButton registerButton;
	protected java.lang.String Spassword;

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";

	/**
	 * Create the frame.
	 */
	public Registration() {
		setTitle("Doctor Login");
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
		labelPassword.setBounds(70, 104, 86, 14);
		// add into contentPane
		contentPane.add(labelPassword);

		// create text field for password
		userPassword = new JTextField();
		// set bound for password field
		userPassword.setBounds(188, 101, 99, 20);
		// add text field on contentPane
		contentPane.add(userPassword);

		userPassword.setColumns(10);
		
		// Added for registration
		// lable the text field
		JLabel labelId = new JLabel("Id");
		// set bounds for label
		labelId.setBounds(70, 154, 86, 14);
		// add into contentPane
		contentPane.add(labelId);

		userID = new JTextField();
		// set bounds for text fields
		userID.setBounds(188, 151, 99, 20);
		// in contentPane add text field
		contentPane.add(userID);
		// set column for text field
		userID.setColumns(10);
		
		

		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					String username = "";
					String password ;
					String userid = "";

					// get values using getText() method
					username = userName.getText().trim();
					password = userPassword.getText().trim();
					userid = userID.getText().trim();

					if (username.equals("") || password.equals("") || userid.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter all the fields", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					else {

						Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
						
						
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append("INSERT INTO Users VALUES ('");
						stringBuilder.append(username);
						stringBuilder.append("',SHA('");
						stringBuilder.append(password);
						stringBuilder.append("'),");
						stringBuilder.append(userid);
						stringBuilder.append(")");
						String sql = stringBuilder.toString();
						
					
				        Statement statement = conn.createStatement();
						statement.execute(sql);
						
						String SMessage = "User created for " + username;

						// create dialog ox which is print message
						JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.PLAIN_MESSAGE);

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

		// set bound for SignUp button
		registerButton.setBounds(100, 200, 89, 23);
		// add button into contentPane
		contentPane.add(registerButton);
	}

}

