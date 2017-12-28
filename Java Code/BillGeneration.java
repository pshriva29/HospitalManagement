
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;

public class BillGeneration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lselectName;
	JButton bGenerate;
	JTextField tfselectName;
	
	int pID,fee,days,room;
	double totalAmount;
	String patientName;
	
	DecimalFormat twoDigits = new DecimalFormat("0.00");

	String dbURL = "jdbc:mysql://localhost:3306/pshrivas_db";
	String dbUsername = "root";
	String dbPassword = "greatgod";


	public BillGeneration() {
		
		
		setTitle("Generate Bill");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lselectName = new JLabel("Select Name:");
		bGenerate = new JButton("Generate Bill");
		tfselectName = new JTextField();
		
		lselectName.setBounds(70, 54, 86, 14);
		tfselectName.setBounds(188, 51, 99, 20);
		bGenerate.setBounds(150, 150, 86, 14);
		

		contentPane.add(tfselectName);
		contentPane.add(lselectName);
		contentPane.add(bGenerate);

		
		bGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();

			}

			public void showData() {

				String name = tfselectName.getText().trim();

				try {

					Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
					
					
					PreparedStatement ps1 = conn.prepareStatement("select patientID,name,illnessFee,roomNo,DATEDIFF(dischargeDate,admissionDate) from Patient where name=?");
					ps1.setString(1, name);
					ResultSet rs1 = ps1.executeQuery();
					while (rs1.next()) {
						
						pID = rs1.getInt(1);
						patientName = rs1.getString(2);
						fee = rs1.getInt(3);
						room = rs1.getInt(4);
						days = rs1.getInt(5);
						
						if(room == 0)
						{
							totalAmount = fee;
						}
						
						else
						{
							totalAmount = fee + 100 + (days*50);
						}
						
						
						
                   }
					
					
					PreparedStatement ps = conn.prepareStatement("Insert into Bill (patientID,amount,billDate,billPaid) values (?,?,?,?)");
					ps.setInt(1, pID);
					ps.setDouble(2, totalAmount);
					
					java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
					ps.setDate(3, sqlDate);
					
					ps.setString(4, "No");
					
					
				 ps.execute();	
				 
				 String SMessage = "Bill generated of amount $"+twoDigits.format(totalAmount)+" for "+patientName;					
                 // create dialog box which is print message
                    JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
	

					//close connection
					((java.sql.Connection)conn).close();
			

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "There are no records for Patient", "Message",
							JOptionPane.PLAIN_MESSAGE);
					
				}

			}
		});

	}

}



